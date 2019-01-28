package de.lamsal.elo.smash.smashelo.apis;

import de.lamsal.elo.smash.smashelo.game.GameService;
import de.lamsal.elo.smash.smashelo.model.EloChangeEvent;
import de.lamsal.elo.smash.smashelo.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MatchAPI {

    @Autowired
    GameService gameService;

    @RequestMapping("/api/match")
    public void match(@RequestBody Map<String, String> body) {
        gameService.matchByIDs(body.get("winner"), body.get("loser"));
    }

    @RequestMapping("/api/elo")
    public String getPlayerElo(@RequestParam("id") String id) {
        Player player = gameService.getPlayer(id);
        if (player != null)
            return String.valueOf(player.getElo());
        return null;
    }

    @RequestMapping("/api/findByMatch/{matchId}")
    public void foo(@PathVariable long matchId) {
        gameService.undoMatch(matchId);
    }
}
