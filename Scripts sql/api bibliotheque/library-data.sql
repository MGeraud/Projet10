
SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3002 (class 0 OID 21661)
-- Dependencies: 202
-- Data for Name: author; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.author (id_author, firstname, lastname) VALUES (1, 'Rene', 'Goscinny');
INSERT INTO public.author (id_author, firstname, lastname) VALUES (2, 'Albert', 'Uderzo');
INSERT INTO public.author (id_author, firstname, lastname) VALUES (3, 'Didier', 'Conrad');
INSERT INTO public.author (id_author, firstname, lastname) VALUES (4, 'Jean-Yves', 'Ferri');
INSERT INTO public.author (id_author, firstname, lastname) VALUES (5, 'JRR', 'Tolkien');
INSERT INTO public.author (id_author, firstname, lastname) VALUES (6, 'Etienne', 'Langlet');
INSERT INTO public.author (id_author, firstname, lastname) VALUES (7, 'Maxime', 'Gréau');

--
-- TOC entry 3010 (class 0 OID 21691)
-- Dependencies: 210
-- Data for Name: publisher; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.publisher (id_publisher, name) VALUES (1, 'Pocket');
INSERT INTO public.publisher (id_publisher, name) VALUES (2, 'Gallimard jeunesse');
INSERT INTO public.publisher (id_publisher, name) VALUES (3, 'ENI Editions');
INSERT INTO public.publisher (id_publisher, name) VALUES (4, 'Albert Rene');


--
-- TOC entry 3004 (class 0 OID 21669)
-- Dependencies: 204
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.book (idbook, category, description, image, isbn, title, year, publisher_id_publisher) VALUES (1, 'LOISIRS', 'Apache Maven la description ...... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.', 'https://static.fnac-static.com/multimedia/Images/FR/NR/0e/96/a9/11113998/1507-1/tsp20190606164132/Apache-Maven.jpg', '2409019536', 'Apache Maven', 2019, 3);
INSERT INTO public.book (idbook, category, description, image, isbn, title, year, publisher_id_publisher) VALUES (2, 'BD', 'Asterix papyrus description ...... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQPnqLg-ZNDp-gCozCKJcC_dCNJp_rhRMAyLw&usqp=CAU', '2864973235', 'Asterix : le Papyrus de César', 2015, 4);
INSERT INTO public.book (idbook, category, description, image, isbn, title, year, publisher_id_publisher) VALUES (3, 'BD', 'Asterix Le Menhir d Or description ...... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQPnqLg-ZNDp-gCozCKJcC_dCNJp_rhRMAyLw&usqp=CAU', '2864973464', 'Asterix : Le Menhir d Or', 2020, 4);
INSERT INTO public.book (idbook, category, description, image, isbn, title, year, publisher_id_publisher) VALUES (4, 'BD', 'Astérix et la rentrée gauloise description ...... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQPnqLg-ZNDp-gCozCKJcC_dCNJp_rhRMAyLw&usqp=CAU', '2864971534', 'Astérix et la rentrée gauloise', 2003, 4);
INSERT INTO public.book (idbook, category, description, image, isbn, title, year, publisher_id_publisher) VALUES (5, 'LITTERATURE', 'Le Seigneur des anneaux - Tome 3 description ...... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQPnqLg-ZNDp-gCozCKJcC_dCNJp_rhRMAyLw&usqp=CAU', '2070612902', 'Le Seigneur des anneaux - Tome 3', 2019, 2);
INSERT INTO public.book (idbook, category, description, image, isbn, title, year, publisher_id_publisher) VALUES (6, 'LITTERATURE', 'Le Seigneur des anneaux - Tome 3 description ...... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.', 'https://pictures.abebooks.com/isbn/9782266282413-fr-300.jpg', '2266282417', 'Le Seigneur des anneaux - Tome 3', 2017, 1);
INSERT INTO public.book (idbook, category, description, image, isbn, title, year, publisher_id_publisher) VALUES (7, 'LITTERATURE', 'Le Seigneur des anneaux - Tome 2 description ...... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.', 'https://pictures.abebooks.com/isbn/9782070612895-fr.jpg', '2070612899', 'Le Seigneur des anneaux - Tome 2', 2019, 2);
INSERT INTO public.book (idbook, category, description, image, isbn, title, year, publisher_id_publisher) VALUES (8, 'LITTERATURE', 'Le Seigneur des anneaux - Tome 2 description ...... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.', 'https://pictures.abebooks.com/isbn/9782266282406-fr.jpg', '2266282409', 'Le Seigneur des anneaux - Tome 2', 2017, 1);
INSERT INTO public.book (idbook, category, description, image, isbn, title, year, publisher_id_publisher) VALUES (9, 'LITTERATURE', 'Le Seigneur des anneaux - Tome 1 description ...... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.', 'https://pictures.abebooks.com/isbn/9782070612888-fr.jpg', '2070612880', 'Le Seigneur des anneaux - Tome 1', 2019, 2);
INSERT INTO public.book (idbook, category, description, image, isbn, title, year, publisher_id_publisher) VALUES (10, 'LITTERATURE', 'Le Seigneur des anneaux - Tome 1 description ...... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.', 'https://pictures.abebooks.com/isbn/9782266282369-fr.jpg', '2266282360', 'Le Seigneur des anneaux - Tome 1', 2017, 1);


--
-- TOC entry 3005 (class 0 OID 21675)
-- Dependencies: 205
-- Data for Name: book_author; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.book_author (idbook, id_author) VALUES (1, 6);
INSERT INTO public.book_author (idbook, id_author) VALUES (1, 7);
INSERT INTO public.book_author (idbook, id_author) VALUES (2, 4);
INSERT INTO public.book_author (idbook, id_author) VALUES (2, 1);
INSERT INTO public.book_author (idbook, id_author) VALUES (2, 2);
INSERT INTO public.book_author (idbook, id_author) VALUES (2, 3);
INSERT INTO public.book_author (idbook, id_author) VALUES (3, 1);
INSERT INTO public.book_author (idbook, id_author) VALUES (3, 2);
INSERT INTO public.book_author (idbook, id_author) VALUES (4, 1);
INSERT INTO public.book_author (idbook, id_author) VALUES (4, 2);
INSERT INTO public.book_author (idbook, id_author) VALUES (5, 5);
INSERT INTO public.book_author (idbook, id_author) VALUES (6, 5);
INSERT INTO public.book_author (idbook, id_author) VALUES (7, 5);
INSERT INTO public.book_author (idbook, id_author) VALUES (8, 5);
INSERT INTO public.book_author (idbook, id_author) VALUES (9, 5);
INSERT INTO public.book_author (idbook, id_author) VALUES (10, 5);

--
-- TOC entry 3013 (class 0 OID 21702)
-- Dependencies: 213
-- Data for Name: topic; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.topic (id_topic, keyword) VALUES (1, 'informatique');
INSERT INTO public.topic (id_topic, keyword) VALUES (2, 'maven');
INSERT INTO public.topic (id_topic, keyword) VALUES (3, 'java');
INSERT INTO public.topic (id_topic, keyword) VALUES (4, 'asterix');
INSERT INTO public.topic (id_topic, keyword) VALUES (5, 'obelix');
INSERT INTO public.topic (id_topic, keyword) VALUES (6, 'anneau');
INSERT INTO public.topic (id_topic, keyword) VALUES (7, 'fantastique');

--
-- TOC entry 3007 (class 0 OID 21680)
-- Dependencies: 207
-- Data for Name: book_topics; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.book_topics (idbook, id_topics) VALUES (1, 2);
INSERT INTO public.book_topics (idbook, id_topics) VALUES (1, 1);
INSERT INTO public.book_topics (idbook, id_topics) VALUES (1, 3);
INSERT INTO public.book_topics (idbook, id_topics) VALUES (2, 4);
INSERT INTO public.book_topics (idbook, id_topics) VALUES (2, 5);
INSERT INTO public.book_topics (idbook, id_topics) VALUES (3, 4);
INSERT INTO public.book_topics (idbook, id_topics) VALUES (3, 5);
INSERT INTO public.book_topics (idbook, id_topics) VALUES (4, 4);
INSERT INTO public.book_topics (idbook, id_topics) VALUES (4, 5);
INSERT INTO public.book_topics (idbook, id_topics) VALUES (5, 7);
INSERT INTO public.book_topics (idbook, id_topics) VALUES (5, 6);
INSERT INTO public.book_topics (idbook, id_topics) VALUES (6, 7);
INSERT INTO public.book_topics (idbook, id_topics) VALUES (6, 6);
INSERT INTO public.book_topics (idbook, id_topics) VALUES (7, 7);
INSERT INTO public.book_topics (idbook, id_topics) VALUES (7, 6);
INSERT INTO public.book_topics (idbook, id_topics) VALUES (8, 7);
INSERT INTO public.book_topics (idbook, id_topics) VALUES (8, 6);
INSERT INTO public.book_topics (idbook, id_topics) VALUES (9, 7);
INSERT INTO public.book_topics (idbook, id_topics) VALUES (9, 6);
INSERT INTO public.book_topics (idbook, id_topics) VALUES (10, 7);
INSERT INTO public.book_topics (idbook, id_topics) VALUES (10, 6);


--
-- TOC entry 3008 (class 0 OID 21683)
-- Dependencies: 208
-- Data for Name: library; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.library (id_library, address, library_name, opening_hours) VALUES (1, 'rue autre', 'Grande librairie de la ville', '9h-21h');
INSERT INTO public.library (id_library, address, library_name, opening_hours) VALUES (2, 'rue minus', 'Petite librairie de la ville', '9h-21h');
INSERT INTO public.library (id_library, address, library_name, opening_hours) VALUES (3, 'rue encore une', 'La 3eme de la ville', '8h-12h  14h-18h');





--
-- TOC entry 3012 (class 0 OID 21696)
-- Dependencies: 212
-- Data for Name: stock; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.stock (label, pillar, shelf, wings, idbook, id_library) VALUES ('APA1234', '54a0', '2A', 'allee E', 1, 2);
INSERT INTO public.stock (label, pillar, shelf, wings, idbook, id_library) VALUES ('AST1234', '54a0', '2A', 'allee E', 2, 2);
INSERT INTO public.stock (label, pillar, shelf, wings, idbook, id_library) VALUES ('AST1234A', '54a0', '2A', 'allee E', 2, 1);
INSERT INTO public.stock (label, pillar, shelf, wings, idbook, id_library) VALUES ('AST1234C', '54a0', '2A', 'allee E', 2, 3);
INSERT INTO public.stock (label, pillar, shelf, wings, idbook, id_library) VALUES ('AST5678', '54a0', '2A', 'allee E', 3, 2);
INSERT INTO public.stock (label, pillar, shelf, wings, idbook, id_library) VALUES ('AST5678A', '54a0', '2A', 'allee E', 3, 1);
INSERT INTO public.stock (label, pillar, shelf, wings, idbook, id_library) VALUES ('AST9011', '54a0', '2A', 'allee E', 4, 2);
INSERT INTO public.stock (label, pillar, shelf, wings, idbook, id_library) VALUES ('AST9011A', '54a0', '2A', 'allee E', 4, 1);
INSERT INTO public.stock (label, pillar, shelf, wings, idbook, id_library) VALUES ('AST9011C', '54a0', '2A', 'allee E', 4, 3);
INSERT INTO public.stock (label, pillar, shelf, wings, idbook, id_library) VALUES ('SA123', '54a0', '2A', 'allee E', 5, 2);
INSERT INTO public.stock (label, pillar, shelf, wings, idbook, id_library) VALUES ('SA123C', '54a0', '2A', 'allee E', 5, 3);
INSERT INTO public.stock (label, pillar, shelf, wings, idbook, id_library) VALUES ('SA456', '54a0', '2A', 'allee E', 6, 2);
INSERT INTO public.stock (label, pillar, shelf, wings, idbook, id_library) VALUES ('SA2-123', '54a0', '2A', 'allee E', 7, 2);
INSERT INTO public.stock (label, pillar, shelf, wings, idbook, id_library) VALUES ('SA2-456', '54a0', '2A', 'allee E', 8, 2);
INSERT INTO public.stock (label, pillar, shelf, wings, idbook, id_library) VALUES ('SA1-123', '54a0', '2A', 'allee E', 9, 2);
INSERT INTO public.stock (label, pillar, shelf, wings, idbook, id_library) VALUES ('SA1-456', '54a0', '2A', 'allee E', 10, 2);
INSERT INTO public.stock (label, pillar, shelf, wings, idbook, id_library) VALUES ('APM2', '25', '1B', 'allee D', 1, 1);
INSERT INTO public.stock (label, pillar, shelf, wings, idbook, id_library) VALUES ('APM3', '14', '5C', 'allee S', 1, 3);


