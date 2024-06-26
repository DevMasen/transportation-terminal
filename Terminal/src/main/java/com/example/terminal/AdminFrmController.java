package com.example.terminal;

import javafx.event.ActionEvent;

import java.io.IOException;

public class AdminFrmController {
    SwitchPage switchPage = new SwitchPage();
    public void switchToAdminPanel(ActionEvent event) throws IOException {
        switchPage.switchPage(event,"admin_panel.fxml");
    }
}
