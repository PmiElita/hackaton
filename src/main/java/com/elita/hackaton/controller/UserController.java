package com.elita.hackaton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elita.hackaton.dto.user.UserDTO;
import com.elita.hackaton.dto.user.UserLoginDTO;
import com.elita.hackaton.dto.user.UserRegistrationDTO;
import com.elita.hackaton.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public UserDTO userLogin(@RequestBody final UserLoginDTO userLoginDTO) {
		return userService.login(userLoginDTO);
	}

	@RequestMapping(value = "registrate", method = RequestMethod.POST)
	public void userRegistration(@RequestBody final UserRegistrationDTO userRegistrationDTO) {
		userService.registrate(userRegistrationDTO);
	}

}
