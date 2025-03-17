

INSERT INTO vol (
    ville_depart_id, 
    ville_arrivee_id, 
    date_depart, 
    date_arrivee, 
    nombre_sieges_business_promo, 
    nombre_sieges_eco_promo, 
    heure_reservation_avant_vol, 
    heure_annulation_avant_vol
) VALUES
    ('VIL1', 'VIL2', '2025-03-15 08:00:00', '2025-03-15 10:00:00', 4, 10, 24, 12), -- Paris à Londres
    ('VIL3', 'VIL4', '2025-03-16 12:00:00', '2025-03-17 02:00:00', 8, 20, 48, 24), -- New York à Tokyo
    ('VIL5', 'VIL6', '2025-03-18 06:30:00', '2025-03-18 12:45:00', 10, 30, 24, 6), -- Dubaï à Los Angeles
    ('VIL7', 'VIL8', '2025-03-19 14:00:00', '2025-03-19 20:00:00', 6, 25, 12, 8), -- Berlin à Moscou
    ('VIL9', 'VIL10', '2025-03-20 09:15:00', '2025-03-20 11:45:00', 5, 15, 24, 10); -- Pékin à Rome
