package compulsory.model;

import jakarta.persistence.*;

//clasa asta trb transformata intr un tabel in BD
@Entity
@Table(name = "players")
public class Player {

    @Id//marcheaza cheia primara
    @GeneratedValue(strategy = GenerationType.IDENTITY)//id se genereaza automat in bd
    private Long id;

    //alte coloane din tabel
    private String name;
    private int score;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Player{id=" + id + ", name='" + name + "', score=" + score + "}";
    }
}