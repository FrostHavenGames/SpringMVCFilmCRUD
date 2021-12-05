package com.skilldistillery.film.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.film.data.FilmDaoJdbcImpl;

class FilmTest {
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() throws SQLException {
		FilmDaoJdbcImpl db = new FilmDaoJdbcImpl();
		Film films = new Film();
		films.setTitle("wer");
		films.setDescription("hgdfgfd");
		films.setReleaseYear(1);
		films.setLanguageId(1);
		films.setRentalDuration(8);
		films.setRentalRate(2.0);
		films.setLength(98);
		films.setReplacementCost(22);
		films.setRating("G");
		films.setSpecialFeature("hgd");
		
		
		
		db.createFilm(films);
		db.deleteFilm(films);

		assertNotNull(db.deleteFilm(films));
		
//		fail("Not yet implemented");
	}
	

}
