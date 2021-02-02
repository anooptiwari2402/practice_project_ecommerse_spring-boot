package com.tech.practiceproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.practiceproject.model.Registraion;

public interface PracticeRepo extends JpaRepository<Registraion, String>{

	Registraion findByEmail(String email);
}
