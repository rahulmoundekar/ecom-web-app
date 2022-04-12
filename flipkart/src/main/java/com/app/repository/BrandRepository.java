package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

}
