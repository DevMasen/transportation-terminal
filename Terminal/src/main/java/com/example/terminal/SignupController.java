package com.example.terminal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SignupController {
    @FXML
    TextField signupUsername;
    @FXML
    TextField signupPassword;
    @FXML
    TextField signupRepeatPassword;
    @FXML
    TextField signupEmail;
    @FXML
    Label signupSuccess;
    @FXML
    Label signupError;

    SwitchPage switchPage = new SwitchPage();

    public void switchToLogin(ActionEvent event) throws IOException {
        switchPage.switchPage(event, "login.fxml");
    }
    public void submit() {
        boolean cond = false;
        for(User user:UsersList.usersList){
            if (Objects.equals(user.getUserName(), signupUsername.getText())){
                cond = true;
                break;
            }
        }

        boolean cond2 =  alphabetChecker(signupUsername);

        if (cond){
            signupSuccess.setText("");
            signupError.setText("Username already taken! Use another one.");
        }
        else if(signupUsername.getText().length() < 3 || signupUsername.getText().length() > 20){
            signupSuccess.setText("");
            signupError.setText("Username must be at least 3, and at most 20 character!");
        }
        else if (!cond2) {
            signupSuccess.setText("");
            signupError.setText("Username must contain alphabets only!");
        }
        else if (signupPassword.getText().length() < 8){
            signupSuccess.setText("");
            signupError.setText("Password must be at least 8 character!");
        }
        else if(!Objects.equals(signupRepeatPassword.getText(), signupPassword.getText())){
            signupSuccess.setText("");
            signupError.setText("The Password Repeat does not match the first one!  ");
        }
        else if(signupEmail.getText().length() < 8){
            signupSuccess.setText("");
            signupError.setText("Email is not valid!");
        }
        else{
            UsersList.setNewUser(signupUsername.getText(), signupPassword.getText(), signupEmail.getText(),"300");
            signupSuccess.setText("Account Successfully Created! Go For Login :) ");
            signupError.setText("");
        }
    }

    static boolean alphabetChecker(TextField signupUsername) {
        boolean cond2 = true;
        for(int i = 0; i< signupUsername.getText().length(); i++){
            if(((int) signupUsername.getText().charAt(i) < 65 || (int) signupUsername.getText().charAt(i) > 122)||
                    ((int) signupUsername.getText().charAt(i) > 90 && (int) signupUsername.getText().charAt(i) < 97)){
                cond2 = false;
                return cond2;
            }
        }
        return cond2;
    }
}
