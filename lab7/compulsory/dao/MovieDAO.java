package lab7.compulsory.dao;

import lab7.compulsory.database.Database;
import lab7.compulsory.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MovieDAO {

    public void create(String title, int year, int duration, String soundtrack, int genreId) {

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("INSERT INTO movies (title, year, duration, soundtrack, genre_id) VALUES (?, ?, ?, ?, ?)")){

            pstmt.setString(1, title);
            pstmt.setInt(2, year);
            pstmt.setInt(3, duration);
            pstmt.setString(4, soundtrack);
            pstmt.setInt(5, genreId);
            pstmt.executeUpdate();
            Database.commit(connection);
        }
        catch (SQLException e){
            System.err.println("roare la inserare film: " + e.getMessage());
        }
    }

    public Movie findById(int id){

        try(Connection connection = Database.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM movies WHERE id = ?")){

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                return new Movie(rs.getInt("id"), rs.getInt("genre_id"), rs.getString("soundtrack"), rs.getInt("duration"), rs.getInt("year"), rs.getString("title"));
            }
        }
        catch (SQLException e){
            System.err.println("eroare la cautare film dupa id: " + e.getMessage());
        }
        return null;
    }

    public Movie findByTitle(String title) {

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM movies WHERE title = ?")){

            pstmt.setString(1, title);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                return new Movie(rs.getInt("id"), rs.getInt("genre_id"), rs.getString("soundtrack"), rs.getInt("duration"), rs.getInt("year"), rs.getString("title"));
            }
        }
        catch (SQLException e){
            System.err.println("roare la cautare film dupa titlu: " + e.getMessage());
        }
        return null;
    }

    public void addActorToMovie(int movieId, int actorId) {

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("INSERT INTO movie_actors (movie_id, actor_id) VALUES (?, ?)")){

            pstmt.setInt(1, movieId);
            pstmt.setInt(2, actorId);
            pstmt.executeUpdate();
            Database.commit(connection);
        }
        catch (SQLException e){
            System.err.println("eroare la asociere actor cu film: " + e.getMessage());
        }
    }



//aflu ce filme exista
    //ia toate filmele din tabela movies si le transforma in obiecte java Movie
    public List<Movie> findAll() {

        List<Movie> movies = new ArrayList<>();

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement( "SELECT * FROM movies ORDER BY id")) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Movie movie = new Movie(rs.getInt("id"), rs.getInt("genre_id"), rs.getString("soundtrack"), rs.getInt("duration"), rs.getInt("year"), rs.getString("title"));
                movies.add(movie);
            }
        }
        catch (SQLException e){
            System.err.println("eroare la citire filme: " + e.getMessage());
        }
        return movies;
    }


//aflu ce actori are fiecare film
//pt un film dat prin moveId ia toti actorii asociati acelui film din tabela de legatura movie_actors
    public Set<Integer> findActorsMovie(int movieId){

        //tine id urile actorilor pt cate un film
        Set<Integer> actors = new HashSet<>();

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT actor_id FROM movie_actors WHERE movie_id = ?")) {

            pstmt.setInt(1, movieId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                actors.add(rs.getInt("actor_id"));
            }

        } catch (SQLException e) {
            System.err.println("eroare la citirea actorilor pt un film: " + e.getMessage());
        }
        return actors;
    }

}