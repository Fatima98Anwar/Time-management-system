package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.example.project.HelloApplication.db;

public class signUp {
    boolean userNameExists;
    boolean passwordExists;
    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField pass;

    @FXML
    private Label label;
    @FXML
    private Button signUnBtn;

    @FXML
    private TextField userName;

    @FXML
    void SignUp(ActionEvent event) throws IOException, SQLException {
        if(firstName.getText().isEmpty() || lastName.getText().isEmpty() || pass.getText().isEmpty() || userName.getText().isEmpty()){
            label.setText("Please fill all the fields");
        }
        else{
            String f,l,u,p;
            f=firstName.getText().toString();
            l=lastName.getText().toString();
            u=userName.getText().toString();
            userNameExists=db.checkUserName(u);
            p=pass.getText().toString();
            passwordExists=db.checkPassword(p);
            if(userNameExists){
                label.setText("Username already exists please enter again");
                userName.setText("");
            }
            else if(passwordExists){
                label.setText("Password already exists please enter again");
                pass.setText("");
            }
            else{
                db.insertData(f,l,u,p);
                label.setText("Registered Successfully");
                HelloApplication.changeScene("startTask.fxml");
            }
        }
    }

}
