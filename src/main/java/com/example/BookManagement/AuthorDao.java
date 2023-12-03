package com.example.BookManagement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class AuthorDao {
        public List<Author> getAllAuthors() {
            List<Author> authorList = new ArrayList<>();
            try (Connection connection = DBConnection.getConnection()) {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM authors");
                while (rs.next()) {
                    Author author = new Author(
                            rs.getInt("author_id"),
                            rs.getString("author_name"));


                    authorList.add(author);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return authorList;
        }

        public Author getAuthor(int id) {
            try (Connection connection = DBConnection.getConnection()) {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM authors WHERE author_id = ?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return new Author(
                            rs.getInt("author_id"),
                            rs.getString("author_name"));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public void addAuthor(Author author) {
            try (Connection connection = DBConnection.getConnection()) {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO authors (author_id, author_name) VALUES (?, ?)");
                ps.setInt(1, author.getId());
                ps.setString(2, author.getName());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void updateAuthor(int id, Author author) {
            try (Connection connection = DBConnection.getConnection()) {
                PreparedStatement ps = connection.prepareStatement("UPDATE authors SET author_name = ? WHERE author_id = ?");
                ps.setInt(2, author.getId());
                ps.setString(1, author.getName());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void deleteAuthor(int id) {
            try (Connection connection = DBConnection.getConnection()) {
                PreparedStatement ps = connection.prepareStatement("DELETE FROM authors WHERE author_id = ?");
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


