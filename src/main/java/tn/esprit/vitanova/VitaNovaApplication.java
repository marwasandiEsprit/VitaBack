package tn.esprit.vitanova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableAspectJAutoProxy
@EnableJpaRepositories
public class VitaNovaApplication {

    public static void main(String[] args) {
        SpringApplication.run(VitaNovaApplication.class, args);
    }

}
