


CREATE TABLE public.user_balances (
                                      user_id int4 NOT NULL,
                                      balance int8 NOT NULL DEFAULT 0
);
CREATE UNIQUE INDEX user_balances_user_id_idx ON public.user_balances USING btree (user_id);


INSERT INTO public.user_balances (user_id, balance) VALUES(1, 0),
                                                          (2, 100000),
                                                          (3, 4000);