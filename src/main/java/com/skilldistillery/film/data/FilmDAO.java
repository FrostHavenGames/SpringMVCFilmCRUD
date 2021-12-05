package com.skilldistillery.film.data;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {

	public Film findFilmById(int filmId) throws SQLException;

	public Film createFilm(Film film);

	public List<Film> findFilmByKeyword(String titleKeywords) throws SQLException;

	public List<Actor> findActorsByFilmId(int filmId);
	public boolean deleteFilm(Film film);
	public boolean updateFilm(Film film);

}
