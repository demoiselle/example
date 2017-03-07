-- You can use this file to load seed data into the database using SQL statements
INSERT INTO USUARIO VALUES('b83810af-7ba9-4aea-8bb6-f4992a72c5fb', 'admin@demoiselle.org', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 0);

INSERT INTO CATEGORIA VALUES('bc9b86f7-63ee-4175-8443-dcb6589235d4', 'Multas');
INSERT INTO CATEGORIA VALUES('095fbf22-364c-4d84-b332-87efca17da86', 'Dúvidas');

INSERT INTO TOPICO VALUES('095fbf22-364c-4d84-b332-87efca17da86', 'bc9b86f7-63ee-4175-8443-dcb6589235d4', 'Solicitação de mudança de endereço');
INSERT INTO TOPICO VALUES('cd46a824-ade4-4cee-a2af-7e881bf4801d', 'bc9b86f7-63ee-4175-8443-dcb6589235d4', 'Lei seca');

INSERT INTO TOPICO VALUES('ee851622-2ac1-4931-ab73-a668d570db6a', '095fbf22-364c-4d84-b332-87efca17da86', 'Como faço para emplacar meu carro');
INSERT INTO TOPICO VALUES('117fe76f-05e7-44a0-a7fd-52e89a53375e', '095fbf22-364c-4d84-b332-87efca17da86', 'Processo de emplacamento');

INSERT INTO MENSAGEM VALUES('1612e1e7-a388-4f76-bd67-9954f3062ed1', '2017-03-07T11:57:40+00:00' , 'AI SIM',  '117fe76f-05e7-44a0-a7fd-52e89a53375e', 'b83810af-7ba9-4aea-8bb6-f4992a72c5fb');
INSERT INTO MENSAGEM VALUES('e3267237-fb59-4f62-a045-0cd5af692e3c', '2017-03-07T11:57:40+00:00' , 'Quero andar sem placa',  '117fe76f-05e7-44a0-a7fd-52e89a53375e', 'b83810af-7ba9-4aea-8bb6-f4992a72c5fb');