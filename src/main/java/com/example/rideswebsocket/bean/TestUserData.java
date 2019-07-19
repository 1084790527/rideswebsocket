package com.example.rideswebsocket.bean;

public class TestUserData {
    private String id;
    private String name;

    @Override
    public String toString() {
        return "TestUserData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
