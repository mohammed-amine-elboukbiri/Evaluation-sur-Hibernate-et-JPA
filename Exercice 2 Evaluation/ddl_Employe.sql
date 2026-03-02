CREATE TABLE Employe
(
    id        INT AUTO_INCREMENT NOT NULL,
    nom       VARCHAR(255)       NULL,
    prenom    VARCHAR(255)       NULL,
    telephone VARCHAR(255)       NULL,
    CONSTRAINT pk_employe PRIMARY KEY (id)
);