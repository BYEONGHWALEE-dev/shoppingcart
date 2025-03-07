package com.shoppingcar.forMajo.product.controller;

import com.shoppingcar.forMajo.product.dao.ProductDAO;
import com.shoppingcar.forMajo.product.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

@Controller
public class LoginController {

    private final View error;
    ProductDAO productDAO;

    public LoginController(ProductDAO productDAO, View error) {
        this.productDAO = productDAO;
        this.error = error;
    }

    @GetMapping("/start-page")
    public String startPage() {
        return "start-page";
    }

    @GetMapping("/login-page")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if(error != null) {
            model.addAttribute("errorMessage", "Name or Password is invalid");
        }
        return "login-page";
    }
}
