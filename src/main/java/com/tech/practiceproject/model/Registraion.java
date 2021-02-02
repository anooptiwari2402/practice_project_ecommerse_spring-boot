package com.tech.practiceproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Registraion {
	
	@Id
	private String email;
	
	private String firstName;
	private String lastName;
	private Long phone;
	private String password;
    
	@Transient
	private String confirmPassword;
	
	private String company;
	private String address;
	private String city;
	private Integer passCode;
	private String country;
	private String role;
	

}
