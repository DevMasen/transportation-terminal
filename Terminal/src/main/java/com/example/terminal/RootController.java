package com.example.terminal;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RootController {
    SwitchPage switchPage = new SwitchPage();
    public void switchToLogin(ActionEvent event) throws IOException {
        switchPage.switchPage(event, "login.fxml");
    }
}
