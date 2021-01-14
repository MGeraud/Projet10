package com.geraud.ocr_bibliotheque.controllers;

import com.geraud.ocr_bibliotheque.assemblers.BookModelAssembler;
import com.geraud.ocr_bibliotheque.domain.Book;
import com.geraud.ocr_bibliotheque.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Controller pour récupérer et renvoyer le(s) livre(s) en fonction de différents critères
 * Todo : ajouter les méthodes pour le client des employés afin d'ajouter de nouveaux livres
 */
@RestController
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookModelAssembler bookModelAssembler;
    @Autowired
    PagedResourcesAssembler pagedResourcesAssembler;

    /**
     * requete recherche livre par son isbn
     *
     * @param isbn identifiant isbn du livre à rechercher
     * @return description du livre dont l'isbn correspont à celui recherché
     */
    @RequestMapping(value = "/books/{isbn}")
    public EntityModel<Book> showBookByIsbn(@PathVariable String isbn) {

        Book book = bookService.findByIsbn(isbn);
        return EntityModel.of(book,
                linkTo(methodOn(BookController.class).showBookByIsbn(isbn)).withSelfRel());
    }

    /**
     * Recherche de tous les livres méthode paginée pour ne pas tous les charger en une fois
     * @param page : pagination demandée, par défaut 1ère page de liste
     * @param size : nombre de livres retournés par requête, par défaut 5
     * @return page choisie  de la liste complète des livres (taille par défaut : 5 livres)
     */
    @RequestMapping(value = "/books")
    public PagedModel<EntityModel<Book>> showAllWithPagination(@RequestParam(value = "page", defaultValue = "0") int page,
                                                               @RequestParam(value = "size", defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page,size,Sort.by("idbook").descending());
        Page<Book> books = bookService.findAllWithPagination(pageable);

        return pagedResourcesAssembler.toModel(books , bookModelAssembler);
    }

    /**
     * requete recherche livres par auteur(s)
     *
     * @param page   numéro de page recherchée (par défaut 1ère page de recherche)
     * @param size   nombre de livres maximum par page (par défaut 10)
     * @param author critère de recherche pour les livres : auteur(s)
     * @return page contenant une liste de livres correspondant aux auteurs recherchés
     */
    @RequestMapping(value = "/books/author")
    public PagedModel<EntityModel<Book>> showByAuthor(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "size", defaultValue = "5") int size,
                                   @RequestParam("queryparam") String author) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books = bookService.findByAuthor(author, pageable);
        return pagedResourcesAssembler.toModel(books, bookModelAssembler) ;
    }

    /**
     * requete recherche livre par titre
     *
     * @param page  numéro de page recherchée (par défaut 1ère page de recherche)
     * @param size  nombre de livres maximum par page (par défaut 10)
     * @param title critère de recherche pour les livres : titre
     * @return page contenant une liste de livres correspondant au titre recherché
     */
    @RequestMapping(value = "/books/title")
    public PagedModel<EntityModel<Book>> showByTitle(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "5") int size,
                                  @RequestParam("queryparam") String title) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books = bookService.findByTitle(title, pageable);
        return pagedResourcesAssembler.toModel(books, bookModelAssembler);
    }

    /**
     * requete recherche livre par sujet(s)
     *
     * @param page  numéro de page recherchée (par défaut 1ère page de recherche)
     * @param size  nombre de livres maximum par page (par défaut 10)
     * @param topic critère de recherche pour les livres : sujet(s) du livre
     * @return page contenant une liste de livres correspondant au(x) sujet(s) recherché(s)
     */
    @RequestMapping(value = "/books/topic")
    public PagedModel<EntityModel<Book>> showByTopic(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "5") int size,
                                  @RequestParam("queryparam") String topic) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books = bookService.findByTopic(topic, pageable);
        return pagedResourcesAssembler.toModel(books, bookModelAssembler);
    }
}
