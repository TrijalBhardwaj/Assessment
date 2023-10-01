package com.assessment.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assessment.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

	// Spring Boot Ques 1 Ans : Find All Products With Price Greater Than Specified Value
	@Query("SELECT p FROM Product p WHERE p.productPrice > :specifiedPrice")
	List<Product> findAllProductsWithPriceGreaterThanSpecifiedValue(@Param("specifiedPrice") double specifiedPrice);

	// Spring Boot Ques 2 Ans : Find All Products With Price In Between Specified Price Range Values
	@Query("SELECT p FROM Product p WHERE p.productPrice BETWEEN :minPrice AND :maxPrice")
	List<Product> findAllProductsWithPriceInBetweenRange(double minPrice, double maxPrice);

	Product save(Product newProductToAdd);

	Optional<Product> findById(String productId);

	List<Product> findAll();

	@Modifying
	@Query("UPDATE Product p SET p.productName = :newProductName WHERE p.productId = :productId")
	int updateProductName(@Param("productId") String productId, @Param("newProductName") String newProductName);

	@Modifying
	@Query("UPDATE Product p SET p.productPrice = :newProductPrice WHERE p.productId = :productId")
	int updateProductPrice(@Param("productId") String productId, @Param("newProductPrice") double newProductPrice);

	@Modifying
	@Query("UPDATE Product p SET p.productQuantity = :newProductQuantity WHERE p.productId = :productId")
	int updateProductQuantity(@Param("productId") String productId,
			@Param("newProductQuantity") int newProductQuantity);

	void deleteById(String productId);

}
