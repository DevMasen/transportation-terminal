package com.example.terminal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.Objects;

public class PasswordRecoveryController{
    @FXML
    Label passwordRecoverySuccess;
    @FXML
    Label passwordRecoveryError;
    @FXML
    TextField passwordRecoveryUsername;
    @FXML
    TextField passwordRecoveryEmail;

    String adminName = Admin.getAdminName();
    String adminPassword = Admin.getAdminPassword();
    String adminEmail = Admin.getAdminEmail();



    public void setPasswordRecoveryUsername(String Name) {
        passwordRecoveryUsername.setText(Name);
    }

    SwitchPage switchPage = new SwitchPage();
    public void switchToLogin(ActionEvent event) throws IOException {
        switchPage.switchPage(event, "login.fxml");
    }

    public void recovery(){
        if(passwordRecoveryUsername.getText().toLowerCase().equals(adminName)){
            if (Objects.equals(passwordRecoveryEmail.getText(), adminEmail)){
                passwordRecoveryError.setText("");
                passwordRecoverySuccess.setText(STR."Admin Password: \{adminPassword}");
            }
            else {
                passwordRecoverySuccess.setText("");
                passwordRecoveryError.setText("Email is Incorrect!");
            }
        }
        else {
            for (User user :UsersList.usersList){
                if(Objects.equals(user.getUserName(), passwordRecoveryUsername.getText())){
                    if (Objects.equals(user.getEmail(), passwordRecoveryEmail.getText())){
                        passwordRecoveryError.setText("");
                        passwordRecoverySuccess.setText(STR."User Password is: \{user.getPassword()}");
                    }
                    else {
                        passwordRecoverySuccess.setText("");
                        passwordRecoveryError.setText("Email is Incorrect!");
                    }
                }
            }
        }
    }
}
