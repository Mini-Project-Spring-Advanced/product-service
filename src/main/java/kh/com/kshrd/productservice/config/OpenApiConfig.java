package kh.com.kshrd.productservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product Service API")
                        .version("1.0.0")
                        .description("""
                                Endpoints for managing product categories owned by users.
                                Supports create/read/update/delete operations, listing and searching products,
                                and exposing category metadata used by the product and order flows.
                                """)
                        .contact(new Contact()
                                .name("KSGA")
                                .email("ksga@gmail.com"))
                        .license(new License()
                                .name("KSGA 2.0")
                                .url("https://www.ksga.org/licenses/LICENSE-2.0"))
                )
                .addServersItem(new Server().url("/"))
                .components(new Components().addSecuritySchemes("mini-project",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                ));
    }

}
