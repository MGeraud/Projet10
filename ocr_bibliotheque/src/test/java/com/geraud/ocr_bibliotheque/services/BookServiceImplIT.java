package com.geraud.ocr_bibliotheque.services;


import com.geraud.ocr_bibliotheque.domain.Book;
import com.geraud.ocr_bibliotheque.exceptions.ResultNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
/**
 * classe test intégration de BookService
 * utilisation du profil test pour accès à la base de données de test
 */
public class BookServiceImplIT {

    @Autowired
    private BookService bookService;
    private Book book;
    private Page<Book> bookPage;

    @Test
    void findByIsbnThrowExceptionIT(){
        Assertions.assertThrows(ResultNotFoundException.class , () -> {
            bookService.findByIsbn("3456");
        });
    }

    @Test
    void findByIsbnIT(){
        book = bookService.findByIsbn("2409019536");
        assertEquals("Apache Maven" , book.getTitle());
    }

    @Test
    void findByTitleIT(){
        Pageable pageable =  PageRequest.of(0 , 5);
        bookPage = bookService.findByTitle("seigneur" , pageable);
        assertEquals(6 , bookPage.getTotalElements());
        assertEquals(2 , bookPage.getTotalPages());
        assertTrue(bookPage.get().findFirst().get().getTitle().contains("anneaux"));
    }

    @Test
    void findByCompleteTitleIT(){
        Pageable pageable =  PageRequest.of(0 , 5);
        bookPage = bookService.findByTitle("Le Seigneur des anneaux - Tome 3" , pageable);
        assertEquals(2 , bookPage.getTotalElements());
        assertEquals(1 , bookPage.getTotalPages());
    }


}
