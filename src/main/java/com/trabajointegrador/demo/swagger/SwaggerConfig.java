package com.trabajointegrador.demo.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Arrays;

//http://localhost:8080/swagger-ui/index.html#/

@Configuration
public class SwaggerConfig {
    private static final String TITLE = "INFORMATORIO: Api para obtener turnos";
    private static final String DESCRIPTION = "Nos han solicitado diseñar y desarrollar una API rest para poder sacar turnos para cualquier evento o motivo\n" +
            " que cualquier empresa  u organización cargue en el sistema.";

    @Configuration
    public class SwaggerConfiguration  {

        @Bean
        public OpenAPI customOpenAPI() {

            OpenAPI openApi = new OpenAPI();
            openApi.info(
                    new Info()
                            .title(TITLE)
                            .description(DESCRIPTION)
                            .contact(new Contact().name("Schmidt Fernando").
                                    url("https://github.com/fersch21").email("ferschmidt8@gmail.com"))
            );
            openApi.components(
                    new Components().addSecuritySchemes("bearer-jwt",
                            new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                                    .in(SecurityScheme.In.HEADER).name("Authorization"))
            );

            openApi.addSecurityItem(
                    new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write"))
            );
            return openApi;
        }
    }
}