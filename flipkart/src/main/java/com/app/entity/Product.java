package com.app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Setter
@Getter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "qty")
	private int qty;

	@Column(name = "price")
	private double price;
	
	@Column(name = "img")
	private String img;

	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;

	/*
	 * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy =
	 * "product") private List<Cart> cartProductList;
	 */

	/*
	 * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy =
	 * "product") private List<CustomerOrderHst> CustomerOrderHstList;
	 */
}
