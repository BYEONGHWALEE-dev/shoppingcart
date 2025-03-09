package com.shoppingcar.forMajo.product.controller;

import com.shoppingcar.forMajo.product.dao.ProductDAO;
import com.shoppingcar.forMajo.product.entity.Cart;
import com.shoppingcar.forMajo.product.entity.Product;
import com.shoppingcar.forMajo.product.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/add-to-cart")
    public String addintocart(@RequestParam String name) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Cart theCart = productDAO.getUser(userName).getCart();

        // product_name을 사용해서 product 객체 가져오기
        Product theProduct = productDAO.getProduct(name);
        productDAO.addIntoCart(theCart, theProduct);

        return "redirect:/shopping-homepage";
    }

}
