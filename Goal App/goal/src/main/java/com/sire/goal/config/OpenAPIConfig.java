package com.sire.goal.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenAPIConfig {
    @Value("${kristine.openapi.dev-url}")
    private String devUrl;

//    private SecurityScheme createAPIKeyScheme() {
//        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
//                .bearerFormat("JWT")
//                .scheme("bearer");
//    }

    @Bean
    public OpenAPI openAPI() {
        Server server = new Server();
        server.setUrl(devUrl);
        server.setDescription("Dev URL.");

        Contact contact = new Contact();
        contact.setName("Desire");
        contact.setUrl("https://github.com/GuanKid");

        Info info = new Info()
                .title("Goal Setter")
                .version("1.0")
                .contact(contact)
                .description("This endpoint sets goals and performs CRUD operations on them");

        return new OpenAPI()
//                .addSecurityItem(new SecurityRequirement()
//                        .addList("Bearer Authentication"))
//                .components(new Components().addSecuritySchemes
//                        ("Bearer Authentication", createAPIKeyScheme()))
                .info(info).servers(List.of(server));
    }
}
