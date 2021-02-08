package com.geraud.ocr_bibliotheque.services;

import com.geraud.ocr_bibliotheque.dao.BookDao;
import com.geraud.ocr_bibliotheque.domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
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

    @Test
    void findByIsbn() {
        Optional<Book> optionalBook = Optional.of(book1);

        Mockito.when(bookDaoMock.findBookByIsbn(any(String.class))).thenReturn(optionalBook);

        assumeTrue("Book 1".equals(bookServiceImpl.findByIsbn("3456").getTitle()) );
    }

    @Test
    void findByTitle() {

    }

    @Test
    void findAllWithPagination() {
    }

    @Test
    void findByAuthor() {
    }

    @Test
    void findByTopic() {
    }
}