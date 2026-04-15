package homework;

import homework.dao.ActorDAO;
import homework.dao.GenreDAO;
import homework.dao.MovieDAO;
import homework.dao.MovieReportDAO;
import homework.database.Database;
import homework.model.Actor;
import homework.model.Genre;
import homework.model.Movie;
import homework.model.MovieReport;
import homework.report.Report;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        GenreDAO genreDAO = new GenreDAO();
        ActorDAO actorDAO = new ActorDAO();
        MovieDAO movieDAO = new MovieDAO();
        MovieReportDAO movieReportDAO = new MovieReportDAO();
        Report report = new Report();

        try {
            // genuri
            genreDAO.create("Actiune");
            genreDAO.create("Drama");

            Genre genre = genreDAO.findByName("Actiune");
            System.out.println("Gen gasit: " + genre);

             //actori
            actorDAO.create("Angelina Jolie");
            actorDAO.create("Brad Pitt");

            Actor actor = actorDAO.findByName("Angelina Jolie");
            System.out.println("Actor gasit: " + actor);

            // film
            movieDAO.create("Maleficent", 2010, 148, "soundtrack1", genre.getId());

            Movie movie = movieDAO.findByTitle("Maleficent");
            System.out.println("Film gasit: " + movie);

            // legatura film actor in tabela de legatura
            movieDAO.addActorToMovie(movie.getId(), actor.getId());

            // raport HTML
            List<MovieReport> moviesForReport = movieReportDAO.findAllFromView();
            report.generateReport(moviesForReport, "src/main/resources/reportTemplate.html", "report.html");

        } catch (Exception e) {
            System.err.println("Eroare in main: " + e.getMessage());
        } finally {
            Database.closeConnection();
        }
    }
}