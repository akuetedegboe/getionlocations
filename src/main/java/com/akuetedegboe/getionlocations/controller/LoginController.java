package com.akuetedegboe.getionlocations.controller;

import com.akuetedegboe.getionlocations.model.Employe;
import com.akuetedegboe.getionlocations.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    EmployeService employeService;

    Employe currentUser;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    @GetMapping("/logout")
    public String showLogoutForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(name = "loginForm") Employe employeLoged, Model m) {
        String redirection = "";

        currentUser = employeService.getConnect(employeLoged.getLogin(), employeLoged.getMdp());
        if (currentUser == null) {
            m.addAttribute("error", "Incorrect Username & Password");
            redirection = "/login";
        } else {
            m.addAttribute("login", employeLoged.getLogin());
            m.addAttribute("mdp", employeLoged.getMdp());
            m.addAttribute("employeId", employeLoged.getId());
            m.addAttribute("user", currentUser.getId());

            redirection = switch (currentUser.getPoste().toString()) {
                case "DIRECTEUR" -> "redirect:/employe/";
                case "RESPONSABLE" -> "redirect:/voiture/";
                case "SUBORDONNE" -> "redirect:/client/";
                default -> "redirect:/";
            };

        }
        return redirection;

    }

}
