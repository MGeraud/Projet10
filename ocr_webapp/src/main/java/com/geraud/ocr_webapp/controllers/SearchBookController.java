package com.geraud.ocr_webapp.controllers;

import com.geraud.ocr_webapp.model.Book;
import com.geraud.ocr_webapp.model.Stock;
import com.geraud.ocr_webapp.utils.Login;
import com.geraud.ocr_webapp.utils.SearchBookParameters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class SearchBookController {

    @Value("${base.url.library}")
    private String libraryUrl;
    @Value("${base.url.loan}")
    private String loanUrl;

    @Autowired
    RestTemplate restTemplate;

    /**
     * Page de recherche de livres d'après un formulaire à 2 entrées
     * @param model
     * @return
     */
    @RequestMapping("/search")
    public String searchForm(Model model) {

        model.addAttribute("searchBookParameters" , new SearchBookParameters());
        model.addAttribute("identifiants", new Login());
        return "searchBook";
    }


    /**
     * Recherche des livres en fonctions des paramètres indiqués
     * @param searchBookParameters Objet regroupant les 2 paramètres de recheche : le type de recherche (Auteur, titre, sujet)
     *                             et le champ de texte à entrer
     * @param model attribution des entités de Book récupérés via l'api REST bibliothèque
     * @return première page des résultat de recherche de liste de livre en fonction des critères choisis
     */
    @RequestMapping("/foundBook")
    public String foundBook(@ModelAttribute("searchBookParameters") SearchBookParameters searchBookParameters,
                            Model model){

        //création de l'url à appeler à partir des critères de recherche récupérés via l'objet SearchBookParameters
        String url = UriComponentsBuilder.fromHttpUrl(libraryUrl + searchBookParameters.getSearchType())
                    .queryParam("queryparam" , searchBookParameters.getSearchInput())
                    .toUriString();
        try {
            //Appel de l'API rest et récupération sous forme d'EntityModel (objet de Spring Hateaos)
            EntityModel<Book> foundentities = restTemplate.getForObject(url, EntityModel.class);
            model.addAttribute("entities", foundentities);
        }catch (Exception e){
            log.error("Erreur serveur : " + e.getMessage());
            return "redirect:/errorPage";
        }
        model.addAttribute("identifiants", new Login());
        return "searchBook";
    }


    /**
     * Rafraichissement de la page pour afficher la liste de livre correspondant à la page suivante
     * ou précédente via les liens hypermédia générés par l'api REST
     * @param linkUrl : lien hypermédia généré par l'api rest
     * @param model : attribution des entités de Book récupérés via l'api REST bibliotheque
     * @return page de recherche (suivante ou précédente) selon le bouton clické
     */
    @RequestMapping("/refreshBook")
    public String refreshResult(@RequestParam(value = "refresh" ) String linkUrl, Model model  ) {


        //Appel de l'API rest directement depuis le lien hypermédia récupéré et récupération sous forme d'EntityModel (objet de Spring Hateaos)
        try {
            EntityModel<Book> foundentities = restTemplate.getForObject(linkUrl, EntityModel.class);
            model.addAttribute("entities" , foundentities);
        }catch (Exception e){
            log.error("Erreur serveur : " + e.getMessage());
            return "redirect:/errorPage";
        }

        model.addAttribute("searchBookParameters" , new SearchBookParameters());
        model.addAttribute("identifiants", new Login());
        return "searchBook";
    }

    /**
     * Affichage du livre sélectionné par son lien hateoas
     * @param urlLink lien vers les détails du livres appellés sur l'api Rest ocr-loan-api
     * @param model attribution des différents attributs récupérés pour envoi vers la page html
     * @return page décrivant les détails du livre sélectionné
     */
    @RequestMapping("/book")
    public String showBook(@RequestParam("urlLink") String urlLink,
                           Model model) {
        //récupération du livre et de ses détails
        Book book = restTemplate.getForObject(urlLink, Book.class);

        //Map key = label du livre (identifiant unique de l'exemplaire) value = booléen (true = disponible, false=indisponible)
        Map<String, String> available = new HashMap<>();
        //Pour chaque label, appel de l'api rest des emprunts pour vérification si exemplaire en cours d'emprunt (donc non disponible)
        for ( Stock stock:book.getStocks()
             ) {
            String url = UriComponentsBuilder.fromHttpUrl(loanUrl + stock.getLabel())
                    .toUriString();
            try {
                String availability = restTemplate.getForObject(url, String.class);
                available.put(stock.getLabel(), availability);
            }catch (Exception e){
                log.error("Erreur serveur : " + e.getMessage());
                return "redirect:/errorPage";
            }



        }
        model.addAttribute("available" , available );
        model.addAttribute("book" , book);
        model.addAttribute("identifiants", new Login());

        return "book";
    }
}
