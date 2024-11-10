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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");

        int id = 0;
        if (strId != null && !strId.isEmpty()) {
            id = Integer.parseInt(strId);
        }
        new BooksDAO().deleteBook(id);

        response.sendRedirect("./BookListServlet");
    }
}
