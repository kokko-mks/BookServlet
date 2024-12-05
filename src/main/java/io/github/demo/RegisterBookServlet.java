package io.github.demo;

import io.github.demo.DAO.Book;
import io.github.demo.DAO.BooksDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.Serial;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/RegisterBookServlet")
public class RegisterBookServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //フォームから送信されたデータを取得
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String publishDateStr = request.getParameter("publish_date"); //文字列

        //入力エラーを検出するための
        boolean errors = false;

        //タイトルが未入力の場合のエラーチェック
        if(title == null || title.isEmpty()){
            request.setAttribute("titleError", "タイトルが入力されていません");
            errors = true;
        }

        //著者が未入力の場合のエラーチェック
        if(author == null || author.isEmpty()){
            request.setAttribute("authorError", "著者が入力されていません");
            errors = true;
        }

        //出版社が未入力の場合のエラーチェック
        if(publisher == null || publisher.isEmpty()){
            request.setAttribute("publisherError", "出版社が入力されていません");
            errors = true;
        }

        //出版日が未入力の場合のエラーチェック
        if(publishDateStr == null || publishDateStr.isEmpty()){
            request.setAttribute("publishDateStrError", "出版日が入力されていません");
            errors = true;
        }

        //入力エラーがある場合の処理
        if (errors) {
            //ユーザーが入力した内容を再表示するためにリクエストスコープに設定
            request.setAttribute("title", title);
            request.setAttribute("author", author);
            request.setAttribute("publisher", publisher);
            request.setAttribute("publishDateStr", publishDateStr);

            //エラーがある場合は再度登録画面表示
            RequestDispatcher dispatcher = request.getRequestDispatcher("./RegisterBook.jsp");
            dispatcher.forward(request, response);
            return;
        }

        //出版日を文字列からData型に変換
        Date date;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateFormat.parse(publishDateStr);

            //日付の変換に失敗した場合
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        //book作成
        Book book = new Book(title, author, publisher, date);

        //DAOを利用してデータベースに登録
        BooksDAO booksDAO = new BooksDAO();
        booksDAO.calendar(book);

        //蔵書委一覧画面にリダイレクト
        response.sendRedirect("./BookListServlet");

    }
}