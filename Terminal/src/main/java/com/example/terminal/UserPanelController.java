package com.example.terminal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

import java.io.IOException;


public class UserPanelController {
    @FXML
    RadioButton availableTickets;
    @FXML
    RadioButton reserveCancelTicket;
    @FXML
    RadioButton editTickets;
    @FXML
    RadioButton myTickets;

    SwitchPage switchPage = new SwitchPage();
    public void onSelectClickAction(ActionEvent event) throws IOException {
        if (availableTickets.isSelected()){
            switchPage.switchPage(event, "user_available_tickets.fxml");
        }
        else if (reserveCancelTicket.isSelected()) {
            switchPage.switchPage(event,"user_reserve_cancel.fxml");
        }
        else if (editTickets.isSelected()) {
            switchPage.switchPage(event, "user_edit_ticket.fxml");
        }
        else if (myTickets.isSelected()) {
            switchPage.switchPage(event, "user_tickets.fxml");
        }
    }

    public void switchToLogin(ActionEvent event) throws IOException{
        switchPage.switchPage(event, "login.fxml");
    }
}
