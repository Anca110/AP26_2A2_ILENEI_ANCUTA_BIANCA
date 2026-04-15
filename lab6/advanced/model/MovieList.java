package advanced.model;

import java.util.ArrayList;
import java.util.List;

//cu aceasta clasa creez obiecte de tipul List1, List2..
public class MovieList {
    private String name;
    private List<Movie> movies;//lista de filme care apartin acestei liste

    public MovieList(String name) {
        this.name = name;
        this.movies = new ArrayList<>();
    }

    //adaug un film in lista
    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public String getName() {
        return name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "MovieList{" +
                "name='" + name + '\'' +
                ", movies=" + movies +
                '}';
    }
}