package org.agoncal.quarkus.microservices.book;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@ApplicationPath("/")
@OpenAPIDefinition(
    info = @Info(
        title = "Book Microservice",
        description = "This microservice create a new book",
        version = "1.0",
        contact = @Contact(
            name = "Zulhisham Tan",
            url = "az_zulhisham@live.com"
        )
    )
)
public class BookMicroservice extends Application {
    
}
