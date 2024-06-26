package com.example.terminal;

import java.util.ArrayList;
import java.util.Objects;

public class VehiclesList {
    public static ArrayList<Vehicle> vehiclesList = new ArrayList<>();

    public static void setNewVehicle(String vType, String vID){
        Vehicle vehicle = new Vehicle(vType, vID);
        vehiclesList.add(vehicle);
    }
    public static void changeVehicleType(String newType, String ID){
        for(Vehicle vehicle : vehiclesList){
            if(Objects.equals(vehicle.getVID(), ID)){
                vehiclesList.remove(vehicle);
                setNewVehicle(newType, ID);
            }
        }
    }
}
