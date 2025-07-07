package drtx.cleanArchitecture.example.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("Clean Architecture Example API")
                        .version("v1.0.0")
                        .description("Clean Architecture Example API")
                        .contact(new Contact()
                                .name("DRTX2")
                                .email("davidmanjarres2004@gmail.com")
                                .url("miBuenaPagina.com")
                        )
                );
    }
    /*
    * http://localhost:8080/swagger-ui.html  -> Swagger UI
    * http://localhost:8080/v3/api-docs  -> OpenAPI JSON
     * */
}
