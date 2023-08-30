package br.com.hogwarts.artifactsonline;

import br.com.hogwarts.artifactsonline.artifact.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ArtifactsOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtifactsOnlineApplication.class, args);
	}

	@Bean
	public IdWorker idWorker() {
		return new IdWorker(1, 1);
	}

}
