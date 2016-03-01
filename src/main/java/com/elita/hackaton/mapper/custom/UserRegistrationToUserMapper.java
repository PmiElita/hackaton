package com.elita.hackaton.mapper.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.elita.hackaton.domain.User;
import com.elita.hackaton.dto.user.UserRegistrationDTO;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

@Component
public class UserRegistrationToUserMapper extends CustomMapper<UserRegistrationDTO, User> {
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public void mapAtoB(final UserRegistrationDTO source, final User destination, final MappingContext context) {
		destination.setPassword(encoder.encode(source.getPassword()));
	}
}
