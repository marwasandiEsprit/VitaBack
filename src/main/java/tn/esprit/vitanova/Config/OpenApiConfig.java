package tn.esprit.vitanova.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String SCHEME_NAME = "token";
    private static final String SCHEME = "bearer";


    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .name(SCHEME_NAME)
                .type(SecurityScheme.Type.HTTP)
                .scheme(SCHEME);
    }


    @Bean
    public OpenAPI myOpenAPI() {

        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes(SCHEME_NAME, createSecurityScheme())
                )
                .addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME));

    }


}