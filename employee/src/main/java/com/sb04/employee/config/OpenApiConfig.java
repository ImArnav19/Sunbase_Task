package com.sb04.employee.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Arnav's Java Api Documentation",
                description = "Sunbase Task Api Docs with Schemas with Swagger implementation",
                version = "v1",
                contact = @Contact(
                        name = "Arnav More",
                        email = "morearnav019@gmail.com"
                )
        )
)
public class OpenApiConfig {


}
