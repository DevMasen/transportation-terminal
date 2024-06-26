package com.example.terminal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import java.io.IOException;

public class AdminPanelController {
    @FXML
    RadioButton reserveCancelTicket;
    @FXML
    RadioButton vehicleManage;
    @FXML
    RadioButton setDelay;
    @FXML
    RadioButton frmSystem;
    @FXML
    RadioButton manageTickets;


    SwitchPage switchPage = new SwitchPage();
    public void switchToLogin(ActionEvent event) throws IOException {
        switchPage.switchPage(event,"login.fxml");
    }

    public void selectAction(ActionEvent event) throws IOException {
        if(reserveCancelTicket.isSelected()){
            switchPage.switchPage(event,"admin_reserve_cancel.fxml");
        } else if (vehicleManage.isSelected()) {
            switchPage.switchPage(event,"admin_vehicle_manage.fxml");
        } else if (setDelay.isSelected()) {
            switchPage.switchPage(event,"admin_set_delay.fxml");
        } else if (frmSystem.isSelected()) {
            switchPage.switchPage(event,"admin_frm.fxml");
        } else if(manageTickets.isSelected()){
            switchPage.switchPage(event,"admin_ticket_manage.fxml");
        }
    }
}
