package homework.model;

public class MovieReport{
    private int id;
    private String title;
    private int year;
    private int duration;
    private String soundtrack;
    private String genreName;//in loc de genrId arata ca datele sunt pregatite pt raport

    public MovieReport(int id, String title, int year, int duration, String soundtrack, String genreName) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.soundtrack = soundtrack;
        this.genreName = genreName;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getDuration() {
        return duration;
    }

    public String getSoundtrack() {
        return soundtrack;
    }

    public String getGenreName() {
        return genreName;
    }
}