package com.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.dto.AddToCart;
import com.app.dto.ProductDto;
import com.app.entity.Brand;
import com.app.entity.Cart;
import com.app.entity.Customer;
import com.app.entity.Product;
import com.app.repository.BrandRepository;
import com.app.repository.CartRepository;
import com.app.repository.CustomerRepository;
import com.app.repository.ProductRepository;
import com.app.transformer.ProductTransformer;

@Controller
public class ProudctController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	CartRepository cartRepository;

	@GetMapping(value = "addProduct")
	public String product(Model model) {
		model.addAttribute("productForm", new ProductDto());
		return "addProduct";
	}

	@ModelAttribute("brandList")
	public List<Brand> getListOfBrands() {
		return brandRepository.findAll();
	}

	@PostMapping(value = "saveProduct")
	public String saveProduct(Model model, @ModelAttribute("productForm") ProductDto productDto,
			RedirectAttributes attributes) {
		Product product = ProductTransformer.productDtoToProductEntity(productDto);
		Product p = productRepository.save(product);
		if (null != p) {
			attributes.addFlashAttribute("success", "Product saved successfully!!");
		} else {
			attributes.addFlashAttribute("error", "Product is not Saved! try again !!!");
		}
		return "redirect:/addProduct";
	}

	@GetMapping(value = "addToCart")
	public String addToCart(Model model, @RequestParam("id") Integer id, HttpSession session,
			RedirectAttributes attributes) {
		Integer customerId = (Integer) session.getAttribute("customerId");
		Product product = null;
		Customer customer = null;
		if (null != id) {
			product = productRepository.getById(id);
		}
		if (null != customerId) {
			customer = customerRepository.getCustomerById(customerId);
		}
		if (product != null && customer != null) {
			Cart cart = new Cart();
			cart.setProduct(product);
			cart.setCustomer(customer);
			Cart c = cartRepository.save(cart);
			if (null != c) {
				List<Cart> cartList = cartRepository.findCartByCustomerId(customerId);
				model.addAttribute("cartList", cartList);
				model.addAttribute("cartForm", new AddToCart());
			} else {
				attributes.addFlashAttribute("error", "something went wrong, try again!!");
			}
		}
		return "cart";
	}

	@GetMapping(value = "viewCart")
	public String viewCart(Model model, HttpSession session) {
		Integer customerId = (Integer) session.getAttribute("customerId");
		List<Cart> cartList = cartRepository.findCartByCustomerId(customerId);
		model.addAttribute("cartList", cartList);
		model.addAttribute("cartForm", new AddToCart());
		return "cart";
	}

	@PostMapping(value = "generateBill")
	public String generateBill(Model model, @ModelAttribute AddToCart addToCart, HttpSession httpSession) {
		Integer customerId = (Integer) httpSession.getAttribute("customerId");

		List<String> qtys = Arrays.asList(addToCart.getQuantity().split(","));
		List<String> cartIds = Arrays.asList(addToCart.getCartId().split(","));

		int id = 0;
		List<AddToCart> toCarts = new ArrayList<AddToCart>();
		for (String cartId : cartIds) {
			AddToCart atc = new AddToCart();
			atc.setCId(Integer.parseInt(cartId));
			atc.setQty(Integer.parseInt(qtys.get(id)));
			toCarts.add(atc);
			id++;
		}

		for (AddToCart addToCart2 : toCarts) {
			Cart cart = cartRepository.findById(addToCart2.getCId()).get();
			cart.setQty(addToCart2.getQty());
			cartRepository.save(cart);
		}

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		double cartSubTotal = 0d;
		double exoTax = 100d;
		
		List<Cart> cartList = cartRepository.findCartByCustomerId(customerId);
		for (Cart cart : cartList) {
			double finalProductPrice = cart.getQty() * cart.getProduct().getPrice();
			cartSubTotal = cartSubTotal + finalProductPrice;
		}

		map.put("Cart Sub Total", cartSubTotal);
		map.put("Extra Tax", exoTax);

		double total = cartSubTotal + exoTax;

		map.put("Total", total);

		model.addAttribute("cartList", cartList);
		model.addAttribute("grandBill", map);

		return "bill";
	}

}
