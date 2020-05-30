package com.example.org.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.example.*")
@SpringBootApplication
public class OrgServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrgServerApplication.class);
    }
}
