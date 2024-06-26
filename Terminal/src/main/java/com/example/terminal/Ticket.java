package com.example.terminal;

public class Ticket{
    private final String ticketID;
    private final String moveTime;
    private final String moveDate;
    private String delay;
    private final Vehicle vehicle;
    private final String origin;
    private final String destination;
    private final String price;

    public Ticket(String ticketID, String moveTime, String moveDate, String delay, Vehicle vehicle, String origin, String destination, String price) {
        this.ticketID = ticketID;
        this.moveTime = moveTime;
        this.moveDate = moveDate;
        this.delay = delay;
        this.vehicle = vehicle;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getTicketID() {
        return ticketID;
    }

    public String getMoveTime() {
        return moveTime;
    }

    public String getMoveDate() {
        return moveDate;
    }

    public String getDelay() {
        return delay;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getPrice() {
        return price;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }
}
