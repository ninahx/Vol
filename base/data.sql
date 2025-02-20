-- Insérer les rôles
INSERT INTO role (nom, valeur) VALUES ('admin', 1), ('utilisateur', 2);

-- iamhisariel

INSERT INTO utilisateur (email, mot_de_passe, role_id) 
VALUES ('ninah_xoxo@fan.com', 
        '$2a$10$l4WmQqBvQiQ7aP0m0HtXXOBbJhAqMIqZzsh6QMKH8hWr3Z1MOwKXK', 
        (SELECT id FROM role WHERE nom = 'admin'));
