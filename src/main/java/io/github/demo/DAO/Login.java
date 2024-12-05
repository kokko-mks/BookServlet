package io.github.demo.DAO;

import java.io.Serializable;

//ログイン情報の保持,データの転送
public class Login implements Serializable {
    //直接アクセスを防ぐ
    private String name;
    private String password;

    public Login() {} //デフォルトコンストラクタ
    public Login(String name, String password) {
        this.name = name;
        this.password = password;
    }

    //外部からアクセス可
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
