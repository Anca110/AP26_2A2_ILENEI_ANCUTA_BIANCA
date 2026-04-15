package homework.dao;

import homework.database.Database;
import homework.model.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//DAO data acces object
//trimite SQL catre PostgreSQL
//primeste rezultat si trasforma in obict java
public class GenreDAO {

    public void create(String name){

        try(Connection connection = Database.getConnection();//ceri conexiune de la clasa Database
            PreparedStatement psmt=connection.prepareStatement("INSERT INTO genres (name) VALUES (?)")){

            //creez obiectul care va executa SQL
            psmt.setString(1,name);//primul ? din query primeste valoarea name
            psmt.executeUpdate();//executa insert ul
            Database.commit(connection);
        }
        catch(SQLException e){
            System.out.println("eroare la inserarea genului: "+ e.getMessage());
        }
    }
    //aceasta metoda doar executa inserarea
    // dar pt ca in Database am pus connection.setAutoCommit(false); => inserarea nu se salveaza definitiv pana nu fac commit
    //Databse.commit() fac in main

    public Genre findById(int id){

        try(Connection connection=Database.getConnection();
            PreparedStatement psmt=connection.prepareStatement("SELECT id, name FROM genres WHERE id= ?")){

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

        try(Connection connection=Database.getConnection();
            PreparedStatement psmt=connection.prepareStatement("SELECT id, name FROM genres WHERE name= ?")){

            psmt.setString(1,name);

            ResultSet rs=psmt.executeQuery();
            //ca un cursor care merge peste randurile returnate

            if(rs.next()){//daca exista un rezultat atunci cursorul merge pe primul rand

                //transform randul din baza de date intr un obiect java
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