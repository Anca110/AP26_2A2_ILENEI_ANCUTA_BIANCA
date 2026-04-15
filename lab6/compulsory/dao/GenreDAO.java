package compulsory.dao;

import compulsory.database.Database;
import compulsory.model.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GenreDAO {

    public void create(String name){

        Connection connection= Database.getConnection();

        try{
            PreparedStatement psmt=connection.prepareStatement("INSERT INTO genres (name) VALUES (?)");
            psmt.setString(1,name);
            psmt.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("eroare la inserarea genului: "+ e.getMessage());
        }
    }
   
    public Genre findById(int id){

        Connection connection=Database.getConnection();

        try{
            PreparedStatement psmt=connection.prepareStatement("SELECT id, name FROM genres WHERE id= ?");
            psmt.setInt(1,id);

            ResultSet rs=psmt.executeQuery();

            if(rs.next()){
                return new Genre(rs.getInt("id"), rs.getString("name"));
            }
        }
        catch(SQLException e){
            System.out.println("eroare la select pt un id: "+ e.getMessage());
        }
        return null;
    }

    public Genre findByName(String name){

        Connection connection=Database.getConnection();

        try{
            PreparedStatement psmt=connection.prepareStatement("SELECT id, name FROM genres WHERE name= ?");
            psmt.setString(1,name);

            ResultSet rs=psmt.executeQuery();

            if(rs.next()){

                return new Genre(rs.getInt("id"), rs.getString("name"));
            }
        }
        catch(SQLException e){
            System.out.println("eroare la select pt un name: "+ e.getMessage());
        }
        return null;
    }

}


//merge in baza de date si executa SQL
//pt create face INSERT
//pt findById => SELECT WHERE..id..
//findByNme => SELECT..WHERE name=...