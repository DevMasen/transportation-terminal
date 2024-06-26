package com.example.terminal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserAvailableTicketsController implements Initializable {
    @FXML
    DatePicker ticketDate;
    @FXML
    ListView<String> availableTicketsList;

    private final String[] availableTickets = new String[TicketsList.ticketsList.size()];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Arrays.fill(availableTickets, "  ");
        availableTicketsList.getItems().addAll(availableTickets);
    }

    public void onDoneClickAction(){
        LocalDate date = ticketDate.getValue();
        if (ticketDate.getValue() == null){
            return;
        } else {
            String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            for (int i = 0; i < TicketsList.ticketsList.size(); i++) {
                if(Objects.equals(TicketsList.ticketsList.get(i).getMoveDate(), formattedDate)){
                    availableTickets[i] = STR." Id:\{TicketsList.ticketsList.get(i).getTicketID()}   Move Time:\{TicketsList.ticketsList.get(i).getMoveTime()}   Move Date:\{TicketsList.ticketsList.get(i).getMoveDate()}   Delay:\{TicketsList.ticketsList.get(i).getDelay()}   Vehicle-Type:\{TicketsList.ticketsList.get(i).getVehicle().getVType()}   Vehicle-ID:\{TicketsList.ticketsList.get(i).getVehicle().getVID()}   Origin:\{TicketsList.ticketsList.get(i).getOrigin()}   Destination:\{TicketsList.ticketsList.get(i).getDestination()}   Price:\{TicketsList.ticketsList.get(i).getPrice()}$";
                } else {
                    availableTickets[i] = " ";
                }
            }
        }
        availableTicketsList.getItems().clear();
        availableTicketsList.getItems().addAll(availableTickets);
    }



    SwitchPage switchPage = new SwitchPage();
    public void switchToUserPanel(ActionEvent event) throws IOException {
        switchPage.switchPage(event, "user_panel.fxml");
    }

}
