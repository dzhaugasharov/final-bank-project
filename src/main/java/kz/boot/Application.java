package kz.boot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = "kz.boot.*")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
