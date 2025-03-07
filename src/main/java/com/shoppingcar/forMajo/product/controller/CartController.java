package com.shoppingcar.forMajo.product.controller;

import com.shoppingcar.forMajo.product.dao.ProductDAO;
import com.shoppingcar.forMajo.product.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {

    ProductDAO productDAO;

    public CartController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping("/start-page")
    public String startPage() {
        return "start-page";
    }

    @GetMapping("/login-page")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());

        return "login-page";
    }

    @GetMapping("/shopping-homepage")
    public String shoppingHomepage(Model model) {
        model.addAttribute("theProducts", productDAO.getAllProducts());

        return "shopping-homepage";
    }
}
