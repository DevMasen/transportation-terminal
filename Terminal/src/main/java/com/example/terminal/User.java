package com.example.terminal;

import java.util.ArrayList;
import java.util.Objects;

public final class User {
    private String userName;
    private final String password;
    private final String email;
    private final ArrayList<Ticket> userTickets;
    private String balance;

    public User(String userName, String password, String email, ArrayList<Ticket> userTickets, String balance) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userTickets = userTickets;
        this.balance = balance;
    }

    public void addNewTicket(Ticket ticket){
        userTickets.add(ticket);
    }
    public void removeTicket(Ticket ticket){
        userTickets.remove(ticket);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Ticket> getUserTickets() {
        return userTickets;
    }

    public String getBalance() {
        return balance;
    }
}
