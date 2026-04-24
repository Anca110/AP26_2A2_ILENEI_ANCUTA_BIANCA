package lab7.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = "lab7.homework",
        excludeName = "org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration"
)
public class HomeworkApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomeworkApplication.class, args);
    }
}