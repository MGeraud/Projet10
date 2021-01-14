package com.geraud.ocr_bibliotheque.dao;

import com.geraud.ocr_bibliotheque.domain.Author;
import com.geraud.ocr_bibliotheque.domain.Book;
import com.geraud.ocr_bibliotheque.domain.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookDao extends JpaRepository<Book, Long> {

    Optional<Book> findBookByIsbn(String isbn);

    Page<Book> findBooksByTitleContainsIgnoreCase(String title, Pageable pageable);

    Page<Book> findDistinctByAuthorsIn(List<Author> authors, Pageable pageable);

    Page<Book> findDistinctByTopicsIn(List<Topic> topics, Pageable pageable);
}
