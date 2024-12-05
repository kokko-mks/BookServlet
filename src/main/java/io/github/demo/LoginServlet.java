package io.github.demo;

import io.github.demo.DAO.Login;
import io.github.demo.DAO.User;
import io.github.demo.DAO.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    //フォームから送信されたPOSTリクエストを処理
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        Login login = new Login(name, pass);

        try {
            //UserDAO クラスの findByLogin メソッドを呼び出し
            User loginUser = new UserDAO().findByLogin(login);

            //Login オブジェクトに対応するユーザー情報をデータベースから取得
            if (loginUser != null
                    && loginUser.getName().equals(name)
                    && loginUser.getPassword().equals(pass)) {

                //ユーザー名をリクエストにセット、LoginSuccess.jspにフォワード、ログイン成功画面へ(LoginSuccess.jsp)
                request.setAttribute("name", loginUser.getName());
                RequestDispatcher dispatcher = request.getRequestDispatcher("./LoginSuccess.jsp");
                dispatcher.forward(request, response);

                //LoginFalse.jsp にフォワード、ログイン失敗画面へ(LoginFalse.jsp)
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("./LoginFalse.jsp");
                dispatcher.forward(request, response);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
