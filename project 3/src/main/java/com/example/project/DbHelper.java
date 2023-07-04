package com.example.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;

public class DbHelper {

    public static int result;
    public static ArrayList<User> users;

    public static ArrayList<Task> tasks;
    public static Connection con;
    public static Statement statement;

    public static void startDb() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Fatima1122");
            statement = con.createStatement();
            statement.execute("alter table userInfo AUTO_INCREMENT = 1");
            users = new ArrayList<>();
            getUserFromDB();
            getTaskFromDB();
            populateUserWithTask();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void insertData(String fname, String lname, String uName, String pass) {
        String sql = "Insert into userInfo (firstName,lastName,userName,Password,Points) values(?,?,?,?,?)";
        try {
            PreparedStatement pStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, fname);
            pStatement.setString(2, lname);
            pStatement.setString(3, uName);
            pStatement.setString(4, pass);
            pStatement.setInt(5, 100);
            int row = pStatement.executeUpdate();
            if (row > 0) {
                System.out.println("You have been successfully signed up");
                ResultSet rs = pStatement.getGeneratedKeys();
                if (rs.next()) {
                    result = rs.getInt(1);
                }
                users.add(new User(result, fname, lname, uName, pass, 100));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkUserName(String uName) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).userName.equals(uName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkPassword(String pass) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).password.equals(pass)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkUser(String uName, String pass) {
        for (int i = 0; i < DbHelper.users.size(); i++) {
            if (DbHelper.users.get(i).userName.equals(uName) && DbHelper.users.get(i).password.equals(pass)) {
                return true;
            }
        }
        return false;

    }

    public static ArrayList<User> getList() {
        return users;
    }

    public static void showList() {
        for (int i = 0; i < DbHelper.users.size(); i++) {
            System.out.println(users.get(i).ID);
            System.out.println(users.get(i).firstName);
            System.out.println(users.get(i).lastName);
            System.out.println(users.get(i).userName);
            System.out.println(users.get(i).password);
            System.out.println(users.get(i).points);
        }
    }

    public static void getUserFromDB() {
        try {
            String sql = "select * from userInfo";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String fname = rs.getString("firstName");
                String lname = rs.getString("lastName");
                String uName = rs.getString("userName");
                String pass = rs.getString("Password");
                int points = rs.getInt("Points");
                users.add(new User(id, fname, lname, uName, pass, points));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static User getUser(String uName) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).userName.equals(uName)) {
                return users.get(i);
            }
        }
        return null;
    }

    public static void addTimeToDb(String uname, int time) {

    }

    public static void getTaskFromDB(){
        try {
            String sql = "select * from taskInfo";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String taskName = rs.getString("Task");
                int time = rs.getInt("Timer");
                int points = rs.getInt("Points");
                String uName = rs.getString("uName");
                tasks.add(new Task(taskName,time,uName,points));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void populateUserWithTask(){
        for(int i=0;i< users.size()-1;i++){
            for(int j=0;j<tasks.size()-1;j++){
                if(users.get(i).userName.equals(tasks.get(j).userName)){
                    users.get(i).tasksList.add(tasks.get(j));
                }
            }
        }
    }

    public static void saveTaskToDb(User u, int i) {
        try {
            String sql = "Insert into taskInfo (Task,Timer,Points,uName) values(?,?,?,?)";
            PreparedStatement pStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, u.tasksList.get(i).task);
            pStatement.setInt(2, u.tasksList.get(i).time);
            pStatement.setInt(3, u.tasksList.get(i).points);
            pStatement.setString(4, u.getUserName());
            int row = pStatement.executeUpdate();
            if (row > 0) {
                System.out.println("Task has been completed");
                ResultSet rs = pStatement.getGeneratedKeys();
                if (rs.next()) {
                    result = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePoints(User u) {
        try {
            String sql = "update userInfo set Points=? where userName=?";
            PreparedStatement pStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < u.tasksList.size() - 1; i++) {
                if (u.userName.equals(u.tasksList.get(i).userName)) {
                    u.points += u.tasksList.get(i).points;
                }
            }

            pStatement.setInt(1, u.points);
            pStatement.setString(2, u.getUserName());
            int row = pStatement.executeUpdate();
            if (row > 0) {
                System.out.println("Points have been updated");
                ResultSet rs = pStatement.getGeneratedKeys();
                if (rs.next()) {
                    result = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
