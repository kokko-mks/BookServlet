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
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String publishDateStr = request.getParameter("publish_date");

        boolean errors = false;

        if(title == null || title.isEmpty()){
            request.setAttribute("titleError", "タイトルが入力されていません");
            errors = true;
        }

        if(author == null || author.isEmpty()){
            request.setAttribute("authorError", "著者が入力されていません");
            errors = true;
        }

        if(publisher == null || publisher.isEmpty()){
            request.setAttribute("publisherError", "出版社が入力されていません");
            errors = true;
        }

        if(publishDateStr == null || publishDateStr.isEmpty()){
            request.setAttribute("publishDateStrError", "出版日が入力されていません");
            errors = true;
        }

        if (errors) {
            request.setAttribute("title", title);
            request.setAttribute("author", author);
            request.setAttribute("publisher", publisher);
            request.setAttribute("publishDateStr", publishDateStr);

            RequestDispatcher dispatcher = request.getRequestDispatcher("./RegisterBook.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Date date;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateFormat.parse(publishDateStr);

        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        Book book = new Book(title, author, publisher, date);
        BooksDAO booksDAO = new BooksDAO();
        booksDAO.calendar(book);

        response.sendRedirect("./BookListServlet");

    }
}