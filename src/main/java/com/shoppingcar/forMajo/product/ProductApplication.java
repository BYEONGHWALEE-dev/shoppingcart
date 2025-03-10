package com.shoppingcar.forMajo.product;

import com.shoppingcar.forMajo.product.dao.ProductDAO;
import com.shoppingcar.forMajo.product.entity.Cart;
import com.shoppingcar.forMajo.product.entity.Product;
import com.shoppingcar.forMajo.product.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);

	}


	@Bean
	CommandLineRunner commandLineRunner(ProductDAO productDAO) {
		return args -> {

			User theUser = productDAO.getUser("Majo");
			List<Product> theProducts = productDAO.getAllProductsFromCart(theUser);
			for (Product product : theProducts) {
				System.out.println(product.getName());


			}
		};
	}



	public void addUser(ProductDAO productDAO) {
		Cart cart = new Cart();

		String bcryptPassword = new BCryptPasswordEncoder().encode("ilovebabybunny");
		User user = new User("ByeongHwa", bcryptPassword, "USER");

		//associate objects
		user.setCart(cart);

		// add user into database
		productDAO.addUser(user);

	}


}
