package com.example.terminal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminSetDelayController implements Initializable {
    @FXML
    TextField ticketID;
    @FXML
    ChoiceBox<String> delay;
    @FXML
    Label success;
    @FXML
    Label error;

    private final String[] delayList = {"10","20","30","40","50","60"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        delay.getItems().addAll(delayList);
    }

    public void onDoneClickAction(){
        boolean isTicketExist = false;
        for(Ticket ticket : TicketsList.ticketsList){
            if(Objects.equals(ticketID.getText(), ticket.getTicketID())){
                int index = TicketsList.ticketsList.indexOf(ticket);
                TicketsList.ticketsList.get(index).setDelay(delay.getValue());
                isTicketExist = true;
                break;
            }
        }

        boolean isSpaceIn = false;
        for (int i = 0; i < ticketID.getText().length(); i++) {
            if((int)ticketID.getText().charAt(i) == 32){
                isSpaceIn = true;
                break;
            }
        }

        if(ticketID.getText().isEmpty()){
            success.setText("");
            error.setText("Field ID must not be empty!");
        } else if (isSpaceIn) {
            success.setText("");
            error.setText("Space is not Permitted! ");
        } else if (isTicketExist) {
            error.setText("");
            success.setText("Delay Successfully Added! ");
        } else {
            success.setText("");
            error.setText("Ticket NOT found! ");
        }


    }

    SwitchPage switchPage = new SwitchPage();
    public void switchToAdminPanel(ActionEvent event) throws IOException {
        switchPage.switchPage(event,"admin_panel.fxml");
    }


}
