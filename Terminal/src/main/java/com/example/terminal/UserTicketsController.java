package com.example.terminal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserTicketsController implements Initializable {
    @FXML
    ListView<String> ticketList;

    private final String username = LoginController.publicUsername;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] tickets = null;
        for (User user: UsersList.usersList){
            if (Objects.equals(user.getUserName(), username)){
                tickets = new String[user.getUserTickets().size()];
                for (int i = 0; i < user.getUserTickets().size(); i++) {
                    tickets[i] = STR." Id:\{user.getUserTickets().get(i).getTicketID()}   Move Time:\{user.getUserTickets().get(i).getMoveTime()}   Move Date:\{user.getUserTickets().get(i).getMoveDate()}   Delay:\{user.getUserTickets().get(i).getDelay()}   Vehicle-Type:\{user.getUserTickets().get(i).getVehicle().getVType()}   Vehicle-ID:\{user.getUserTickets().get(i).getVehicle().getVID()}   Origin:\{user.getUserTickets().get(i).getOrigin()}   Destination:\{user.getUserTickets().get(i).getDestination()}   Price:\{user.getUserTickets().get(i).getPrice()}$";
                }
                break;
            }
        }
        ticketList.getItems().addAll(tickets);
    }

    SwitchPage switchPage = new SwitchPage();
    public void switchToUserPanel(ActionEvent event) throws IOException{
        switchPage.switchPage(event, "user_panel.fxml");
    }

}
