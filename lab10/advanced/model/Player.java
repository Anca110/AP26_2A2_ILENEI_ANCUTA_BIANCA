package advanced.model;

public class Player {
    private String name;//nume jucator
    private int score;//nr de raspunsuri corecte a jucatorului
    private long totalTime;//timp folosit pt raspunsuri (long-se masoara in milisec)

    //creez un jucator cu scor, timp 0
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.totalTime = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public long getTotalTime() {
        return totalTime;
    }

    //creste scorul cu 1 cand raspunsul e corect
    public void addPoint() {
        score++;
    }

    //adauga timpul folosit pt o intrebare
    public void addTime(long time) {
        totalTime += time;
    }
}