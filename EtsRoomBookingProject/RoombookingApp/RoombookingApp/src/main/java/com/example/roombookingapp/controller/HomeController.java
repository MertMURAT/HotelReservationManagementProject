package com.example.roombookingapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.roombookingapp.entities.Role;
import com.example.roombookingapp.entities.User;
import com.example.roombookingapp.repositories.RoleRepository;
import com.example.roombookingapp.repositories.UserRepository;

@Controller
public class HomeController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/register")
	public String register( @ModelAttribute User user) {
		
		return "register";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute User user, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "register";

		} else {
			Role roleUser = roleRepository.findByCode("ROLE_USER");
			user.setPassword(encoder.encode(user.getPassword()));
			user.setRole(roleUser);
			user.setEnabled(true);
			userRepository.save(user);
			redirectAttributes.addFlashAttribute("accountCreated", true);
		}
		return "redirect:/login";
	}
}
