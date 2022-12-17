package com.example.collectiveproject.Utility;

import com.sun.istack.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    @NotNull
    public static boolean isValidName(String name){
        Pattern p = Pattern.compile("^[A-Z][a-z]*$");
        Matcher matcher = p.matcher(name);
        return matcher.matches();
    }

    @NotNull
    public static boolean isValidEmail(String email){
        Pattern p = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = p.matcher(email);
        return matcher.matches();
    }

    @NotNull
    public static boolean isValidPassword(@org.jetbrains.annotations.NotNull String pass){
        boolean hasLower = false, hasUpper = false,
                hasDigit = false;
        for (char i : pass.toCharArray())
        {
            if (Character.isLowerCase(i))
                hasLower = true;
            if (Character.isUpperCase(i))
                hasUpper = true;
            if (Character.isDigit(i))
                hasDigit = true;
        }
        return hasLower && hasUpper && hasDigit && pass.length() >= 8;
    }


}
