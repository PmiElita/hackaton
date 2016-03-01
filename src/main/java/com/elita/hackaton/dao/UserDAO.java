package com.elita.hackaton.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elita.hackaton.domain.User;

public interface UserDAO extends JpaRepository<User, Integer> {
	User findByEmail(String email);
}
