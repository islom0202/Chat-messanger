--
-- PostgreSQL database dump
--

-- Dumped from database version 14.11 (Ubuntu 14.11-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.11 (Ubuntu 14.11-0ubuntu0.22.04.1)

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

SET default_table_access_method = heap;

--
-- Name: chat_message; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.chat_message (
    id bigint NOT NULL,
    content character varying(255),
    sender character varying(255),
    "time" character varying(255),
    type character varying(255),
    chat_name character varying(255),
    recipient_name character varying(255),
    sender_name character varying(255),
    "timestamp" timestamp(6) without time zone,
    CONSTRAINT chat_message_type_check CHECK (((type)::text = ANY ((ARRAY['CHAT'::character varying, 'JOIN'::character varying, 'LEAVE'::character varying])::text[])))
);


ALTER TABLE public.chat_message OWNER TO postgres;

--
-- Name: chat_message_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.chat_message_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.chat_message_id_seq OWNER TO postgres;

--
-- Name: chat_message_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.chat_message_id_seq OWNED BY public.chat_message.id;


--
-- Name: chat_room; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.chat_room (
    id bigint NOT NULL,
    chat_name character varying(255),
    recipient_name character varying(255),
    sender_name character varying(255)
);


ALTER TABLE public.chat_room OWNER TO postgres;

--
-- Name: chat_room_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.chat_room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.chat_room_id_seq OWNER TO postgres;

--
-- Name: chat_room_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.chat_room_id_seq OWNED BY public.chat_room.id;


--
-- Name: chat_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.chat_user (
    id bigint NOT NULL,
    username character varying(255) NOT NULL
);


ALTER TABLE public.chat_user OWNER TO postgres;

--
-- Name: chat_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.chat_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.chat_user_id_seq OWNER TO postgres;

--
-- Name: chat_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.chat_user_id_seq OWNED BY public.chat_user.id;


--
-- Name: one_chat_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.one_chat_user (
    id bigint NOT NULL,
    full_name character varying(255),
    nik_name character varying(255),
    status smallint,
    CONSTRAINT one_chat_user_status_check CHECK (((status >= 0) AND (status <= 1)))
);


ALTER TABLE public.one_chat_user OWNER TO postgres;

--
-- Name: one_chat_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.one_chat_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.one_chat_user_id_seq OWNER TO postgres;

--
-- Name: one_chat_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.one_chat_user_id_seq OWNED BY public.one_chat_user.id;


--
-- Name: chat_message id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chat_message ALTER COLUMN id SET DEFAULT nextval('public.chat_message_id_seq'::regclass);


--
-- Name: chat_room id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chat_room ALTER COLUMN id SET DEFAULT nextval('public.chat_room_id_seq'::regclass);


--
-- Name: chat_user id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chat_user ALTER COLUMN id SET DEFAULT nextval('public.chat_user_id_seq'::regclass);


--
-- Name: one_chat_user id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.one_chat_user ALTER COLUMN id SET DEFAULT nextval('public.one_chat_user_id_seq'::regclass);


--
-- Data for Name: chat_message; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.chat_message (id, content, sender, "time", type, chat_name, recipient_name, sender_name, "timestamp") FROM stdin;
27	salom	islom	18:01:04	CHAT	\N	\N	\N	\N
28	qalesan javohir	ali	18:02:06	CHAT	\N	\N	\N	\N
29	yoz tvar	ali	18:02:31	CHAT	\N	\N	\N	\N
30	Asl tvarmi kotmi	Islomjon	18:03:01	CHAT	\N	\N	\N	\N
31	yo man isloman	ali	18:03:08	CHAT	\N	\N	\N	\N
32	darmayet	ali	18:03:11	CHAT	\N	\N	\N	\N
33	Bilaman	Islomjon	18:03:17	CHAT	\N	\N	\N	\N
34	Gsgsg	Javohir	18:06:40	CHAT	\N	\N	\N	\N
35	salo	islom	20:39:29	CHAT	\N	\N	\N	\N
36	sqadaegw	islom	20:39:46	CHAT	\N	\N	\N	\N
37	sdmfsjd	islom	20:40:28	CHAT	\N	\N	\N	\N
38	bvhv	islom	20:44:27	CHAT	\N	\N	\N	\N
39	jjnsdjdfsdf	islom	20:59:10	CHAT	\N	\N	\N	\N
40	szacev	ksjahdgj	21:15:03	CHAT	\N	\N	\N	\N
41	ahcdbc	sdgvsghd	21:38:57	CHAT	\N	\N	\N	\N
42	dQFQ4G	sdgvsghd	21:39:08	CHAT	\N	\N	\N	\N
43	GERAGT	sdgvsghd	21:40:37	CHAT	\N	\N	\N	\N
44	salom	asil	00:57:22	CHAT	\N	\N	\N	\N
45	salom	islom	00:57:33	CHAT	\N	\N	\N	\N
46	Jajjjnsjnsnnsbsbbbsnsns	Asal	02:01:06	CHAT	\N	\N	\N	\N
47	sas	islom	16:28:23	CHAT	\N	\N	\N	\N
48	qwdq	islom	16:30:20	CHAT	\N	\N	\N	\N
49	salom	guli	16:35:41	CHAT	\N	\N	\N	\N
\.


--
-- Data for Name: chat_room; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.chat_room (id, chat_name, recipient_name, sender_name) FROM stdin;
\.


--
-- Data for Name: chat_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.chat_user (id, username) FROM stdin;
31	islom
32	ali
33	Islomjon
34	Javohir
48	dsfsdf
50	ksjahdgj
51	sdgvsghd
53	asil
56	Guli
58	Asal
60	kon
61	pp
62	tom
63	guli
65	a
67	Tom
\.


--
-- Data for Name: one_chat_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.one_chat_user (id, full_name, nik_name, status) FROM stdin;
\.


--
-- Name: chat_message_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.chat_message_id_seq', 49, true);


--
-- Name: chat_room_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.chat_room_id_seq', 1, false);


--
-- Name: chat_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.chat_user_id_seq', 68, true);


--
-- Name: one_chat_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.one_chat_user_id_seq', 1, false);


--
-- Name: chat_message chat_message_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chat_message
    ADD CONSTRAINT chat_message_pkey PRIMARY KEY (id);


--
-- Name: chat_room chat_room_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chat_room
    ADD CONSTRAINT chat_room_pkey PRIMARY KEY (id);


--
-- Name: chat_user chat_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chat_user
    ADD CONSTRAINT chat_user_pkey PRIMARY KEY (id);


--
-- Name: one_chat_user one_chat_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.one_chat_user
    ADD CONSTRAINT one_chat_user_pkey PRIMARY KEY (id);


--
-- Name: chat_user uk_srmq2kk0ej0hb8l35xc6cf3wa; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chat_user
    ADD CONSTRAINT uk_srmq2kk0ej0hb8l35xc6cf3wa UNIQUE (username);


--
-- PostgreSQL database dump complete
--

