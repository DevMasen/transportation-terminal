package com.example.terminal;

import java.util.Objects;

public final class Vehicle {
    private final String vType;
    private final String vID;

    public Vehicle(String vType, String vID) {
        this.vType = vType;
        this.vID = vID;
    }

    public String getVType() {
        return vType;
    }

    public String getVID() {
        return vID;
    }
}
