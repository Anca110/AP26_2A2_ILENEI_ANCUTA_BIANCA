package homework.report;

import homework.model.MovieReport;
import homework.model.MovieReport;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Report {

    //ia datele din java List<MoveReport>, ia template ul=> le combina
    public void generateReport(List<MovieReport> movies, String templateFile, String outputFile) {
        //datele din BD venite din view, fisierul Html template, unde se salveaza raportul final
        try {
            //citesc continutul fisierului reportTemplate si l pun intr un String
            String template = Files.readString(Path.of(templateFile));

            //construiesc randurile din html, tabelul cu filme
            StringBuilder rows = new StringBuilder();

            //pt fiecare film
            for (MovieReport movie : movies) {
                //incep un rand in tabel
                rows.append("<tr>\n");
                //fiecare coloana
                rows.append("<td>").append(movie.getId()).append("</td>\n");
                rows.append("<td>").append(movie.getTitle()).append("</td>\n");
                rows.append("<td>").append(movie.getYear()).append("</td>\n");
                rows.append("<td>").append(movie.getDuration()).append("</td>\n");
                rows.append("<td>").append(movie.getSoundtrack()).append("</td>\n");
                rows.append("<td>").append(movie.getGenreName()).append("</td>\n");
                rows.append("</tr>\n");
            }

            //inlocuiesc in template
            String finalHtml = template.replace("{{ROWS}}", rows.toString());

            //creez fisierul report.html si scriu in el html ul final
            try (FileWriter writer = new FileWriter(outputFile)) {
                writer.write(finalHtml);
            }

            //il deschid in browser
            File file = new File(outputFile);
            if (file.exists()) {
                Desktop.getDesktop().browse(file.toURI());
            }

        } catch (IOException e) {
            System.err.println("Eroare la generare raport: " + e.getMessage());
        }
    }
}