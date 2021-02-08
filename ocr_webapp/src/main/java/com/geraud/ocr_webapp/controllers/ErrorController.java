package com.geraud.ocr_webapp.controllers;

import com.geraud.ocr_webapp.utils.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ErrorController {

    @GetMapping("/errorPage")
    public String errorPage(Model model){
        model.addAttribute("identifiants", new Login());
        return "errorPage";
    }

    @GetMapping("/alreadyBooked")
    public String alredayBookedError (@ModelAttribute("identifiants")Login login, Model model){

        model.addAttribute("identifiants" , login);
        return  "alreadyBooked";
    }
}
