package com.example.BookManagement;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/books")
public class BookResource {
    BookDao bookDao = new BookDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooks() {
        return bookDao.getAllBooks();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("id") int id) {
        Book book = bookDao.getBook(id);
        if (book== null){
            return Response .status(Response.Status.BAD_REQUEST).entity("Book not found").build();
        }
        return Response.ok(book).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateBook(@PathParam("id") int id, Book book) {
        bookDao.updateBook(id, book);
    }

    @DELETE
    @Path("/{id}")
    public void deleteBook(@PathParam("id") int id) {
        bookDao.deleteBook(id);
    }
}
