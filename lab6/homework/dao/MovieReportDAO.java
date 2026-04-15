package homework.dao;

import homework.database.Database;
import homework.model.MovieReport;
import homework.model.MovieReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//citeste datele pt raport nu din tabela principala, dar dintr un view
public class MovieReportDAO {

    public List<MovieReport> findAllFromView(){

        List<MovieReport> rows = new ArrayList<>();
        String sql = "SELECT * FROM movies_report_view";

        try(Connection connection = Database.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            ResultSet rs = pstmt.executeQuery();

            //iau coloanele din view si le transform in obiect java
            while (rs.next()){
                MovieReport row = new MovieReport( rs.getInt("id"), rs.getString("title"), rs.getInt("year"), rs.getInt("duration"), rs.getString("soundtrack"), rs.getString("genre_name"));
                rows.add(row);
            }
        }
        catch (SQLException e){
            System.err.println("Eroare la citirea view-ului pentru raport: " + e.getMessage());
        }

        return rows;
    }
}