INSERT INTO "STATUS" (ID, VERSION, NAME)
VALUES (1, 1, 'Imported lead'),
       (2, 1, 'Not contacted'),
       (3, 1, 'Contacted'),
       (4, 1, 'Customer'),
       (5, 1, 'Closed (lost)');

INSERT INTO "COURIER" (ID, VERSION, FULL_NAME)
VALUES (6, 1, 'Phillsadsadsadips Van Heusen Corp.'),
       (7, 1, 'Avsadsadaya Inc.'),
       (8, 1, 'Laboratory Corporation of America Holdings'),
       (9, 1, 'AutoZone, Inc.'),
       (10, 1, 'Linens ''n Things Inc.');

INSERT INTO "DELIVERY" (ID, VERSION, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, COURIER_ID, STATUS_ID)VALUES
(11,1,'Ivan','Solakov','Bulgaria 2','0888888888',8,2),
(12,1,'Martin','Ivanov','Marmarliiska 22','0821342121',7,4),
(13,1,'Simeon','Lukov','Asti 221','0888421218',10,2),
(14,1,'Trifon','Simeonov','Bulgaria 112','088809481288',9,1),
(15,1,'Silvestar','Petrov','Bulgaria 242','08898298188',6,3);
