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

    //getリクエストの処理
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        //BooksDAOを利用しすべての本の情報を取得
        List<Book> books = new BooksDAO().getAllBooks();

        //取得した本のリストをリクエストスコープに保存
        request.setAttribute("books", books);

        //BookList.jspへフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/BookList.jsp");
        dispatcher.forward(request, response);

    }

    //POSTリクエストの処理
    protected void  doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        //リクエストから検索条件を取得
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");

        //検索条件がすべて未入力の場合の処理
        if ((title == null || title.isEmpty()) &&
                (author == null || author.isEmpty()) &&
                (publisher == null || publisher.isEmpty())) {

            //エラーメッセージをリクエストスコープに設定
            request.setAttribute("errorMsg", "検索条件を入力してください。");

            //全ての本を取得、リクエストスコープに保存
            request.setAttribute("books", new BooksDAO().getAllBooks());

            //BookList.jspへフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("/BookList.jsp");
            dispatcher.forward(request, response);
            return;
        }

        //BooksDAOを利用して本を検索
        BooksDAO booksDAO = new BooksDAO();
        List<Book> books = new ArrayList<>();

        //タイトルで検索
        if (title != null && !title.isEmpty()) {
            books = booksDAO.getBookByTitle(title);

        //著者で検索
        } else if (author != null && !author.isEmpty()) {
            books = booksDAO.getBookByAuthor(author);

        //出版社で検索
        } else {
            books = booksDAO.getBookByPublisher(publisher);
        }

        //検索結果をリクエストスコープに保存
        request.setAttribute("books", books);

        //検索条件もリクエストスコープに保存して再表示に利用
        request.setAttribute("title", title);
        request.setAttribute("author", author);
        request.setAttribute("publisher", publisher);

        //BookList.jspへフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/BookList.jsp");
        dispatcher.forward(request, response);
    }

}
