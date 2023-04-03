package org.agoncal.quarkus.microservices.book;

import java.time.Instant;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@Path("/api/books")
public class BookResource {

    @Inject
    @RestClient
    NumberProxy proxy;

    @Inject
    Logger logger;

    @Retry(maxRetries = 3, delay = 3000)
    @Fallback(
        fallbackMethod = "fallbackOnCreateNewBook"
    )
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createNewBook(@FormParam("title") String title, @FormParam("author") String author, @FormParam("yearOfPublication") int yearOfPublication, @FormParam("genre") String genre) {
        
        Book book = new Book();
        book.isbn13 = proxy.generateIsbnNumbers().isbn13;
        book.author = author;
        book.genre = genre;
        book.title = title;
        book.yearOfPublication = yearOfPublication;
        book.creationDate = Instant.now();

        logger.info("New Book Created: " + book);
        return Response.status(201).entity(book).build();
    }

    public Response fallbackOnCreateNewBook(@FormParam("title") String title, @FormParam("author") String author, @FormParam("yearOfPublication") int yearOfPublication, @FormParam("genre") String genre) {
        
        Book book = new Book();
        book.isbn13 = "";
        book.author = author;
        book.genre = genre;
        book.title = title;
        book.yearOfPublication = yearOfPublication;
        book.creationDate = Instant.now();

        logger.warn("New Book Partially Created: " + book);
        return Response.status(206).entity(book).build();
    }
}