-- liquibase formatted sql

-- changeset torkov:populate_schema_05

INSERT INTO public.tpp_ref_account_type (value)
VALUES ('Клиентский'),
       ('Внутрибанковский');

INSERT INTO public.tpp_ref_product_class (value, gbi_code, gbi_name, product_row_code, product_row_name,
                                                  subclass_code, subclass_name)
VALUES ('03.012.002', '03', 'Розничный бизнес', '012', 'Драг. металлы', '002', 'Хранение'),
       ('02.001.005', '02', 'Розничный бизнес', '001', 'Сырье', '005', 'Продажа');

INSERT INTO public.tpp_ref_product_register_type ( value
                                                         , register_type_name
                                                         , product_class_code
                                                         , account_type)
VALUES ('03.012.002_47533_ComSoLd', 'Хранение ДМ.', '03.012.002', 'Клиентский'),
       ('03.012.002_47534_ComSoLd', 'Хранение ДМ.', '03.012.002', 'Внутрибанковский'),
       ('02.001.005_45343_CoDowFF', 'Серебро. Выкуп.', '02.001.005', 'Клиентский');
--        ('RKO', 'Расчетный счет', 'RKO', TIMESTAMP '2023-12-03 00:00:00', 'CLIENT_ACCOUNT');

INSERT INTO public.account_pool ( branch_code
                                        , currency_code
                                        , mdm_code
                                        , priority_code
                                        , registry_type_code)
VALUES ('0022', '800', '15', '00', '03.012.002_47533_ComSoLd'),
       ('0021', '500', '13', '00', '02.001.005_45343_CoDowFF');

INSERT INTO public.account(account_pool_id, account_number, bussy)
SELECT id, '475335516415314841861', false
FROM public.account_pool
WHERE registry_type_code = '03.012.002_47533_ComSoLd';

INSERT INTO public.account(account_pool_id, account_number, bussy)
SELECT id, '4753321651354151', false
FROM public.account_pool
WHERE registry_type_code = '03.012.002_47533_ComSoLd';

INSERT INTO public.account(account_pool_id, account_number, bussy)
SELECT id, '4753352543276345', false
FROM public.account_pool
WHERE registry_type_code = '03.012.002_47533_ComSoLd';

INSERT INTO public.account(account_pool_id, account_number, bussy)
SELECT id, '453432352436453276', false
FROM public.account_pool
WHERE registry_type_code = '02.001.005_45343_CoDowFF';

INSERT INTO public.account(account_pool_id, account_number, bussy)
SELECT id, '45343221651354151', false
FROM public.account_pool
WHERE registry_type_code = '02.001.005_45343_CoDowFF';

INSERT INTO public.account(account_pool_id, account_number, bussy)
SELECT id, '4534352543276345', false
FROM public.account_pool
WHERE registry_type_code = '02.001.005_45343_CoDowFF';

-- rollback truncate table public.account;
-- rollback truncate table public.account_pool;
-- rollback truncate table public.tpp_ref_product_register_type;
-- rollback truncate table public.tpp_ref_product_class;
-- rollback truncate table public.tpp_ref_account_type;