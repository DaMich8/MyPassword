package com.example.mypassword.validation;

import java.util.regex.Pattern;

public class EnteredFieldsChecking {

    public static boolean emailIsValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        return email != null && pattern.matcher(email).matches();
    }

    public static boolean passwordIsValid(String password) {
        String passwordRegex = "^(?=.*[0-9])" +         // at least 1 digit
                "(?=.*[a-z])" +         // at least 1 lower case letter
                "(?=.*[A-Z])" +         // at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      // any letter
                //"(?=.*[@#$%^&+=])" +    // at least 1 special character
                "(?=\\S+$)" +           // no white spaces
                ".{6,}";                // at least 6 characters

        Pattern pattern = Pattern.compile(passwordRegex);
        return password != null && pattern.matcher(password).matches();
    }
}
