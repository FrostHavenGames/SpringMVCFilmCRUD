package com.skilldistillery.film.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO filmDao;

	@RequestMapping({ "/", "home.do" })
	public String home(Model model) {
		model.addAttribute("TEST", "Hello, Spring MVC!");
		return "home";
	}
	@RequestMapping("findById")
	public String findbyId(Model model) {
		return "findById";
	}

	@RequestMapping(path = "findById.do", params = "filmId", method = RequestMethod.GET)
	public ModelAndView getByFilmId(@RequestParam("filmId") int id) throws SQLException {
		ModelAndView mv = new ModelAndView();
		Film film = filmDao.findFilmById(id);
		System.out.println(film);
		mv.addObject("Film", film);
		mv.setViewName("result");
		return mv;
	}
	
	
	@RequestMapping({ "findByKeyword"})
	public String findbyKeyword(Model model) {
		return "findByKeyword";
	}

	@RequestMapping(path = "findByKeyword.do", params = "filmkeyword", method = RequestMethod.GET)
	public ModelAndView getByFilmKeyword(@RequestParam("filmkeyword") String id) throws SQLException {
		ModelAndView mv = new ModelAndView();
		List<Film> film = filmDao.findFilmByKeyword(id);
		mv.addObject("Film", film);
		mv.setViewName("filmListResult");
		return mv;
	}
	@RequestMapping({"addFilm"})
	public String createNewFilm(Model model) {
		return "addFilm";
	}

	@RequestMapping(path = "createNewFilm.do", method = RequestMethod.POST)
	public ModelAndView createANewFilm(Film NewFilm) throws SQLException {
		ModelAndView mv = new ModelAndView();
		Film film =  filmDao.createFilm(NewFilm) ;
		mv.addObject("Film", film);
		mv.setViewName("result");
		return mv;
	}
	@RequestMapping({"deleteFilm"})
	public String deleteFilm(Model model) {
		return "deleteFilm";
	}

	@RequestMapping(path = "deleteFilm.do", params = "filmId", method = RequestMethod.GET)
	public ModelAndView deleteAFilm(@RequestParam("filmId") int id) throws SQLException {
		ModelAndView mv = new ModelAndView();
		Film film = filmDao.findFilmById(id);
		filmDao.deleteFilm(film);
		mv.setViewName("result");
		return mv;
	}
	
	@RequestMapping({"updateFilm"})
	public String updateFilm(Model model) {
		return "filmUpdater";
	}

	@RequestMapping(path = "updateFilm.do", method = RequestMethod.GET)
	public ModelAndView updateAFilm(Film updatedFilm) throws SQLException {
		ModelAndView mv = new ModelAndView();
		filmDao.updateFilm(updatedFilm);
		mv.setViewName("result");
		return mv;
	}

}
