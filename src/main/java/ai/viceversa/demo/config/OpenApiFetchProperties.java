package ai.viceversa.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "openapi.fetch")
public record OpenApiFetchProperties(boolean disable, int titleFetchCount, int detailFetchCount) {
}
