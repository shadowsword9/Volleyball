package com.example.volleyball;

public class Player {

    private int id;
    private String name;
    private String isSetter;

    public Player(int id, String name, String isSetter) {
        this.id = id;
        this.name = name;
        this.isSetter = isSetter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsSetter() {
        return isSetter;
    }

    public void setIsSetter(String isSetter) {
        this.isSetter = isSetter;
    }
}
