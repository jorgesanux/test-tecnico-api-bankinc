package com.ex.novatech.bankinc.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("DEV Server");

        Contact contact = new Contact();
        contact.setEmail("jorgesanux1@gmail.com");
        contact.setName("Jorge Sanabria");

        Info info = new Info()
                .title("Bank Inc API")
                .version("1.0")
                .contact(contact)
                .description("Technical test of an API for the Bank Inc. exercise - Novatech|Crediban.")
                .termsOfService("https://github.com/jorgesanux/test-tecnico-api-bankinc");

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}