package org.bls.helper;

import org.bls.helper.dao.ClientRepository;
import org.bls.helper.entities.Client;
import org.bls.helper.entities.BLSUser;
import org.bls.helper.services.IClientService;
import org.bls.helper.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlsWebServiceApplication  {
     @Autowired
     private IUserService userService;
     @Autowired
     private IClientService clientService;
     @Autowired
     private ClientRepository clientRepository;
    public static void main(String[] args) {
        SpringApplication.run(BlsWebServiceApplication.class, args);

    }


}
