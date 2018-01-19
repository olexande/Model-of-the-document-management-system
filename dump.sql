--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.3
-- Dumped by pg_dump version 9.6.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: bookstore; Type: DATABASE; Schema: -; Owner: admin
--

CREATE DATABASE bookstore WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';


ALTER DATABASE bookstore OWNER TO admin;

\connect bookstore

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- Name: place; Type: TYPE; Schema: public; Owner: admin
--

CREATE TYPE place AS ENUM (
    'офис',
    'торговая точка'
);


ALTER TYPE place OWNER TO admin;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: binding; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE binding (
    type character varying(255) NOT NULL
);


ALTER TABLE binding OWNER TO admin;

--
-- Name: book; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE book (
    id integer NOT NULL,
    title character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    number_page smallint,
    publishing_house character varying(255) NOT NULL,
    dimensions integer[],
    type_of_binding character varying(255) NOT NULL,
    id_product integer NOT NULL,
    CONSTRAINT true_dimensions CHECK ((((dimensions[0])::numeric >= 0.1) AND ((dimensions[1])::numeric >= 0.1))),
    CONSTRAINT true_number_page CHECK ((number_page >= 2))
);


ALTER TABLE book OWNER TO admin;

--
-- Name: book_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE book_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE book_id_seq OWNER TO admin;

--
-- Name: book_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE book_id_seq OWNED BY book.id;


--
-- Name: employee; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE employee (
    id integer NOT NULL,
    id_person integer NOT NULL,
    title_of_position character varying(255) NOT NULL
);


ALTER TABLE employee OWNER TO admin;

--
-- Name: employee_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE employee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE employee_id_seq OWNER TO admin;

--
-- Name: employee_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE employee_id_seq OWNED BY employee.id;


--
-- Name: person; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE person (
    id integer NOT NULL,
    lastname character varying(255) NOT NULL,
    firstname character varying(255) NOT NULL,
    patronymic character varying(255) NOT NULL,
    birthday date NOT NULL
);


ALTER TABLE person OWNER TO admin;

--
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE person_id_seq OWNER TO admin;

--
-- Name: person_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE person_id_seq OWNED BY person.id;


--
-- Name: position; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE "position" (
    title character varying(255) NOT NULL,
    place place NOT NULL
);


ALTER TABLE "position" OWNER TO admin;

--
-- Name: product; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE product (
    id integer NOT NULL,
    title character varying(255) NOT NULL,
    purchase_price numeric(12,2) DEFAULT 0,
    retail_price numeric(12,2) DEFAULT 0,
    CONSTRAINT true_purchase_price CHECK ((purchase_price >= (0)::numeric)),
    CONSTRAINT true_retail_price CHECK ((retail_price >= (purchase_price * 1.18)))
);


ALTER TABLE product OWNER TO admin;

--
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_id_seq OWNER TO admin;

--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE product_id_seq OWNED BY product.id;


--
-- Name: book id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY book ALTER COLUMN id SET DEFAULT nextval('book_id_seq'::regclass);


--
-- Name: employee id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY employee ALTER COLUMN id SET DEFAULT nextval('employee_id_seq'::regclass);


--
-- Name: person id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY person ALTER COLUMN id SET DEFAULT nextval('person_id_seq'::regclass);


--
-- Name: product id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY product ALTER COLUMN id SET DEFAULT nextval('product_id_seq'::regclass);


--
-- Data for Name: binding; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY binding (type) FROM stdin;
\.


--
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY book (id, title, author, number_page, publishing_house, dimensions, type_of_binding, id_product) FROM stdin;
\.


--
-- Name: book_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('book_id_seq', 1, false);


--
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY employee (id, id_person, title_of_position) FROM stdin;
\.


--
-- Name: employee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('employee_id_seq', 1, false);


--
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY person (id, lastname, firstname, patronymic, birthday) FROM stdin;
\.


--
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('person_id_seq', 1, false);


--
-- Data for Name: position; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY "position" (title, place) FROM stdin;
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY product (id, title, purchase_price, retail_price) FROM stdin;
\.


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('product_id_seq', 1, false);


--
-- Name: binding binding_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY binding
    ADD CONSTRAINT binding_pkey PRIMARY KEY (type);


--
-- Name: book book_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY book
    ADD CONSTRAINT book_pkey PRIMARY KEY (id);


--
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);


--
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- Name: position position_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY "position"
    ADD CONSTRAINT position_pkey PRIMARY KEY (title);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: book book_id_product_fkey; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY book
    ADD CONSTRAINT book_id_product_fkey FOREIGN KEY (id_product) REFERENCES product(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: book book_type_of_binding_fkey; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY book
    ADD CONSTRAINT book_type_of_binding_fkey FOREIGN KEY (type_of_binding) REFERENCES binding(type) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: employee employee_id_person_fkey; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT employee_id_person_fkey FOREIGN KEY (id_person) REFERENCES person(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: employee employee_title_of_position_fkey; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT employee_title_of_position_fkey FOREIGN KEY (title_of_position) REFERENCES "position"(title) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

