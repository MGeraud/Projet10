package com.geraud.ocr_bibliotheque.services;

import com.geraud.ocr_bibliotheque.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BookService {

    Book findByIsbn(String isbn);

    Page<Book> findByTitle(String title, Pageable pageable);

    Page<Book> findAllWithPagination(Pageable pageable);

    Page<Book> findByAuthor(String name, Pageable pageable);

    Page<Book> findByTopic(String topic, Pageable pageable);
}
