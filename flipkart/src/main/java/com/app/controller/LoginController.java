package com.app.controller;

import javax.servlet.http.HttpSession;

import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.dto.CustomerDto;
import com.app.entity.Customer;
import com.app.entity.User;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repository.BrandRepository;
import com.app.repository.CustomerRepository;
import com.app.repository.ProductRepository;
import com.app.repository.UserRepository;
import com.app.transformer.CustomerTransformer;
import com.app.validators.CustomerValidator;

@Controller
public class LoginController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BrandRepository brandRepository;

	@GetMapping(value = "/")
	public String loginView(Model model) {
		model.addAttribute("customerForm", new CustomerDto());
		model.addAttribute("userForm", new User());
		return "login";
	}

	@PostMapping(value = "login")
	public String userLogin(Model model, @ModelAttribute("userForm") User user, RedirectAttributes attributes,
			HttpSession session) {
		User loggedUser = userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (null != loggedUser) {
			session.setAttribute("user", loggedUser);
			session.setAttribute("customerId", loggedUser.getCustomerList().get(0).getId());
			model.addAttribute("brandList", brandRepository.findAll());
			model.addAttribute("products", productRepository.findAll());
			return "products";
		} else {
			attributes.addFlashAttribute("error", "Bad Credentials!!");
			return "redirect:/";
		}
	}

	@PostMapping(value = "userRegistration")
	public String userRegistration(Model model, @ModelAttribute("customerForm") @Validated CustomerDto customerDto,
			BindingResult bindingResult, RedirectAttributes attributes) throws ResourceNotFoundException {
		try {
			if (bindingResult.hasErrors()) {
				model.addAttribute("customerForm", customerDto);
				model.addAttribute("userForm", new User());
				return "login";
			}

			Customer customer = CustomerTransformer.customerDtoToCustomerEntity(customerDto);
			Customer c = customerRepository.save(customer);
			if (null != c) {
				attributes.addFlashAttribute("success", "Customer registred successfully!!");
			} else {
				attributes.addFlashAttribute("error", "Customer is not registered!! try again !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}

	@InitBinder("customerForm")
	public void customerFormValidation(WebDataBinder dataBinder) {
		dataBinder.setValidator(new CustomerValidator());
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public String exceptionHandling(Model model) {
		return "error";
	}
}
