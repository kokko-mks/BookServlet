package io.github.demo.DAO;

import java.io.Serializable;

public class User implements Serializable {
    //直接アクセスを防ぐ
    private int id;
    private String name;
    private String password;

    public User() {} //デフォルトコンストラクタ
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        System.out.printf(this.name + " " + this.password);
    }

    public int getId() {
        return id;
    }

    //クラスの外部からアクセス可
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
