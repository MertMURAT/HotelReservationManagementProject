package com.example.roombookingapp.controller;

import com.example.roombookingapp.command.DateRequestCommand;
import com.example.roombookingapp.command.SearchForm;
import com.example.roombookingapp.entities.User;
import com.example.roombookingapp.repositories.BookingRepository;
import com.example.roombookingapp.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class BookingDisplayController {

	@Autowired
	BookingRepository bookingRepository;
	@Autowired
	UserRepository userRepository;

	public String showCalender(SearchForm search, Model model) throws ParseException {
		model.addAttribute("search", search);
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		if (!search.getStartDate().isEmpty() && !search.getEndDate().isEmpty())
			model.addAttribute("bookings", bookingRepository.getAllBetweenDates(formatter.parse(search.getStartDate()),
					formatter.parse(search.getEndDate())));
		else {
			if (!search.getStartDate().isEmpty() && search.getEndDate().isEmpty())
				model.addAttribute("bookings", bookingRepository.findAllByDate(formatter.parse(search.getStartDate())));
			else {
				if (search.getStartDate().isEmpty() && !search.getEndDate().isEmpty()) {

					model.addAttribute("bookings",
							bookingRepository.findAllByDate(formatter.parse(search.getEndDate())));
				} else {
					model.addAttribute("bookings", bookingRepository.findAll());
				}
			}
		}

		return "home";
	}

	@RequestMapping("/")
	public String homePage(Model model, @ModelAttribute SearchForm search) throws ParseException {

		return showCalender(search, model);
	}

	@RequestMapping("/calender")
	public String calender(@ModelAttribute SearchForm search, Model model) throws ParseException {
		return showCalender(search, model);
	}

	@GetMapping("/bookings")
	public String bookings(Model model, Principal p) {
		User connecedUser = userRepository.findByEmail(p.getName());
		if (connecedUser != null && connecedUser.getRole().getCode().equals("ROLE_USER")) {
			model.addAttribute("bookings", bookingRepository.findByUserEmail(p.getName()));
		} else {
			model.addAttribute("bookings", bookingRepository.findAll());
		}

		return "bookings/reservations";
	}

}
