package com.bank.transaction.infrastructure.config;


import com.bank.transaction.domain.model.Transaction;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Transaction Service API")
                        .version("1.0.0")
                        .description("""
                                API REST para la gestión de transacciones bancarias.
                                
                                Este servicio proporciona endpoints para crear, consultar y 
                                gestionar transacciones bancarias con soporte para múltiples 
                                mecanismos de autenticación incluyendo JWT y OAuth2.
                                """)
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("desarrollo@bank.com")
                                .url("https://www.bank.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("""
                                        Autenticación mediante token JWT.
                                        
                                        Para obtener un token, utilize el endpoint de autenticación
                                        y proporcione credenciales válidas.
                                        """))
                        .addSecuritySchemes("oauth2", new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .description("""
                                        Autenticación mediante OAuth2 con flujo de credenciales de cliente.
                                        
                                        Este esquema requiere configuración prévia del servidor de autorización.
                                        """)
                                .flows(new io.swagger.v3.oas.models.security.OAuthFlows()
                                        .clientCredentials(new io.swagger.v3.oas.models.security.OAuthFlow()
                                                .tokenUrl("/oauth/token")
                                                .scopes(new io.swagger.v3.oas.models.Scopes()
                                                        .addString("transaction:read", "Permiso para leer transacciones")
                                                        .addString("transaction:write", "Permiso para crear transacciones")
                                                        .addString("transaction:admin", "Permiso administrativo completo"))))));
    }
}