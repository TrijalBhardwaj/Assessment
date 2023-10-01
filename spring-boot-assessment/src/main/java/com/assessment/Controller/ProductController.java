package com.assessment.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.assessment.Entity.Product;
import com.assessment.Repository.ProductRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	// Spring Boot Ques 1 Ans : Find All Products With Price Greater Than Specified Value
	@GetMapping("/findAllProductsWithPriceGreaterThan/{price}")
	public List<Product> findAllProductsWithPriceGreaterThan(@PathVariable double price) {
		return productRepository.findAllProductsWithPriceGreaterThanSpecifiedValue(price);
	}

	// Spring Boot Ques 2 Ans : Find All Products With Price In Between Specified Price Range Values
	@GetMapping("/findAllProductsWithPriceInBetweenRange")
	public List<Product> findAllProductsWithPriceInBetweenRange(@RequestParam(name = "minPrice") double minPrice, @RequestParam(name = "maxPrice") double maxPrice) {
	    return productRepository.findAllProductsWithPriceInBetweenRange(minPrice, maxPrice);
	}

	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody @Valid Product newProductToAdd) {
		return productRepository.save(newProductToAdd);
	}

	@GetMapping(value = "/{productId}")
	public Product getProductById(@PathVariable String productId) {
		return productRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Product Not Found With productId = " + productId));
	}

	@GetMapping("/allProducts")
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Transactional
	@PatchMapping("/updateProductName/{productId}")
	public Product updateProductName(@PathVariable String productId, @RequestParam String newProductName) {
		productRepository.updateProductName(productId, newProductName);
		return getProductById(productId);
	}

	@Transactional
	@PatchMapping("/updateProductPrice/{productId}")
	public Product updateProductPrice(@PathVariable String productId, @RequestParam double newProductPrice) {
		productRepository.updateProductPrice(productId, newProductPrice);
		return getProductById(productId);
	}

	@Transactional
	@PatchMapping("/updateProductQuantity/{productId}")
	public Product updateProductQuantity(@PathVariable String productId, @RequestParam int newProductQuantity) {
		productRepository.updateProductQuantity(productId, newProductQuantity);
		return getProductById(productId);
	}

	@DeleteMapping(value = "/deleteProduct/{productId}")
	public void deleteProduct(@PathVariable String productId) {
		productRepository.deleteById(productId);
	}

}
