package lab7.homework.controller;

import lab7.homework.dao.MovieDAO;
import lab7.homework.model.Movie;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {


    @GetMapping("/movies")
    public List<Movie> getMovies() {
        MovieDAO movieDAO = new MovieDAO();
        return movieDAO.findAll();
    }


    @PostMapping("/movies")
    public String createMovie(@RequestParam String title, @RequestParam int year, @RequestParam int duration, @RequestParam String soundtrack, @RequestParam int genreId) {
        MovieDAO movieDAO = new MovieDAO();
        movieDAO.create(title, year, duration, soundtrack, genreId);

        return "Filmul a fost adăugat";
    }

    @PutMapping("/movies")
    public String updateMovie(@RequestParam int id, @RequestParam String title, @RequestParam int year, @RequestParam int duration, @RequestParam String soundtrack, @RequestParam int genreId){

        MovieDAO movieDAO = new MovieDAO();
        movieDAO.update(id, title, year, duration, soundtrack, genreId);

        return "Filmul a fost modificat";
    }

    @PatchMapping("/movies")
    public String patchMovieScore(@RequestParam int id, @RequestParam double score){

        MovieDAO movieDAO = new MovieDAO();
        boolean updated = movieDAO.updateScore(id, score);

        if (updated){
            return "scorul filmului a fost modificat";
        }
        else{
            return "Nu exista film cu acest id";
        }
    }

    @DeleteMapping("/movies")
    public String deleteMovie(@RequestParam int id){

        MovieDAO movieDAO = new MovieDAO();
        boolean deleted = movieDAO.delete(id);

        if (deleted){
            return "Filmul a fost sters";
        } else {
            return "Nu exista film cu acest id";
        }
    }

}