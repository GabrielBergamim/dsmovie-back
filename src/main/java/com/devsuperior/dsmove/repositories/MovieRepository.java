package com.devsuperior.dsmove.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.dsmove.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
