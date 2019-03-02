package com.openclassrooms.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BorrowingProperty {

    @Id
    private int libraryId;

    private int borrowingLength;

    private int borrowingExtensionLength;

    public BorrowingProperty() {
    }

    public BorrowingProperty(int libraryId, int borrowingLength, int borrowingExtensionLength) {
        this.libraryId = libraryId;
        this.borrowingLength = borrowingLength;
        this.borrowingExtensionLength = borrowingExtensionLength;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    public int getBorrowingLength() {
        return borrowingLength;
    }

    public void setBorrowingLength(int borrowingLength) {
        this.borrowingLength = borrowingLength;
    }

    public int getBorrowingExtensionLength() {
        return borrowingExtensionLength;
    }

    public void setBorrowingExtensionLength(int borrowingExtensionLength) {
        this.borrowingExtensionLength = borrowingExtensionLength;
    }
}
