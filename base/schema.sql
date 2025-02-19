CREATE TABLE role(
    id
    nom (admin, utilisateur simple, ...)
    valeur (admin = 1 , utilisateur =2 )
)

-- Table utilisateur (pour l'admin uniquement)
CREATE TABLE utilisateur (
    id_utilisateur SERIAL PRIMARY KEY,
    email
    mot_de_passe VARCHAR(60) NOT NULL
    role_utilisateur foreign key
);


create tbale model(
'boeibg', jet
)
-- Table avion
CREATE TABLE avion (
    id_avion SERIAL PRIMARY KEY,
    nom_avion VARCHAR(255) NOT NULL,
    id_modele
    date_fabrication DATE NOT NULL CHECK (date_fabrication <= CURRENT_DATE)
);


create table caracterisitique_siege(
    business, eco, alle, fenetre, pres issus de secours 
)


create table avion_siege(
    id_avion
    numero_siege
    id_caracteristique_siege
)


-- Table ville
CREATE TABLE ville (
    id_ville SERIAL PRIMARY KEY,
    nom_ville VARCHAR(100) NOT NULL UNIQUE
);

-- Table statut des vols
CREATE TABLE statut_vol (
    id_statut_vol SERIAL PRIMARY KEY,
    statut VARCHAR(50) NOT NULL CHECK (statut IN ('annulé', 'en attente', 'confirmé'))
);

-- Table vol
CREATE TABLE vol (
    id_vol SERIAL PRIMARY KEY,
    id_avion INTEGER NOT NULL REFERENCES avion(id_avion) ON DELETE CASCADE,
    id_ville_depart INTEGER NOT NULL REFERENCES ville(id_ville) ON DELETE CASCADE,
    id_ville_arrivee INTEGER NOT NULL REFERENCES ville(id_ville) ON DELETE CASCADE,
    date_depart TIMESTAMP NOT NULL CHECK (date_depart > NOW()),
    duree_vol TIME NOT NULL CHECK (duree_vol > '00:00:00'),
    prix_base NUMERIC(10,2) NOT NULL CHECK (prix_base > 0),
    id_statut_vol INTEGER NOT NULL REFERENCES statut_vol(id_statut_vol) ON DELETE CASCADE,
    CHECK (id_ville_depart <> id_ville_arrivee),
    UNIQUE (id_avion, date_depart)
);


-- Table réservation (pas besoin de lier à un utilisateur dans le Front-Office)
CREATE TABLE reservation (
    id_reservation SERIAL PRIMARY KEY,
    code_reservation VARCHAR(50) NOT NULL UNIQUE,
    id_vol INTEGER NOT NULL REFERENCES vol(id_vol) ON DELETE CASCADE,
    avion_siege ,
    date_reservation TIMESTAMP NOT NULL DEFAULT NOW(),
    prix_total NUMERIC(10,2) NOT NULL CHECK (prix_total >= 0),
    UNIQUE (id_vol, id_siege) -- Empêche la double réservation du même siège sur un vol
);

-- Table historique des états de réservation
CREATE TABLE historique_etat_reservation (
    id_historique SERIAL PRIMARY KEY,
    id_reservation INTEGER NOT NULL REFERENCES reservation(id_reservation) ON DELETE CASCADE,
    date_modification TIMESTAMP NOT NULL DEFAULT NOW(),
    description TEXT
);

-- Table promotions de vol
CREATE TABLE promotion_vol (
    id_promotion SERIAL PRIMARY KEY,
    id_vol INTEGER NOT NULL REFERENCES vol(id_vol) ON DELETE CASCADE,
    pourcentage_reduction INTEGER NOT NULL CHECK (pourcentage_reduction BETWEEN 0 AND 100),
    date_debut TIMESTAMP NOT NULL CHECK (date_debut > NOW()),
    date_fin TIMESTAMP NOT NULL CHECK (date_fin > date_debut),
    id_avion_siege 
);

-- Table de configuration des réservations
CREATE TABLE config_reservation (
    id_config SERIAL PRIMARY KEY,
    heure_validite_avant_vol TIME NOT NULL,
    heure_marge_annulation TIME NOT NULL,
    date_insertion TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Ajout d'index pour améliorer la performance des recherches
CREATE INDEX idx_reservation_code ON reservation(code_reservation);
CREATE INDEX idx_vol_depart ON vol(id_ville_depart);
CREATE INDEX idx_vol_arrivee ON vol(id_ville_arrivee);
CREATE INDEX idx_siege_avion ON siege(id_avion);
