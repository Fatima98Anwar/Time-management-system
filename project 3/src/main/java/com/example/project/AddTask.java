package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.project.HelloApplication.user;

public class AddTask implements Initializable {
    String currentTask;
    private Stage thisStage;


    @FXML
    private TextField Time;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button startBtn;

    @FXML
    private ChoiceBox<String> task;

    @FXML
    void getTask(ActionEvent event) throws IOException {
        currentTask=task.getSelectionModel().getSelectedItem();
        Task task=new Task(currentTask, Integer.parseInt(Time.getText()),user.getUserName(), 0);
        user.addTask(task);
        HelloApplication.changeScene("startTask.fxml");
    }


    @FXML
    void goBack(ActionEvent event) throws IOException {
        HelloApplication.changeScene("startTask.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.task.getItems().addAll("Studying","Creative","Exercise","Reading","Writing");
    }


}
