package com.riscue.kuponstar.repository;

import org.springframework.data.repository.CrudRepository;

import com.riscue.kuponstar.entity.Match;

public interface MatchRepository extends CrudRepository<Match, Long> {

	Match findMatchByCode(int code);

    //@Query(value = "SELECT * FROM USERS")
	Iterable<Match> findAll();
}
