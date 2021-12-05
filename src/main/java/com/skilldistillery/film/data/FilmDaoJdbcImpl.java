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
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;

		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT film.id, film.title, film.description, film.release_year, film.language_id, film.rental_duration, ";
		sql += " film.rental_rate, film.length, film.replacement_cost, film.rating, film.special_features,"
				+ "category.name " + " FROM film join film_category on"
						+ " film.id = film_category.film_id"
						+ " join category on category.id = film_category.category_id  WHERE film.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet actorResult = stmt.executeQuery();
		if (actorResult.next() && filmId > 0) {
			film = new Film(); // Create the object
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
			film.setCategoryName(actorResult.getString(12));
			film.setActors(findActorsByFilmId(filmId));


		}

		return film;
	}
	
	
	
	
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actor = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "select actor.id , actor.first_name , actor.last_name,film.title  from actor "
					+ "join film_actor on actor.id = film_actor.actor_id "
					+ "join film on film_actor.film_id = film.id " + "where film.id =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int Id = rs.getInt("actor.id");
				String firstName = rs.getString("actor.first_name");
				String lastName = rs.getString("actor.last_name");

				Actor actors = new Actor(Id, firstName, lastName);
				actor.add(actors);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
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
			film.setCategoryName(actorResult.getString(12));
			film.setActors(findActorsByFilmId(film.getId()));
			films.add(film);

		}
		return films;
	}

	@Override
	public Film createFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			String sql = "Insert into Film(title, description ,release_year, language_id ,"
					+ "rental_duration,rental_rate ,length,replacement_cost,rating,special_features)"
					+ "values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setInt(4, film.getLanguageId());
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setInt(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			stmt.setString(10, film.getSpecialFeature());
			int uc = stmt.executeUpdate();
			if (uc == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newFilmId = keys.getInt(1);
					film.setId(newFilmId);
					if(film.getActors() !=null && film.getActors().size() >0) {
						sql = "insert into film_actor(film_id,actor_id) values(?,?)";
						stmt = conn.prepareStatement(sql);
						for(Actor actor: film.getActors()) {
							stmt.setInt(1, newFilmId);
							stmt.setInt(2, actor.getId());
						}
					}
				}
			} else {
				film = null;
			}
			conn.commit();
		} catch (SQLException sqle) {

			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.out.println("Error trying to rollback");
				}
			}
		}

		return film;
	}

	public boolean deleteFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			String sql = "delete from film_actor where film_id =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			int updatedCount = stmt.executeUpdate();
			sql = "delete from film where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			updatedCount = stmt.executeUpdate();
			conn.commit();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.out.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}

	public boolean updateFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			String sql = " update film set description =? where id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, film.getDescription());
			stmt.setInt(2, film.getId());
			int updateCount = stmt.executeUpdate();
			conn.commit();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.out.println("Error trying to rollback");

				}
			}
			return false;
		}
		return true;
	}

}
