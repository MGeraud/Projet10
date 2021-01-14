package com.geraud.ocr_webapp.controllers;

import com.geraud.ocr_webapp.model.Book;
import com.geraud.ocr_webapp.utils.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;


@Controller
@Slf4j
public class IndexController {
    @Value("${base.url.library}")
    private String libraryUrl;

    @Autowired
    RestTemplate restTemplate;
    @RequestMapping({"" , "/" , "/index" ,"/index.html"})
    public String index(Model model ){
        try {
            EntityModel<Book> books = restTemplate.getForObject(libraryUrl, EntityModel.class);
            model.addAttribute("books", books);
        }catch (Exception e){
            log.error("Erreur serveur : " + e.getMessage());
            return "redirect:/errorPage";
        }
        model.addAttribute("identifiants", new Login());

        return "index";
    }
}
