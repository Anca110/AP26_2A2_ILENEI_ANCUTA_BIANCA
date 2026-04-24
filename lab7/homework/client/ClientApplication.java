package lab7.homework.client;

import lab7.homework.model.Movie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication(
        scanBasePackages = "lab7.homework",
        excludeName = "org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration"
)
public class ClientApplication {
    public static void main(String[] args){
        new SpringApplicationBuilder(ClientApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Bean
    public RestClient restClient(){
        return RestClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
    }

    @Bean
    public CommandLineRunner runClient(RestClient restClient) {
        return args -> {
            System.out.println("Clientul a pornit");

            //GET
            Movie[] movies = restClient.get()
                    .uri("/movies")
                    .retrieve()
                    .body(Movie[].class);

            if (movies != null) {
                for (Movie movie : movies) {
                    System.out.println(movie);
                }
            }

            //POST
            String postResult = restClient.post()
                    .uri("/movies?title=Interstellar&year=2014&duration=169&soundtrack=HansZimmer&genreId=1")
                    .retrieve()
                    .body(String.class);
            System.out.println(postResult);

            //PUT
            String putResult = restClient.put()
                    .uri("/movies?id=1&title=MaleficientUpdated&year=2014&duration=97&soundtrack=sound1&genreId=1")
                    .retrieve()
                    .body(String.class);
            System.out.println(putResult);

            //PATCH
            String patchResult = restClient.patch()
                    .uri("/movies?id=1&score=8.5")
                    .retrieve()
                    .body(String.class);
            System.out.println(patchResult);

            //DELETE
            String deleteResult = restClient.delete()
                    .uri("/movies?id=8")
                    .retrieve()
                    .body(String.class);
            System.out.println(deleteResult);
        };
    }
}