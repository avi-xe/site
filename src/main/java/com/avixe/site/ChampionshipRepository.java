package com.avixe.site;

import org.springframework.data.repository.CrudRepository;

public interface ChampionshipRepository extends CrudRepository<Championship, Long>{
    Iterable<Championship> findAllByYearId(Long id);
    Iterable<Championship> findAllByLeagueId(Long id);
}
