package advanced;

import advanced.dao.MovieDAO;
import advanced.model.Movie;
import advanced.model.MovieList;
import advanced.solver.MovieSolver;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        MovieDAO movieDAO = new MovieDAO();
        MovieSolver solver = new MovieSolver();

        //ia toate filmele
        List<Movie> movies = movieDAO.findAll();

        System.out.println("toate filmele");
        for (Movie movie : movies) {
            System.out.println(movie);
        }

        //afiseaza relatiile
        System.out.println("\nrelatii intre filme");
        for (int i = 0; i < movies.size(); i++) {
            for (int j = i + 1; j < movies.size(); j++) {
                if (solver.areRelated(movies.get(i), movies.get(j))) {
                    System.out.println(
                            movies.get(i).getTitle() + "--" +
                                    movies.get(j).getTitle()
                    );
                }
            }
        }

        // solverul
        List<MovieList> lists = solver.solve(movies);

        //rezultatul final
        System.out.println();
        for (MovieList list : lists) {
            System.out.println(list);
        }
    }
}