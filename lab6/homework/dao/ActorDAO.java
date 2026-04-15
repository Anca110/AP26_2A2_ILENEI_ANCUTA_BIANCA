package homework.dao;

import homework.database.Database;
import homework.model.Actor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActorDAO {

    public void create(String name) {

        try(Connection connection = Database.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO actors (name) VALUES (?)")){

            pstmt.setString(1, name);
            pstmt.executeUpdate();
            Database.commit(connection);
        }
        catch (SQLException e){
            System.err.println("eroare la inserare actor: " + e.getMessage());
        }
    }

    public Actor findById(int id) {
        try(Connection connection = Database.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM actors WHERE id = ?")){

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                return new Actor(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            System.err.println("eroare la cautare actor dupa id: " + e.getMessage());
        }
        return null;
    }

    public Actor findByName(String name){
        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM actors WHERE name = ?")){

            pstmt.setString(1, name);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                return new Actor(rs.getInt("id"), rs.getString("name"));
            }
        }
        catch (SQLException e){
            System.err.println("eroare la cautare actor dupa nume: " + e.getMessage());
        }
        return null;
    }
}