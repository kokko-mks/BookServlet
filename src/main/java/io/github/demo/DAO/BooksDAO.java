package io.github.demo.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BooksDAO {
    //データベースに接続するための情報
    private final String url = "jdbc:mysql://database:3306/Book";
    private final String user = "root";
    private final String pass = "abc123";

    //全ての本を取得
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            Class.forName("com.mysql.cj.jdbc.Driver"); //JDBCドライバ
            //SQL
            PreparedStatement pst = conn.prepareStatement("SELECT ID, TITLE, AUTHOR, PUBLISHER, PUBLISH_DATE FROM BOOKS");
            ResultSet rs = pst.executeQuery();

            //結果からデータを受け取り、bookとしてリストに追加
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
            //JDBCドライバエラー
            throw new IllegalStateException("JDBCドライバを読み込めませんでした。");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    //IDで本の情報を取得
    public Book getBookByID(int ID) {
        Book book = null;

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement pst = conn.prepareStatement("SELECT ID, TITLE, AUTHOR, PUBLISHER, PUBLISH_DATE FROM BOOKS WHERE ID = ?");
            pst.setInt(1, ID);
            ResultSet rs = pst.executeQuery();

            //該当する本があれば、bookを作成
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

    //タイトルで本を検索
    public List<Book> getBookByTitle(String bookTitle) {
        List<Book> books = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, pass);) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement pst = conn.prepareStatement("SELECT ID, TITLE, AUTHOR, PUBLISHER, PUBLISH_DATE FROM BOOKS WHERE TITLE = ?");
            pst.setString(1, bookTitle);
            ResultSet rs = pst.executeQuery();

            //一致する本をリストに追加
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

    //著者で本を検索
    public List<Book> getBookByAuthor(String bookAuthor) {
        List<Book> books = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, pass);) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement pst = conn.prepareStatement("SELECT ID, TITLE, AUTHOR, PUBLISHER, PUBLISH_DATE FROM BOOKS WHERE AUTHOR = ?");
            pst.setString(1, bookAuthor);
            ResultSet rs = pst.executeQuery();

            //一致する本をリストに追加
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

    //出版社で本をを検索する
    public List<Book> getBookByPublisher(String bookPublisher) {
        List<Book> books = new ArrayList<>();

    try(Connection conn = DriverManager.getConnection(url, user, pass);) {
        Class.forName("com.mysql.cj.jdbc.Driver");
        PreparedStatement pst = conn.prepareStatement("SELECT ID, TITLE, AUTHOR, PUBLISHER, PUBLISH_DATE FROM BOOKS WHERE PUBLISHER = ?");
        pst.setString(1, bookPublisher);
        ResultSet rs = pst.executeQuery();

        //一致する本をリストに追加
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

    //新しい本を追加
    public void calendar(Book book) {
        try(Connection conn = DriverManager.getConnection(url,user,pass)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement pst = conn.prepareStatement("INSERT INTO BOOKS(TITLE, AUTHOR, PUBLISHER, PUBLISH_DATE) VALUES(?, ?, ?, ?)");
            pst.setString(1, book.getTitle());
            pst.setString(2, book.getAuthor());
            pst.setString(3, book.getPublisher());

            //Data型をSQL用に変換
            java.sql.Date sqlD = new java.sql.Date(book.getPublishDate().getTime());
            pst.setDate(4, sqlD);
            pst.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //本の情報を更新
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

    //本を削除
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