package br.com.querino.bancodigital.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
        .info(
            new Info()
                .title("Banco Digital API")
                .version("1.0.0")
                .description("""
                    API REST para gerenciamento de usuários,
                    contas bancárias, transferências e chaves Pix.
                    """)
                .contact(
                    new Contact()
                        .name("Caio Querino")
                        .email("caioquerino@souunisuam.com.br")
                )
                .license(
                    new License()
                        .name("MIT")
                )
        );
    }
}