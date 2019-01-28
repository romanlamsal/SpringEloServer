package de.lamsal.elo.smash.smashelo.apis;

import de.lamsal.elo.smash.smashelo.game.GameService;
import de.lamsal.elo.smash.smashelo.model.Match;
import de.lamsal.elo.smash.smashelo.repository.MatchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
public class RepoAPI {

    @Autowired
    GameService gameService;

    @Autowired
    MatchRepo matchRepo;

    @RequestMapping(value = "/api/lastMatches")
    public ResponseEntity matches() {
        ArrayList<Match> result = new ArrayList<>();
        matchRepo.findAll(Sort.by(Sort.Direction.DESC, "timestamp"))
                .forEach(result::add);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/playerHistory/{playerName}")
    public ResponseEntity playerHistory(@PathVariable(value = "playerName") String playerName) {
        return new ResponseEntity<>(gameService.getPlayerHistory(playerName), HttpStatus.OK);
    }
}
