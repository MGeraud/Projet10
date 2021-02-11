package com.geraud.ocr_bibliotheque.services;

import com.geraud.ocr_bibliotheque.dao.BookDao;
import com.geraud.ocr_bibliotheque.domain.Book;
import com.geraud.ocr_bibliotheque.exceptions.ResultNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BookServiceImplTest {

    private Book book1;


    @Mock
    private BookDao bookDaoMock;


    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        book1 = new Book();
        book1.setIsbn("3456");
        book1.setTitle("Book 1");

    }

    /**
     * test recherche par Isbn avec résultat attendu
     */
    @Test
    void findByIsbn() {
        Optional<Book> optionalBook = Optional.of(book1);
        when(bookDaoMock.findBookByIsbn(any(String.class))).thenReturn(optionalBook);
        assumeTrue("Book 1".equals(bookServiceImpl.findByIsbn("3456").getTitle()) );
    }

    /**
     * Test recherche par isbn renvoi ResultNotFoundException quand livre non trouvé
     */
    @Test()
    void findByIsbnThrowException(){
        Optional<Book> optionalBook = Optional.empty();
        when(bookDaoMock.findBookByIsbn(any(String.class))).thenReturn(optionalBook);
        Assertions.assertThrows(ResultNotFoundException.class , () -> {
            bookServiceImpl.findByIsbn("3456");
        });
    }

    /**
     * test de recherche par titre renvoi un Page<Book> correct
     */
    @Test
    void findByTitle() {

        Pageable pageable = PageRequest.of(0,2);
        Page<Book> bookPage = new PageImpl<Book>(List.of(book1) ,pageable,1);
        when(bookDaoMock.findBooksByTitleContainsIgnoreCase(any(String.class), any(Pageable.class))).thenReturn(bookPage);

        assertEquals(1 , bookServiceImpl.findByTitle("ddd" , PageRequest.of(2 , 3)).getTotalElements());
        assertEquals("Book 1" , bookServiceImpl.findByTitle("fff" , PageRequest.of(1,2)).get().findFirst().get().getTitle());
    }


}