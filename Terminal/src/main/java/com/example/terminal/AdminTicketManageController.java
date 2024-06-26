package com.example.terminal;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminTicketManageController implements Initializable{
    @FXML
    RadioButton addTicket;
    @FXML
    RadioButton removeTicket;
    @FXML
    TextField ticketID;
    @FXML
    ChoiceBox<String> moveTime;
    @FXML
    DatePicker moveDate;
    @FXML
    ChoiceBox<String> origin;
    @FXML
    ChoiceBox<String> destination;
    @FXML
    ListView<String> VehicleList;
    @FXML
    Label error;
    @FXML
    Label success;

    private final String[] times = {"8:00", "10:00", "12:00", "14:00", "16:00", "18:00"};
    private final String[] cities = {"NewYork","WashingtonDC","LosAngles","Texas","Brookline"};

    private final int vehicleListSize = VehiclesList.vehiclesList.size();
    private final String[] vehiclesIDs = new String[vehicleListSize];
    private final String[] vehicleTypes = new String[vehicleListSize];
    private final String[] vehicleInfo = new String[vehicleListSize];
    private String currentVehicle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moveTime.getItems().addAll(times);
        origin.getItems().addAll(cities);
        destination.getItems().addAll(cities);

        for (int i = 0; i < vehicleListSize; i++) {
            vehiclesIDs[i] = VehiclesList.vehiclesList.get(i).getVID();
            vehicleTypes[i] = VehiclesList.vehiclesList.get(i).getVType();
            vehicleInfo[i] = STR."ID: \{vehiclesIDs[i]} Type: \{vehicleTypes[i]}";
        }
        VehicleList.getItems().addAll(vehicleInfo);
        VehicleList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentVehicle = VehicleList.getSelectionModel().getSelectedItem();
            }
        });
    }


    public void onDoneClickAction(){
        if (addTicket.isSelected()){
            if (ticketID.getText().isEmpty()){
                success.setText("");
                error.setText("Ticket ID must NOT be empty!");
            } else if (ticketID.getText().length() < 3 || ticketID.getText().length() > 20){
                success.setText("");
                error.setText("Ticket ID must be at least 3 and at most 20 character!");
            } else {
                boolean ticketFlag = false;
                for(Ticket ticket:TicketsList.ticketsList){
                    if (Objects.equals(ticketID.getText(), ticket.getTicketID())){
                        success.setText("");
                        error.setText("Ticket ID Already Exist!");
                        ticketFlag = true;
                        break;
                    }
                }
                if(moveTime.getValue() == null || Objects.equals(moveTime.getValue(), "")){
                    success.setText("");
                    error.setText("Please Enter Move Time!");
                }
                else if (moveDate.getValue() == null || moveDate.getValue().toString().isEmpty()) {
                    success.setText("");
                    error.setText("Please Enter a Date!");
                }
                else if (origin.getValue() == null || origin.getValue().isEmpty() ||
                        destination.getValue() == null || destination.getValue().isEmpty()) {
                    success.setText("");
                    error.setText("Please Enter City!");
                } else if (Objects.equals(origin.getValue(), destination.getValue())) {
                    success.setText("");
                    error.setText("The origin city can't be the same as destination! ");
                }
                else if (currentVehicle == null || currentVehicle.isEmpty()) {
                    success.setText("");
                    error.setText("Please Choose a Vehicle");
                }
                else if (!ticketFlag){
                    String finalID = ticketID.getText();
                    String finalMoveTime = moveTime.getValue();
                    String finalMoveDate = moveDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    String finalOrigin = origin.getValue();
                    String finalDestination = destination.getValue();
                    String finalDelay ="0";
                    String finalPrice = "100";

                    Vehicle finalVehicle = null;
                    String[] currentVehicleParts = currentVehicle.split("\\s+");
                    String tempVehicleID = currentVehicleParts[1];

                    for (Vehicle vehicle:VehiclesList.vehiclesList){
                        if (Objects.equals(vehicle.getVID(), tempVehicleID)){
                            finalVehicle = vehicle;
                        }
                    }
                    TicketsList.setNewTicket(finalID,finalMoveTime,finalMoveDate,finalDelay, finalVehicle, finalOrigin, finalDestination,finalPrice);
                    error.setText("");
                    success.setText("Ticket Successfully Added! ");
                }
            }
        } else if (removeTicket.isSelected()) {
            if (ticketID.getText().isEmpty()){
                success.setText("");
                error.setText("Ticket ID must NOT be empty!");
            } else if (ticketID.getText().length() < 3 || ticketID.getText().length() > 20){
                success.setText("");
                error.setText("Ticket ID must be at least 3 and at most 20 character!");
            } else {
                boolean ticketExist = false;
                for (Ticket ticket : TicketsList.ticketsList) {
                    if (Objects.equals(ticketID.getText(), ticket.getTicketID())) {
                        ticketExist = true;
                        TicketsList.ticketsList.remove(ticket);
                        break;
                    }
                }
                if (ticketExist){
                    error.setText("");
                    success.setText("Ticket Successfully Removed! ");
                } else {
                    success.setText("");
                    error.setText("Ticket NOT Found! ");
                }
            }
        }
    }

    SwitchPage switchPage = new SwitchPage();
    public void switchToAdminPanel(ActionEvent event) throws IOException {
        switchPage.switchPage(event,"admin_panel.fxml");
    }


}
