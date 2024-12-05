package io.github.demo.DAO;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private Date publishDate;

    //デフォルトコンストラクタ
    public Book() {}

    //初期化してbookを生成
    public Book(int id, String title, String author, String publisher, Date publishedDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishedDate;
    }

    //新規登録はこっち
    public Book(String title, String author, String publisher, Date publishedDate) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishedDate;
    }

    //各セッター、外部から値を設定する
    public void setId(int id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setAuthor(String author) {this.author = author;}
    public void setPublisher(String publisher) {this.publisher = publisher;}
    public void setPublishedDate(Date publishedDate) {this.publishDate = publishDate;}

    //各ゲッター、外部から値を取得
    public int getId() {return id;}
    public String getTitle() {return title;}
    public String getAuthor() {return author;}
    public String getPublisher() {return publisher;}
    public Date getPublishDate() {return publishDate;}
}
