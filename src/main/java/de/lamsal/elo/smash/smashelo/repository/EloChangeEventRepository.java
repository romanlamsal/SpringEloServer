package de.lamsal.elo.smash.smashelo.repository;

import de.lamsal.elo.smash.smashelo.model.EloChangeEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface EloChangeEventRepository extends PagingAndSortingRepository<EloChangeEvent, Long> {

    List<EloChangeEvent> findAllByMatch_Id(long matchId);
}
