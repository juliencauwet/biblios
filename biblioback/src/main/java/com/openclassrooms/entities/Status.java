package com.openclassrooms.entities;

public enum Status {
    WAITINGLIST(1),
    ONGOING(2),
    NONE(3),
    DENIED(4),
    RETURNED(5);


    int value;

    private Status(int value) { this.value = value; }
}
