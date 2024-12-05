//蔵書を削除
package io.github.demo;

import io.github.demo.DAO.BooksDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;

@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    //getリクエストを処理
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //リクエストから削除対象のIDを取得
        String strId = request.getParameter("id");

        int id = 0;

        //idがnullもしくは空ではない場合、整数に変換
        if (strId != null && !strId.isEmpty()) {
            id = Integer.parseInt(strId); //文字列を整数に変換
        }

        //DAOを利用してデータベースから本を削除
        new BooksDAO().deleteBook(id);

        //削除後、蔵書一覧画面にリダイレクト
        response.sendRedirect("./BookListServlet");
    }
}
