package com.example.terminal;

import java.util.ArrayList;
import java.util.Objects;

public class TicketsList {
    public static ArrayList<Ticket> ticketsList = new ArrayList<>();

    public static void setNewTicket(String ticketID, String moveTime,
                                    String moveDate, String delay, Vehicle vehicle,
                                    String origin, String destination, String price){
        Ticket ticket = new Ticket(ticketID, moveTime, moveDate, delay, vehicle, origin, destination,price);
        ticketsList.add(ticket);
    }
    public static boolean setDelay(String ticketId, String delayTime){
        for (Ticket ticket : ticketsList){
            if (Objects.equals(ticket.getTicketID(), ticketId)){
                int index = ticketsList.indexOf(ticket);
                ticketsList.get(index).setDelay(delayTime);
                return true;
            }
        }
        return false;
    }

}
