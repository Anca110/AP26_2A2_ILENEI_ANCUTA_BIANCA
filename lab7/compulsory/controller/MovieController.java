package lab7.compulsory.controller;

import lab7.compulsory.dao.MovieDAO;
import lab7.compulsory.model.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {


    //daca vine request de la browser pe /movies atunci se apeleaza metoda asta
    @GetMapping("/movies")
    public List<Movie> getMovies() {
        MovieDAO movieDAO = new MovieDAO();
        return movieDAO.findAll();
    }
}