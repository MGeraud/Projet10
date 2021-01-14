package com.geraud.ocr_bibliotheque.services;

import com.geraud.ocr_bibliotheque.dao.AuthorDao;
import com.geraud.ocr_bibliotheque.dao.BookDao;
import com.geraud.ocr_bibliotheque.dao.TopicDao;
import com.geraud.ocr_bibliotheque.domain.Author;
import com.geraud.ocr_bibliotheque.domain.Book;
import com.geraud.ocr_bibliotheque.domain.Topic;
import com.geraud.ocr_bibliotheque.exceptions.ResultNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private TopicDao topicDao;

    /**
     * @param isbn du livre recherché
     * @return livre avec l'isbn recherché
     */
    @Override
    public Book findByIsbn(String isbn) {

        Optional<Book> optionalBook = bookDao.findBookByIsbn(isbn);
        if (!optionalBook.isPresent()) {
            throw new ResultNotFoundException("Livre non trouvé");
        }
        return optionalBook.get();
    }

    /**
     * @param title    titre du livre recherché
     * @param pageable objet de type Pageable contenant le numéro de page et le nombre d'objet par page
     * @return Page contenant une liste de livres dont le titre est défini dans le paramètre title , limitée à la taille choisie
     */
    @Override
    public Page<Book> findByTitle(String title, Pageable pageable) {
        return bookDao.findBooksByTitleContainsIgnoreCase(title, pageable);
    }

    @Override
    public Page<Book> findAllWithPagination(Pageable pageable) {
        return bookDao.findAll(pageable);
    }


    /**
     * Méthode de recherche de livres par auteur avec pagination
     *
     * @param name     : nom et/ou prenom de l'auteur (en entier ou partiel)
     * @param pageable objet de type Pageable contenant le numéro de page et le nombre d'objet par page
     * @return Page contenant une liste de livres dont le(s) auteur(s) sont définis dans le paramètre name , limitée à la taille choisie
     */
    @Override
    public Page<Book> findByAuthor(String name, Pageable pageable) {
        /*Décomposition de l'entrée name en mots distincts les uns des autres (présupposés nom et prénom) */
        List<String> wordByWord = Arrays.asList(name.split("[ ]"));

        /*Création d'une liste d'auteurs dont l'un des mots correspond au nom ou prénom d'un auteur de la base de données  */
        List<Author> authors = new ArrayList<>();
        for (String word : wordByWord) {
            List<Author> foundAuthors = authorDao.findAuthorsByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(word, word);
            authors.addAll(foundAuthors);
        }

        /*recherche des livres correspondants à l'un des auteurs de la liste trouvée */
        return bookDao.findDistinctByAuthorsIn(authors, pageable);
    }

    /**
     * Méthode de recherche de livres par sujet avec pagination
     *
     * @param topic    sujet(s) à rechercher
     * @param pageable objet de type Pageable contenant le numéro de page et le nombre d'objet par page
     * @returnPage contenant une liste de livres dont le(s) sujet(s) sont définis dans le paramètre topic , limitée à la taille choisie
     */
    @Override
    public Page<Book> findByTopic(String topic, Pageable pageable) {
        /*Décomposition de l'entrée topic en mots distincts les uns des autres */
        List<String> keywords = Arrays.asList(topic.split("[ ]"));

        /*Création d'une liste de sujets dont l'un des mots correspond à un sujet en base de données  */
        List<Topic> topics = new ArrayList<>();
        for (String keyword : keywords) {
            List<Topic> foundTopics = topicDao.findTopicByKeywordContains(keyword);
            topics.addAll(foundTopics);
        }

        /*recherche des livres correspondants à l'un des sujets de la liste trouvée */
        return bookDao.findDistinctByTopicsIn(topics, pageable);
    }
}
