package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.project.HelloApplication.db;
import static com.example.project.HelloApplication.user;
public class HelloController {
    @FXML
    private Button goToSignUpBtn;

    @FXML
    private Label label;

    @FXML
    private TextField pass;

    @FXML
    private Button signInBtn;

    @FXML
    private TextField userName;

    @FXML
    void goToHomePage(ActionEvent event) throws IOException {

        System.out.println("You clicked me!");
        if(userName.getText().isEmpty() || pass.getText().isEmpty()){
            label.setText("Please fill all the fields");
        }
        else{
            String u,p;
            u=userName.getText().toString();
            p=pass.getText().toString();
            boolean check=db.checkUser(u,p);
            if(check){
                user=db.getUser(u);
                HelloApplication.changeScene("startTask.fxml");
            }
            else{
                label.setText("Invalid username or password");
                userName.setText("");
                pass.setText("");
            }
        }
    }

    @FXML
    void goToRegister(ActionEvent event) throws IOException {
        HelloApplication.changeScene("signUp.fxml");
    }

}
