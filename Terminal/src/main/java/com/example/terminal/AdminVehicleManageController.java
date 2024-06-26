package com.example.terminal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminVehicleManageController implements Initializable {
    @FXML
    RadioButton addVehicle;
    @FXML
    RadioButton deleteVehicle;
    @FXML
    RadioButton editVehicle;
    @FXML
    TextField vehicleID;
    @FXML
    ChoiceBox<String> vehicleType;
    @FXML
    Label success;
    @FXML
    Label error;

    private final String[] types = {"Bus","Car"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vehicleType.getItems().addAll(types);
//        vehicleType.setOnAction(this::onDoneClickAction);
    }

    public void onDoneClickAction(){
        if (addVehicle.isSelected()){
            boolean isIDValid = true;
            for(int i=0;i<vehicleID.getText().length();i++){
                if ((int)vehicleID.getText().charAt(i) == 32){
                    success.setText("");
                    error.setText("Space Is NOT permitted!");
                    isIDValid =false;
                    break;
                }
            }
            boolean IDExist = false;
            for (Vehicle vehicle : VehiclesList.vehiclesList){
                if (Objects.equals(vehicle.getVID(), vehicleID.getText())){
                    success.setText("");
                    error.setText("ID is already taken! ");
                    IDExist = true;
                    break;
                }
            }


            if (vehicleID.getText().isEmpty()){
                success.setText("");
                error.setText("ID field must NOT be empty! ");
            }
            else if(vehicleType.getValue() == null || Objects.equals(vehicleType.getValue(), "")){
                success.setText("");
                error.setText("Please enter vehicle type!");
            }
            else if (isIDValid && !IDExist) {
                String finalVID = vehicleID.getText();
                String finalVType = vehicleType.getValue();
                VehiclesList.setNewVehicle(finalVType,finalVID);
                error.setText("");
                success.setText("Vehicle Successfully Added!");
            }
        }


        else if (deleteVehicle.isSelected()) {
            boolean IDExist = false;
            for (Vehicle vehicle : VehiclesList.vehiclesList){
                if (Objects.equals(vehicle.getVID(), vehicleID.getText())){
                    VehiclesList.vehiclesList.remove(vehicle);
                    error.setText("");
                    success.setText("Vehicle Successfully Deleted!");
                    IDExist = true;
                    break;
                }
            }

            if (vehicleID.getText().isEmpty()){
                success.setText("");
                error.setText("Field Vehicle ID must NOT be empty! ");
            }
            else if(!IDExist){
                success.setText("");
                error.setText("Vehicle NOT found! ");
            }
        }


        else if (editVehicle.isSelected()) {
            boolean IDExist = false;
            for (Vehicle vehicle : VehiclesList.vehiclesList){
                if (Objects.equals(vehicle.getVID(), vehicleID.getText())){
                    IDExist =true;
                    break;
                }
            }

            if (vehicleID.getText().isEmpty()){
                success.setText("");
                error.setText("Please enter vehicle ID!");
            }
            else if(vehicleType.getValue() == null || Objects.equals(vehicleType.getValue(), "")){
                success.setText("");
                error.setText("Please enter vehicle type!");
            }
            else if (IDExist) {
                VehiclesList.changeVehicleType(vehicleType.getValue(), vehicleID.getText());
                error.setText("");
                success.setText("Vehicle Successfully Edited! ");
            }
            else {
                success.setText("");
                error.setText("Vehicle NOT found! ");
            }
        }
    }

    SwitchPage switchPage = new SwitchPage();
    public void switchToAdminPanel(ActionEvent event) throws IOException {
        switchPage.switchPage(event,"admin_panel.fxml");
    }


}
