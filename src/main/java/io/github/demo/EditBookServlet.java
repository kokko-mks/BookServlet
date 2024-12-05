//タイトルリンク押下でここに来る
package io.github.demo;

import io.github.demo.DAO.Book;
import io.github.demo.DAO.BooksDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;

@WebServlet("/EditBookServlet")
public class EditBookServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    //getリクエストを処理
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        //リクエストからidを取得し、整数に変換
        int id = Integer.parseInt(request.getParameter("id"));

        //取得した本の情報をリクエストに設定
        Book book = new BooksDAO().getBookByID(id);

        request.setAttribute("id", book.getId());
        request.setAttribute("title", book.getTitle());
        request.setAttribute("author", book.getAuthor());
        request.setAttribute("publisher", book.getPublisher());
        request.setAttribute("publishDate", book.getPublishDate());

        //EditBook.jspに処理をフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("./EditBook.jsp");
        dispatcher.forward(request, response);
    }
}
