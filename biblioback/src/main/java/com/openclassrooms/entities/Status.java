package com.openclassrooms.entities;

public enum Status {
    WAITINGLIST(1),
    ONGOING(2),
    NONE(3);

    int value;

    private Status(int value) { this.value = value; }
}
