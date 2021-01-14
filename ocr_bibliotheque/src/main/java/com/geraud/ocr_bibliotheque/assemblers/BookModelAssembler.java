package com.geraud.ocr_bibliotheque.assemblers;

import com.geraud.ocr_bibliotheque.controllers.BookController;
import com.geraud.ocr_bibliotheque.domain.Book;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookModelAssembler implements RepresentationModelAssembler<Book , EntityModel<Book>> {

    /**
     * Creation du lien hypermédia vers le livre affiché
     * @param book l'instance de l'objet livre sélectionnée
     * @return
     */
    @Override
    public EntityModel<Book> toModel(Book book) {
        return EntityModel.of(book ,
                linkTo(methodOn(BookController.class).showBookByIsbn(book.getIsbn())).withSelfRel());
    }
}
