INSERT INTO address (id, country, zipcode, town, street, housenumber) VALUES
(10001, 'Hungary', 1039, 'Budapest', 'Balazs utca', 19),
(10002, 'USA', 10025, 'New York', 'Bleecker street', 66),
(10003, 'Hungary', 1035, 'Budapest', 'Jozsef Attila ut', 3),
(10004, 'Hungary', 2011, 'Budakalasz', 'Budai ut', 112),
(10005, 'Germany', 10318, 'Berlin', 'Berliner Strasse', 218);

INSERT INTO manufacturer (id, name, address_id, dateoffundation, description) VALUES
(10001, 'Adidas', 10001, TO_DATE('17/12/1933', 'DD/MM/YYYY'), 'Adidas description'),
(10002, 'Nike', 10002, TO_DATE('11/04/1909', 'DD/MM/YYYY'), 'Nike description');

INSERT INTO cart (id) VALUES
(10001),
(10002),
(10003);

INSERT INTO customer (id, firstname, lastname, dateofbirth, gender, email, address_id, cart_id) VALUES
(10001, 'Tivadar', 'Bakos', TO_DATE('12/08/1991', 'DD/MM/YYYY'), 'male', 'tivadarbakos@fakemail.com', 10003, 10001),
(10002, 'Artur', 'Gomboc', TO_DATE('11/11/1977', 'DD/MM/YYYY'), 'male', 'gombocartur@fakemail.com', 10004, 10002),
(10003, 'Herbert', 'Bauer', TO_DATE('23/02/1993', 'DD/MM/YYYY'), 'male', 'herbertmaster777@fakemail.com', 10005, 10003);


INSERT INTO product (id, name, price, color, category, manufacturer_id) VALUES
(10001, 'Adidas F10', 22990, 'black', 'shoes', 10001),
(10002, 'Adidas SlippersX10', 13990, 'blue', 'slippers', 10001),
(10003, 'Adidas NMD_R1', 44990, 'white', 'shoes', 10001),
(10004, 'Nike Air Max 720', 43990, 'blue', 'shoes', 10002),
(10005, 'Nike Air Max Alpha', 26990, 'black', 'shoes', 10002);

INSERT INTO review (id, customer_id, product_id, grade, content, date) VALUES
(10001, 10001, 10001, 5, 'Looks great and really confortable. I really like it.', TO_DATE('11/07/2018', 'DD/MM/YYYY')),
(10002, 10002, 10001, 3, 'I loved it, but it has durability problems.', TO_DATE('10/10/2017', 'DD/MM/YYYY'));