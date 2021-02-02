package com.tech.practiceproject.service;

import com.tech.practiceproject.model.Registraion;

public interface PracticeServiceInterface {
	
	public boolean isAuthenticated(String email,String password);

	public void saving(Registraion registration);
	
	public boolean isAdmin(String email);

}
