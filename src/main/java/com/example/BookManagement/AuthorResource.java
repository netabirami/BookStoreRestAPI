package com.example.BookManagement;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

    @Path("/authors")
    public class AuthorResource {
        AuthorDao authorDao = new AuthorDao();

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<Author> getAllAuthors() {
            return authorDao.getAllAuthors();
        }

        @GET
        @Path("/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getAuthorById(@PathParam("id") int authorId) {
            Author author = authorDao.getAuthor(authorId);
            if (author == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Author not found").build();
            }
            return Response.ok(author).build();
        }

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        public void addAuthor(Author author) {
            authorDao.addAuthor(author);
        }

        @PUT
        @Path("/{id}")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response updateAuthor(@PathParam("id") int id, Author author) {
            Author existingAuthor = authorDao.getAuthor(id);

            if (existingAuthor == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Author not found").build();
            }

            // Update author details
            existingAuthor.setName(author.getName());

            // Call the update method in AuthorDao
            authorDao.updateAuthor(id, existingAuthor);

            return Response.ok().build();
        }
        @DELETE
        @Path("/{id}")
        public void deleteAuthor(@PathParam("id") int id) {
            authorDao.deleteAuthor(id);
        }


        // You can add more methods like updateAuthor, deleteAuthor, etc., based on your requirements
    }


