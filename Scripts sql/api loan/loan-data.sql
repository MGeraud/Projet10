--
-- PostgreSQL database dump complete
--
-- PostgreSQL database dump
--

-- Dumped from database version 12.5 (Ubuntu 12.5-1.pgdg20.04+1)
-- Dumped by pg_dump version 13.1 (Ubuntu 13.1-1.pgdg20.04+1)

-- Started on 2021-02-09 10:24:55 CET

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
-- TOC entry 2962 (class 0 OID 21644)
-- Dependencies: 207
-- Data for Name: member; Type: TABLE DATA; Schema: public; Owner: ocr_admin
--

INSERT INTO public.member (id, cardnumber, email) VALUES (1, '1234A', 'geraud4dev@gmail.com');
INSERT INTO public.member (id, cardnumber, email) VALUES (2, '4562B', 'geraud.ocr@gmail.com');
INSERT INTO public.member (id, cardnumber, email) VALUES (3, '3333', 'membre3@email.com');
INSERT INTO public.member (id, cardnumber, email) VALUES (4, '4444', 'membre4@email.com');
INSERT INTO public.member (id, cardnumber, email) VALUES (5, '5555', 'membre5@email.com');
INSERT INTO public.member (id, cardnumber, email) VALUES (6, '6666', 'membre6@email.com');



--
-- TOC entry 2965 (class 0 OID 21771)
-- Dependencies: 210
-- Data for Name: booking; Type: TABLE DATA; Schema: public; Owner: ocr_admin
--

INSERT INTO public.booking (id, booking_date, mail_send_date, title, member_id) VALUES (-2, '2021-01-24', NULL, 'Apache Maven', 2);
INSERT INTO public.booking (id, booking_date, mail_send_date, title, member_id) VALUES (-3, '2021-01-02', NULL, 'Apache Maven', 3);
INSERT INTO public.booking (id, booking_date, mail_send_date, title, member_id) VALUES (-4, '2021-01-02', NULL, 'Apache Maven', 4);
INSERT INTO public.booking (id, booking_date, mail_send_date, title, member_id) VALUES (-5, '2021-01-02', NULL, 'Apache Maven', 5);
INSERT INTO public.booking (id, booking_date, mail_send_date, title, member_id) VALUES (-6, '2021-01-01', '2021-02-07', 'Asterix : le Papyrus de César', 6);
INSERT INTO public.booking (id, booking_date, mail_send_date, title, member_id) VALUES (-7, '2021-01-05', NULL, 'Asterix : le Papyrus de César', 2);
INSERT INTO public.booking (id, booking_date, mail_send_date, title, member_id) VALUES (-8, '2021-01-20', NULL, 'Astérix et la rentrée gauloise', 2);
INSERT INTO public.booking (id, booking_date, mail_send_date, title, member_id) VALUES (-9, '2021-01-22', NULL, 'Astérix et la rentrée gauloise', 1);


--
-- TOC entry 2960 (class 0 OID 21636)
-- Dependencies: 205
-- Data for Name: loan; Type: TABLE DATA; Schema: public; Owner: ocr_admin
--

INSERT INTO public.loan (id, book_back_date, label, refresh_ending_counter, starting_date, title, member_id) VALUES (-1, NULL, 'AST9011', 0, '2021-02-01', 'Astérix et la rentrée gauloise', 4);
INSERT INTO public.loan (id, book_back_date, label, refresh_ending_counter, starting_date, title, member_id) VALUES (-2, NULL, 'AST9011A', 0, '2021-02-02', 'Astérix et la rentrée gauloise', 5);
INSERT INTO public.loan (id, book_back_date, label, refresh_ending_counter, starting_date, title, member_id) VALUES (-3, NULL, 'AST9011C', 0, '2021-02-03', 'Astérix et la rentrée gauloise', 6);
INSERT INTO public.loan (id, book_back_date, label, refresh_ending_counter, starting_date, title, member_id) VALUES (-4, NULL, 'AST1234A', 0, '2020-05-02', 'Asterix : le Papyrus de César', 1);
INSERT INTO public.loan (id, book_back_date, label, refresh_ending_counter, starting_date, title, member_id) VALUES (-5, NULL, 'AST1234', 0, '2020-03-02', 'Asterix : le Papyrus de César', 5);
INSERT INTO public.loan (id, book_back_date, label, refresh_ending_counter, starting_date, title, member_id) VALUES (-6, '2019-03-25', 'SA456', 0, '2019-03-03', 'Le Seigneur des anneaux - Tome 3', 1);
INSERT INTO public.loan (id, book_back_date, label, refresh_ending_counter, starting_date, title, member_id) VALUES (-7, '2019-04-26', 'SA2-456', 0, '2019-04-04', 'Le Seigneur des anneaux - Tome 2', 1);
INSERT INTO public.loan (id, book_back_date, label, refresh_ending_counter, starting_date, title, member_id) VALUES (-8, '2019-04-27', 'SA1-456', 0, '0201-05-05', 'Le Seigneur des anneaux - Tome 1', 1);
INSERT INTO public.loan (id, book_back_date, label, refresh_ending_counter, starting_date, title, member_id) VALUES (-9, NULL, 'APM2', 0, '2021-01-10', 'Apache Maven', 3);
INSERT INTO public.loan (id, book_back_date, label, refresh_ending_counter, starting_date, title, member_id) VALUES (-10, NULL, 'APM3', 1, '2021-01-08', 'Apache Maven', 4);
INSERT INTO public.loan (id, book_back_date, label, refresh_ending_counter, starting_date, title, member_id) VALUES (-11, NULL, 'APA1234', 0, '2021-01-31', 'Apache Maven', 5);


--
-- TOC entry 2974 (class 0 OID 0)
-- Dependencies: 209
-- Name: booking_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ocr_admin
--

SELECT pg_catalog.setval('public.booking_id_seq', 7, true);


--
-- TOC entry 2975 (class 0 OID 0)
-- Dependencies: 206
-- Name: loan_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ocr_admin
--

SELECT pg_catalog.setval('public.loan_id_seq', 12, true);


--
-- TOC entry 2976 (class 0 OID 0)
-- Dependencies: 208
-- Name: member_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ocr_admin
--

SELECT pg_catalog.setval('public.member_id_seq', 2, true);


-- Completed on 2021-02-09 10:24:55 CET

--
-- PostgreSQL database dump complete
--

--
