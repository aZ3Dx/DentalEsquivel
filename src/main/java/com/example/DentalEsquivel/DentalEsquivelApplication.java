package com.example.DentalEsquivel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DentalEsquivelApplication implements CommandLineRunner{
    
    @Autowired
    BCryptPasswordEncoder passEncoder;

    public static void main(String[] args) {
        SpringApplication.run(DentalEsquivelApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("");
        System.out.println("--> Sistema Ejecutado <--");
        System.out.println("");
    }
}
