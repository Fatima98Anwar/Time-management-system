package com.example.project;

import java.util.ArrayList;

public class User {

    public int ID;
    public int points;
    public String firstName;
    String lastName;
    String userName;
    String password;

    ArrayList<Task> tasksList;

    public String getUserName() {
        return userName;
    }

    public User(){}

    public User(int ID, String firstName, String lastName, String userName, String password, int points) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.points = points;
        tasksList = new ArrayList<Task>();
    }

    public void addTask(Task task){
        tasksList.add(task);
    }

}
