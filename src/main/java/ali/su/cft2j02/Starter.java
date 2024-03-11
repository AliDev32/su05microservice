package ali.su.cft2j02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication(scanBasePackages = "ali.su.cft2j02")
public class Starter {
    public static void main(String[] args) {
        var ctx = SpringApplication.run(Starter.class);

    }
}
