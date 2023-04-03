package org.agoncal.quarkus.microservices.number;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@ApplicationPath("/")
@OpenAPIDefinition(
    info = @Info(
        title = "Number Microservice",
        description = "This microservice generate book ISBN number",
        version = "1.0",
        contact = @Contact(
            name = "Zulhisham Tan",
            url = "az_zulhisham@live.com"
        )
    )
)
public class NumberMicroservice extends Application {

}