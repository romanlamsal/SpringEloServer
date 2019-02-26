package de.lamsal.elo.smash.smashelo.apis;

import de.lamsal.elo.smash.smashelo.game.GameService;
import de.lamsal.elo.smash.smashelo.model.EloChangeEvent;
import de.lamsal.elo.smash.smashelo.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
public class MatchAPI {

    @Autowired
    GameService gameService;

    @RequestMapping(value = "/api/player", method = RequestMethod.PUT)
    public void createPlayer(@RequestParam String playerName, HttpServletResponse response) {
        try {
            gameService.createPlayer(playerName);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }

    @RequestMapping(value = "/api/match", method = RequestMethod.POST)
    public void registerMatch(@RequestBody Map<String, String> body) {
        gameService.matchByIDs(body.get("winner"), body.get("loser"));
    }

    @RequestMapping(value = "/api/elo", method = RequestMethod.GET)
    public String getPlayerElo(@RequestParam("id") String id) {
        Player player = gameService.getPlayer(id);
        return String.valueOf(player.getElo());
    }
}
