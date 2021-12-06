package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Service
public class FilmDaoJdbcImpl implements FilmDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	String user = "student";
	String pass = "student";
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		String sql = "SELECT * FROM film WHERE id = ?";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, filmId);
			ResultSet filmResult = statement.executeQuery();

			if (filmResult.next()) {
				String title = filmResult.getString("title");
				String desc = filmResult.getString("description");
				short releaseYear = filmResult.getShort("release_year");
				int langId = filmResult.getInt("language_id");
				int rentDur = filmResult.getInt("rental_duration");
				double rate = filmResult.getDouble("rental_rate");
				int length = filmResult.getInt("length");
				double repCost = filmResult.getDouble("replacement_cost");
				String rating = filmResult.getString("rating");
				String features = filmResult.getString("special_features");
				String category = filmResult.getString("special_features");
				film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features, category, findActorsByFilmId(filmId));

			}

			filmResult.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return film;
	}
	
	
	@Override
	public List<Actor> findActorsByFilmId(int filmID) {
		List<Actor> actors = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT DISTINCT a.id, a.first_name, a.last_name FROM actor a JOIN film_actor fa on a.id = fa.actor_id JOIN film f on fa.film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmID);
			ResultSet actorResult = stmt.executeQuery();

			while (actorResult.next()) {
				actors.add(new Actor(actorResult.getInt("id"), actorResult.getString("first_name"),
						actorResult.getString("last_name")));
			}

			actorResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actors;
	}
	
	
	
	
	@Override
	public List<Film> findFilmByKeyword(String titleKeywords) throws SQLException {
		Connection conn = DriverManager.getConnection(URL, user, pass);

		List<Film> films = new ArrayList<>();
		String sql = "SELECT film.id, film.title, film.description, film.release_year, film.language_id, film.rental_duration, ";
		sql += " film.rental_rate, film.length, film.replacement_cost, film.rating, film.special_features, "
				+ "category.name" + " FROM film join film_category on"
				+ " film.id = film_category.film_id"
				+ " join category on category.id = film_category.category_id"
				+ " WHERE title like ? or description like ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + titleKeywords + "%");
		stmt.setString(2, "%" + titleKeywords + "%");
		ResultSet actorResult = stmt.executeQuery();
		while (actorResult.next()) {
			Film film = new Film(); // Create the object
			// Here is our mapping of query columns to our object fields:
			film.setId(actorResult.getInt(1));
			film.setTitle(actorResult.getString(2));
			film.setDescription(actorResult.getString(3));
			film.setReleaseYear(actorResult.getInt(4));
			film.setLanguageId(actorResult.getInt(5));
			film.setRentalDuration(actorResult.getInt(6));
			film.setRentalRate(actorResult.getDouble(7));
			film.setLength(actorResult.getInt(8));
			film.setReplacementCost(actorResult.getDouble(9));
			film.setRating(actorResult.getString(10));
			film.setSpecialFeature(actorResult.getString(11));
			film.setActors(findActorsByFilmId(film.getId()));
			films.add(film);

		}
		return films;
	}

	@Override
	public Film createFilm(Film newFilm) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "INSERT INTO film (title, description, language_id) " + " VALUES (?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, newFilm.getTitle());
			stmt.setString(2, newFilm.getDescription());
			stmt.setInt(3, 1);

			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newFilmID = keys.getInt(1);
					newFilm.setId(newFilmID);
//					if (newFilm.getTitle() != null && newFilm.getDescription() != null) {
//						sql = "INSERT INTO film_list (FID, title, description,) VALUES (?,?,?)";
//						stmt.setInt(1, newFilm.getId());
//						stmt.setString(2, newFilm.getTitle());
//						stmt.setString(3, newFilm.getDescription());
//						stmt = conn.prepareStatement(sql);
//						updateCount = stmt.executeUpdate();
//					}
				}
			} else {
				newFilm = null;
			}
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting film " + newFilm);
		}
		return newFilm;
	}

	@Override
	public boolean deleteFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "DELETE FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			stmt.executeUpdate();
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}

	@Override
	public boolean updateFilm(Film newFilm) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "UPDATE film SET title=?, description=? " + " WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, newFilm.getTitle());
			stmt.setString(2, newFilm.getDescription());
			stmt.setInt(3, newFilm.getId());
			stmt.executeUpdate();
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} // ROLLBACK TRANSACTION ON ERROR
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}

}
