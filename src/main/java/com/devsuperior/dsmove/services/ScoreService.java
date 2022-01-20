package com.devsuperior.dsmove.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmove.dto.ScoreDTO;
import com.devsuperior.dsmove.entities.Movie;
import com.devsuperior.dsmove.entities.Score;
import com.devsuperior.dsmove.entities.User;
import com.devsuperior.dsmove.repositories.MovieRepository;
import com.devsuperior.dsmove.repositories.ScoreRepository;
import com.devsuperior.dsmove.repositories.UserRepository;

@Service
public class ScoreService {
	@Autowired
	private ScoreRepository repository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void saveScore(ScoreDTO dto) {
		User user = findUserAndSaveIfNoExist(dto);
		Movie movie = movieRepository.findById(dto.getMovieId()).get();	
		save(dto, user, movie);
		saveScoreAvg(movie);
	}

	private void saveScoreAvg(Movie movie) {
		double avg = calcAvgScore(movie);
		
		movie.setScore(avg);
		movie.setCount(movie.getScores().size());
		movieRepository.save(movie);
	}

	private void save(ScoreDTO dto, User user, Movie movie) {
		Score score = new Score();
		score.setMovie(movie);
		score.setUser(user);
		score.setValue(dto.getScore());
		score = repository.saveAndFlush(score);
	}

	private User findUserAndSaveIfNoExist(ScoreDTO dto) {
		User user = userRepository.findByEmail(dto.getEmail());

		if (user == null) {
			user = new User();
			user.setEmail(dto.getEmail());
			user = userRepository.saveAndFlush(user);
		}
		
		return user;
	}

	private double calcAvgScore(Movie movie) {
		double sum = 0.0;
		
		for(Score s : movie.getScores()) {
			sum += s.getValue();
		}

		return sum / movie.getScores().size();
	}
}
