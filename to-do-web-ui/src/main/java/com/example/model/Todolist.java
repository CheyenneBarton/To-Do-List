package com.example.model;

import jakarta.persistence.*;

@Entity(name = "Todolist")
public class Todolist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "item")
    private String item;

    public Todolist() {
    }

    public Todolist(String item) {
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
