package ali.su.cft2j02;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class Starter {
    public static void main(String[] args) {
        var ctx = SpringApplication.run(Starter.class, args);
    }
    @Bean
    public ApplicationRunner run() {
        return args -> log.info("Service is working!!!");
    }
}
