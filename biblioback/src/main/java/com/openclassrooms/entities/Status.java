package com.openclassrooms.entities;

public enum Status {
    WAITINGLIST(1),
    ONGOING(2),
    NONE(3),
    DENIED(4),
    RETURNED(5),
    AVAILABLE(6),
    CANCELLED(7);


    int value;

    private Status(int value) { this.value = value; }
}
