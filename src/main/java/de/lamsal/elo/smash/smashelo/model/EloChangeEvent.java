package de.lamsal.elo.smash.smashelo.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "elo_change_event")
public class EloChangeEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    public String timestamp;
    @OneToOne
    public Player player;
    public long eloBefore;
    public long eloAfter;
    @OneToOne
    public Match match;

    EloChangeEvent() {}

    public EloChangeEvent(Player player) {
        this.timestamp = Instant.now().toString();
        this.player = player;
        this.eloBefore = player.getElo();
    }

    public void setEloAfter(long eloAfter) {
        this.eloAfter = eloAfter;
    }

    public void setMatch(Match match) {
        this.match = match;
        this.timestamp = match.timestamp;
    }
}
