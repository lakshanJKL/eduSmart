package com.devstack.edu.util;

import org.mindrot.BCrypt;

public class PasswordManger {
    public static String encryptpw(String text){
        return BCrypt.hashpw(text, BCrypt.gensalt());
    }
    public static boolean checkpw(String text,String hash){
       return BCrypt.checkpw(text,hash);
    }
}
