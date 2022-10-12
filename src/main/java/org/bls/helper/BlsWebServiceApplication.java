package org.bls.helper;

import org.bls.helper.dao.ClientRepository;
import org.bls.helper.entities.Client;
import org.bls.helper.entities.User;
import org.bls.helper.services.IClientService;
import org.bls.helper.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BlsWebServiceApplication  implements CommandLineRunner {
//     @Autowired
//     private IUserService userService;
//     @Autowired
//     private IClientService clientService;
//     @Autowired
//     private ClientRepository clientRepository;
    public static void main(String[] args) {
        SpringApplication.run(BlsWebServiceApplication.class, args);

    }


    @Override
    public void run(String... args) throws Exception {
//        User user=new User();
//            user.setEmail("TEST@gmail.com");
//            user.setFirstName("TEST");
//            user.setLastName("TEST");
//            user.setPassword("ZZZ333");
//            userService.addUser(user);
//        Client client=new Client();
//               client.setFirstName("Reda");
//               client.setUser(user);
//        Client client2=new Client();
//        client2.setFirstName("Ahmed");
//        client2.setUser(user);
//        Client client3=new Client();
//        client3.setFirstName("Ahmed");
//        client3.setUser(user);
//
//        clientService.addClient(client);
//        clientService.addClient(client2);
//        clientService.addClient(client3);
//
//          User userRes= userService.getUserById(5L);
//         List<Client> clients= clientRepository.getByUser(userRes);
//        System.out.println(clients.size());


    }
}
