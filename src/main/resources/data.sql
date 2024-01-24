INSERT INTO "STATUS" (ID, VERSION, NAME)
VALUES (1, 1, 'Sent'),
       (2, 1, 'Delivered'),
       (3, 1, 'In Office'),
       (4, 1, 'Registered'),
       (5, 1, 'Unknown');

INSERT INTO "COURIER" (ID, VERSION, FULL_NAME)
VALUES (6, 1, 'Econt'),
       (7, 1, 'DHL'),
       (8, 1, 'UPS'),
       (9, 1, 'Speedy'),
       (10, 1, 'Fedex');

INSERT INTO "DELIVERY" (ID, VERSION, FIRST_NAME, LAST_NAME,CITY, ADDRESS, PHONE_NUMBER, COURIER_ID, STATUS_ID)VALUES
(11,1,'Ivan','Solakov','Sofia','Bulgaria 2','0888888888',8,2),
(12, 1, 'Maria', 'Ivanova', 'Plovdiv', 'Vasil Levski 5', '0899999999', 6, 1),
(13, 1, 'Stefan', 'Georgiev', 'Varna', 'Hristo Botev 8', '0877777777', 7, 3),
(14, 1, 'Elena', 'Petrova', 'Burgas', 'Tsar Samuil 12', '0866666666', 9, 4),
(15, 1, 'Nikolay', 'Dimitrov', 'Ruse', 'Struma 15', '0844444444', 10, 5),
(16, 1, 'Diana', 'Ilieva', 'Stara Zagora', 'Angel Kanchev 20', '0833333333', 8, 2),
(17, 1, 'Petar', 'Georgiev', 'Sofia', '8-mi Dekemvri 18', '0822222222', 7, 1),
(18, 1, 'Viktoria', 'Petrova', 'Pleven', 'Doiran 25', '0811111111', 6, 3),
(19, 1, 'Mihail', 'Ivanov', 'Sofia', 'Slivnica 30', '0800000000', 9, 4),
(20, 1, 'Stela', 'Nikolova', 'Veliko Tarnovo', 'Ivan Momchilov 3', '0799999999', 10, 5),
(21, 1, 'Ivan', 'Stoyanov', 'Gabrovo', 'Dunav 4', '0788888888', 8, 2);

INSERT INTO "USERS"(ID,VERSION,IS_ACTIVE,PASSWORD_HASH,PASSWORD_SALT,ROLE_ID,USERNAME) VALUES
                        (1, 1, TRUE, 'c361c3e336063cef419ef2d66127a84bde58e38a', '3Y+|eH7c4q/E(>gmd+FC#4r=Wik_]T+I', 0, 'user'),
                        (2, 1, true, '847aaf6a3bdcc3fe9a3d6ccd87edb423f62ce0e1', ']]/qK|J,TzP[R8VaT:Tlla=,)4@&#6;t', 1, 'admin');