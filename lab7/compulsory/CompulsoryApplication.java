package lab7.compulsory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompulsoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompulsoryApplication.class, args);
    }

    //aici spring boot porneste un sever web (tomcat), adc creez un mini serevr pe http://localhost:8081
    //metoda run imi scaneaza tot proiectul meu, cauta @RestController, @GetMapping...
    //sta si astepta request de la browser
    //acum cand intru in http://localhost:8081/movies

}
