
create schema public;
CREATE TABLE public.company(
                      id INT  PRIMARY KEY     NOT NULL GENERATED ALWAYS AS IDENTITY ,
                      name  TEXT NOT NULL,
                      age INT     NOT NULL,
                    adress VARCHAR(50),
                      salary REAL

);