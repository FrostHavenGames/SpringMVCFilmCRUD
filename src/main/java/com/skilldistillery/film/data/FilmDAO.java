package com.skilldistillery.film.data;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.Film;

public interface FilmDAO {

	Film findFilmById(int filmId) throws SQLException;

	Film createFilm(Film film);

	List<Film> findFilmByKeyword(String titleKeywords) throws SQLException;

}
