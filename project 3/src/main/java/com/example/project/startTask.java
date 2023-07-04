package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.Timer;

import static com.example.project.HelloApplication.db;
import static com.example.project.HelloApplication.user;

public class startTask {

    Date date;
    long diff;
    @FXML
    private ImageView StopTime;

    @FXML
    private Button calendarBtn;

    @FXML
    private Label currTask;

    @FXML
    private Button profileBtn;

    @FXML
    private Button progressBtn;

    @FXML
    private Button startBtn;

    @FXML
    private ImageView startTime;

    @FXML
    private Button timeBtn;

    @FXML
    private Text timeSet;

    @FXML
    void StopActivity(MouseEvent event) {
        user.tasksList.get(user.tasksList.size()-1).StopTimer();
        int size=user.tasksList.size()-1;
        boolean wellDone=user.tasksList.get(user.tasksList.size()-1).isTaskDone();
        if(wellDone){
            currTask.setText("Well Done!");
            int points=Math.toIntExact(diff*2);
            user.tasksList.get(user.tasksList.size()-1).setPoints(points);
            user.tasksList.get(user.tasksList.size()-1).setTime(Math.toIntExact(diff));
            db.saveTaskToDb(user,size);
            user.tasksList.get(user.tasksList.size()-1).StopTimer();
            db.updatePoints(user);

        }
        else{
            currTask.setText("You can do better!");
            int points=Math.toIntExact(diff);
            user.tasksList.get(user.tasksList.size()-1).setPoints(points);
            user.tasksList.get(user.tasksList.size()-1).setTime(Math.toIntExact(diff));
            db.saveTaskToDb(user,size);
            user.tasksList.get(user.tasksList.size()-1).StopTimer();
            db.updatePoints(user);
        }
    }

    @FXML
    void goToCalendar(ActionEvent event) {

    }

    @FXML
    void goToProfile(ActionEvent event) {

    }

    @FXML
    void goToProgress(ActionEvent event) {

    }

    @FXML
    void goToStart(ActionEvent event) throws IOException {
        HelloApplication.changeScene("AddTask.fxml");
    }

    @FXML
    void goToUsage(ActionEvent event) {

    }

    @FXML
    void setData(MouseEvent event) {
        Date d=new Date();
        diff=d.getTime()-date.getTime();
        diff=(diff/1000)%60;
        String task=user.tasksList.get(user.tasksList.size()-1).getTask();
        int tim=user.tasksList.get(user.tasksList.size()-1).getTime();
        currTask.setText(task);
        timeSet.setText(String.valueOf(diff));
    }

    @FXML
    public void initialize(){
        date=new Date();
    }

}
