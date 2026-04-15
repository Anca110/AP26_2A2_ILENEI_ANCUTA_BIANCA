package lab7.homework.model;

public class Movie {

    private int id;
    private String title;
    private int year;
    private int duration;
    private String soundtrack;
    private int genreId;

    private Double score;

    public Movie() {}

    public Movie(int id, int genreId, String soundtrack, int duration, int year, String title) {
        this.id = id;
        this.genreId = genreId;
        this.soundtrack = soundtrack;
        this.duration = duration;
        this.year = year;
        this.title = title;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getSoundtrack() {
        return soundtrack;
    }

    public void setSoundtrack(String soundtrack) {
        this.soundtrack = soundtrack;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                ", soundtrack='" + soundtrack + '\'' +
                ", genreId=" + genreId +
                ", score=" + score +
                '}';
    }
}
