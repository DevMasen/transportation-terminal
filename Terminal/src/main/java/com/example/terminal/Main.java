package com.example.terminal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;


public class Main extends Application {
    @Override
    public void init() {
        try {
            //Write user information from FILE to ARRAYLIST
            File userDataFile = new File("userData.txt");
            Scanner userDataInput = new Scanner(userDataFile);
            while (userDataInput.hasNextLine()) {
                String line = userDataInput.nextLine();
                String[] userData = line.split("\\s+");
                UsersList.setNewUser(userData[0],userData[1],userData[2],userData[3]);
            }
            userDataInput.close();

            //Write Ticket and Vehicle information from FILE to ARRAYLIST
            File ticketVehicleDataFile = new File("ticketVehicleData.txt");
            Scanner ticketVehicleDataInput = new Scanner(ticketVehicleDataFile);
            while (ticketVehicleDataInput.hasNextLine()) {
                String line = ticketVehicleDataInput.nextLine();
                String[] ticketData = line.split("\\s+");
                VehiclesList.setNewVehicle(ticketData[5],ticketData[4]);
                Vehicle v = new Vehicle(ticketData[5],ticketData[4]);
                TicketsList.setNewTicket(ticketData[0], ticketData[1], ticketData[2],
                        ticketData[3],v,ticketData[6],ticketData[7],ticketData[8]);
            }
            ticketVehicleDataInput.close();

//            Write user reserved tickets from FILE to ARRAYLIST
            File userReservedTicketsDataFile = new File("userReservedTicketsData.txt");
            Scanner userReservedTicketsDataInput = new Scanner(userReservedTicketsDataFile);
            while (userReservedTicketsDataInput.hasNextLine()) {
                String line = userReservedTicketsDataInput.nextLine();
                String[] reservedTicketData = line.split("\\s+");
                String userId = reservedTicketData[0];
                String userTicketID = reservedTicketData[1];
                for (User user: UsersList.usersList){
                    if(Objects.equals(user.getUserName(), userId)){
                        for (Ticket ticket : TicketsList.ticketsList){
                            if(Objects.equals(ticket.getTicketID(), userTicketID)){
                                user.addNewTicket(ticket);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
            ticketVehicleDataInput.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred!");
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("root.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root,600,600);
        Image icon = new Image(Objects.requireNonNull(getClass().getResource("bus_img.png")).toString());
        stage.setResizable(false);
        stage.getIcons().add(icon);
        stage.setTitle("Terminal");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            exit(stage);
        });
    }
    public void exit(Stage stage){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("EXIT");
        alert.setHeaderText("You are about too exit the application!");
        alert.setContentText("Do you want to Save and Exit?!");

        if(alert.showAndWait().get() == ButtonType.OK){
        try {
            //Write user data from ARRAYLIST to FILE.
            FileWriter userDataFile = new FileWriter("userData.txt");
            for(User user:UsersList.usersList){
                userDataFile.write(STR."\{user.getUserName()} \{user.getPassword()} \{user.getEmail()} \{user.getBalance()}\n");
            }
            userDataFile.close();
            

            FileWriter ticketsDataFile = new FileWriter("ticketVehicleData.txt");
            for(Ticket ticket : TicketsList.ticketsList){
                ticketsDataFile.write(STR."\{ticket.getTicketID()} \{ticket.getMoveTime()} \{ticket.getMoveDate()} \{ticket.getDelay()}"+
                        STR." \{ticket.getVehicle().getVID()} \{ticket.getVehicle().getVType()} \{ticket.getOrigin()}"+
                        STR." \{ticket.getDestination()} \{ticket.getPrice()}\n");
            }
            ticketsDataFile.close();

            //Write user reserved tickets data from ARRAYLIST to FILE.
            FileWriter userReservedTicketsDataFile = new FileWriter("userReservedTicketsData.txt");
            for (User user : UsersList.usersList){
                for (Ticket ticket : user.getUserTickets()){
                    userReservedTicketsDataFile.write(STR."\{user.getUserName()} \{ticket.getTicketID()}\n");
                }
            }
            userReservedTicketsDataFile.close();

        } catch (IOException e){
            System.out.println("Error: Can't Exit File!");
        }
            stage.close();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}