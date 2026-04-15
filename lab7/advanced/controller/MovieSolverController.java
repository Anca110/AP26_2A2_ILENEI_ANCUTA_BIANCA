package lab7.advanced.controller;

import lab7.advanced.dao.MovieDAO;
import lab7.advanced.model.Movie;
import lab7.advanced.model.MovieList;
import lab7.advanced.solver.MovieSolver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieSolverController {

    @GetMapping("/solve")
    public List<MovieList> solve(@RequestParam int minSize) {

        MovieDAO movieDAO = new MovieDAO();
        MovieSolver solver = new MovieSolver();

        List<Movie> movies = movieDAO.findAll();
        List<MovieList> result = solver.solve(movies, minSize);

        return result;
    }
}

//asculta pe /solve?minSize=2; ia tate filmele din bd; le da lui MinSolver cu minSize; returneaza listele rezultate cu JSON