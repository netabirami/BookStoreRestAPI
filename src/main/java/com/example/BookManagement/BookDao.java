package com.example.BookManagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT b.id as bookId, b.title as title, b.price as price, b.quantity as quantity, " +
                    "a.author_id as authorId, a.author_name as name" +
                    " FROM books b INNER JOIN authors a on a.author_id=b.author_id");
            while (rs.next()) {
                Author author = new Author(rs.getInt("authorId"), rs.getString("name"));
                Book book = new Book(
                        rs.getInt("bookId"),
                        rs.getString("title"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        author);
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public Book getBook(int id) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT b.id as bookId, b.title as title, b.price as price, b.quantity as quantity, " +
                    "a.id as authorId, a.name as name" +
                    " FROM books b INNER JOIN authors a on a.id=b.author_id WHERE b.id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Author author = new Author(rs.getInt("authorId"), rs.getString("name"));
                return new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        author
                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addBook(Book book) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO books(id,title, price, quantity,author_id ) VALUES(?, ?, ?, ?,?) ");
            ps.setInt(1, book.getId());
            ps.setString(2, book.getTitle());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4, book.getQuantity());
            ps.setInt(5, book.getAuthor().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBook(int id, Book book) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE books SET title = ?, price = ? ,quantity = ?, author_id = ? WHERE id = ?");
            ps.setInt(5, book.getId());
            ps.setString(1, book.getTitle());
            ps.setDouble(2, book.getPrice());
            ps.setInt(3, book.getQuantity());
            ps.setInt(4, book.getAuthor().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int id) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM books WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
