package io.github.demo.DAO;

import java.sql.*;

public class UserDAO {
    private final String url = "jdbc:mysql://database:3306/Book";
    private final String user = "root";
    private final String pass = "abc123";

    public User findByLogin(Login login) throws SQLException {
        User loginUser = null;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            pst = con.prepareStatement("SELECT USERNAME, PASSWORD FROM USERS WHERE USERNAME = ? AND PASSWORD = ?");
            pst.setString(1, login.getName());
            pst.setString(2, login.getPassword());
            rs = pst.executeQuery();

            if (rs == null) {
                return null;
            }

            if (rs.next()) {
                String name = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                loginUser = new User(name, password);
            }

        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバが読み込めませんでした。");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (loginUser == null) {
            return null;
        }
        return loginUser;
    }
}