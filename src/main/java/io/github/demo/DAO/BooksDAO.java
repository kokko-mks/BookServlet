package io.github.demo.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BooksDAO {
    private final String url = "jdbc:mysql://database:3306/Book";
    private final String user = "root";
    private final String pass = "abc123";

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement pst = conn.prepareStatement("SELECT ID, TITLE, AUTHOR, PUBLISHER, PUBLISH_DATE FROM BOOKS");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String author = rs.getString("AUTHOR");
                String publisher = rs.getString("PUBLISHER");
                Date publishDate = rs.getDate("PUBLISH_DATE");

                Book book = new Book(id,title,author,publisher,publishDate);
                books.add(book);
            }

        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした。");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Book getBookByID(int ID) {
        Book book = null;

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement pst = conn.prepareStatement("SELECT ID, TITLE, AUTHOR, PUBLISHER, PUBLISH_DATE FROM BOOKS WHERE ID = ?");
            pst.setInt(1, ID);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String author = rs.getString("AUTHOR");
                String publisher = rs.getString("PUBLISHER");
                Date publishDate = rs.getDate("PUBLISH_DATE");

                book = new Book(id, title, author, publisher, publishDate);
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public List<Book> getBookByTitle(String bookTitle) {
        List<Book> books = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, pass);) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement pst = conn.prepareStatement("SELECT ID, TITLE, AUTHOR, PUBLISHER, PUBLISH_DATE FROM BOOKS WHERE TITLE = ?");
            pst.setString(1, bookTitle);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String author = rs.getString("AUTHOR");
                String publisher = rs.getString("PUBLISHER");
                Date publishDate = rs.getDate("PUBLISH_DATE");

                Book book = new Book(id, title, author, publisher, publishDate);
                books.add(book);
            }

        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> getBookByAuthor(String bookAuthor) {
        List<Book> books = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, pass);) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement pst = conn.prepareStatement("SELECT ID, TITLE, AUTHOR, PUBLISHER, PUBLISH_DATE FROM BOOKS WHERE AUTHOR = ?");
            pst.setString(1, bookAuthor);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String author = rs.getString("AUTHOR");
                String publisher = rs.getString("PUBLISHER");
                Date publishDate = rs.getDate("PUBLISH_DATE");

                Book book = new Book(id, title, author, publisher, publishDate);
                books.add(book);

            }

        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> getBookByPublisher(String bookPublisher) {
        List<Book> books = new ArrayList<>();

    try(Connection conn = DriverManager.getConnection(url, user, pass);) {
        Class.forName("com.mysql.cj.jdbc.Driver");
        PreparedStatement pst = conn.prepareStatement("SELECT ID, TITLE, AUTHOR, PUBLISHER, PUBLISH_DATE FROM BOOKS WHERE PUBLISHER = ?");
        pst.setString(1, bookPublisher);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("ID");
            String title = rs.getString("TITLE");
            String author = rs.getString("AUTHOR");
            String publisher = rs.getString("PUBLISHER");
            Date publishDate = rs.getDate("PUBLISH_DATE");

            Book book = new Book(id, title, author, publisher, publishDate);
            books.add(book);

        }
    } catch(ClassNotFoundException e) {
        throw new IllegalStateException("JDBCドライバを読み込めませんでした");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return books;
}

public void calendar(Book book) {
        try(Connection conn = DriverManager.getConnection(url,user,pass)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement pst = conn.prepareStatement("INSERT INTO BOOKS(TITLE, AUTHOR, PUBLISHER, PUBLISH_DATE) VALUES(?, ?, ?, ?)");
            pst.setString(1, book.getTitle());
            pst.setString(2, book.getAuthor());
            pst.setString(3, book.getPublisher());

            java.sql.Date sqlD = new java.sql.Date(book.getPublishDate().getTime());
            pst.setDate(4, sqlD);
            pst.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBook(Book book) {
        try(Connection conn = DriverManager.getConnection(url,user,pass)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement pst = conn.prepareStatement("UPDATE BOOKS SET TITLE = ?, AUTHOR = ?, PUBLISHER = ?, PUBLISH_DATE = ? WHERE ID = ?");
            pst.setString(1, book.getTitle());
            pst.setString(2, book.getAuthor());
            pst.setString(3, book.getPublisher());

            java.sql.Date sqlD = new java.sql.Date(book.getPublishDate().getTime());
            pst.setDate(4, sqlD);
            pst.setInt(5, book.getId());
            pst.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int id) {
        try(Connection conn = DriverManager.getConnection(url,user,pass)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement pst = conn.prepareStatement("DELETE FROM BOOKS WHERE ID = ?");
            pst.setInt(1,id);
            pst.execute();

        }catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}