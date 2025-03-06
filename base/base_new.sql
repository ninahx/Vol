-- Séquence pour la table Avion
CREATE SEQUENCE avion_seq START 1;

-- Séquence pour la table VilleDeservie
CREATE SEQUENCE ville_deservie_seq START 1;

-- Séquence pour la table User
CREATE SEQUENCE user_seq START 1;

-- Séquence pour la table TypeSiege
CREATE SEQUENCE type_siege_seq START 1;

-- Séquence pour la table Reservation
CREATE SEQUENCE reservation_seq START 1;

-- Séquence pour la table Vol
CREATE SEQUENCE vol_seq START 1;

-- Fonction pour générer l'ID de la table Avion
CREATE OR REPLACE FUNCTION generate_avion_id()
RETURNS TEXT AS $$
BEGIN
    RETURN 'AVI' || nextval('avion_seq');
END;
$$ LANGUAGE plpgsql;

-- Fonction pour générer l'ID de la table VilleDeservie
CREATE OR REPLACE FUNCTION generate_ville_deservie_id()
RETURNS TEXT AS $$
BEGIN
    RETURN 'VIL' || nextval('ville_deservie_seq');
END;
$$ LANGUAGE plpgsql;

-- Fonction pour générer l'ID de la table User
CREATE OR REPLACE FUNCTION generate_user_id()
RETURNS TEXT AS $$
BEGIN
    RETURN 'USR' || nextval('user_seq');
END;
$$ LANGUAGE plpgsql;

-- Fonction pour générer l'ID de la table TypeSiege
CREATE OR REPLACE FUNCTION generate_type_siege_id()
RETURNS TEXT AS $$
BEGIN
    RETURN 'TSG' || nextval('type_siege_seq');
END;
$$ LANGUAGE plpgsql;

-- Fonction pour générer l'ID de la table Reservation
CREATE OR REPLACE FUNCTION generate_reservation_id()
RETURNS TEXT AS $$
BEGIN
    RETURN 'RES' || nextval('reservation_seq');
END;
$$ LANGUAGE plpgsql;

-- Fonction pour générer l'ID de la table Vol
CREATE OR REPLACE FUNCTION generate_vol_id()
RETURNS TEXT AS $$
BEGIN
    RETURN 'VOL' || nextval('vol_seq');
END;
$$ LANGUAGE plpgsql;

-- Table Avion
CREATE TABLE Avion (
    id VARCHAR(50) PRIMARY KEY DEFAULT generate_avion_id(),
    model VARCHAR(100) NOT NULL,
    nombre_siege_business INT NOT NULL,
    nombre_siege_eco INT NOT NULL,
    date_fabrication DATE NOT NULL
);

-- Table VilleDeservie
CREATE TABLE VilleDeservie (
    id VARCHAR(50) PRIMARY KEY DEFAULT generate_ville_deservie_id(),
    nom_ville VARCHAR(100) NOT NULL,
    code_ville VARCHAR(10) NOT NULL
);

-- Table User
CREATE TABLE Users (
    id VARCHAR(50) PRIMARY KEY DEFAULT generate_user_id(),
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    mot_de_passe VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- Table TypeSiege
CREATE TABLE TypeSiege (
    id VARCHAR(50) PRIMARY KEY DEFAULT generate_type_siege_id(),
    type VARCHAR(50) NOT NULL
);

-- Table Reservation
CREATE TABLE Reservation (
    id VARCHAR(50) PRIMARY KEY DEFAULT generate_reservation_id(),
    vol_id VARCHAR(50) NOT NULL,
    client_nom VARCHAR(100) NOT NULL,
    client_prenom VARCHAR(100) NOT NULL,
    client_email VARCHAR(100) NOT NULL,
    type_siege_id VARCHAR(50) NOT NULL,
    nombre_siege INT NOT NULL, -- Nouvelle colonne pour le nombre de sièges
    date_reservation TIMESTAMP NOT NULL,
    FOREIGN KEY (vol_id) REFERENCES Vol(id),
    FOREIGN KEY (type_siege_id) REFERENCES TypeSiege(id)
);
-- Table Vol
CREATE TABLE vol (
	id varchar(50) DEFAULT generate_vol_id() NOT NULL,
	ville_depart_id varchar(50) NOT NULL,
	ville_arrivee_id varchar(50) NOT NULL,
	date_depart timestamp NOT NULL,
	date_arrivee timestamp NOT NULL,
	nombre_sieges_business_promo int4 NULL,
	nombre_sieges_eco_promo int4 NULL,
	heure_reservation_avant_vol int4 NULL,
	heure_annulation_avant_vol int4 NULL,
	CONSTRAINT vol_pkey PRIMARY KEY (id),
	CONSTRAINT vol_ville_arrivee_id_fkey FOREIGN KEY (ville_arrivee_id) REFERENCES villedeservie(id),
	CONSTRAINT vol_ville_depart_id_fkey FOREIGN KEY (ville_depart_id) REFERENCES villedeservie(id)
);

CREATE TABLE vol_avion (
	id serial4 NOT NULL,
	vol_id varchar(50) NOT NULL,
	avion_id varchar(50) NOT NULL,
	CONSTRAINT vol_avion_pkey PRIMARY KEY (id),
	CONSTRAINT vol_avion_avion_id_fkey FOREIGN KEY (avion_id) REFERENCES avion(id),
	CONSTRAINT vol_avion_vol_id_fkey FOREIGN KEY (vol_id) REFERENCES vol(id)
);

INSERT INTO Users (nom, prenom, email, mot_de_passe, role)
VALUES ('Admin', 'Super', 'admin@gmail.com', 'admin123', 'admin');

INSERT INTO VilleDeservie (nom_ville, code_ville) VALUES
('Paris', 'CDG'),
('Londres', 'LHR'),
('New York', 'JFK'),
('Tokyo', 'NRT'),
('Dubaï', 'DXB'),
('Los Angeles', 'LAX'),
('Berlin', 'BER'),
('Moscou', 'SVO'),
('Pékin', 'PEK'),
('Rome', 'FCO');

INSERT INTO Avion (model, nombre_siege_business, nombre_siege_eco, date_fabrication) VALUES
('Boeing 737', 16, 150, '2018-06-15'),
('Airbus A320', 12, 180, '2020-09-23'),
('Boeing 787 Dreamliner', 30, 250, '2019-03-10'),
('Airbus A350', 36, 280, '2021-01-05'),
('Embraer E190', 8, 90, '2017-11-30'),
('Boeing 777', 42, 310, '2016-07-12'),
('Airbus A330', 24, 270, '2015-05-20'),
('Bombardier CRJ900', 6, 80, '2019-12-07'),
('Cessna Citation X', 4, 10, '2022-04-18'),
('Gulfstream G650', 6, 14, '2023-02-27');

INSERT INTO vol_avion (vol_id, avion_id) VALUES
-- Vol VOL2 utilise trois avions différents
('VOL2', 'AVI1'),  -- Boeing 737
('VOL2', 'AVI2'),  -- Airbus A320
('VOL2', 'AVI5'),  -- Embraer E190

-- Vol VOL3 utilise trois avions différents
('VOL3', 'AVI3'),  -- Boeing 787 Dreamliner
('VOL3', 'AVI4'),  -- Airbus A350
('VOL3', 'AVI6');  -- Boeing 777

INSERT INTO TypeSiege(type) VALUES
    ('Business'), ('Economique');