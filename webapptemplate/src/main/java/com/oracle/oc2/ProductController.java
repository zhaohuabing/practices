package com.oracle.oc2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	@RequestMapping("/products")
	public Product[] getProducts() {
		Product[] results = new Product[100];
		for (int i = 0; i < results.length; i++) {
			results[i] = new Product("FI-SW-0" + i, "Koi", "100", "1", "", "");
		}
		return results;
	}
}
