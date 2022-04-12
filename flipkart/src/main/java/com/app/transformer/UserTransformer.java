package com.app.transformer;

import com.app.dto.CustomerDto;
import com.app.entity.User;

public class UserTransformer {

	public static User userDtoToUserEntity(CustomerDto customerDto) {
		User user = new User();
		user.setUsername(customerDto.getUsername());
		user.setPassword(customerDto.getPassword());
		return user;
	}

}
