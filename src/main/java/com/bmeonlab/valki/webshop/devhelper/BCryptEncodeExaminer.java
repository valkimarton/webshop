package com.bmeonlab.valki.webshop.devhelper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncodeExaminer {
    public static void main(String[] args){
        String password = "password";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(password);

        System.out.println(hashedPassword);
    }
}
