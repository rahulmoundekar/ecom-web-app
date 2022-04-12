package com.app.transformer;

import com.app.dto.CustomerDto;
import com.app.entity.Customer;
import com.app.entity.User;

public class CustomerTransformer {

	public static Customer customerDtoToCustomerEntity(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setName(customerDto.getName());
		customer.setAddress(customerDto.getAddress());
		customer.setMobileNo(customerDto.getMobileNo());

		User user = UserTransformer.userDtoToUserEntity(customerDto);
		customer.setUser(user);
		return customer;
	}

}
