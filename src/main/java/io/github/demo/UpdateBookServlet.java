//蔵書を更新
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    //postリクエストを処理
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //リクエストパラメータから値を取得
        String strId = request.getParameter("id");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String publishDateStr = request.getParameter("publish_date");

        boolean errors = false;

        //タイトルが未入力化かチェック
        if (title == null || title.isEmpty()) {
            request.setAttribute("titleError", "タイトルが入力されていません");
            errors = true;
        }

        //著者が未入力化かチェック
        if (author == null || author.isEmpty()) {
            request.setAttribute("authorError", "著者が入力されていません");
            errors = true;
        }

        //出版社が未入力化かチェック
        if (publisher == null || publisher.isEmpty()) {
            request.setAttribute("publisherError", "出版社が入力されていません");
            errors = true;
        }

        //出版日が未入力化かチェック
        if (publishDateStr == null || publishDateStr.isEmpty()) {
            request.setAttribute("publishDateStrError", "出版日が入力されていません");
            errors = true;
        }

        //入力エラーがある場合
        if (errors) {

            //本の値をリクエストに設定してフォームに戻す
            request.setAttribute("id", strId);
            request.setAttribute("title", title);
            request.setAttribute("author", author);
            request.setAttribute("publisher", publisher);
            request.setAttribute("publishDate", publishDateStr);

            //EditBook.jspにフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("./EditBook.jsp");
            dispatcher.forward(request, response);
            return;
        }

        //idを正数に変換
        int id = 0;
        if (strId != null && !strId.isEmpty()) {
            id = Integer.parseInt(strId);
        }

        //日をData型に変換
        Date date;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateFormat.parse(publishDateStr);

        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        //book作成
        Book book = new Book(id, title, author, publisher, date);

        //DAOをインスタンス化
        BooksDAO booksDAO = new BooksDAO();
        booksDAO.updateBook(book);

        //蔵書一覧画面にリダイレクト
        response.sendRedirect("./BookListServlet");
    }
}
