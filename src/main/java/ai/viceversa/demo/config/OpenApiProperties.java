package ai.viceversa.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "openapi")
public record OpenApiProperties(String secretKey, String dataType, int timeout) {
}
