//ログイン機能を実装
package io.github.demo.DAO;

import java.sql.*;

public class UserDAO {
    //データベースに接続するための情報
    private final String url = "jdbc:mysql://database:3306/Book";
    private final String user = "root";
    private final String pass = "abc123";

    //ログイン情報から該当のユーザーを検索
    public User findByLogin(Login login) throws SQLException {
        User loginUser = null;

        Connection con = null; //データベース接続用
        PreparedStatement pst = null; //　SQL文を送信するためのprepare
        ResultSet rs = null; // sql結果保持

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url, user, pass);
//          //sql文
            pst = con.prepareStatement("SELECT USERNAME, PASSWORD FROM USERS WHERE USERNAME = ? AND PASSWORD = ?");
            //プレースフォルダに入力データ設定
            pst.setString(1, login.getName());
            pst.setString(2, login.getPassword());
            //結果取得
            rs = pst.executeQuery();

            //結果が空の場合
            if (rs == null) {
                return null;
            }

            //結果があればuserを作成して格納
            if (rs.next()) {
                String name = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                loginUser = new User(name, password);
            }

        } catch (ClassNotFoundException e) {
            //JDBCドライバが見つからなかった場合
            throw new IllegalStateException("JDBCドライバが読み込めませんでした。");
        } catch (SQLException e) {
            //SQL例外
            e.printStackTrace();
        }

        if (loginUser == null) {
            return null;
        }
        return loginUser;
    }
}