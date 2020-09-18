package com.ataraxia.gabriel_vz

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.config.EnableIntegration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@Configuration
@EnableIntegration
@EnableSwagger2
class GabrielVzConfig {

    @Bean
    fun swaggerConfiguration(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.ataraxia.gabriel_vz"))
                .build()
                .apiInfo(apiDetails())
    }

    private fun apiDetails(): ApiInfo {
        return ApiInfo(
                "Wolfgang Gabriel Verzeichnis API",
                "This Api is part of the Master-Thesis: Digital Thematic Catalogues - Software Architecture and Reference Implementation",
                "1.0",
                "Free to use",
                springfox.documentation.service.Contact("Kopp, Maximilian", "ataraxiatech.com", "m.kopp89@web.de"),
                "MIT License",
                "https://opensource.org/licenses/MIT",
                Collections.emptyList()
        )
    }
}