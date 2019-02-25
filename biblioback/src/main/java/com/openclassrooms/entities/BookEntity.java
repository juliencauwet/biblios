package com.openclassrooms.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BookEntity {

    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @Size(min = 1, max = 300)
    private String title;
    @NotNull
    @Size(min = 1, max = 100)
    private String authorName;
    private String authorFirstName;

    @OneToMany
    @JoinColumn(name = "borrowing_id")
    private Set<Borrowing> borrowings = new HashSet<>();

    private int number;

    public BookEntity(@NotNull @Size(min = 1, max = 300) String title, @NotNull @Size(min = 1, max = 100) String authorName, String authorFirstName, int number) {
        this.title = title;
        this.authorName = authorName;
        this.authorFirstName = authorFirstName;
        this.number = number;
    }

    public BookEntity(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Set<Borrowing> getBorrowings() {
        return borrowings;
    }

    public void setBorrowings(Set<Borrowing> borrowings) {
        this.borrowings = borrowings;
    }
}
