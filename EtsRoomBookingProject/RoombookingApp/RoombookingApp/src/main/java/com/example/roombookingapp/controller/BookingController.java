package com.example.roombookingapp.controller;

import com.example.roombookingapp.command.BookingCommand;
import com.example.roombookingapp.command.Layout;
import com.example.roombookingapp.entities.Booking;
import com.example.roombookingapp.entities.BookingStatus;
import com.example.roombookingapp.entities.User;
import com.example.roombookingapp.repositories.BookingRepository;
import com.example.roombookingapp.repositories.RoomRepository;
import com.example.roombookingapp.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	UserRepository userRepository;

	private Map<String, Object> getBookingFormModel(Booking booking) {
		Map<String, Object> model = new HashMap<>();
		model.put("booking", new BookingCommand(booking));
		model.put("rooms", roomRepository.findAll());
		model.put("layouts", Layout.values());
		model.put("users", userRepository.findAll());
		return model;
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView editBooking(@PathVariable Long id) {
		return new ModelAndView("bookings/edit", getBookingFormModel(bookingRepository.findById(id).get()));
	}

	@RequestMapping("/validate/{id}")
	public String validateBooking(@PathVariable Long id) {
		Optional<Booking> booking = bookingRepository.findById(id);

		if (booking.isPresent()) {
			booking.get().setStatus(BookingStatus.VALIDATED);
			bookingRepository.save(booking.get());
		}
		return "redirect:/bookings";
	}

	@RequestMapping("/cancel/{id}")
	public String cancelBooking(@PathVariable Long id) {
		Optional<Booking> booking = bookingRepository.findById(id);

		if (booking.isPresent()) {
			booking.get().setStatus(BookingStatus.CANCLED);
			bookingRepository.save(booking.get());
		}
		return "redirect:/bookings";
	}

	@RequestMapping("/new")
	public ModelAndView newBooking() {
		return new ModelAndView("bookings/edit", getBookingFormModel(new Booking()));
	}

	@PostMapping("/save")
	public String save(BookingCommand bookingCommand, Principal p) {
		User connecedUser=userRepository.findByEmail(p.getName());
		if(connecedUser !=null) {
			bookingCommand.setUser(connecedUser);
		}
		bookingCommand.setStatus(BookingStatus.NEW);
		bookingRepository.save(bookingCommand.toBooking());
		return "redirect:/";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		bookingRepository.deleteById(id);
		return "redirect:/bookings";
	}

}
