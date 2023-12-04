package ai.viceversa.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import ai.viceversa.demo.config.OpenApiFetchProperties;
import ai.viceversa.demo.config.OpenApiProperties;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties({
	OpenApiProperties.class,
	OpenApiFetchProperties.class
})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
