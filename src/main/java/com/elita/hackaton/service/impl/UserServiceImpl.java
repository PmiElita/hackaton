package com.elita.hackaton.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.elita.hackaton.dao.UserDAO;
import com.elita.hackaton.domain.CustomUserDetails;
import com.elita.hackaton.domain.User;
import com.elita.hackaton.domain.UserRole;
import com.elita.hackaton.dto.user.UserDTO;
import com.elita.hackaton.dto.user.UserLoginDTO;
import com.elita.hackaton.dto.user.UserRegistrationDTO;
import com.elita.hackaton.mapper.OrikaBeanMapper;
import com.elita.hackaton.service.EmailService;
import com.elita.hackaton.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private OrikaBeanMapper mapper;
	@Autowired
	private EmailService emailService;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final User user = userDAO.findByEmail(username);
		return new CustomUserDetails(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getRegisteredAt(),
				user.getPassword(), user.getRole());
	}

	@Override
	public UserDTO login(final UserLoginDTO userLoginDTO) {
		final Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail().toLowerCase().trim(), userLoginDTO.getPassword()));
		if (!authentication.isAuthenticated()) {
			throw new RuntimeException("Wrong credentials");
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final User user = userDAO.findByEmail(userLoginDTO.getEmail());
		return mapper.map(user, UserDTO.class);
	}

	@Override
	public void registrate(final UserRegistrationDTO userRegistrationDTO) {
		emailService.send(userRegistrationDTO.getEmail(), "Wellcome",
				"Hello, " + userRegistrationDTO.getFirstName() + ", wellcome to Spring Boot test Project");
		final User user = mapper.map(userRegistrationDTO, User.class);
		user.setRegisteredAt(LocalDateTime.now());
		user.setRole(UserRole.USER);
		userDAO.save(user);
	}

}
