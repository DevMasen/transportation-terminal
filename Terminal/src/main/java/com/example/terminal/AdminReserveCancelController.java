package com.example.terminal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

public class AdminReserveCancelController {
    @FXML
    RadioButton reserveTicket;
    @FXML
    RadioButton cancelTicket;
    @FXML
    TextField userName;
    @FXML
    TextField ticketID;
    @FXML
    Label success;
    @FXML
    Label error;

    public void onDoneAction(){
        if (reserveTicket.isSelected()){
            boolean userFlag = false ;
            for(User user : UsersList.usersList){
                if (Objects.equals(user.getUserName(), userName.getText())){
                    userFlag = true;
                    boolean ticketFlag = false;
                    boolean isBalanceEnough = true;
                    for (Ticket ticket : TicketsList.ticketsList){
                        if (Objects.equals(ticket.getTicketID(), ticketID.getText())) {
                            ticketFlag =true;
                            int index = UsersList.usersList.indexOf(user);
                            UsersList.usersList.get(index).addNewTicket(ticket);
                            int intFinalBalance = Integer.parseInt(UsersList.usersList.get(index).getBalance()) - Integer.parseInt(ticket.getPrice());
                            if(intFinalBalance<0){
                                isBalanceEnough = false;
                            }
                            else {
                                String strFinalBalance = STR."\{intFinalBalance}";
                                UsersList.usersList.get(index).setBalance(strFinalBalance);
                                error.setText("");
                                success.setText(STR."Ticket Successfully Added for User \{userName.getText()}");
                            }
                            break;
                        }
                    }
                    if(!ticketFlag){
                        success.setText("");
                        error.setText("Ticket NOT Found!");
                    }
                    else if(!isBalanceEnough){
                        success.setText("");
                        error.setText("Balance is NOT enough! ");
                    }
                    break;
                }
            }
            if (!userFlag){
                success.setText("");
                error.setText("User Not Found!");
            }
        } else if (cancelTicket.isSelected()) {
            boolean userFlag = false ;
            for(User user : UsersList.usersList){
                if (Objects.equals(user.getUserName(), userName.getText())){
                    userFlag = true;
                    boolean ticketFlag = false;
                    for (Ticket ticket : TicketsList.ticketsList){
                        if (Objects.equals(ticket.getTicketID(), ticketID.getText())) {
                            int index = UsersList.usersList.indexOf(user);
                            UsersList.usersList.get(index).removeTicket(ticket);
                            int intFinalBalance = Integer.parseInt(UsersList.usersList.get(index).getBalance()) + Integer.parseInt(ticket.getPrice());
                            String strFinalBalance = STR."\{intFinalBalance}";
                            UsersList.usersList.get(index).setBalance(strFinalBalance);
                            error.setText("");
                            success.setText(STR."Ticket Successfully Cancelsed for User \{userName.getText()}");
                            ticketFlag =true;
                            break;
                        }
                    }
                    if(!ticketFlag){
                        success.setText("");
                        error.setText("Ticket NOT Found!");
                    }
                    break;
                }
            }
            if (!userFlag){
                success.setText("");
                error.setText("User Not Found!");
            }
        }
    }

    SwitchPage switchPage = new SwitchPage();
    public void switchToAdminPanel(ActionEvent event) throws IOException {
        switchPage.switchPage(event,"admin_panel.fxml");
    }
}
