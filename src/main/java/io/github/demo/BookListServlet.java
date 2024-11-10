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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/BookListServlet")
public class BookListServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        List<Book> books = new BooksDAO().getAllBooks();
        request.setAttribute("books", books);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/BookList.jsp");
        dispatcher.forward(request, response);

    }

    protected void  doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");

        if ((title == null || title.isEmpty()) &&
                (author == null || author.isEmpty()) &&
                (publisher == null || publisher.isEmpty())) {

            request.setAttribute("errorMsg", "検索条件を入力してください。");
            request.setAttribute("books", new BooksDAO().getAllBooks());

            RequestDispatcher dispatcher = request.getRequestDispatcher("/BookList.jsp");
            dispatcher.forward(request, response);
            return;
        }

        BooksDAO booksDAO = new BooksDAO();
        List<Book> books = new ArrayList<>();

        if (title != null && !title.isEmpty()) {
            books = booksDAO.getBookByTitle(title);
        } else if (author != null && !author.isEmpty()) {
            books = booksDAO.getBookByAuthor(author);
        } else {
            books = booksDAO.getBookByPublisher(publisher);
        }

        request.setAttribute("books", books);

        request.setAttribute("title", title);
        request.setAttribute("author", author);
        request.setAttribute("publisher", publisher);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/BookList.jsp");
        dispatcher.forward(request, response);
    }

}
