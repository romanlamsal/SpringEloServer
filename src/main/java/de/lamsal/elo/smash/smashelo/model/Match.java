package de.lamsal.elo.smash.smashelo.model;

import de.lamsal.elo.smash.smashelo.game.SettingsKt;
import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @JoinColumn(name = "winner_name", referencedColumnName = "name")
    @OneToOne(fetch = FetchType.EAGER)
    Player winner;

    @JoinColumn(name = "loser_name", referencedColumnName = "name")
    @OneToOne(fetch = FetchType.EAGER)
    Player loser;

    public double eloUpdateFactor;
    public double winnerWeight;
    public double loserWeight;
    String timestamp;

    public Match() {}

    public Match(Player winner, Player loser) {
        this.winner = winner;
        this.loser = loser;
        this.eloUpdateFactor = SettingsKt.getEloUpdateFactor();
        this.winnerWeight = SettingsKt.getWinnerWeight();
        this.loserWeight = SettingsKt.getLoserWeight();
        this.timestamp = Instant.now().toString();
    }

    public Player getWinner() { return this.winner; }
    public Player getLoser() { return this.loser; }
    public String getTimestamp() { return this.timestamp; }
}
