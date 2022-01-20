package com.devsuperior.dsmove.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.dsmove.entities.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long>{

}
