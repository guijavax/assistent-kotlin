--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5 (Ubuntu 11.5-0ubuntu0.19.04.1)
-- Dumped by pg_dump version 11.5 (Ubuntu 11.5-0ubuntu0.19.04.1)

-- Started on 2020-03-30 17:30:07 -03

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

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 25693)
-- Name: cidades; Type: TABLE; Schema: public; Owner: guiborges
--

CREATE TABLE public.cidades (
    id integer NOT NULL,
    nome character varying,
    codigo_ibge integer,
    estado_id integer,
    populacao_2010 integer,
    densidade_demo numeric,
    gentilico character varying(250),
    area numeric
);


ALTER TABLE public.cidades OWNER TO guiborges;

--
-- TOC entry 197 (class 1259 OID 25699)
-- Name: client; Type: TABLE; Schema: public; Owner: guiborges
--

CREATE TABLE public.client (
    id_client bigint NOT NULL,
    name character varying(200) NOT NULL,
    cpf numeric(10,0) NOT NULL,
    age integer NOT NULL,
    zip_code numeric(10,0),
    street character varying(150),
    district character varying(150) NOT NULL,
    id_city bigint NOT NULL,
    id_state integer NOT NULL,
    country character varying(150) NOT NULL,
    telephone numeric(10,0) NOT NULL,
    number bigint,
    data_nascimento timestamp without time zone
);


ALTER TABLE public.client OWNER TO guiborges;

--
-- TOC entry 198 (class 1259 OID 25705)
-- Name: estados; Type: TABLE; Schema: public; Owner: guiborges
--

CREATE TABLE public.estados (
    id integer NOT NULL,
    nome character varying,
    sigla character varying
);


ALTER TABLE public.estados OWNER TO guiborges;

--
-- TOC entry 205 (class 1259 OID 25780)
-- Name: login; Type: TABLE; Schema: public; Owner: guiborges
--

CREATE TABLE public.login (
    id_user integer NOT NULL,
    data_login timestamp without time zone,
    id_login integer NOT NULL
);


ALTER TABLE public.login OWNER TO guiborges;

--
-- TOC entry 199 (class 1259 OID 25711)
-- Name: seq_id_client; Type: SEQUENCE; Schema: public; Owner: guiborges
--

CREATE SEQUENCE public.seq_id_client
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_id_client OWNER TO guiborges;

--
-- TOC entry 204 (class 1259 OID 25773)
-- Name: seq_id_user; Type: SEQUENCE; Schema: public; Owner: guiborges
--

CREATE SEQUENCE public.seq_id_user
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_id_user OWNER TO guiborges;

--
-- TOC entry 200 (class 1259 OID 25713)
-- Name: seq_service; Type: SEQUENCE; Schema: public; Owner: guiborges
--

CREATE SEQUENCE public.seq_service
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_service OWNER TO guiborges;

--
-- TOC entry 201 (class 1259 OID 25715)
-- Name: service_type; Type: TABLE; Schema: public; Owner: guiborges
--

CREATE TABLE public.service_type (
    id_type bigint NOT NULL,
    type_name character varying(250) NOT NULL
);


ALTER TABLE public.service_type OWNER TO guiborges;

--
-- TOC entry 202 (class 1259 OID 25718)
-- Name: services; Type: TABLE; Schema: public; Owner: guiborges
--

CREATE TABLE public.services (
    id_service integer NOT NULL,
    service_name character varying(150) NOT NULL,
    id_type_service integer NOT NULL,
    execution_function character varying(150) NOT NULL,
    service_price numeric NOT NULL,
    desc_service character varying(250),
    begin_date date NOT NULL,
    experation_date date NOT NULL
);


ALTER TABLE public.services OWNER TO guiborges;

--
-- TOC entry 203 (class 1259 OID 25768)
-- Name: user_assistent; Type: TABLE; Schema: public; Owner: guiborges
--

CREATE TABLE public.user_assistent (
    id_user integer NOT NULL,
    id_client integer NOT NULL,
    username character varying(100),
    password character varying(100),
    type character(1),
    email character varying(200)
);


ALTER TABLE public.user_assistent OWNER TO guiborges;

--
-- TOC entry 2846 (class 2606 OID 25725)
-- Name: client client_pk; Type: CONSTRAINT; Schema: public; Owner: guiborges
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pk PRIMARY KEY (id_client);


--
-- TOC entry 2854 (class 2606 OID 25784)
-- Name: login login_pkey; Type: CONSTRAINT; Schema: public; Owner: guiborges
--

ALTER TABLE ONLY public.login
    ADD CONSTRAINT login_pkey PRIMARY KEY (id_login);


--
-- TOC entry 2844 (class 2606 OID 25727)
-- Name: cidades pk_cidade_id; Type: CONSTRAINT; Schema: public; Owner: guiborges
--

ALTER TABLE ONLY public.cidades
    ADD CONSTRAINT pk_cidade_id PRIMARY KEY (id);


--
-- TOC entry 2848 (class 2606 OID 25729)
-- Name: estados pk_estados_id; Type: CONSTRAINT; Schema: public; Owner: guiborges
--

ALTER TABLE ONLY public.estados
    ADD CONSTRAINT pk_estados_id PRIMARY KEY (id);


--
-- TOC entry 2850 (class 2606 OID 25731)
-- Name: service_type service_type_pkey; Type: CONSTRAINT; Schema: public; Owner: guiborges
--

ALTER TABLE ONLY public.service_type
    ADD CONSTRAINT service_type_pkey PRIMARY KEY (id_type);


--
-- TOC entry 2852 (class 2606 OID 25772)
-- Name: user_assistent user_assistent_pkey; Type: CONSTRAINT; Schema: public; Owner: guiborges
--

ALTER TABLE ONLY public.user_assistent
    ADD CONSTRAINT user_assistent_pkey PRIMARY KEY (id_user);


--
-- TOC entry 2856 (class 2606 OID 25758)
-- Name: client client_fk; Type: FK CONSTRAINT; Schema: public; Owner: guiborges
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_fk FOREIGN KEY (id_city) REFERENCES public.cidades(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2857 (class 2606 OID 25763)
-- Name: client fk_client_state; Type: FK CONSTRAINT; Schema: public; Owner: guiborges
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT fk_client_state FOREIGN KEY (id_state) REFERENCES public.estados(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2855 (class 2606 OID 25732)
-- Name: cidades fk_estado_id; Type: FK CONSTRAINT; Schema: public; Owner: guiborges
--

ALTER TABLE ONLY public.cidades
    ADD CONSTRAINT fk_estado_id FOREIGN KEY (estado_id) REFERENCES public.estados(id) ON DELETE CASCADE;


--
-- TOC entry 2858 (class 2606 OID 25737)
-- Name: services services_fk; Type: FK CONSTRAINT; Schema: public; Owner: guiborges
--

ALTER TABLE ONLY public.services
    ADD CONSTRAINT services_fk FOREIGN KEY (id_type_service) REFERENCES public.service_type(id_type) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2859 (class 2606 OID 25775)
-- Name: user_assistent user_assistent_fk; Type: FK CONSTRAINT; Schema: public; Owner: guiborges
--

ALTER TABLE ONLY public.user_assistent
    ADD CONSTRAINT user_assistent_fk FOREIGN KEY (id_client) REFERENCES public.client(id_client) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2020-03-30 17:30:08 -03

--
-- PostgreSQL database dump complete
--

