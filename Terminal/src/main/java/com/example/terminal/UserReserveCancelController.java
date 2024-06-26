package com.example.terminal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

public class UserReserveCancelController {
    @FXML
    RadioButton reserveTicket;
    @FXML
    RadioButton cancelTicket;
    @FXML
    TextField ticketID;
    @FXML
    Label price;
    @FXML
    Label success;
    @FXML
    Label error;

    public void onDoneClickAction(){
        String username = LoginController.publicUsername;
//        System.out.println(STR."This is username: \{username}");
        if(reserveTicket.isSelected()){
            boolean isTicketExist = false;
            boolean isBalanceEnough = false;
            for (User user : UsersList.usersList){
                if(Objects.equals(user.getUserName(), username)){
                    for (Ticket ticket : TicketsList.ticketsList){
                        if (Objects.equals(ticket.getTicketID(), ticketID.getText())){
                            isTicketExist = true;
                            if (Integer.parseInt(ticket.getPrice()) <= Integer.parseInt(user.getBalance())){
                                isBalanceEnough = true;
                                int index = UsersList.usersList.indexOf(user);
                                UsersList.usersList.get(index).addNewTicket(ticket);
                                int intFinalBalance = Integer.parseInt(UsersList.usersList.get(index).getBalance()) -
                                        Integer.parseInt(ticket.getPrice());
                                String strFinalBalance = STR."\{intFinalBalance}";
                                UsersList.usersList.get(index).setBalance(strFinalBalance);
                                break;
                            }
                        }
                    }
                    break;
                }
            }
            if (ticketID.getText().isEmpty()){
                success.setText("");
                error.setText("Field Ticket ID must NOT be empty!");
            }
            else if (!isTicketExist){
                success.setText("");
                error.setText("Ticket NOT found!");
            }
            else if (!isBalanceEnough) {
                success.setText("");
                error.setText("Balance is NOT enough!");
            }
            else {
                error.setText("");
                success.setText("Ticket Reserved Successfully!");
            }

        }
        else if (cancelTicket.isSelected()) {
            boolean isTicketExist = false;
            for (User user : UsersList.usersList){
                if(Objects.equals(user.getUserName(), username)){
                    for (Ticket ticket : user.getUserTickets()){
                        if (Objects.equals(ticket.getTicketID(), ticketID.getText())){
                            isTicketExist = true;
                            int index = UsersList.usersList.indexOf(user);
                            UsersList.usersList.get(index).removeTicket(ticket);
                            int intFinalBalance = Integer.parseInt(UsersList.usersList.get(index).getBalance()) +
                                    Integer.parseInt(ticket.getPrice());
                            String strFinalBalance = STR."\{intFinalBalance}";
                            UsersList.usersList.get(index).setBalance(strFinalBalance);
                            break;
                        }
                    }
                    break;
                }
            }
            if (ticketID.getText().isEmpty()){
                success.setText("");
                error.setText("Field Ticket ID must NOT be empty!");
            }
            else if (!isTicketExist){
                success.setText("");
                error.setText("Ticket NOT found!");
            }
            else {
                error.setText("");
                success.setText("Ticket Canceled Successfully!");
            }
        }
    }

    public void onRefreshClickAction(){
        boolean isTicketExist = false;
        String price = null;
        for (Ticket ticket : TicketsList.ticketsList){
            if(Objects.equals(ticket.getTicketID(), ticketID.getText())){
                price = ticket.getPrice();
                isTicketExist = true;
                break;
            }
        }
        if(ticketID.getText().isEmpty()){
            success.setText("");
            error.setText("Field Ticket ID must NOT be empty!");
        }
        else if (isTicketExist) {
            this.price.setText(STR."\{price}$");
            success.setText("");
            error.setText("");
        } else {
            this.price.setText("-$");
            success.setText("");
            error.setText("Ticket NOT found!");
        }
    }

    SwitchPage switchPage = new SwitchPage();
    public void switchToUserPanel(ActionEvent event) throws IOException {
        switchPage.switchPage(event, "user_panel.fxml");
    }
}
