package com.util;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
    
    // Hacher le mot de passe avant de le stocker en base de données
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(12)); // 12 = facteur de complexité
    }

    // Vérifier si un mot de passe correspond au hachage stocké
    public static boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}

