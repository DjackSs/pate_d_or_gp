
/*
*											DATABASE_BETA TEST DATAS
*/

-- Cards

INSERT INTO Cards (name)
VALUES
    ('Collection Hiver'),
    ('Saveurs de Printemps'),
    ('D�lices d''Automne'),
    ('Sp�cialit�s d''�t�');


-- Restaurants
INSERT INTO Restaurants (name, address, postal_code, town, id_card)
VALUES
    ('Exp�rience Gastronomique', '123 Rue �l�gante', '44000', 'NANTES', 1),
    ('Fusion Gastronomique', '456 Avenue Culinaire', '44000', 'NANTES', 2),
    ('Bistrot M�diterran�en', '789 Chemin Savoureux', '44000', 'NANTES', 3),
    ('Cuisine Contemporaine', '101 Place Moderne', '44000', 'NANTES', 4);


-- Schedules
INSERT INTO Schedules (open_hour, close_hour, id_restaurant)
VALUES
    (CAST('2024-01-01 12:00:00' AS DATETIME), CAST('2024-01-01 14:30:00' AS DATETIME), 1),
	(CAST('2024-01-01 18:00:00' AS DATETIME), CAST('2024-01-01 22:30:00' AS DATETIME), 1),
    (CAST('2024-01-01 11:30:00' AS DATETIME), CAST('2024-01-01 15:00:00' AS DATETIME), 2),
    (CAST('2024-01-01 12:15:00' AS DATETIME), CAST('2024-01-01 15:00:00' AS DATETIME), 3),
    (CAST('2024-01-01 12:00:00' AS DATETIME), CAST('2024-01-01 14:30:00' AS DATETIME), 4);


-- Dishes
INSERT INTO Dishes (name, price, description, category, id_card)
VALUES
    ('Spaghetti Bolognese', 18, 'P�tes italiennes classiques avec une sauce � la viande riche', 'dish', 1),
    ('Filet Mignon', 28, 'Steak de b�uf tendre, parfaitement cuit selon vos pr�f�rences', 'dish', 2),
    ('Martini Royale', 12, 'Un m�lange sophistiqu� de vodka et de vermouth', 'beverage', 3),
    ('Mousse au Chocolat', 10, 'D�lice au chocolat fondant, une fin sucr�e pour votre repas', 'desert', 4),
    ('Panna Cotta � la Vanille', 9, 'Dessert � la vanille cr�meux avec compote de baies', 'desert', 1),
    ('Ratatouille Proven�ale', 22, 'Un m�lange de l�gumes frais dans une sauce tomate savoureuse', 'dish', 2),
    ('Salade C�sar', 14, 'Laitue romaine croquante, fromage parmesan et vinaigrette C�sar', 'entry', 3),
    ('Gratin Dauphinois', 18, 'Gratin de pommes de terre cr�meux avec fromage fondu, un classique fran�ais', 'dish', 4),
    ('Old Fashioned', 15, 'Un cocktail intemporel avec bourbon, sucre et bitter', 'beverage', 1),
    ('Limonade P�tillante', 8, 'Limonade rafra�chissante avec une touche d''effervescence', 'beverage', 2),
    ('Salade de Ch�vre', 12, 'Salade mixte garnie de fromage de ch�vre chaud et vinaigrette balsamique', 'entry', 3),
    ('Confit de Canard', 24, 'Cuisse de canard cuite lentement avec une peau croustillante et une viande tendre', 'dish', 4),
    ('Kebab d''Agneau', 20, 'Brochettes d''agneau marin�es grill�es, un d�lice savoureux', 'dish', 1),
    ('Tarte aux Pommes', 10, 'Tarte aux pommes maison avec une cro�te feuillet�e, servie chaude', 'desert', 2),
    ('Porc R�ti aux Carottes', 22, 'Porc r�ti tendre avec des carottes glac�es, un plat copieux', 'dish', 3);


-- Users
INSERT INTO Users
	(name,				lastname,			email,								password,		role)
VALUES
    ('Pierre Olivier', 'Mpolesha',		'pierre-olivier.mpolesha@email.com', 'madara',			'admi'),
    ('Julien',			 'Bisson',		'julien.bisson@email.com',			 'roronoa',			 'admi'),
    ('Nathan',			'Hamon',		'nathan.hamon@email.com',			'ryuzaki',			'admi');


-- Messages
INSERT INTO Messages (object, content, id_user)
VALUES
    ('Feedback Qualit�', 'Merci pour l''exp�rience culinaire exceptionnelle! La nourriture �tait exceptionnelle.', 1),
    ('Demande de Menu', 'Pourriez-vous fournir des informations sur les options v�g�tariennes dans le menu?', 2);


-- Tables
INSERT INTO Tables (number_place, state, id_restaurant)
VALUES
    (4, 'pres', 1),
    (2, null, 1),
    (6, 'pres', 2),
    (8, null, 3);


-- Orders
INSERT INTO Orders (state, id_table)
VALUES
    (null, 1),
    ('take', 1),
    ('read', 1),
    ('serv', 1),
    ('sold', 1);


-- Reservations
INSERT INTO Reservations (reservation_time, state, id_table, id_user)
VALUES
    (CAST('2024-01-05 18:30:00' AS DATETIME), 'hold', 1, 1),
    (CAST('2024-01-07 19:00:00' AS DATETIME), 'gran', 2, 2),
    (CAST('2024-01-10 20:00:00' AS DATETIME), 'deni', 3, 3)


-- Orders_dishes
INSERT INTO Orders_Dishes (id_order, id_dish)
VALUES
    (1, 3),
    (2, 5),
    (3, 6),
    (4, 9),
    (5, 12),
    (1, 1),
    (2, 4),
    (3, 8),
    (4, 11);
