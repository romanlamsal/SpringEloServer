package de.lamsal.elo.smash.smashelo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "players")
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    public String name;

    private long elo;
    private String lastGame;

    public Player() {}

    public Player(String name) {
        this.name = name;
        this.elo = 1600;
        this.lastGame = "";
    }

    public Player(String name, long elo, String lastGame) {
        this.name = name;
        this.elo = elo;
        this.lastGame = lastGame;
    }

    public String getId() {
        return this.name;
    }

    public long getElo() {
        return this.elo;
    }
    public void setElo(long elo) { this.elo = elo; }

    public String getLastGame() {
        return this.lastGame;
    }

    private void setLastGameTimeToNow() {
        this.lastGame = Instant.now().toString();
    }

}
