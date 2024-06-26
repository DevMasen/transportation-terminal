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

public class LoginController{
    @FXML
    TextField loginUserName;
    @FXML
    TextField loginPassword;
    @FXML
    Label loginError;

    String adminName = Admin.getAdminName();
    String adminPassword = Admin.getAdminPassword();

    private boolean recoveryUsernameIsValid = false;
    public static String publicUsername = "";

    SwitchPage switchPage = new SwitchPage();

    public void switchToRoot(ActionEvent event) throws IOException {
        switchPage.switchPage(event, "root.fxml");
    }
    public void switchToSignUp(ActionEvent event) throws IOException {
        switchPage.switchPage(event, "signup.fxml");
    }
    public void switchToPasswordRecovery(ActionEvent event) throws IOException {
        if (recoveryUsernameIsValid){
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("password_recovery.fxml")));
            Parent passwordRecoveryRoot = loader.load();
            PasswordRecoveryController passwordRecoveryController = loader.getController();
            passwordRecoveryController.setPasswordRecoveryUsername(loginUserName.getText());
            Scene scene = new Scene(passwordRecoveryRoot);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else {
            loginError.setText("Please Enter Your Username And Try Passwords First!");
        }
    }
    public void switchToPanel(ActionEvent event) throws IOException {

        boolean isAlphabet = SignupController.alphabetChecker(loginUserName);

        if(Objects.equals(loginUserName.getText(), "") || Objects.equals(loginPassword.getText(), "")){
            loginError.setText("Fields must NOT be empty!");
        }
        else if(loginUserName.getText().length() < 3 || loginUserName.getText().length() > 20){
            loginError.setText("Username must be at least 3, and at most 20 character!");
        }
        else if (!isAlphabet) {
            loginError.setText("Username must contain alphabets only!");
        }
        else if (loginPassword.getText().length() < 8){
            loginError.setText("Password must be at least 8 character!");
        }
        else if(Objects.equals(loginUserName.getText().toLowerCase(), adminName)){

            if(Objects.equals(loginPassword.getText(), adminPassword)){
                switchPage.switchPage(event, "admin_panel.fxml");
            } else {
                recoveryUsernameIsValid = true;
                loginError.setText("Password is Incorrect! :( ");
            }
        }
        else {
            boolean isUserExist = false;
            for (User user: UsersList.usersList){
                if(Objects.equals(user.getUserName(), loginUserName.getText())){
                    isUserExist = true;
                    if(Objects.equals(user.getPassword(), loginPassword.getText())){
                        LoginController.publicUsername = user.getUserName();
                        switchPage.switchPage(event,"user_panel.fxml");
                    } else {
                        recoveryUsernameIsValid = true;
                        loginError.setText("Password is Incorrect! :( ");
                    }
                    break;
                }
            }
            if(!isUserExist){
                loginError.setText("UserName NOT Found! :( Please Sign Up.");
            }
        }

    }
}