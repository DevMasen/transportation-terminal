package com.example.terminal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

public class UserEditTicketController {
    @FXML
    TextField previousTicketID;
    @FXML
    TextField newTicketID;
    @FXML
    Label success;
    @FXML
    Label error;


    public void onDoneClickAction(){
        String username = LoginController.publicUsername;

        Ticket newTicket = null;
        boolean newTicketExist = false;
        for (Ticket ticket : TicketsList.ticketsList){
            if (Objects.equals(ticket.getTicketID(), newTicketID.getText())){
                newTicketExist = true;
                newTicket = ticket;
                break;
            }
        }

        Ticket preTicket = null;
        boolean previousExist = false;
        if(newTicketExist) {
            for (User user : UsersList.usersList) {
                if (Objects.equals(user.getUserName(), username)) {
                    for (Ticket ticket : user.getUserTickets()) {
                        if (Objects.equals(ticket.getTicketID(), previousTicketID.getText())) {
                            previousExist = true;
                            preTicket = ticket;
                            break;
                        }
                    }
                    break;
                }
            }
        }

        if (previousTicketID.getText().isEmpty() || newTicketID.getText().isEmpty()){
            success.setText("");
            error.setText("Fields must NOT be empty!");
        } else if (!newTicketExist) {
            success.setText("");
            error.setText("New Ticket NOT found!");
        } else if (!previousExist) {
            success.setText("");
            error.setText("Previous Ticket NOT Exist!");
        } else {
            for (User user : UsersList.usersList){
                if (Objects.equals(user.getUserName(), username)){
                    user.removeTicket(preTicket);
                    user.addNewTicket(newTicket);
                    break;
                }
            }
            error.setText("");
            success.setText("Ticket Edited Successfully! ");
        }
    }

    SwitchPage switchPage = new SwitchPage();
    public void switchToUserPanel(ActionEvent event) throws IOException {
        switchPage.switchPage(event, "user_panel.fxml");
    }

}
