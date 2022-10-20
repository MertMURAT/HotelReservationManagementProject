package com.example.roombookingapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.roombookingapp.command.UserDetailsUser;
import com.example.roombookingapp.entities.User;
import com.example.roombookingapp.repositories.UserRepository;
@Service
public class AppUserDetailsService  implements UserDetailsService{
	 @Autowired
	 private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User userDb = this.userRepo.findByEmail(email);
		UserDetails user = null;
        if (userDb != null) {
            user = new UserDetailsUser(userDb);
        }
        if (user == null) {
            throw new UsernameNotFoundException("User with email of " + email + " not found!");
        }
        return user;
	}
	
	   

}
