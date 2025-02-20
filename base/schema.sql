-- Table role
CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL UNIQUE,
    valeur INTEGER NOT NULL UNIQUE CHECK (valeur > 0)
);

-- Table utilisateur (pour l'admin uniquement)
CREATE TABLE utilisateur (
    id_utilisateur SERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    mot_de_passe VARCHAR(60) NOT NULL,
    role_id INTEGER NOT NULL REFERENCES role(id) ON DELETE CASCADE
);
-- ALTER TABLE utilisateur RENAME COLUMN role_utilisateur TO role_id;

-- Table modele (correction du nom "model")
CREATE TABLE modele (
    id_modele SERIAL PRIMARY KEY,
    nom_modele VARCHAR(100) NOT NULL UNIQUE,
    type VARCHAR(50) NOT NULL CHECK (type IN ('Jet', 'Boeing', 'Airbus'))
);

-- Table avion
CREATE TABLE avion (
    id_avion SERIAL PRIMARY KEY,
    nom_avion VARCHAR(255) NOT NULL,
    id_modele INTEGER NOT NULL REFERENCES modele(id_modele) ON DELETE CASCADE,
    date_fabrication DATE NOT NULL CHECK (date_fabrication <= CURRENT_DATE)
);

-- Table type_siege
CREATE TABLE type_siege (
    id_type_siege SERIAL PRIMARY KEY,
    classe VARCHAR(20) NOT NULL CHECK (classe IN ('Business', 'Economique')),
    emplacement VARCHAR(20) NOT NULL CHECK (emplacement IN ('Alle', 'Fenetre'))
);

-- Table avion_siege
CREATE TABLE avion_siege (
    id_avion_siege SERIAL PRIMARY KEY,
    id_avion INTEGER NOT NULL REFERENCES avion(id_avion) ON DELETE CASCADE,
    id_type_siege INTEGER NOT NULL REFERENCES type_siege(id_type_siege) ON DELETE CASCADE,
    nbr_siege_dispo INTEGER NOT NULL CHECK (nbr_siege_dispo >= 0),
    UNIQUE (id_avion, id_type_siege)
);


-- Table ville
CREATE TABLE ville (
    id_ville SERIAL PRIMARY KEY,
    nom_ville VARCHAR(100) NOT NULL UNIQUE
);

-- Table statut_vol
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
    duree_vol INTERVAL NOT NULL CHECK (duree_vol > INTERVAL '0' SECOND),
    id_statut_vol INTEGER NOT NULL REFERENCES statut_vol(id_statut_vol) ON DELETE CASCADE,
    CHECK (id_ville_depart <> id_ville_arrivee),
    UNIQUE (id_avion, date_depart)
);

-- Table prix_vol
CREATE TABLE prix_vol (
    id_prix SERIAL PRIMARY KEY,
    valeur NUMERIC(10,2) NOT NULL CHECK (valeur >= 0),
    id_vol INTEGER NOT NULL REFERENCES vol(id_vol) ON DELETE CASCADE,
    id_type_siege INTEGER NOT NULL REFERENCES type_siege(id_type_siege) ON DELETE CASCADE,
    UNIQUE (id_vol, id_type_siege)
);

-- Table reservation
CREATE TABLE reservation (
    id_reservation SERIAL PRIMARY KEY,
    code_reservation VARCHAR(50) NOT NULL UNIQUE,
    id_vol INTEGER NOT NULL REFERENCES vol(id_vol) ON DELETE CASCADE,
    id_avion_siege INTEGER NOT NULL REFERENCES avion_siege(id_avion_siege) ON DELETE CASCADE,
    date_reservation TIMESTAMP NOT NULL DEFAULT NOW(),
    prix_total NUMERIC(10,2) NOT NULL CHECK (prix_total >= 0),
    id_statut_reservation INTEGER NOT NULL REFERENCES statut_vol(id_statut_vol) ON DELETE CASCADE,
    UNIQUE (id_vol, id_avion_siege) -- Empêche la double réservation du même siège sur un vol
);

-- Table historique des états de réservation
CREATE TABLE historique_etat_reservation (
    id_historique SERIAL PRIMARY KEY,
    id_reservation INTEGER NOT NULL REFERENCES reservation(id_reservation) ON DELETE CASCADE,
    date_modification TIMESTAMP NOT NULL DEFAULT NOW(),
    description TEXT
);

-- Table promotion_vol
CREATE TABLE promotion_vol (
    id_promotion SERIAL PRIMARY KEY,
    id_vol INTEGER NOT NULL REFERENCES vol(id_vol) ON DELETE CASCADE,
    pourcentage_reduction INTEGER NOT NULL CHECK (pourcentage_reduction BETWEEN 0 AND 100),
    date_debut TIMESTAMP NOT NULL CHECK (date_debut > NOW()),
    date_fin TIMESTAMP NOT NULL CHECK (date_fin > date_debut),
    id_avion_siege INTEGER NOT NULL REFERENCES avion_siege(id_avion_siege) ON DELETE CASCADE
);

-- Table config_reservation
CREATE TABLE config_reservation (
    id_config SERIAL PRIMARY KEY,
    heure_validite_avant_vol INTERVAL NOT NULL,
    heure_marge_annulation INTERVAL NOT NULL,
    date_insertion TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Index pour améliorer la performance des recherches
CREATE INDEX idx_reservation_code ON reservation(code_reservation);
CREATE INDEX idx_vol_depart ON vol(id_ville_depart);
CREATE INDEX idx_vol_arrivee ON vol(id_ville_arrivee);
CREATE INDEX idx_avion_siege ON avion_siege(id_avion);
