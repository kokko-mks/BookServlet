package io.github.demo.DAO;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    private String password;

    public User() {}
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        System.out.printf(this.name + " " + this.password);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
