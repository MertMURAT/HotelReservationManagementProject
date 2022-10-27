package com.example.roombookingapp.controller;

import com.example.roombookingapp.entities.Role;
import com.example.roombookingapp.entities.User;
import com.example.roombookingapp.repositories.RoleRepository;
import com.example.roombookingapp.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@RequestMapping("")
	public ModelAndView listUsers() {
		return new ModelAndView("users/list", "users", userRepository.findAll());
	}


	@RequestMapping("/add")
	public String addUser(Model model, @ModelAttribute User user) {
		
		model.addAttribute("roles", roleRepository.findAll());
		
		return "users/edit";
	}

	@RequestMapping("/edit/{id}")
	public String editUser(@PathVariable Long id, Model model) {
		User user = userRepository.findById(id).get();
		model.addAttribute("user", user);
		model.addAttribute("roles", roleRepository.findAll());
		return "users/edit";
	}

	@PostMapping("/save")
	public Object saveUser(@Valid User user, BindingResult bindingResult, RedirectAttributes attributes) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("users/edit", "user", user);
		}
		if(user.getRole() ==null) {
			Role roleUser = roleRepository.findByCode("ROLE_USER");
			user.setPassword(encoder.encode(user.getPassword()));
			user.setRole(roleUser);
		}
		user.setPassword(encoder.encode(user.getPassword()));

//		user.setEnabled(true);
		userRepository.save(user);
		return "redirect:/users";
	}

	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		userRepository.deleteById(id);
		return "redirect:/users";
	}

	@RequestMapping("/resetPW")
	public String resetUserPW(@RequestParam Long id) {
		User user = userRepository.findById(id).get();
		user.setPassword("secret");
		userRepository.save(user);
		return "redirect:/users";
	}

}
