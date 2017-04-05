--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: aledania; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE aledania WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE aledania OWNER TO postgres;

\connect aledania

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE categoria (
    id integer NOT NULL,
    nombre text NOT NULL
);


ALTER TABLE categoria OWNER TO postgres;

--
-- Name: categoria_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE categoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE categoria_id_seq OWNER TO postgres;

--
-- Name: categoria_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE categoria_id_seq OWNED BY categoria.id;


--
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE employee (
    id integer NOT NULL,
    email character varying(255),
    firstname character varying(255),
    lastname character varying(255),
    telephone character varying(255)
);


ALTER TABLE employee OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO postgres;

--
-- Name: predicado; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE predicado (
    id text NOT NULL,
    predicado text NOT NULL,
    alias text NOT NULL,
    predserializado text NOT NULL,
    loginusuario text NOT NULL,
    numargumentos integer NOT NULL
);


ALTER TABLE predicado OWNER TO postgres;

--
-- Name: publicacion; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE publicacion (
    alias text,
    login text
);


ALTER TABLE publicacion OWNER TO postgres;

--
-- Name: resuelve; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE resuelve (
    id integer NOT NULL,
    nombreteorema text,
    numeroteorema text NOT NULL,
    resuelto boolean DEFAULT false NOT NULL,
    loginusuario text NOT NULL,
    teoremaid integer NOT NULL
);


ALTER TABLE resuelve OWNER TO postgres;

--
-- Name: dispone; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE dispone (
    id integer NOT NULL,
    numerometateorema text,
    resuelto boolean DEFAULT false NOT NULL,
    loginusuario text NOT NULL,
    metateoremaid integer NOT NULL
);


ALTER TABLE dispone OWNER TO postgres;

--
-- Name: resuelve_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE resuelve_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE resuelve_id_seq OWNER TO postgres;

--
-- Name: resuelve_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE resuelve_id_seq OWNED BY resuelve.id;


--
-- Name: dispone_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE dispone_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE dispone_id_seq OWNER TO postgres;

--
-- Name: dispone_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE dispone_id_seq OWNED BY dispone.id;


--
-- Name: solucion; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE solucion (
    id integer NOT NULL,
    resuelveid integer NOT NULL,
    arregloserializado bytea NOT NULL
);


ALTER TABLE solucion OWNER TO postgres;

--
-- Name: solucion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE solucion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE solucion_id_seq OWNER TO postgres;

--
-- Name: solucion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE solucion_id_seq OWNED BY solucion.id;


--
-- Name: teorema; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE teorema (
    id integer NOT NULL,
    enunciadoizq text NOT NULL,
    enunciadoder text NOT NULL,
    teoserializadoizq bytea NOT NULL,
    teoserializadoder bytea NOT NULL,
    categoriaid integer NOT NULL,
    ocultartrue boolean NOT NULL,
    esquema boolean NOT NULL
);


ALTER TABLE teorema OWNER TO postgres;

--
-- Name: metateorema; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE metateorema (
    id integer NOT NULL,
    enunciadoizq text NOT NULL,
    enunciadoder text NOT NULL,
    metateoserializadoizq bytea NOT NULL,
    metateoserializadoder bytea NOT NULL,
    categoriaid integer NOT NULL,
    ocultartrue boolean NOT NULL
);


ALTER TABLE metateorema OWNER TO postgres;

--
-- Name: teorema_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE teorema_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE teorema_id_seq OWNER TO postgres;

--
-- Name: teorema_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE teorema_id_seq OWNED BY teorema.id;


--
-- Name: metateorema_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE metateorema_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE metateorema_id_seq OWNER TO postgres;

--
-- Name: metateorema_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE metateorema_id_seq OWNED BY metateorema.id;


--
-- Name: termino; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE termino (
    combinador text NOT NULL,
    serializado bytea NOT NULL,
    alias text NOT NULL,
    login text NOT NULL
);


ALTER TABLE termino OWNER TO postgres;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuario (
    login text NOT NULL,
    nombre text NOT NULL,
    apellido text NOT NULL,
    correo text NOT NULL,
    password text NOT NULL,
    admin boolean DEFAULT false NOT NULL
);


ALTER TABLE usuario OWNER TO postgres;

--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categoria ALTER COLUMN id SET DEFAULT nextval('categoria_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY resuelve ALTER COLUMN id SET DEFAULT nextval('resuelve_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dispone ALTER COLUMN id SET DEFAULT nextval('dispone_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY teorema ALTER COLUMN id SET DEFAULT nextval('teorema_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY metateorema ALTER COLUMN id SET DEFAULT nextval('metateorema_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY solucion ALTER COLUMN id SET DEFAULT nextval('solucion_id_seq'::regclass);


--
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY categoria (id, nombre) FROM stdin;
1	Equivalencia
2	Negaciṕn
3	Disyunción
4	Conjunción
5	Implicación
\.


--
-- Name: categoria_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('categoria_id_seq', 5, true);


--
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY employee (id, email, firstname, lastname, telephone) FROM stdin;
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 1, false);


--
-- Data for Name: predicado; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY predicado (id, predicado, alias, predserializado, loginusuario, numargumentos) FROM stdin;
\.


--
-- Data for Name: publicacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY publicacion (alias, login) FROM stdin;
\.


--
-- Name: resuelve_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('resuelve_id_seq', 1, false);


--
-- Name: dispone_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('dispone_id_seq', 1, false);


--
-- Data for Name: solucion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY solucion (id, resuelveid,arregloserializado) FROM stdin;
\.


--
-- Name: teorema_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('teorema_id_seq', 1, false);


--
-- Name: metateorema_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('metateorema_id_seq', 1, false);


--
-- Data for Name: termino; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY termino (combinador, serializado, alias, login) FROM stdin;
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY usuario (login, nombre, apellido, correo, password, admin) FROM stdin;
					f
ascander	Ascander	Suarez	dede@efef.com	2718281	f
etahhan	Elias	Tahhan	etahhan@gmail.com	2718281	f
travis	travis	travis	correo@demasiadofalso.falso	2718281	f
ascander2	Ascander	Suarez	federico.flaviani@gmail.com	2718281	f
bigconde	Fernando	Flaviani	fflaviani60@yahoo.es	2718281	f
traka	traka	bol	trakatraka@bol.falso	trakatraka	f
minender	Federico	Flaviani	federico.flaviani@gmail.com	2718281	f
migsar	migsar	migsar	migsar@gmail.com	migsar	f
federico	Federico	Flaviani	federico.flaviani@gmail.com	2718281	t
admin	admin	admin	correo@demasiadofalso.com	12345	t
publico	publico	publico	correo@demasiadofalso.com	publico	f
\.


--
-- Name: categoria_PK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY categoria
    ADD CONSTRAINT "categoria_PK" PRIMARY KEY (id);


--
-- Name: categoria_UNIQUE; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY categoria
    ADD CONSTRAINT "categoria_UNIQUE" UNIQUE (nombre);


--
-- Name: employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);


--
-- Name: predicado_PK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY predicado
    ADD CONSTRAINT "predicado_PK" PRIMARY KEY (id, loginusuario);


--
-- Name: predicado_alias_UNIQUE; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY predicado
    ADD CONSTRAINT "predicado_alias_UNIQUE" UNIQUE (alias);


--
-- Name: predicado_predSerializado_UNIQUE; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY predicado
    ADD CONSTRAINT "predicado_predSerializado_UNIQUE" UNIQUE (predserializado);


--
-- Name: resuelve_PK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY resuelve
    ADD CONSTRAINT "resuelve_PK" PRIMARY KEY (id);


--
-- Name: resuelve_teorema_y_usuario_UNIQUE; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY resuelve
    ADD CONSTRAINT "resuelve_teorema_y_usuario_UNIQUE" UNIQUE (loginusuario, teoremaid);


--
-- Name: dispone_PK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY dispone
    ADD CONSTRAINT "dispone_PK" PRIMARY KEY (id);


--
-- Name: dispone_metateorema_y_usuario_UNIQUE; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY dispone
    ADD CONSTRAINT "dispone_metateorema_y_usuario_UNIQUE" UNIQUE (loginusuario, metateoremaid);


--
-- Name: solucion_PK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY solucion
    ADD CONSTRAINT "solucion_PK" PRIMARY KEY (id);


--
-- Name: teorema_PK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY teorema
    ADD CONSTRAINT "teorema_PK" PRIMARY KEY (id);


--
-- Name: teorema_UNIQUE; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY teorema
    ADD CONSTRAINT "teorema_UNIQUE" UNIQUE (enunciadoizq, enunciadoder);


--
-- Name: resuelve_teoremaSerializado_UNIQUE; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY teorema
    ADD CONSTRAINT "teorema_teoremaSerializado_UNIQUE" UNIQUE (teoserializadoizq, teoserializadoder);


--
-- Name: metateorema_PK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY metateorema
    ADD CONSTRAINT "metateorema_PK" PRIMARY KEY (id);


--
-- Name: metateorema_UNIQUE; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY metateorema
    ADD CONSTRAINT "metateorema_UNIQUE" UNIQUE (enunciadoizq, enunciadoder);


--
-- Name: dispone_teoremaSerializado_UNIQUE; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY metateorema
    ADD CONSTRAINT "metateorema_metateoremaSerializado_UNIQUE" UNIQUE (metateoserializadoizq, metateoserializadoder);


--
-- Name: termino_PK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY termino
    ADD CONSTRAINT "termino_PK" PRIMARY KEY (alias, login);


--
-- Name: termino_UNIQUE; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY termino
    ADD CONSTRAINT "termino_UNIQUE" UNIQUE (combinador, login);


--
-- Name: usuario_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pk PRIMARY KEY (login);


--
-- Name: predicado_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY predicado
    ADD CONSTRAINT "predicado_FK" FOREIGN KEY (loginusuario) REFERENCES usuario(login);


--
-- Name: resuelve_teorema_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY resuelve
    ADD CONSTRAINT "resuelve_teorema_FK" FOREIGN KEY (teoremaid) REFERENCES teorema(id);


--
-- Name: resuelve_usuario_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY resuelve
    ADD CONSTRAINT "resuelve_usuario_FK" FOREIGN KEY (loginusuario) REFERENCES usuario(login);


--
-- Name: solucion_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY solucion
    ADD CONSTRAINT "solucion_FK" FOREIGN KEY (resuelveid) REFERENCES resuelve(id);


--
-- Name: dispone_metateorema_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dispone
    ADD CONSTRAINT "dispone_metateorema_FK" FOREIGN KEY (metateoremaid) REFERENCES metateorema(id);


--
-- Name: dispone_usuario_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dispone
    ADD CONSTRAINT "dispone_usuario_FK" FOREIGN KEY (loginusuario) REFERENCES usuario(login);


--
-- Name: teorema_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY teorema
    ADD CONSTRAINT "teorema_FK" FOREIGN KEY (categoriaid) REFERENCES categoria(id);


--
-- Name: metateorema_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY metateorema
    ADD CONSTRAINT "metateorema_FK" FOREIGN KEY (categoriaid) REFERENCES categoria(id);


--
-- Name: termino_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY termino
    ADD CONSTRAINT "termino_FK" FOREIGN KEY (login) REFERENCES usuario(login);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

