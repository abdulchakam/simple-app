package com.abdulchakam.loginservice.util;

import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;

@Slf4j
public class BCryptUtil {

    public static String hashPassword(String password) {
        try {
            int costFactor = 16;
            return BCrypt.hashpw(password, BCrypt.gensalt(costFactor));
        } catch (Exception e) {
            log.error("Error when encrypt password");
            throw e;
        }
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
