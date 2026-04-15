package compulsory;

import compulsory.dao.GenreDAO;
import compulsory.database.Database;
import compulsory.model.Genre;

public class Main {
    public static void main(String[] args) {
        GenreDAO genreDAO = new GenreDAO();

        genreDAO.create("Actiune");
        genreDAO.create("Drama");

        Database.commit();

        Genre genreById = genreDAO.findById(1);
        System.out.println("gen de film gasit dupa id: " + genreById);

        Genre genreByName = genreDAO.findByName("Drama");
        System.out.println("ge de film gasi dupa nume: " + genreByName);

        Database.closeConnection();
    }
}