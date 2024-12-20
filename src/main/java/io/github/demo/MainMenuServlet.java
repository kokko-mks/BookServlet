//MainMenuServlet にアクセスしたときに、MainMenu.jsp ページを表示する

package io.github.demo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;

//アクセスすると、このサーブレットが動作
@WebServlet("/MainMenuServlet")
public class MainMenuServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //JSPページにリクエスト
        RequestDispatcher dispatcher = request.getRequestDispatcher("/MainMenu.jsp");
        dispatcher.forward(request,response);
    }
}
