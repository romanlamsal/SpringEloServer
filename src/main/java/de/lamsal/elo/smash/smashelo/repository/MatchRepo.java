package de.lamsal.elo.smash.smashelo.repository;

import de.lamsal.elo.smash.smashelo.model.Match;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface MatchRepo extends PagingAndSortingRepository<Match, Long> {
}
