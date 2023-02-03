create database enchere;

\c enchere;
create sequence categorie_seq;
create sequence Categorie_produit_seq;
create sequence commission_seq;
create sequence enchere_seq;
create sequence mot_cle_seq;
create sequence mouvement_seq;
create sequence produit_seq;
create sequence renchere_utilisateur_seq;
create sequence sary_seq;
create sequence Utilisateur_seq;
create sequence unite_seq;
create sequence admin_seq;

CREATE TABLE categorie (
  idcategorie  varchar(15) NOT NULL DEFAULT 'CAT'||nextval('categorie_seq'), 
  nomcategorie varchar(50) NOT NULL, 
  PRIMARY KEY (idcategorie)
  );
insert into categorie(nomcategorie) values
('Loisirs'),('Antiquite'),('Instruments'),('Jouet'),('Divertissement')
;

CREATE TABLE Utilisateur (
  idutilisateur  varchar(15) NOT NULL DEFAULT 'UTI'||nextval('utilisateur_seq'), 
  nom            varchar(40) NOT NULL, 
  prenom         varchar(40) NOT NULL, 
  identifiant    varchar(40) NOT NULL, 
  mdp            varchar(40) NOT NULL , 
  datenaissance  date DEFAULT current_timestamp NOT NULL, 
  token          varchar(255) default NULL, 
  dateExpiration timestamptz default current_timestamp+'1:00:00'::time,
  datedeconnect timestamptz default current_timestamp,
  PRIMARY KEY (idutilisateur)
  );
insert into Utilisateur(nom, prenom, identifiant, mdp, datenaissance) values
('Rakoto','Jean','jean','51f8b1fa9b424745378826727452997ee2a7c3d7','2000-03-20'),
('Ravelo','Jeanne','jeanne','f4cd4c30077271414e90e7feb56bda34e19cb7ad','1999-04-10'),
('Rajao','Liva','liva','4909f4f5dbac2fe2b2bcaab0b3537fdbe9ca24d0','2002-10-11')
;

create table unite(
  idunite varchar(15) default 'UNI'||nextval('unite_seq') PRIMARY key,
  nomunite varchar(40)
);
insert into unite(nomunite) values('unite'),('litre'),('ml'),('paquet');


CREATE TABLE Produit (
  idproduit     varchar(15) NOT NULL DEFAULT 'PRO'||nextval('produit_seq') , 
  nomproduit    varchar(40) NOT NULL, 
  idutilisateur varchar(15) NOT NULL, 
  nombre float NOT NULL default 0,
  idunite varchar(15) NOT NULL,
  descri varchar(200) not null,
  PRIMARY KEY (idproduit)
);



insert into produit(nomproduit,idutilisateur,idunite,nombre,descri) values
('Statue','UTI1','UNI1',10,'Mlay'),
('Vase','UTI2','UNI1',10,'Mlay'),
('Violon','UTI3','UNI1',10,'Mlay')
;

CREATE TABLE Categorie_produit (
  idcategorie varchar(15) NOT NULL, 
  idproduit   varchar(15) NOT NULL
  );
  insert into Categorie_produit(idproduit,idcategorie) values
  ('PRO1','CAT2'),
  ('PRO2','CAT2'),
  ('PRO3','CAT3'),
  ('PRO3','CAT5')
  ;



CREATE TABLE comission (
  idcommission     varchar(15) NOT NULL DEFAULT 'COM'||nextval('commission_seq'),  
  valeurcommission float NOT NULL default 0, 
  PRIMARY KEY (idcommission)
  );
insert into comission(valeurcommission) values
(10.0),(30.0)
;  

CREATE TABLE enchere (
  idenchere    varchar(15) NOT NULL DEFAULT 'ENC'||nextval('enchere_seq'),
  idproduit    varchar(15) NOT NULL, 
  dateenchere  timestamptz  NOT NULL default CURRENT_TIMESTAMP, 
  prixenchere  float DEFAULT 0 NOT NULL, 
  dureeenchere float DEFAULT 0 NOT NULL, 
  etat         integer NOT NULL default 0, 
  idcommission varchar(15) NOT NULL, 
  PRIMARY KEY (idenchere)
);

insert into enchere(idproduit,prixenchere,dureeenchere,idcommission) values
('PRO1',200,20,'COM1'),
('PRO2',1000,30,'COM2'),
('PRO3',40000,40,'COM2')
;




CREATE TABLE Mouvement (
  idmouvement   varchar(15) NOT NULL DEFAULT 'MOU'||nextval('mouvement_seq') , 
  idutilisateur varchar(15) NOT NULL, 
  montant       float NOT NULL, 
  dateinsertion date NOT NULL default current_timestamp, 
  motif varchar(20) not null,
  valide integer not null default 0,
  PRIMARY KEY (idmouvement)
  );
insert into mouvement(idutilisateur,montant,dateinsertion,motif) values
('UTI1',100000,current_timestamp,'enchere'),
('UTI2',100000,current_timestamp,'enchere'),
('UTI3',100000,current_timestamp,'enchere')
;


CREATE TABLE Renchere_utilisateur (
  idrenchere    varchar(15) NOT NULL DEFAULT 'REN'||nextval('renchere_utilisateur_seq'), 
  idenchere     varchar(15) NOT NULL, 
  idutilisateur varchar(15) NOT NULL, 
  daterenchere  timestamptz default current_timestamp, 
  prix          float NOT NULL default 0, 
  PRIMARY KEY (idrenchere)
  );
insert into renchere_utilisateur(idenchere,idutilisateur,prix) values
('ENC1','UTI2',350),
('ENC1','UTI3',400),
('ENC2','UTI1',1100),
('ENC2','UTI3',1200),
('ENC3','UTI1',42000),
('ENC3','UTI2',43000)
;

CREATE TABLE Sary (
  idsary   varchar(15) NOT NULL DEFAULT 'SAR'||nextval('sary_seq'), 
  basesary text NOT NULL, 
  PRIMARY KEY (idsary)
  );

CREATE TABLE Sary_produit (
  idsary    varchar(15) NOT NULL, 
  idproduit varchar(15) NOT NULL
  );

create table administrateur(
  idadministrateur varchar(15) NOT NULL default 'AD'||nextval('admin_seq')  primary key,
  identifiant varchar(40) not null,
  mdp varchar(40) not null,
  dateexpiration timestamptz not null  default current_timestamp+'1:00:00'::time,
  token varchar(200)
);
insert into administrateur (identifiant,mdp) values ('admin','d033e22ae348aeb5660fc2140aec35850c4da997');

-- Details
create view detail_enchere as
select enchere.*,produit.nomproduit,utilisateur.*,comission.valeurcommission
from enchere
join produit on produit.idproduit=enchere.idproduit
join comission on comission.idcommission=enchere.idcommission
join utilisateur on utilisateur.idutilisateur=produit.idutilisateur
;

create view detail_catprod as 
select produit.*,categorie.* from Categorie_produit
join produit on produit.idproduit=Categorie_produit.idproduit
join categorie on categorie.idcategorie=Categorie_produit.idcategorie
;

create view detail_saryprod as 
select produit.*,sary.*
from sary_produit
join produit 
on produit.idproduit=sary_produit.idproduit
join sary on sary.idsary=sary_produit.idsary
;
create view detail_renchere as 
select utilisateur.*,
renchere_utilisateur.idenchere,renchere_utilisateur.idrenchere,renchere_utilisateur.prix,renchere_utilisateur.daterenchere
from renchere_utilisateur 
join utilisateur on utilisateur.idutilisateur=renchere_utilisateur.idutilisateur;


create view tri_mise AS
select * from detail_renchere
order by prix desc
;


-- Recherche avancée
create view recherche_avancee as 
select enchere.*,
produit.nomproduit,produit.idutilisateur,produit.nombre,produit.idunite,produit.descri,
detail_catprod.idcategorie,detail_catprod.nomcategorie
from enchere
join produit on 
produit.idproduit=enchere.idproduit
join detail_catprod 
on detail_catprod.idproduit=produit.idproduit;
;

CREATE or replace VIEW renchere_enchere as
select renchere_utilisateur.*,
enchere.idproduit,enchere.dateenchere,enchere.prixenchere,enchere.dureeenchere,enchere.etat,enchere.idcommission
from renchere_utilisateur
join enchere on enchere.idenchere=renchere_utilisateur.idenchere;




create view variation_money as 
select sum(montant),idutilisateur,EXTRACT(month from dateinsertion) mois,EXTRACT(year from dateinsertion) annee
from mouvement 
group by idutilisateur,EXTRACT(month from dateinsertion),EXTRACT(year from dateinsertion)
;


--Stats admin
create view cat_rencheri as 
select count(idcategorie)::integer nombre,idcategorie from renchere_utilisateur 
join enchere
on enchere.idenchere=renchere_utilisateur.idenchere
join detail_catprod on detail_catprod.idproduit=enchere.idproduit
group by idcategorie
;  

create view categorie_rencheri as 
select cat_rencheri.*,nomcategorie from cat_rencheri 
join categorie 
on categorie.idcategorie=cat_rencheri.idcategorie
order by nombre desc
; 

create view moyenne_commission as 
select avg(valeurcommission) 
from enchere
join comission 
on comission.idcommission=enchere.idcommission
;

create view moyenne_mise as 
select avg(prix) from renchere_utilisateur
;

--Enchere be renchere indrindra
create view enchereplusrencheri as
select count(idrenchere)::integer,idenchere from renchere_utilisateur
group by idenchere; 


create view enchere_plus_rencheri as 
select count, 
detail_enchere.*
from 
enchereplusrencheri
join detail_enchere 
on detail_enchere.idenchere = enchereplusrencheri.idenchere
;



ALTER TABLE Categorie_produit ADD CONSTRAINT FKCategorie_343143 FOREIGN KEY (idproduit) REFERENCES Produit (idproduit);

ALTER TABLE Categorie_produit ADD CONSTRAINT FKCategorie_385079 FOREIGN KEY (idcategorie) REFERENCES Categorie (idcategorie);


ALTER TABLE Enchere ADD CONSTRAINT FKEnchere288962 FOREIGN KEY (idproduit) REFERENCES Produit (idproduit);

ALTER TABLE Renchere_utilisateur ADD CONSTRAINT FKRenchere_u78995 FOREIGN KEY (idutilisateur) REFERENCES Utilisateur (idutilisateur);

ALTER TABLE Renchere_utilisateur ADD CONSTRAINT FKRenchere_u869792 FOREIGN KEY (idenchere) REFERENCES Enchere (idenchere);

ALTER TABLE Mouvement ADD CONSTRAINT FKMouvement235248 FOREIGN KEY (idutilisateur) REFERENCES Utilisateur (idutilisateur);

ALTER TABLE Enchere ADD CONSTRAINT FKEnchere693976 FOREIGN KEY (idcommission) REFERENCES Comission (idcommission);

ALTER TABLE Sary_produit ADD CONSTRAINT FKSary_produ764002 FOREIGN KEY (idsary) REFERENCES Sary (idsary);

ALTER TABLE Sary_produit ADD CONSTRAINT FKSary_produ364146 FOREIGN KEY (idproduit) REFERENCES Produit (idproduit);

ALTER TABLE Produit ADD CONSTRAINT FKProduit544176 FOREIGN KEY (idutilisateur) REFERENCES Utilisateur (idutilisateur);

ALTER TABLE Produit ADD CONSTRAINT FKProduit544177 FOREIGN KEY (idunite) REFERENCES unite (idunite);

CREATE VIEW nombre_rencherisseur AS
SELECT 
    count(idutilisateur)::integer as nombre,
  EXTRACT('month' from dateenchere) AS mois,
  EXTRACT('year' from dateenchere) AS annee
FROM 
  enchere 
  JOIN Renchere_utilisateur ON enchere.idenchere = Renchere_utilisateur.idenchere
GROUP BY 
  mois, 
  annee
ORDER BY 
  mois,annee;
  
  create view statistique_argent_gagne as
SELECT 
    enchere.idproduit, 
    SUM(prixenchere) AS total_ench, 
    MAX(prixenchere) AS max_ench,
    EXTRACT(month from dateenchere) as mois,
    EXTRACT(year from dateenchere) as annee
FROM enchere
GROUP BY idproduit , EXTRACT(month from dateenchere) , EXTRACT(year from dateenchere);

