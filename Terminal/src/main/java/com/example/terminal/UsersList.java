package com.example.terminal;

import java.util.ArrayList;

public class UsersList {
    public static ArrayList<User> usersList = new ArrayList<>();

    public static void setNewUser(String userName, String password, String email, String balance){
        User user = new User(userName, password, email, new ArrayList<>(), balance);
        usersList.add(user);
    }
}
