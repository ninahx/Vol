CREATE TABLE config_vol (
    id SERIAL PRIMARY KEY,                   -- Identifiant unique pour chaque configuration
    pourcentage_enfant INT NOT NULL,         -- Pourcentage de réduction pour les enfants
    date_fin DATE DEFAULT NULL,              -- Date de fin de validité de la configuration (peut être NULL si illimitée)
    date_debut TIMESTAMP DEFAULT NOW(),      -- Date de début de la configuration (historique)
    actif BOOLEAN DEFAULT TRUE               -- Indique si cette configuration est active
);

ALTER TABLE config_vol 
    ALTER COLUMN date_fin TYPE TIMESTAMP;


-- Insertion d'une configuration initiale
INSERT INTO config_vol (pourcentage_enfant) 
VALUES 
    (20); -- Réduction de 20% pour les enfants, sans date de fin spécifiée


INSERT INTO config_vol (pourcentage_enfant, date_fin, actif)
VALUES (30, NULL, true);


CREATE OR REPLACE FUNCTION set_previous_config_inactive() 
RETURNS TRIGGER AS $$
BEGIN
    -- Set all previously active configurations to false
    UPDATE config_vol
    SET actif = false
    WHERE actif = true;

    -- Return the newly inserted row
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trigger_set_previous_inactive
BEFORE INSERT ON config_vol
FOR EACH ROW
EXECUTE FUNCTION set_previous_config_inactive();

DROP TRIGGER trigger_set_previous_inactive ON config_vol;

