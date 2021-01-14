--
-- PostgreSQL database dump
--

-- Dumped from database version 12.5 (Ubuntu 12.5-1.pgdg20.04+1)
-- Dumped by pg_dump version 13.1 (Ubuntu 13.1-1.pgdg20.04+1)

-- Started on 2020-11-19 19:24:55 CET

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
-- TOC entry 2951 (class 0 OID 20834)
-- Dependencies: 205
-- Data for Name: member; Type: TABLE DATA; Schema: public; Owner: ocr_admin
--

INSERT INTO public.member VALUES (1, '1234A', 'geraud4dev@gmail.com');
INSERT INTO public.member VALUES (2, '4562B', 'geraud.ocr@gmail.com');


--
-- TOC entry 2949 (class 0 OID 20824)
-- Dependencies: 203
-- Data for Name: loan; Type: TABLE DATA; Schema: public; Owner: ocr_admin
--

INSERT INTO public.loan VALUES (1, NULL, 'APA1234', 1, '2020-10-14', 'Apache Maven',  1);
INSERT INTO public.loan VALUES (2, NULL, 'AST1234A', 0, '2020-05-02', 'Asterix : le Papyrus de César', 1);
INSERT INTO public.loan VALUES (4, NULL, 'AST1234', 0, '2020-03-02', 'Asterix : le Papyrus de César', 2);
INSERT INTO public.loan VALUES (5, '2019-03-25', 'SA456', 0, '2019-03-03', 'Le Seigneur des anneaux - Tome 3',1);
INSERT INTO public.loan VALUES (6, '2019-04-26', 'SA2-456', 0, '2019-04-04', 'Le Seigneur des anneaux - Tome 2', 1);
INSERT INTO public.loan VALUES (7, '2019-04-27', 'SA1-456', 0, '0201-05-05', 'Le Seigneur des anneaux - Tome 1', 1);


--
-- TOC entry 2960 (class 0 OID 0)
-- Dependencies: 207
-- Name: batch_job_execution_seq; Type: SEQUENCE SET; Schema: public; Owner: ocr_admin
--

SELECT pg_catalog.setval('public.batch_job_execution_seq', 26, true);


--
-- TOC entry 2961 (class 0 OID 0)
-- Dependencies: 208
-- Name: batch_job_seq; Type: SEQUENCE SET; Schema: public; Owner: ocr_admin
--

SELECT pg_catalog.setval('public.batch_job_seq', 10, true);


--
-- TOC entry 2962 (class 0 OID 0)
-- Dependencies: 206
-- Name: batch_step_execution_seq; Type: SEQUENCE SET; Schema: public; Owner: ocr_admin
--

SELECT pg_catalog.setval('public.batch_step_execution_seq', 10, true);


--
-- TOC entry 2963 (class 0 OID 0)
-- Dependencies: 202
-- Name: loan_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ocr_admin
--

SELECT pg_catalog.setval('public.loan_id_seq', 4, true);


--
-- TOC entry 2964 (class 0 OID 0)
-- Dependencies: 204
-- Name: member_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ocr_admin
--

SELECT pg_catalog.setval('public.member_id_seq', 2, true);


-- Completed on 2020-11-19 19:24:55 CET

--
-- PostgreSQL database dump complete
--

