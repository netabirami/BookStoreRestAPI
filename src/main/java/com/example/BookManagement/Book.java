package com.example.BookManagement;

public class Book {
    private int id;
    private String title;
    private double price;
    private int quantity;
    private Author author;

    public Book(int id, String title, double price, int quantity, Author author) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    public Book() {
    }
}
