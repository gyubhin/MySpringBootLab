package com.rookies4.myspringbootlab.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 100)
    private String author;

    @Column(nullable = false, unique = true, length = 20)
    private String isbn;

    @Column(nullable = false)
    private Integer price;

    @Column(name = "publish_date", nullable = false)
    private LocalDate publishDate;

    @OneToOne(mappedBy = "book",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private BookDetail detail;

    public Book() {}

    public Book(String title, String author, String isbn, Integer price, LocalDate publishDate) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.publishDate = publishDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }

    public LocalDate getPublishDate() { return publishDate; }
    public void setPublishDate(LocalDate publishDate) { this.publishDate = publishDate; }

    public BookDetail getDetail() { return detail; }

    public void setDetail(BookDetail newDetail) {
        if (this.detail == newDetail) return;

        if (this.detail != null) {
            this.detail.setBook(null);
        }
        this.detail = newDetail;

        if (newDetail != null) {
            newDetail.setBook(this);
        }
    }
}