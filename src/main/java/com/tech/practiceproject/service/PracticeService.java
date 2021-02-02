package com.tech.practiceproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.practiceproject.model.Registraion;
import com.tech.practiceproject.repo.PracticeRepo;

@Service
public class PracticeService implements PracticeServiceInterface{

	@Autowired
	private PracticeRepo practiceRepo;
	
	@Override
	public boolean isAuthenticated(String email, String password) {
		List<Registraion> findAll = practiceRepo.findAll();
		for (Registraion registraion : findAll) {
			if (email.equals(registraion.getEmail()) && password.equals(registraion.getPassword())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void saving(Registraion registration) {
		this.practiceRepo.save(registration);		
	}

	@Override
	public boolean isAdmin(String email) {
		Registraion findByEmail = this.practiceRepo.findByEmail(email);
		if (findByEmail.getRole().equals("Admin")) {
			return true;
		}else {
			return false;
		}
	}

}
