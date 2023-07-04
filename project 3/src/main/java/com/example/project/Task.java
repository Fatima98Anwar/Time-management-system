package com.example.project;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import static com.example.project.HelloApplication.db;
public class Task extends TimerTask {
    public static Timer timer;
    public static String userName;
    public static String task;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        Task.userName = userName;
    }

    public static int t;
    public static Date date;
    public static int time;

    public static int getPoints() {
        return points;
    }

    public static void setPoints(int points) {
        Task.points = points;
    }

    public static int points;

    public Task(String Task, int t,String uName,int point) {
        task = Task;
        date=new Date();
        time=0;
        timer = new Timer();
        timer.schedule(this, 1000, 2000);
        userName=uName;
        points=point;
    }

    @Override
    public void run() {
        time++;
    }

    public static void StopTimer(){
        timer.cancel();
    }

    public static void addTimeToDb(String uName){
        db.addTimeToDb(uName,time);
    }

    public static void setTask(String tas,int timer){
        task=tas;
        time=timer;
    }

    public static int getTime(){
        return time;
    }

    public static String getTask(){
        return task;
    }
    public static int getEnteredTime(){
        return t;
    }

    public static void setTime(int time) {
        Task.time = time;
    }

    public static boolean isTaskDone(){
        if(time==t){
            return true;
        }
        else{
            return false;
        }
    }
    public static Date getDate(){
        return date;
    }

    public static void setDate(Date d){
        date=d;
    }
}
