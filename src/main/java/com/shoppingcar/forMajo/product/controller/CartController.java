package com.shoppingcar.forMajo.product.controller;

import com.shoppingcar.forMajo.product.dao.ProductDAO;
import com.shoppingcar.forMajo.product.entity.Cart;
import com.shoppingcar.forMajo.product.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class CartController {

    ProductDAO productDAO;

    public CartController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping("/shopping-homepage")
    public String shoppingHomepage(Model model) {
        model.addAttribute("theProducts", productDAO.getAllProducts());

        return "shopping-homepage";
    }

    @PostMapping("/addintocart")
    @ModelAttribute("product")
    public String addintocart(Authentication authentication) {
        User theUser = (User) authentication.getPrincipal();
        Cart theCart = theUser.getCart();

        productDAO.addIntoCart(theCart, prod);
    }

}
