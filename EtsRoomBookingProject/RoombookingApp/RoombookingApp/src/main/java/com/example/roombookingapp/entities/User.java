package com.example.roombookingapp.entities;



import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
	private String fullName;
    
    
    @NotBlank(message = "First name cannot be blank.")  
	private String firstName;
    
    @NotBlank(message = "Last name cannot be blank.")  
	private String lastName;
    
	@NotBlank(message = "Email cannot be blank.")
	private String email;
	

	private boolean enabled;
	
	@ManyToOne
	private Role role;

//    @NotBlank(message = "name cannot be blank.")
    private String name;

    @NotBlank(message = "password cannot be blank.")
    private String password;
   
}
