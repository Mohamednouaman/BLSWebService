package org.bls.helper;

import org.bls.helper.bo.Client;
import org.bls.helper.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class BlsWebServiceApplication  {
     @Autowired
     private  IClientService clientService;
    public static void main(String[] args) {
        SpringApplication.run(BlsWebServiceApplication.class, args);

    }



}
