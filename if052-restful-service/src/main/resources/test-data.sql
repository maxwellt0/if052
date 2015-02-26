INSERT INTO `user` VALUES (1,'Name1','SURNAME1','MIDDLENAME1','LOGIN111','PASS1111'),
(2,'Name2','SURNAME2','MIDDLENAME2','LOGIN222','PASS2222'),
(3,'Name3','SURNAME3','MIDDLENAME3','LOGIN333','PASS3333');


INSERT INTO `address` VALUES (1, 'CITY1', 'STREET1', 'BUILDING1', 'APPARTMENT1', 0.1, 1),
  (2, 'CITY2', 'STREET2', 'BUILDING2', 'APPARTMENT2', 0.2, 1),
  (3, 'CITY3', 'STREET3', 'BUILDING3', 'APPARTMENT3', 0.3, 1),
  (4, 'CITY4', 'STREET4', 'BUILDING4', 'APPARTMENT4', 0.15, 2),
  (5, 'CITY5', 'STREET5', 'BUILDING5', 'APPARTMENT5', 0.16, 2),
  (6, 'CITY6', 'STREET6', 'BUILDING6', 'APPARTMENT6', 0.17, 2),
  (7, 'CITY7', 'STREET7', 'BUILDING7', 'APPARTMENT7', 0.19, 3),
  (8, 'CITY8', 'STREET8', 'BUILDING8', 'APPARTMENT8', 0.1, 3);

INSERT INTO `watermeter`
VALUES (1, 'NAME1', 'DESCRIPTION1', 1), (2, 'NAME2', 'DESCRIPTION2', 1), (3, 'NAME3', 'DESCRIPTION3', 1),
  (4, 'NAME4', 'DESCRIPTION4', 2), (5, 'NAME5', 'DESCRIPTION5', 2), (6, 'NAME6', 'DESCRIPTION6', 3),
  (7, 'NAME7', 'DESCRIPTION7', 3), (8, 'NAME8', 'DESCRIPTION8', 4), (9, 'NAME9', 'DESCRIPTION9', 4),
  (10, 'NAME10', 'DESCRIPTION10', 5), (11, 'NAME11', 'DESCRIPTION11', 5), (12, 'NAME12', 'DESCRIPTION12', 6),
  (13, 'NAME13', 'DESCRIPTION13', 6), (14, 'NAME14', 'DESCRIPTION14', 7), (15, 'NAME15', 'DESCRIPTION15', 7),
  (16, 'NAME16', 'DESCRIPTION16', 8), (17, 'NAME17', 'DESCRIPTION17', 8), (18, 'NAME18', 'DESCRIPTION18', 8),
  (19, 'NAME19', 'DESCRIPTION19', 7), (20, 'NAME20', 'DESCRIPTION20', 6), (21, 'NAME21', 'DESCRIPTION21', 5),
  (22, 'NAME22', 'DESCRIPTION22', 4), (23, 'NAME23', 'DESCRIPTION23', 3), (24, 'NAME24', 'DESCRIPTION24', 2),
  (25, 'NAME25', 'DESCRIPTION25', 1);

INSERT INTO `indicator` VALUES (1, '2015-01-01 00:00:00', 101, 0, 0, 1), (2, '2015-02-02 20:03:07', 1234, 1, 0, 1),
  (3, '2015-01-02 00:00:00', 101, 0, 1, 2), (4, '2015-01-03 00:00:00', 101, 1, 1, 3),
  (5, '2015-01-04 00:00:00', 101, 0, 1, 4), (6, '2015-01-05 00:00:00', 101, 1, 0, 5);
