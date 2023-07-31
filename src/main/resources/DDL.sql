-- Create sequence for table users
    CREATE SEQUENCE public.users_seq
    	INCREMENT BY 1
    	MINVALUE 1
    	MAXVALUE 9223372036854775807
    	START 1
    	CACHE 1
    	NO CYCLE;

CREATE TABLE public.users (
	id numeric(20) NOT NULL DEFAULT nextval('users_seq'::regclass),
	username varchar(100) NULL,
	"password" varchar(500) NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (id)
);