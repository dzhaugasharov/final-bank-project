CREATE TABLE public.transactions (
                                     id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
                                     user_id int4 NOT NULL,
                                     "type" int2 NOT NULL,
                                     sum int4 NOT NULL,
                                     created_at timestamp NOT NULL DEFAULT now()
);
CREATE UNIQUE INDEX transactions_id_idx ON public.transactions USING btree (id);


-- INSERTS
INSERT INTO public.transactions (user_id, type, "sum") VALUES (1, 0, 1000), (1, 0, 4000), (1, 1, 7000);