package de.lamsal.elo.smash.smashelo.repository;

import de.lamsal.elo.smash.smashelo.model.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@CrossOrigin
public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {

    @Query(value = "SELECT p.name, ev.elo_before, elo_after, ev.timestamp, winner.name as winner_name, loser.name as loser_name\n" +
            "FROM elo_change_event ev JOIN players p ON ev.player_id = p.id\n" +
            "    JOIN matches m ON m.id = ev.match_id\n" +
            "    JOIN players winner ON winner.name = m.winner_name\n" +
            "    JOIN players loser ON loser.name = m.loser_name\n" +
            "WHERE p.name = :playerName\n" +
            "ORDER BY timestamp ASC", nativeQuery = true)
    List<Object[]> findPlayerHistory(@Param("playerName") String playerName);

    Optional<Player> findPlayerByName(String name);
}
