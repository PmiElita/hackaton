package com.elita.hackaton.service;

import com.elita.hackaton.dto.user.UserDTO;
import com.elita.hackaton.dto.user.UserLoginDTO;
import com.elita.hackaton.dto.user.UserRegistrationDTO;

public interface UserService {

	UserDTO login(UserLoginDTO userLoginDTO);

	void registrate(UserRegistrationDTO userRegistrationDTO);

}
