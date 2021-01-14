INSERT INTO topic (keyword)
VALUES ('informatique'),
       ('maven'),
       ('java'),
       ('asterix'),
       ('obelix'),
       ('anneau'),
       ('fantastique');


INSERT INTO publisher (name)
VALUES ('Pocket');
INSERT INTO publisher (name)
VALUES ('Gallimard jeunesse');
INSERT INTO publisher (name)
VALUES ('ENI Editions');
INSERT INTO publisher (name)
VALUES ('Albert Rene');

INSERT INTO author (firstname, lastname)
VALUES ('Rene', 'Goscinny');
INSERT INTO author (firstname, lastname)
VALUES ('Albert', 'Uderzo');
INSERT INTO author (firstname, lastname)
VALUES ('Didier', 'Conrad');
INSERT INTO author (firstname, lastname)
VALUES ('Jean-Yves', 'Ferri');
INSERT INTO author (firstname, lastname)
VALUES ('JRR', 'Tolkien');
INSERT INTO author (firstname, lastname)
VALUES ('Etienne', 'Langlet');
INSERT INTO author (firstname, lastname)
VALUES ('Maxime', 'Gréau');

INSERT INTO library (address, library_name, opening_hours)
VALUES ('rue autre', 'Grande librairie de la ville', '9h-21h');
INSERT INTO library (address, library_name, opening_hours)
VALUES ('rue minus', 'Petite librairie de la ville', '9h-21h');
INSERT INTO library (address, library_name, opening_hours)
VALUES ('rue encore une', 'La 3eme de la ville', '8h-12h  14h-18h');

INSERT INTO book (isbn, description, image, title, year, category, publisher_id_publisher)
VALUES ('2409019536', 'Apache Maven la description ...... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.',
        'https://static.fnac-static.com/multimedia/Images/FR/NR/0e/96/a9/11113998/1507-1/tsp20190606164132/Apache-Maven.jpg',
        'Apache Maven',
        '2019',
        'LOISIRS',
        (SELECT id_publisher FROM publisher WHERE name = 'ENI Editions'));
INSERT INTO stock(idbook, id_library, label, pillar, shelf, wings)
VALUES ((SELECT idbook FROM book WHERE isbn = '2409019536'),
        (SELECT id_library FROM library where library_name = 'Petite librairie de la ville')
           , 'APA1234', '54a0', '2A', 'allee E');
INSERT INTO book_author (idbook, id_author)
VALUES ((SELECT idbook FROM book WHERE isbn = '2409019536'),
        (SELECT id_author from author where firstname = 'Etienne')),
       ((SELECT idbook FROM book WHERE isbn = '2409019536'),
        (SELECT id_author from author where firstname = 'Maxime'));
INSERT INTO book_topics (idbook, id_topics)
VALUES ((SELECT idbook FROM book WHERE isbn = '2409019536'), (SELECT id_topic from topic where keyword = 'maven')),
       ((SELECT idbook FROM book WHERE isbn = '2409019536'),
        (SELECT id_topic from topic where keyword = 'informatique')),
       ((SELECT idbook FROM book WHERE isbn = '2409019536'), (SELECT id_topic from topic where keyword = 'java'));

INSERT INTO book (isbn, description, image, title, year, category, publisher_id_publisher)
VALUES ('2864973235', 'Asterix papyrus description ...... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.',
        'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQPnqLg-ZNDp-gCozCKJcC_dCNJp_rhRMAyLw&usqp=CAU',
        'Asterix : le Papyrus de César',
        '2015',
        'BD',
        (SELECT id_publisher FROM publisher WHERE name = 'Albert Rene'));
INSERT INTO stock(idbook, id_library, label, pillar, shelf, wings)
VALUES ((SELECT idbook FROM book WHERE isbn = '2864973235'),
        (SELECT id_library FROM library where library_name = 'Petite librairie de la ville')
           , 'AST1234', '54a0', '2A', 'allee E');
INSERT INTO stock(idbook, id_library, label, pillar, shelf, wings)
VALUES ((SELECT idbook FROM book WHERE isbn = '2864973235'),
        (SELECT id_library FROM library where library_name = 'Grande librairie de la ville')
           , 'AST1234A', '54a0', '2A', 'allee E');
INSERT INTO stock(idbook, id_library, label, pillar, shelf, wings)
VALUES ((SELECT idbook FROM book WHERE isbn = '2864973235'),
        (SELECT id_library FROM library where library_name = 'La 3eme de la ville')
           , 'AST1234C', '54a0', '2A', 'allee E');
INSERT INTO book_author (idbook, id_author)
VALUES ((SELECT idbook FROM book WHERE isbn = '2864973235'),
        (SELECT id_author from author where firstname = 'Jean-Yves')),
       ((SELECT idbook FROM book WHERE isbn = '2864973235'), (SELECT id_author from author where firstname = 'Rene')),
       ((SELECT idbook FROM book WHERE isbn = '2864973235'),
        (SELECT id_author from author where firstname = 'Albert')),
       ((SELECT idbook FROM book WHERE isbn = '2864973235'),
        (SELECT id_author from author where firstname = 'Didier'));
INSERT INTO book_topics (idbook, id_topics)
VALUES ((SELECT idbook FROM book WHERE isbn = '2864973235'), (SELECT id_topic from topic where keyword = 'asterix')),
       ((SELECT idbook FROM book WHERE isbn = '2864973235'), (SELECT id_topic from topic where keyword = 'obelix'));

INSERT INTO book (isbn, description, image, title, year, category, publisher_id_publisher)
VALUES ('2864973464', 'Asterix Le Menhir d Or description ...... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.',
        'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQPnqLg-ZNDp-gCozCKJcC_dCNJp_rhRMAyLw&usqp=CAU',
        'Asterix : Le Menhir d Or',
        '2020',
        'BD',
        (SELECT id_publisher FROM publisher WHERE name = 'Albert Rene'));
INSERT INTO stock(idbook, id_library, label, pillar, shelf, wings)
VALUES ((SELECT idbook FROM book WHERE isbn = '2864973464'),
        (SELECT id_library FROM library where library_name = 'Petite librairie de la ville')
           , 'AST5678', '54a0', '2A', 'allee E');
INSERT INTO stock(idbook, id_library, label, pillar, shelf, wings)
VALUES ((SELECT idbook FROM book WHERE isbn = '2864973464'),
        (SELECT id_library FROM library where library_name = 'Grande librairie de la ville')
           , 'AST5678A', '54a0', '2A', 'allee E');
INSERT INTO book_author (idbook, id_author)
VALUES ((SELECT idbook FROM book WHERE isbn = '2864973464'), (SELECT id_author from author where firstname = 'Rene')),
       ((SELECT idbook FROM book WHERE isbn = '2864973464'),
        (SELECT id_author from author where firstname = 'Albert'));
INSERT INTO book_topics (idbook, id_topics)
VALUES ((SELECT idbook FROM book WHERE isbn = '2864973464'), (SELECT id_topic from topic where keyword = 'asterix')),
       ((SELECT idbook FROM book WHERE isbn = '2864973464'), (SELECT id_topic from topic where keyword = 'obelix'));


INSERT INTO book (isbn, description, image, title, year, category, publisher_id_publisher)
VALUES ('2864971534', 'Astérix et la rentrée gauloise description ...... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.',
        'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQPnqLg-ZNDp-gCozCKJcC_dCNJp_rhRMAyLw&usqp=CAU',
        'Astérix et la rentrée gauloise',
        '2003',
        'BD',
        (SELECT id_publisher FROM publisher WHERE name = 'Albert Rene'));
INSERT INTO stock(idbook, id_library, label, pillar, shelf, wings)
VALUES ((SELECT idbook FROM book WHERE isbn = '2864971534'),
        (SELECT id_library FROM library where library_name = 'Petite librairie de la ville')
           , 'AST9011', '54a0', '2A', 'allee E');
INSERT INTO stock(idbook, id_library, label, pillar, shelf, wings)
VALUES ((SELECT idbook FROM book WHERE isbn = '2864971534'),
        (SELECT id_library FROM library where library_name = 'Grande librairie de la ville')
           , 'AST9011A', '54a0', '2A', 'allee E');
INSERT INTO stock(idbook, id_library, label, pillar, shelf, wings)
VALUES ((SELECT idbook FROM book WHERE isbn = '2864971534'),
        (SELECT id_library FROM library where library_name = 'La 3eme de la ville')
           , 'AST9011C', '54a0', '2A', 'allee E');
INSERT INTO book_author (idbook, id_author)
VALUES ((SELECT idbook FROM book WHERE isbn = '2864971534'), (SELECT id_author from author where firstname = 'Rene')),
       ((SELECT idbook FROM book WHERE isbn = '2864971534'),
        (SELECT id_author from author where firstname = 'Albert'));
INSERT INTO book_topics (idbook, id_topics)
VALUES ((SELECT idbook FROM book WHERE isbn = '2864971534'), (SELECT id_topic from topic where keyword = 'asterix')),
       ((SELECT idbook FROM book WHERE isbn = '2864971534'), (SELECT id_topic from topic where keyword = 'obelix'));

INSERT INTO book (isbn, description, image, title, year, category, publisher_id_publisher)
VALUES ('2070612902', 'Le Seigneur des anneaux - Tome 3 description ...... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.',
        'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQPnqLg-ZNDp-gCozCKJcC_dCNJp_rhRMAyLw&usqp=CAU',
        'Le Seigneur des anneaux - Tome 3',
        '2019',
        'LITTERATURE',
        (SELECT id_publisher FROM publisher WHERE name = 'Gallimard jeunesse'));
INSERT INTO stock(idbook, id_library, label, pillar, shelf, wings)
VALUES ((SELECT idbook FROM book WHERE isbn = '2070612902'),
        (SELECT id_library FROM library where library_name = 'Petite librairie de la ville')
           , 'SA123', '54a0', '2A', 'allee E');
INSERT INTO stock(idbook, id_library, label, pillar, shelf, wings)
VALUES ((SELECT idbook FROM book WHERE isbn = '2070612902'),
        (SELECT id_library FROM library where library_name = 'La 3eme de la ville')
           , 'SA123C', '54a0', '2A', 'allee E');
INSERT INTO book_author (idbook, id_author)
VALUES ((SELECT idbook FROM book WHERE isbn = '2070612902'), (SELECT id_author from author where firstname = 'JRR'));
INSERT INTO book_topics (idbook, id_topics)
VALUES ((SELECT idbook FROM book WHERE isbn = '2070612902'),
        (SELECT id_topic from topic where keyword = 'fantastique')),
       ((SELECT idbook FROM book WHERE isbn = '2070612902'), (SELECT id_topic from topic where keyword = 'anneau'));

INSERT INTO book (isbn, description, image, title, year, category, publisher_id_publisher)
VALUES ('2266282417', 'Le Seigneur des anneaux - Tome 3 description ...... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.',
        'https://pictures.abebooks.com/isbn/9782266282413-fr-300.jpg',
        'Le Seigneur des anneaux - Tome 3',
        '2017',
        'LITTERATURE',
        (SELECT id_publisher FROM publisher WHERE name = 'Pocket'));
INSERT INTO stock(idbook, id_library, label, pillar, shelf, wings)
VALUES ((SELECT idbook FROM book WHERE isbn = '2266282417'),
        (SELECT id_library FROM library where library_name = 'Petite librairie de la ville')
           , 'SA456', '54a0', '2A', 'allee E');
INSERT INTO book_author (idbook, id_author)
VALUES ((SELECT idbook FROM book WHERE isbn = '2266282417'), (SELECT id_author from author where firstname = 'JRR'));
INSERT INTO book_topics (idbook, id_topics)
VALUES ((SELECT idbook FROM book WHERE isbn = '2266282417'),
        (SELECT id_topic from topic where keyword = 'fantastique')),
       ((SELECT idbook FROM book WHERE isbn = '2266282417'), (SELECT id_topic from topic where keyword = 'anneau'));

INSERT INTO book (isbn, description, image, title, year, category, publisher_id_publisher)
VALUES ('2070612899', 'Le Seigneur des anneaux - Tome 2 description ...... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.',
        'https://pictures.abebooks.com/isbn/9782070612895-fr.jpg',
        'Le Seigneur des anneaux - Tome 2',
        '2019',
        'LITTERATURE',
        (SELECT id_publisher FROM publisher WHERE name = 'Gallimard jeunesse'));
INSERT INTO stock(idbook, id_library, label, pillar, shelf, wings)
VALUES ((SELECT idbook FROM book WHERE isbn = '2070612899'),
        (SELECT id_library FROM library where library_name = 'Petite librairie de la ville')
           , 'SA2-123', '54a0', '2A', 'allee E');
INSERT INTO book_author (idbook, id_author)
VALUES ((SELECT idbook FROM book WHERE isbn = '2070612899'), (SELECT id_author from author where firstname = 'JRR'));
INSERT INTO book_topics (idbook, id_topics)
VALUES ((SELECT idbook FROM book WHERE isbn = '2070612899'),
        (SELECT id_topic from topic where keyword = 'fantastique')),
       ((SELECT idbook FROM book WHERE isbn = '2070612899'), (SELECT id_topic from topic where keyword = 'anneau'));

INSERT INTO book (isbn, description, image, title, year, category, publisher_id_publisher)
VALUES ('2266282409', 'Le Seigneur des anneaux - Tome 2 description ...... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.',
        'https://pictures.abebooks.com/isbn/9782266282406-fr.jpg',
        'Le Seigneur des anneaux - Tome 2',
        '2017',
        'LITTERATURE',
        (SELECT id_publisher FROM publisher WHERE name = 'Pocket'));
INSERT INTO stock(idbook, id_library, label, pillar, shelf, wings)
VALUES ((SELECT idbook FROM book WHERE isbn = '2266282409'),
        (SELECT id_library FROM library where library_name = 'Petite librairie de la ville')
           , 'SA2-456', '54a0', '2A', 'allee E');
INSERT INTO book_author (idbook, id_author)
VALUES ((SELECT idbook FROM book WHERE isbn = '2266282409'), (SELECT id_author from author where firstname = 'JRR'));
INSERT INTO book_topics (idbook, id_topics)
VALUES ((SELECT idbook FROM book WHERE isbn = '2266282409'),
        (SELECT id_topic from topic where keyword = 'fantastique')),
       ((SELECT idbook FROM book WHERE isbn = '2266282409'), (SELECT id_topic from topic where keyword = 'anneau'));

INSERT INTO book (isbn, description, image, title, year, category, publisher_id_publisher)
VALUES ('2070612880', 'Le Seigneur des anneaux - Tome 1 description ...... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.',
        'https://pictures.abebooks.com/isbn/9782070612888-fr.jpg',
        'Le Seigneur des anneaux - Tome 1',
        '2019',
        'LITTERATURE',
        (SELECT id_publisher FROM publisher WHERE name = 'Gallimard jeunesse'));
INSERT INTO stock(idbook, id_library, label, pillar, shelf, wings)
VALUES ((SELECT idbook FROM book WHERE isbn = '2070612880'),
        (SELECT id_library FROM library where library_name = 'Petite librairie de la ville')
           , 'SA1-123', '54a0', '2A', 'allee E');
INSERT INTO book_author (idbook, id_author)
VALUES ((SELECT idbook FROM book WHERE isbn = '2070612880'), (SELECT id_author from author where firstname = 'JRR'));
INSERT INTO book_topics (idbook, id_topics)
VALUES ((SELECT idbook FROM book WHERE isbn = '2070612880'),
        (SELECT id_topic from topic where keyword = 'fantastique')),
       ((SELECT idbook FROM book WHERE isbn = '2070612880'), (SELECT id_topic from topic where keyword = 'anneau'));

INSERT INTO book (isbn, description, image, title, year, category, publisher_id_publisher)
VALUES ('2266282360', 'Le Seigneur des anneaux - Tome 1 description ...... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.',
        'https://pictures.abebooks.com/isbn/9782266282369-fr.jpg',
        'Le Seigneur des anneaux - Tome 1',
        '2017',
        'LITTERATURE',
        (SELECT id_publisher FROM publisher WHERE name = 'Pocket'));
INSERT INTO stock(idbook, id_library, label, pillar, shelf, wings)
VALUES ((SELECT idbook FROM book WHERE isbn = '2266282360'),
        (SELECT id_library FROM library where library_name = 'Petite librairie de la ville')
           , 'SA1-456', '54a0', '2A', 'allee E');
INSERT INTO book_author (idbook, id_author)
VALUES ((SELECT idbook FROM book WHERE isbn = '2266282360'), (SELECT id_author from author where firstname = 'JRR'));
INSERT INTO book_topics (idbook, id_topics)
VALUES ((SELECT idbook FROM book WHERE isbn = '2266282360'),
        (SELECT id_topic from topic where keyword = 'fantastique')),
       ((SELECT idbook FROM book WHERE isbn = '2266282360'), (SELECT id_topic from topic where keyword = 'anneau'));
