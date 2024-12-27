insert into regions (id, name)
values (1, 'Алтайский край'),
       (2, 'Амурская область'),
       (3, 'Архангельская область'),
       (4, 'Астраханская область'),
       (5, 'Белгородская область'),
       (6, 'Брянская область'),
       (7, 'Владимирская область'),
       (8, 'Волгоградская область'),
       (9, 'Вологодская область'),
       (10, 'Воронежская область'),
       (11, 'Еврейская автономная область'),
       (12, 'Забайкальский край'),
       (13, 'Ивановская область'),
       (14, 'Иркутская область'),
       (15, 'Кабардино-Балкарская Республика'),
       (16, 'Калининградская область'),
       (17, 'Калужская область'),
       (18, 'Камчатский край'),
       (19, 'Карачаево-Черкесская Республика'),
       (20, 'Кемеровская область - Кузбасс'),
       (21, 'Кировская область'),
       (22, 'Костромская область'),
       (23, 'Краснодарский край'),
       (24, 'Красноярский край'),
       (25, 'Курганская область'),
       (26, 'Курская область'),
       (27, 'Ленинградская область'),
       (28, 'Липецкая область'),
       (29, 'Магаданская область'),
       (30, 'Москва'),
       (31, 'Московская область'),
       (32, 'Мурманская область'),
       (33, 'Нижегородская область'),
       (34, 'Новгородская область'),
       (35, 'Новосибирская область'),
       (36, 'Омская область'),
       (37, 'Оренбургская область'),
       (38, 'Орловская область'),
       (39, 'Пензенская область'),
       (40, 'Пермский край'),
       (41, 'Приморский край'),
       (42, 'Псковская область'),
       (43, 'Республика Адыгея'),
       (44, 'Республика Алтай'),
       (45, 'Республика Башкортостан'),
       (46, 'Республика Бурятия'),
       (47, 'Республика Дагестан'),
       (48, 'Республика Ингушетия'),
       (49, 'Республика Калмыкия'),
       (50, 'Республика Карелия'),
       (51, 'Республика Коми'),
       (52, 'Республика Крым'),
       (53, 'Республика Марий Эл'),
       (54, 'Республика Мордовия'),
       (55, 'Республика Саха (Якутия)'),
       (56, 'Республика Северная Осетия – Алания'),
       (57, 'Республика Татарстан'),
       (58, 'Республика Тыва'),
       (59, 'Республика Хакасия'),
       (60, 'Ростовская область'),
       (61, 'Рязанская область'),
       (62, 'Самарская область'),
       (63, 'Санкт-Петербург'),
       (64, 'Саратовская область'),
       (65, 'Сахалинская область'),
       (66, 'Свердловская область'),
       (67, 'Севастополь'),
       (68, 'Смоленская область'),
       (69, 'Ставропольский край'),
       (70, 'Тамбовская область'),
       (71, 'Тверская область'),
       (72, 'Томская область'),
       (73, 'Тульская область'),
       (74, 'Тюменская область'),
       (75, 'Удмуртская Республика'),
       (76, 'Ульяновская область'),
       (77, 'Хабаровский край'),
       (78, 'Челябинская область'),
       (79, 'Чеченская Республика'),
       (80, 'Чувашская Республика'),
       (81, 'Чукотский автономный округ'),
       (82, 'Ярославская область');

insert into cities (id, name, region_id)
values (1, 'Алейск', 1),
       (2, 'Барнаул', 1),
       (3, 'Белокуриха', 1),
       (4, 'Бийск', 1),
       (5, 'Горняк', 1),
       (6, 'Заринск', 1),
       (7, 'Змеиногорск', 1),
       (8, 'Камень-на-Оби', 1),
       (9, 'Новоалтайск', 1),
       (10, 'Рубцовск', 1),
       (11, 'Славгород', 1),
       (12, 'Яровое', 1),
       (13, 'Белогорск', 2),
       (14, 'Благовещенск', 2),
       (15, 'Завитинск', 2),
       (16, 'Зея', 2),
       (17, 'Райчихинск', 2),
       (18, 'Свободный', 2),
       (19, 'Сковородино', 2),
       (20, 'Тында', 2),
       (21, 'Циолковский', 2),
       (22, 'Шимановск', 2),
       (23, 'Архангельск', 3),
       (24, 'Вельск', 3),
       (25, 'Каргополь', 3),
       (26, 'Коряжма', 3),
       (27, 'Котлас', 3),
       (28, 'Мезень', 3),
       (29, 'Мирный', 3),
       (30, 'Нарьян-Мар', 3),
       (31, 'Новодвинск', 3),
       (32, 'Няндома', 3),
       (33, 'Онега', 3),
       (34, 'Северодвинск', 3),
       (35, 'Сольвычегодск', 3),
       (36, 'Шенкурск', 3),
       (37, 'Астрахань', 4),
       (38, 'Ахтубинск', 4),
       (39, 'Знаменск', 4),
       (40, 'Камызяк', 4),
       (41, 'Нариманов', 4),
       (42, 'Харабали', 4),
       (43, 'Алексеевка', 5),
       (44, 'Белгород', 5),
       (45, 'Бирюч', 5),
       (46, 'Валуйки', 5),
       (47, 'Грайворон', 5),
       (48, 'Губкин', 5),
       (49, 'Короча', 5),
       (50, 'Новый Оскол', 5),
       (51, 'Старый Оскол', 5),
       (52, 'Строитель', 5),
       (53, 'Шебекино', 5),
       (54, 'Брянск', 6),
       (55, 'Дятьково', 6),
       (56, 'Жуковка', 6),
       (57, 'Злынка', 6),
       (58, 'Карачев', 6),
       (59, 'Клинцы', 6),
       (60, 'Мглин', 6),
       (61, 'Новозыбков', 6),
       (62, 'Почеп', 6),
       (63, 'Севск', 6),
       (64, 'Сельцо', 6),
       (65, 'Стародуб', 6),
       (66, 'Сураж', 6),
       (67, 'Трубчевск', 6),
       (68, 'Унеча', 6),
       (69, 'Фокино', 6),
       (70, 'Александров', 7),
       (71, 'Владимир', 7),
       (72, 'Вязники', 7),
       (73, 'Гороховец', 7),
       (74, 'Гусь-Хрустальный', 7),
       (75, 'Камешково', 7),
       (76, 'Карабаново', 7),
       (77, 'Киржач', 7),
       (78, 'Ковров', 7),
       (79, 'Кольчугино', 7),
       (80, 'Костерево', 7),
       (81, 'Курлово', 7),
       (82, 'Лакинск', 7),
       (83, 'Меленки', 7),
       (84, 'Муром', 7),
       (85, 'Петушки', 7),
       (86, 'Покров', 7),
       (87, 'Радужный', 7),
       (88, 'Собинка', 7),
       (89, 'Струнино', 7),
       (90, 'Судогда', 7),
       (91, 'Суздаль', 7),
       (92, 'Юрьев-Польский', 7),
       (93, 'Волгоград', 8),
       (94, 'Волжский', 8),
       (95, 'Дубовка', 8),
       (96, 'Жирновск', 8),
       (97, 'Калач-на-Дону', 8),
       (98, 'Камышин', 8),
       (99, 'Котельниково', 8),
       (100, 'Котово', 8),
       (101, 'Краснослободск', 8),
       (102, 'Ленинск', 8),
       (103, 'Михайловка', 8),
       (104, 'Николаевск', 8),
       (105, 'Новоаннинский', 8),
       (106, 'Палласовка', 8),
       (107, 'Петров Вал', 8),
       (108, 'Серафимович', 8),
       (109, 'Суровикино', 8),
       (110, 'Урюпинск', 8),
       (111, 'Фролово', 8),
       (112, 'Бабаево', 9),
       (113, 'Белозерск', 9),
       (114, 'Великий Устюг', 9),
       (115, 'Вологда', 9),
       (116, 'Вытегра', 9),
       (117, 'Грязовец', 9),
       (118, 'Кадников', 9),
       (119, 'Кириллов', 9),
       (120, 'Красавино', 9),
       (121, 'Никольск', 9),
       (122, 'Сокол', 9),
       (123, 'Тотьма', 9),
       (124, 'Устюжна', 9),
       (125, 'Харовск', 9),
       (126, 'Череповец', 9),
       (127, 'Бобров', 10),
       (128, 'Богучар', 10),
       (129, 'Борисоглебск', 10),
       (130, 'Бутурлиновка', 10),
       (131, 'Воронеж', 10),
       (132, 'Калач', 10),
       (133, 'Лиски', 10),
       (134, 'Нововоронеж', 10),
       (135, 'Новохоперск', 10),
       (136, 'Острогожск', 10),
       (137, 'Павловск', 10),
       (138, 'Поворино', 10),
       (139, 'Россошь', 10),
       (140, 'Семилуки', 10),
       (141, 'Эртиль', 10),
       (142, 'Биробиджан', 11),
       (143, 'Облучье', 11),
       (144, 'Балей', 12),
       (145, 'Борзя', 12),
       (146, 'Краснокаменск', 12),
       (147, 'Могоча', 12),
       (148, 'Нерчинск', 12),
       (149, 'Петровск-Забайкальский', 12),
       (150, 'Сретенск', 12),
       (151, 'Хилок', 12),
       (152, 'Чита', 12),
       (153, 'Шилка', 12),
       (154, 'Вичуга', 13),
       (155, 'Гаврилов Посад', 13),
       (156, 'Заволжск', 13),
       (157, 'Иваново', 13),
       (158, 'Кинешма', 13),
       (159, 'Комсомольск', 13),
       (160, 'Кохма', 13),
       (161, 'Наволоки', 13),
       (162, 'Плес', 13),
       (163, 'Приволжск', 13),
       (164, 'Пучеж', 13),
       (165, 'Родники', 13),
       (166, 'Тейково', 13),
       (167, 'Фурманов', 13),
       (168, 'Шуя', 13),
       (169, 'Южа', 13),
       (170, 'Юрьевец', 13),
       (171, 'Алзамай', 14),
       (172, 'Ангарск', 14),
       (173, 'Байкальск', 14),
       (174, 'Бирюсинск', 14),
       (175, 'Бодайбо', 14),
       (176, 'Братск', 14),
       (177, 'Вихоревка', 14),
       (178, 'Железногорск-Илимский', 14),
       (179, 'Зима', 14),
       (180, 'Иркутск', 14),
       (181, 'Киренск', 14),
       (182, 'Нижнеудинск', 14),
       (183, 'Саянск', 14),
       (184, 'Свирск', 14),
       (185, 'Слюдянка', 14),
       (186, 'Тайшет', 14),
       (187, 'Тулун', 14),
       (188, 'Усолье-Сибирское', 14),
       (189, 'Усть-Илимск', 14),
       (190, 'Усть-Кут', 14),
       (191, 'Черемхово', 14),
       (192, 'Шелехов', 14),
       (193, 'Баксан', 15),
       (194, 'Майский', 15),
       (195, 'Нальчик', 15),
       (196, 'Нарткала', 15),
       (197, 'Прохладный', 15),
       (198, 'Терек', 15),
       (199, 'Тырныауз', 15),
       (200, 'Чегем', 15),
       (201, 'Багратионовск', 16),
       (202, 'Балтийск', 16),
       (203, 'Гвардейск', 16),
       (204, 'Гурьевск', 16),
       (205, 'Гусев', 16),
       (206, 'Зеленоградск', 16),
       (207, 'Калининград', 16),
       (208, 'Краснознаменск', 16),
       (209, 'Ладушкин', 16),
       (210, 'Мамоново', 16),
       (211, 'Неман', 16),
       (212, 'Нестеров', 16),
       (213, 'Озерск', 16),
       (214, 'Пионерский', 16),
       (215, 'Полесск', 16),
       (216, 'Правдинск', 16),
       (217, 'Приморск', 16),
       (218, 'Светлогорск', 16),
       (219, 'Светлый', 16),
       (220, 'Славск', 16),
       (221, 'Советск', 16),
       (222, 'Черняховск', 16),
       (223, 'Балабаново', 17),
       (224, 'Белоусово', 17),
       (225, 'Боровск', 17),
       (226, 'Ермолино', 17),
       (227, 'Жиздра', 17),
       (228, 'Жуков', 17),
       (229, 'Калуга', 17),
       (230, 'Киров', 17),
       (231, 'Козельск', 17),
       (232, 'Кондрово', 17),
       (233, 'Кременки', 17),
       (234, 'Людиново', 17),
       (235, 'Малоярославец', 17),
       (236, 'Медынь', 17),
       (237, 'Мещовск', 17),
       (238, 'Мосальск', 17),
       (239, 'Обнинск', 17),
       (240, 'Сосенский', 17),
       (241, 'Спас-Деменск', 17),
       (242, 'Сухиничи', 17),
       (243, 'Таруса', 17),
       (244, 'Юхнов', 17),
       (245, 'Вилючинск', 18),
       (246, 'Елизово', 18),
       (247, 'Петропавловск-Камчатский', 18),
       (248, 'Карачаевск', 19),
       (249, 'Теберда', 19),
       (250, 'Усть-Джегута', 19),
       (251, 'Черкесск', 19),
       (252, 'Анжеро-Судженск', 20),
       (253, 'Белово', 20),
       (254, 'Березовский', 20),
       (255, 'Гурьевск', 20),
       (256, 'Калтан', 20),
       (257, 'Кемерово', 20),
       (258, 'Киселевск', 20),
       (259, 'Ленинск-Кузнецкий', 20),
       (260, 'Мариинск', 20),
       (261, 'Междуреченск', 20),
       (262, 'Мыски', 20),
       (263, 'Новокузнецк', 20),
       (264, 'Осинники', 20),
       (265, 'Полысаево', 20),
       (266, 'Прокопьевск', 20),
       (267, 'Салаир', 20),
       (268, 'Тайга', 20),
       (269, 'Таштагол', 20),
       (270, 'Топки', 20),
       (271, 'Юрга', 20),
       (272, 'Белая Холуница', 21),
       (273, 'Вятские Поляны', 21),
       (274, 'Зуевка', 21),
       (275, 'Киров', 21),
       (276, 'Кирово-Чепецк', 21),
       (277, 'Кирс', 21),
       (278, 'Котельнич', 21),
       (279, 'Луза', 21),
       (280, 'Малмыж', 21),
       (281, 'Мураши', 21),
       (282, 'Нолинск', 21),
       (283, 'Омутнинск', 21),
       (284, 'Орлов', 21),
       (285, 'Слободской', 21),
       (286, 'Советск', 21),
       (287, 'Сосновка', 21),
       (288, 'Уржум', 21),
       (289, 'Яранск', 21),
       (290, 'Буй', 22),
       (291, 'Волгореченск', 22),
       (292, 'Галич', 22),
       (293, 'Кологрив', 22),
       (294, 'Кострома', 22),
       (295, 'Макарьев', 22),
       (296, 'Мантурово', 22),
       (297, 'Нерехта', 22),
       (298, 'Нея', 22),
       (299, 'Солигалич', 22),
       (300, 'Чухлома', 22),
       (301, 'Шарья', 22),
       (302, 'Абинск', 23),
       (303, 'Анапа', 23),
       (304, 'Апшеронск', 23),
       (305, 'Армавир', 23),
       (306, 'Белореченск', 23),
       (307, 'Геленджик', 23),
       (308, 'Горячий Ключ', 23),
       (309, 'Гулькевичи', 23),
       (310, 'Ейск', 23),
       (311, 'Кореновск', 23),
       (312, 'Краснодар', 23),
       (313, 'Кропоткин', 23),
       (314, 'Крымск', 23),
       (315, 'Курганинск', 23),
       (316, 'Лабинск', 23),
       (317, 'Новокубанск', 23),
       (318, 'Новороссийск', 23),
       (319, 'Приморско-Ахтарск', 23),
       (320, 'Славянск-на-Кубани', 23),
       (321, 'Сочи', 23),
       (322, 'Темрюк', 23),
       (323, 'Тимашевск', 23),
       (324, 'Тихорецк', 23),
       (325, 'Туапсе', 23),
       (326, 'Усть-Лабинск', 23),
       (327, 'Хадыженск', 23),
       (328, 'Артемовск', 24),
       (329, 'Ачинск', 24),
       (330, 'Боготол', 24),
       (331, 'Бородино', 24),
       (332, 'Дивногорск', 24),
       (333, 'Дудинка', 24),
       (334, 'Енисейск', 24),
       (335, 'Железногорск', 24),
       (336, 'Заозерный', 24),
       (337, 'Зеленогорск', 24),
       (338, 'Игарка', 24),
       (339, 'Иланский', 24),
       (340, 'Канск', 24),
       (341, 'Кодинск', 24),
       (342, 'Красноярск', 24),
       (343, 'Лесосибирск', 24),
       (344, 'Минусинск', 24),
       (345, 'Назарово', 24),
       (346, 'Норильск', 24),
       (347, 'Сосновоборск', 24),
       (348, 'Ужур', 24),
       (349, 'Уяр', 24),
       (350, 'Шарыпово', 24),
       (351, 'Далматово', 25),
       (352, 'Катайск', 25),
       (353, 'Курган', 25),
       (354, 'Куртамыш', 25),
       (355, 'Макушино', 25),
       (356, 'Петухово', 25),
       (357, 'Шадринск', 25),
       (358, 'Шумиха', 25),
       (359, 'Щучье', 25),
       (360, 'Дмитриев-Льговский', 26),
       (361, 'Железногорск', 26),
       (362, 'Курск', 26),
       (363, 'Курчатов', 26),
       (364, 'Льгов', 26),
       (365, 'Обоянь', 26),
       (366, 'Рыльск', 26),
       (367, 'Суджа', 26),
       (368, 'Фатеж', 26),
       (369, 'Щигры', 26),
       (370, 'Бокситогорск', 27),
       (371, 'Волосово', 27),
       (372, 'Волхов', 27),
       (373, 'Всеволожск', 27),
       (374, 'Выборг', 27),
       (375, 'Высоцк', 27),
       (376, 'Гатчина', 27),
       (377, 'Ивангород', 27),
       (378, 'Каменногорск', 27),
       (379, 'Кингисепп', 27),
       (380, 'Кириши', 27),
       (381, 'Кировск', 27),
       (382, 'Коммунар', 27),
       (383, 'Кудрово', 27),
       (384, 'Лодейное Поле', 27),
       (385, 'Луга', 27),
       (386, 'Любань', 27),
       (387, 'Мурино', 27),
       (388, 'Никольское', 27),
       (389, 'Новая Ладога', 27),
       (390, 'Отрадное', 27),
       (391, 'Пикалево', 27),
       (392, 'Подпорожье', 27),
       (393, 'Приморск', 27),
       (394, 'Приозерск', 27),
       (395, 'Светогорск', 27),
       (396, 'Сертолово', 27),
       (397, 'Сланцы', 27),
       (398, 'Сосновый Бор', 27),
       (399, 'Сясьстрой', 27),
       (400, 'Тихвин', 27),
       (401, 'Тосно', 27),
       (402, 'Шлиссельбург', 27),
       (403, 'Грязи', 28),
       (404, 'Данков', 28),
       (405, 'Елец', 28),
       (406, 'Задонск', 28),
       (407, 'Лебедянь', 28),
       (408, 'Липецк', 28),
       (409, 'Усмань', 28),
       (410, 'Чаплыгин', 28),
       (411, 'Магадан', 29),
       (412, 'Сусуман', 29),
       (413, 'Москва', 30),
       (414, 'Апрелевка', 31),
       (415, 'Балашиха', 31),
       (416, 'Белоозерский', 31),
       (417, 'Бронницы', 31),
       (418, 'Верея', 31),
       (419, 'Видное', 31),
       (420, 'Волоколамск', 31),
       (421, 'Воскресенск', 31),
       (422, 'Высоковск', 31),
       (423, 'Голицыно', 31),
       (424, 'Дедовск', 31),
       (425, 'Дзержинский', 31),
       (426, 'Дмитров', 31),
       (427, 'Долгопрудный', 31),
       (428, 'Домодедово', 31),
       (429, 'Дрезна', 31),
       (430, 'Дубна', 31),
       (431, 'Егорьевск', 31),
       (432, 'Жуковский', 31),
       (433, 'Зарайск', 31),
       (434, 'Звенигород', 31),
       (435, 'Ивантеевка', 31),
       (436, 'Истра', 31),
       (437, 'Кашира', 31),
       (438, 'Клин', 31),
       (439, 'Коломна', 31),
       (440, 'Королев', 31),
       (441, 'Котельники', 31),
       (442, 'Красноармейск', 31),
       (443, 'Красногорск', 31),
       (444, 'Краснозаводск', 31),
       (445, 'Краснознаменск', 31),
       (446, 'Кубинка', 31),
       (447, 'Куровское', 31),
       (448, 'Ликино-Дулево', 31),
       (449, 'Лобня', 31),
       (450, 'Лосино-Петровский', 31),
       (451, 'Луховицы', 31),
       (452, 'Лыткарино', 31),
       (453, 'Люберцы', 31),
       (454, 'Можайск', 31),
       (455, 'Мытищи', 31),
       (456, 'Наро-Фоминск', 31),
       (457, 'Ногинск', 31),
       (458, 'Одинцово', 31),
       (459, 'Озеры', 31),
       (460, 'Орехово-Зуево', 31),
       (461, 'Павловский Посад', 31),
       (462, 'Пересвет', 31),
       (463, 'Подольск', 31),
       (464, 'Протвино', 31),
       (465, 'Пушкино', 31),
       (466, 'Пущино', 31),
       (467, 'Раменское', 31),
       (468, 'Реутов', 31),
       (469, 'Рошаль', 31),
       (470, 'Руза', 31),
       (471, 'Сергиев Посад', 31),
       (472, 'Серпухов', 31),
       (473, 'Солнечногорск', 31),
       (474, 'Старая Купавна', 31),
       (475, 'Ступино', 31),
       (476, 'Талдом', 31),
       (477, 'Фрязино', 31),
       (478, 'Химки', 31),
       (479, 'Хотьково', 31),
       (480, 'Черноголовка', 31),
       (481, 'Чехов', 31),
       (482, 'Шатура', 31),
       (483, 'Щелково', 31),
       (484, 'Электрогорск', 31),
       (485, 'Электросталь', 31),
       (486, 'Электроугли', 31),
       (487, 'Яхрома', 31),
       (488, 'Апатиты', 32),
       (489, 'Гаджиево', 32),
       (490, 'Заозерск', 32),
       (491, 'Заполярный', 32),
       (492, 'Кандалакша', 32),
       (493, 'Кировск', 32),
       (494, 'Ковдор', 32),
       (495, 'Кола', 32),
       (496, 'Мончегорск', 32),
       (497, 'Мурманск', 32),
       (498, 'Оленегорск', 32),
       (499, 'Островной', 32),
       (500, 'Полярные Зори', 32),
       (501, 'Полярный', 32),
       (502, 'Североморск', 32),
       (503, 'Снежногорск', 32),
       (504, 'Арзамас', 33),
       (505, 'Балахна', 33),
       (506, 'Богородск', 33),
       (507, 'Бор', 33),
       (508, 'Ветлуга', 33),
       (509, 'Володарск', 33),
       (510, 'Ворсма', 33),
       (511, 'Выкса', 33),
       (512, 'Горбатов', 33),
       (513, 'Городец', 33),
       (514, 'Дзержинск', 33),
       (515, 'Заволжье', 33),
       (516, 'Княгинино', 33),
       (517, 'Кстово', 33),
       (518, 'Кулебаки', 33),
       (519, 'Лукоянов', 33),
       (520, 'Лысково', 33),
       (521, 'Навашино', 33),
       (522, 'Нижний Новгород', 33),
       (523, 'Павлово', 33),
       (524, 'Первомайск', 33),
       (525, 'Перевоз', 33),
       (526, 'Саров', 33),
       (527, 'Семенов', 33),
       (528, 'Сергач', 33),
       (529, 'Урень', 33),
       (530, 'Чкаловск', 33),
       (531, 'Шахунья', 33),
       (532, 'Боровичи', 34),
       (533, 'Валдай', 34),
       (534, 'Великий Новгород', 34),
       (535, 'Малая Вишера', 34),
       (536, 'Окуловка', 34),
       (537, 'Пестово', 34),
       (538, 'Сольцы', 34),
       (539, 'Старая Русса', 34),
       (540, 'Холм', 34),
       (541, 'Чудово', 34),
       (542, 'Барабинск', 35),
       (543, 'Бердск', 35),
       (544, 'Болотное', 35),
       (545, 'Искитим', 35),
       (546, 'Карасук', 35),
       (547, 'Каргат', 35),
       (548, 'Куйбышев', 35),
       (549, 'Купино', 35),
       (550, 'Новосибирск', 35),
       (551, 'Обь', 35),
       (552, 'Татарск', 35),
       (553, 'Тогучин', 35),
       (554, 'Черепаново', 35),
       (555, 'Чулым', 35),
       (556, 'Исилькуль', 36),
       (557, 'Калачинск', 36),
       (558, 'Называевск', 36),
       (559, 'Омск', 36),
       (560, 'Тара', 36),
       (561, 'Тюкалинск', 36),
       (562, 'Абдулино', 37),
       (563, 'Бугуруслан', 37),
       (564, 'Бузулук', 37),
       (565, 'Гай', 37),
       (566, 'Кувандык', 37),
       (567, 'Медногорск', 37),
       (568, 'Новотроицк', 37),
       (569, 'Оренбург', 37),
       (570, 'Орск', 37),
       (571, 'Соль-Илецк', 37),
       (572, 'Сорочинск', 37),
       (573, 'Ясный', 37),
       (574, 'Болхов', 38),
       (575, 'Дмитровск', 38),
       (576, 'Ливны', 38),
       (577, 'Малоархангельск', 38),
       (578, 'Мценск', 38),
       (579, 'Новосиль', 38),
       (580, 'Орел', 38),
       (581, 'Белинский', 39),
       (582, 'Городище', 39),
       (583, 'Заречный', 39),
       (584, 'Каменка', 39),
       (585, 'Кузнецк', 39),
       (586, 'Нижний Ломов', 39),
       (587, 'Никольск', 39),
       (588, 'Пенза', 39),
       (589, 'Сердобск', 39),
       (590, 'Спасск', 39),
       (591, 'Сурск', 39),
       (592, 'Александровск', 40),
       (593, 'Березники', 40),
       (594, 'Верещагино', 40),
       (595, 'Горнозаводск', 40),
       (596, 'Гремячинск', 40),
       (597, 'Губаха', 40),
       (598, 'Добрянка', 40),
       (599, 'Кизел', 40),
       (600, 'Красновишерск', 40),
       (601, 'Краснокамск', 40),
       (602, 'Кудымкар', 40),
       (603, 'Кунгур', 40),
       (604, 'Лысьва', 40),
       (605, 'Нытва', 40),
       (606, 'Оса', 40),
       (607, 'Оханск', 40),
       (608, 'Очер', 40),
       (609, 'Пермь', 40),
       (610, 'Соликамск', 40),
       (611, 'Усолье', 40),
       (612, 'Чайковский', 40),
       (613, 'Чердынь', 40),
       (614, 'Чермоз', 40),
       (615, 'Чернушка', 40),
       (616, 'Чусовой', 40),
       (617, 'Арсеньев', 41),
       (618, 'Артем', 41),
       (619, 'Большой Камень', 41),
       (620, 'Владивосток', 41),
       (621, 'Дальнегорск', 41),
       (622, 'Дальнереченск', 41),
       (623, 'Лесозаводск', 41),
       (624, 'Находка', 41),
       (625, 'Партизанск', 41),
       (626, 'Спасск-Дальний', 41),
       (627, 'Уссурийск', 41),
       (628, 'Фокино', 41),
       (629, 'Великие Луки', 42),
       (630, 'Гдов', 42),
       (631, 'Дно', 42),
       (632, 'Невель', 42),
       (633, 'Новоржев', 42),
       (634, 'Новосокольники', 42),
       (635, 'Опочка', 42),
       (636, 'Остров', 42),
       (637, 'Печоры', 42),
       (638, 'Порхов', 42),
       (639, 'Псков', 42),
       (640, 'Пустошка', 42),
       (641, 'Пыталово', 42),
       (642, 'Себеж', 42),
       (643, 'Адыгейск', 43),
       (644, 'Майкоп', 43),
       (645, 'Горно-Алтайск', 44),
       (646, 'Агидель', 45),
       (647, 'Баймак', 45),
       (648, 'Белебей', 45),
       (649, 'Белорецк', 45),
       (650, 'Бирск', 45),
       (651, 'Благовещенск', 45),
       (652, 'Давлеканово', 45),
       (653, 'Дюртюли', 45),
       (654, 'Ишимбай', 45),
       (655, 'Кумертау', 45),
       (656, 'Межгорье', 45),
       (657, 'Мелеуз', 45),
       (658, 'Нефтекамск', 45),
       (659, 'Октябрьский', 45),
       (660, 'Салават', 45),
       (661, 'Сибай', 45),
       (662, 'Стерлитамак', 45),
       (663, 'Туймазы', 45),
       (664, 'Уфа', 45),
       (665, 'Учалы', 45),
       (666, 'Янаул', 45),
       (667, 'Бабушкин', 46),
       (668, 'Гусиноозерск', 46),
       (669, 'Закаменск', 46),
       (670, 'Кяхта', 46),
       (671, 'Северобайкальск', 46),
       (672, 'Улан-Удэ', 46),
       (673, 'Буйнакск', 47),
       (674, 'Дагестанские Огни', 47),
       (675, 'Дербент', 47),
       (676, 'Избербаш', 47),
       (677, 'Каспийск', 47),
       (678, 'Кизилюрт', 47),
       (679, 'Кизляр', 47),
       (680, 'Махачкала', 47),
       (681, 'Хасавюрт', 47),
       (682, 'Южно-Сухокумск', 47),
       (683, 'Карабулак', 48),
       (684, 'Магас', 48),
       (685, 'Малгобек', 48),
       (686, 'Назрань', 48),
       (687, 'Сунжа', 48),
       (688, 'Городовиковск', 49),
       (689, 'Лагань', 49),
       (690, 'Элиста', 49),
       (691, 'Беломорск', 50),
       (692, 'Кемь', 50),
       (693, 'Кондопога', 50),
       (694, 'Костомукша', 50),
       (695, 'Лахденпохья', 50),
       (696, 'Медвежьегорск', 50),
       (697, 'Олонец', 50),
       (698, 'Петрозаводск', 50),
       (699, 'Питкяранта', 50),
       (700, 'Пудож', 50),
       (701, 'Сегежа', 50),
       (702, 'Сортавала', 50),
       (703, 'Суоярви', 50),
       (704, 'Воркута', 51),
       (705, 'Вуктыл', 51),
       (706, 'Емва', 51),
       (707, 'Инта', 51),
       (708, 'Микунь', 51),
       (709, 'Печора', 51),
       (710, 'Сосногорск', 51),
       (711, 'Сыктывкар', 51),
       (712, 'Усинск', 51),
       (713, 'Ухта', 51),
       (714, 'Алупка', 52),
       (715, 'Алушта', 52),
       (716, 'Армянск', 52),
       (717, 'Бахчисарай', 52),
       (718, 'Белогорск', 52),
       (719, 'Джанкой', 52),
       (720, 'Евпатория', 52),
       (721, 'Керчь', 52),
       (722, 'Красноперекопск', 52),
       (723, 'Саки', 52),
       (724, 'Симферополь', 52),
       (725, 'Старый Крым', 52),
       (726, 'Судак', 52),
       (727, 'Феодосия', 52),
       (728, 'Щелкино', 52),
       (729, 'Ялта', 52),
       (730, 'Волжск', 53),
       (731, 'Звенигово', 53),
       (732, 'Йошкар-Ола', 53),
       (733, 'Козьмодемьянск', 53),
       (734, 'Ардатов', 54),
       (735, 'Инсар', 54),
       (736, 'Ковылкино', 54),
       (737, 'Краснослободск', 54),
       (738, 'Рузаевка', 54),
       (739, 'Саранск', 54),
       (740, 'Темников', 54),
       (741, 'Алдан', 55),
       (742, 'Верхоянск', 55),
       (743, 'Вилюйск', 55),
       (744, 'Ленск', 55),
       (745, 'Мирный', 55),
       (746, 'Нерюнгри', 55),
       (747, 'Нюрба', 55),
       (748, 'Олекминск', 55),
       (749, 'Покровск', 55),
       (750, 'Среднеколымск', 55),
       (751, 'Томмот', 55),
       (752, 'Удачный', 55),
       (753, 'Якутск', 55),
       (754, 'Алагир', 56),
       (755, 'Ардон', 56),
       (756, 'Беслан', 56),
       (757, 'Владикавказ', 56),
       (758, 'Дигора', 56),
       (759, 'Моздок', 56),
       (760, 'Агрыз', 57),
       (761, 'Азнакаево', 57),
       (762, 'Альметьевск', 57),
       (763, 'Арск', 57),
       (764, 'Бавлы', 57),
       (765, 'Болгар', 57),
       (766, 'Бугульма', 57),
       (767, 'Буинск', 57),
       (768, 'Елабуга', 57),
       (769, 'Заинск', 57),
       (770, 'Зеленодольск', 57),
       (771, 'Иннополис', 57),
       (772, 'Казань', 57),
       (773, 'Кукмор', 57),
       (774, 'Лаишево', 57),
       (775, 'Лениногорск', 57),
       (776, 'Мамадыш', 57),
       (777, 'Менделеевск', 57),
       (778, 'Мензелинск', 57),
       (779, 'Набережные Челны', 57),
       (780, 'Нижнекамск', 57),
       (781, 'Нурлат', 57),
       (782, 'Тетюши', 57),
       (783, 'Чистополь', 57),
       (784, 'Ак-Довурак', 58),
       (785, 'Кызыл', 58),
       (786, 'Туран', 58),
       (787, 'Чадан', 58),
       (788, 'Шагонар', 58),
       (789, 'Абаза', 59),
       (790, 'Абакан', 59),
       (791, 'Саяногорск', 59),
       (792, 'Сорск', 59),
       (793, 'Черногорск', 59),
       (794, 'Азов', 60),
       (795, 'Аксай', 60),
       (796, 'Батайск', 60),
       (797, 'Белая Калитва', 60),
       (798, 'Волгодонск', 60),
       (799, 'Гуково', 60),
       (800, 'Донецк', 60),
       (801, 'Зверево', 60),
       (802, 'Зерноград', 60),
       (803, 'Каменск-Шахтинский', 60),
       (804, 'Константиновск', 60),
       (805, 'Красный Сулин', 60),
       (806, 'Миллерово', 60),
       (807, 'Морозовск', 60),
       (808, 'Новочеркасск', 60),
       (809, 'Новошахтинск', 60),
       (810, 'Пролетарск', 60),
       (811, 'Ростов-на-Дону', 60),
       (812, 'Сальск', 60),
       (813, 'Семикаракорск', 60),
       (814, 'Таганрог', 60),
       (815, 'Цимлянск', 60),
       (816, 'Шахты', 60),
       (817, 'Касимов', 61),
       (818, 'Кораблино', 61),
       (819, 'Михайлов', 61),
       (820, 'Новомичуринск', 61),
       (821, 'Рыбное', 61),
       (822, 'Ряжск', 61),
       (823, 'Рязань', 61),
       (824, 'Сасово', 61),
       (825, 'Скопин', 61),
       (826, 'Спас-Клепики', 61),
       (827, 'Спасск-Рязанский', 61),
       (828, 'Шацк', 61),
       (829, 'Жигулевск', 62),
       (830, 'Кинель', 62),
       (831, 'Нефтегорск', 62),
       (832, 'Новокуйбышевск', 62),
       (833, 'Октябрьск', 62),
       (834, 'Отрадный', 62),
       (835, 'Похвистнево', 62),
       (836, 'Самара', 62),
       (837, 'Сызрань', 62),
       (838, 'Тольятти', 62),
       (839, 'Чапаевск', 62),
       (840, 'Санкт-Петербург', 63),
       (841, 'Аркадак', 64),
       (842, 'Аткарск', 64),
       (843, 'Балаково', 64),
       (844, 'Балашов', 64),
       (845, 'Вольск', 64),
       (846, 'Ершов', 64),
       (847, 'Калининск', 64),
       (848, 'Красноармейск', 64),
       (849, 'Красный Кут', 64),
       (850, 'Маркс', 64),
       (851, 'Новоузенск', 64),
       (852, 'Петровск', 64),
       (853, 'Пугачев', 64),
       (854, 'Ртищево', 64),
       (855, 'Саратов', 64),
       (856, 'Хвалынск', 64),
       (857, 'Шиханы', 64),
       (858, 'Энгельс', 64),
       (859, 'Александровск-Сахалинский', 65),
       (860, 'Анива', 65),
       (861, 'Долинск', 65),
       (862, 'Корсаков', 65),
       (863, 'Курильск', 65),
       (864, 'Макаров', 65),
       (865, 'Невельск', 65),
       (866, 'Оха', 65),
       (867, 'Поронайск', 65),
       (868, 'Северо-Курильск', 65),
       (869, 'Томари', 65),
       (870, 'Углегорск', 65),
       (871, 'Холмск', 65),
       (872, 'Южно-Сахалинск', 65),
       (873, 'Алапаевск', 66),
       (874, 'Арамиль', 66),
       (875, 'Артемовский', 66),
       (876, 'Асбест', 66),
       (877, 'Березовский', 66),
       (878, 'Богданович', 66),
       (879, 'Верхний Тагил', 66),
       (880, 'Верхняя Пышма', 66),
       (881, 'Верхняя Салда', 66),
       (882, 'Верхняя Тура', 66),
       (883, 'Верхотурье', 66),
       (884, 'Волчанск', 66),
       (885, 'Дегтярск', 66),
       (886, 'Екатеринбург', 66),
       (887, 'Заречный', 66),
       (888, 'Ивдель', 66),
       (889, 'Ирбит', 66),
       (890, 'Каменск-Уральский', 66),
       (891, 'Камышлов', 66),
       (892, 'Карпинск', 66),
       (893, 'Качканар', 66),
       (894, 'Кировград', 66),
       (895, 'Краснотурьинск', 66),
       (896, 'Красноуральск', 66),
       (897, 'Красноуфимск', 66),
       (898, 'Кушва', 66),
       (899, 'Лесной', 66),
       (900, 'Михайловск', 66),
       (901, 'Невьянск', 66),
       (902, 'Нижние Серги', 66),
       (903, 'Нижний Тагил', 66),
       (904, 'Нижняя Салда', 66),
       (905, 'Нижняя Тура', 66),
       (906, 'Новая Ляля', 66),
       (907, 'Новоуральск', 66),
       (908, 'Первоуральск', 66),
       (909, 'Полевской', 66),
       (910, 'Ревда', 66),
       (911, 'Реж', 66),
       (912, 'Североуральск', 66),
       (913, 'Серов', 66),
       (914, 'Среднеуральск', 66),
       (915, 'Сухой Лог', 66),
       (916, 'Сысерть', 66),
       (917, 'Тавда', 66),
       (918, 'Талица', 66),
       (919, 'Туринск', 66),
       (920, 'Севастополь', 67),
       (921, 'Велиж', 68),
       (922, 'Вязьма', 68),
       (923, 'Гагарин', 68),
       (924, 'Демидов', 68),
       (925, 'Десногорск', 68),
       (926, 'Дорогобуж', 68),
       (927, 'Духовщина', 68),
       (928, 'Ельня', 68),
       (929, 'Починок', 68),
       (930, 'Рославль', 68),
       (931, 'Рудня', 68),
       (932, 'Сафоново', 68),
       (933, 'Смоленск', 68),
       (934, 'Сычевка', 68),
       (935, 'Ярцево', 68),
       (936, 'Благодарный', 69),
       (937, 'Буденновск', 69),
       (938, 'Георгиевск', 69),
       (939, 'Ессентуки', 69),
       (940, 'Железноводск', 69),
       (941, 'Зеленокумск', 69),
       (942, 'Изобильный', 69),
       (943, 'Ипатово', 69),
       (944, 'Кисловодск', 69),
       (945, 'Лермонтов', 69),
       (946, 'Минеральные Воды', 69),
       (947, 'Михайловск', 69),
       (948, 'Невинномысск', 69),
       (949, 'Нефтекумск', 69),
       (950, 'Новоалександровск', 69),
       (951, 'Новопавловск', 69),
       (952, 'Пятигорск', 69),
       (953, 'Светлоград', 69),
       (954, 'Ставрополь', 69),
       (955, 'Жердевка', 70),
       (956, 'Кирсанов', 70),
       (957, 'Котовск', 70),
       (958, 'Мичуринск', 70),
       (959, 'Моршанск', 70),
       (960, 'Рассказово', 70),
       (961, 'Тамбов', 70),
       (962, 'Уварово', 70),
       (963, 'Андреаполь', 71),
       (964, 'Бежецк', 71),
       (965, 'Белый', 71),
       (966, 'Бологое', 71),
       (967, 'Весьегонск', 71),
       (968, 'Вышний Волочек', 71),
       (969, 'Западная Двина', 71),
       (970, 'Зубцов', 71),
       (971, 'Калязин', 71),
       (972, 'Кашин', 71),
       (973, 'Кимры', 71),
       (974, 'Конаково', 71),
       (975, 'Красный Холм', 71),
       (976, 'Кувшиново', 71),
       (977, 'Лихославль', 71),
       (978, 'Нелидово', 71),
       (979, 'Осташков', 71),
       (980, 'Ржев', 71),
       (981, 'Старица', 71),
       (982, 'Тверь', 71),
       (983, 'Торжок', 71),
       (984, 'Торопец', 71),
       (985, 'Удомля', 71),
       (986, 'Асино', 72),
       (987, 'Кедровый', 72),
       (988, 'Колпашево', 72),
       (989, 'Северск', 72),
       (990, 'Стрежевой', 72),
       (991, 'Томск', 72),
       (992, 'Алексин', 73),
       (993, 'Белев', 73),
       (994, 'Богородицк', 73),
       (995, 'Болохово', 73),
       (996, 'Венев', 73),
       (997, 'Донской', 73),
       (998, 'Ефремов', 73),
       (999, 'Кимовск', 73),
       (1000, 'Киреевск', 73),
       (1001, 'Липки', 73),
       (1002, 'Новомосковск', 73),
       (1003, 'Плавск', 73),
       (1004, 'Советск', 73),
       (1005, 'Суворов', 73),
       (1006, 'Тула', 73),
       (1007, 'Узловая', 73),
       (1008, 'Чекалин', 73),
       (1009, 'Щекино', 73),
       (1010, 'Ясногорск', 73),
       (1011, 'Белоярский', 74),
       (1012, 'Губкинский', 74),
       (1013, 'Заводоуковск', 74),
       (1014, 'Ишим', 74),
       (1015, 'Когалым', 74),
       (1016, 'Лабытнанги', 74),
       (1017, 'Лангепас', 74),
       (1018, 'Лянтор', 74),
       (1019, 'Мегион', 74),
       (1020, 'Муравленко', 74),
       (1021, 'Надым', 74),
       (1022, 'Нефтеюганск', 74),
       (1023, 'Нижневартовск', 74),
       (1024, 'Новый Уренгой', 74),
       (1025, 'Ноябрьск', 74),
       (1026, 'Нягань', 74),
       (1027, 'Покачи', 74),
       (1028, 'Пыть-Ях', 74),
       (1029, 'Радужный', 74),
       (1030, 'Салехард', 74),
       (1031, 'Советский', 74),
       (1032, 'Сургут', 74),
       (1033, 'Тарко-Сале', 74),
       (1034, 'Тобольск', 74),
       (1035, 'Тюмень', 74),
       (1036, 'Урай', 74),
       (1037, 'Ханты-Мансийск', 74),
       (1038, 'Югорск', 74),
       (1039, 'Ялуторовск', 74),
       (1040, 'Воткинск', 75),
       (1041, 'Глазов', 75),
       (1042, 'Ижевск', 75),
       (1043, 'Камбарка', 75),
       (1044, 'Можга', 75),
       (1045, 'Сарапул', 75),
       (1046, 'Барыш', 76),
       (1047, 'Димитровград', 76),
       (1048, 'Инза', 76),
       (1049, 'Новоульяновск', 76),
       (1050, 'Сенгилей', 76),
       (1051, 'Ульяновск', 76),
       (1052, 'Амурск', 77),
       (1053, 'Бикин', 77),
       (1054, 'Вяземский', 77),
       (1055, 'Комсомольск-на-Амуре', 77),
       (1056, 'Николаевск-на-Амуре', 77),
       (1057, 'Советская Гавань', 77),
       (1058, 'Хабаровск', 77),
       (1059, 'Аша', 78),
       (1060, 'Бакал', 78),
       (1061, 'Верхнеуральск', 78),
       (1062, 'Верхний Уфалей', 78),
       (1063, 'Еманжелинск', 78),
       (1064, 'Златоуст', 78),
       (1065, 'Карабаш', 78),
       (1066, 'Карталы', 78),
       (1067, 'Касли', 78),
       (1068, 'Катав-Ивановск', 78),
       (1069, 'Копейск', 78),
       (1070, 'Коркино', 78),
       (1071, 'Куса', 78),
       (1072, 'Кыштым', 78),
       (1073, 'Магнитогорск', 78),
       (1074, 'Миасс', 78),
       (1075, 'Миньяр', 78),
       (1076, 'Нязепетровск', 78),
       (1077, 'Озерск', 78),
       (1078, 'Пласт', 78),
       (1079, 'Сатка', 78),
       (1080, 'Сим', 78),
       (1081, 'Снежинск', 78),
       (1082, 'Трехгорный', 78),
       (1083, 'Троицк', 78),
       (1084, 'Усть-Катав', 78),
       (1085, 'Чебаркуль', 78),
       (1086, 'Челябинск', 78),
       (1087, 'Южноуральск', 78),
       (1088, 'Юрюзань', 78),
       (1089, 'Аргун', 79),
       (1090, 'Грозный', 79),
       (1091, 'Гудермес', 79),
       (1092, 'Курчалой', 79),
       (1093, 'Урус-Мартан', 79),
       (1094, 'Шали', 79),
       (1095, 'Алатырь', 80),
       (1096, 'Канаш', 80),
       (1097, 'Козловка', 80),
       (1098, 'Мариинский Посад', 80),
       (1099, 'Новочебоксарск', 80),
       (1100, 'Цивильск', 80),
       (1101, 'Чебоксары', 80),
       (1102, 'Шумерля', 80),
       (1103, 'Ядрин', 80),
       (1104, 'Анадырь', 81),
       (1105, 'Билибино', 81),
       (1106, 'Певек', 81),
       (1107, 'Гаврилов-Ям', 82),
       (1108, 'Данилов', 82),
       (1109, 'Любим', 82),
       (1110, 'Мышкин', 82),
       (1111, 'Переславль-Залесский', 82),
       (1112, 'Пошехонье', 82),
       (1113, 'Ростов', 82),
       (1114, 'Рыбинск', 82),
       (1115, 'Тутаев', 82),
       (1116, 'Углич', 82),
       (1117, 'Ярославль', 82);