package lab7.homework.client;


import lab7.homework.model.Movie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

//partea care foloseste serviciile; trimite requesturi la server, primeste raspunsuri si le afiseaza
@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args){
        new SpringApplicationBuilder(ClientApplication.class)
                //SpringApplicationBuilder e o clasa din Spring Boot care te ajuta sa configurezi cum pornesti aplicatia
                //ClientApplication.class ii dau lui Spring Boot informatii despre clasa asta
                //ca sa stie care i clasa principala si de unde sa inceapa cu scanarea pachetelor
                .web(WebApplicationType.NONE)
                //aplicatia asta nu e server web, nu sta sa astepte requesturi adc nu asculta pe port , doar ruleaza ca aplicatie de consola
                .run(args);//porneste aplicatia
    }

    //@Bean un obiect creat s administrat de Spring; la fel si clasele marcate cu @ devin tot bean uri
    //Spring va apela metoda, va lua obiectul returnat, il va pastra, il va putea da altor componente care vor avea nevoie de el;
    @Bean
    public RestClient restClient(){//RestClient tip metoda; restClient nume metoda; bean ul va avea si el numele metodei adc restClient
        //RestClient e un client HTTP oferit de Spring
        // e un obiect care stie sa faca request uri GET,...
        return RestClient.builder()//porneste proiesul de construire a clientului
                .baseUrl("http://localhost:8081")
                //toate requesturile vor porni de la acest server
                //deci mai tz cand scriu .uri("/movies") el va intelege http://localhost:8081/movies
                .build();//finalizeaza constructia si returneaza obiectul RestClient
    }

    @Bean
    //metoda asta creaza un obiect aministrat de Spring
    public CommandLineRunner runClient(RestClient restClient) {
        //orice bean de tip CommanLinner este executat automat dupa ce aplicatia s a pornit
        //contextul Spring s a creat, bean urile exista, apoi Sprin ruleaza codul de aici;

        //(RestClient restClient) aici sprin injecteaza
        //spun ca metoda asta are nevoie de un RestClient; daca exista un bean de tipul asta( creat mai sus)=> Spring il ia si il da metodei
        //nu mai faci manual  RestClient rc = new RestClient(...);  Spring ia singur

        return args -> {
            System.out.println("Clientul a pornit");

            //apelul HTTP
            //dclar o variabila arry de filme

            //GET
            Movie[] movies = restClient.get()//vr request de tip GET
                    .uri("/movies")//la endpointul /movies
                    .retrieve()//trimite requestul si ia raspunsul
                    .body(Movie[].class);//raspunsul primit e o lista de filme, ca JSON; transforma l in tipul java: Movie[]
            //.class  adc obiectul de tip class care descrie clasa Movie

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
