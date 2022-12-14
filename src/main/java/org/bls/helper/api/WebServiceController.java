package org.bls.helper.api;

import java.util.List;

import org.bls.helper.entities.Client;
import org.bls.helper.entities.BLSUser;
import org.bls.helper.services.IClientService;
import org.bls.helper.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WebServiceController {

    @Autowired
    private IClientService clientService;

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/helper/addclient", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<? extends Object> createClient(@RequestBody Client client) {
        System.out.println("TEST" + client);
        //System.out.println(client+" "+user);
        try {
            //client.setUser(user);
            clientService.addClient(client);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(client);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Client added successfully");

    }

    @PostMapping(value = "/helper/adduser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<? extends Object> createUser(@RequestBody BLSUser user) {
        try {
            userService.addUser(user);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
        }
        return ResponseEntity.status(HttpStatus.OK).body("User added successfully");
    }


    @GetMapping(value = "/helper/search/clients/{email}")
    @ResponseBody
    public Client getClientByEmail(@PathVariable String email) {

        Client client = clientService.getClientByEmail(email);

        return client;

    }

    @GetMapping(value = "/helper/users/{email}")
    @ResponseBody
    public ResponseEntity<? extends Object> getUserByEmail(@PathVariable String email) {

        BLSUser user = null;
        try {
            user = userService.getUserByEmail(email);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @GetMapping(value = "/helper/clients/{userId}")
    @ResponseBody
    public ResponseEntity<? extends Object> getClientsOfUser(@PathVariable int userId) {


        Long id = Long.valueOf(userId);
        List<Client> clients = null;
        try {
            clients = clientService.getClientByUserId(id);
            if (clients == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(clients);
        }
        return ResponseEntity.status(HttpStatus.OK).body(clients);
    }

    @GetMapping(value = "/helper/getClients/{userEmail}")
    @ResponseBody
    public ResponseEntity<? extends Object> getClientsOfUserV2(@PathVariable String userEmail) {

        List<Client> clients = null;
        try {
            clients = clientService.getClientByUserEmail(userEmail);
            if (clients == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(clients);
            }
        } catch (Exception e) {

            return ResponseEntity.status(404).body(clients);
        }
        return ResponseEntity.status(HttpStatus.OK).body(clients);


    }

    @GetMapping(value = "/helper/users/loadAll")
    @ResponseBody
    public ResponseEntity<List<BLSUser>>  getAllUsers() {

        List<BLSUser> users = userService.getAllUsers();
        if(users==null){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(users);
        }

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping(value = "/helper/clients/loadAll")
    @ResponseBody
    public List<Client> getAllClients() {

        List<Client> clients = clientService.loadAll();

        return clients;
    }

    @GetMapping("/helper/removeClient/{id}")
    public void removeClient(@PathVariable Long id) {

        clientService.removeClient(id);

    }

    @GetMapping("/helper/removeUser/{id}")
    public void removeUser(@PathVariable Long id) {
       clientService.removeClients(id);
        userService.removeUser(id);
        System.out.println("User removed successfully");

    }

    @GetMapping("/helper/approveEmployee/{id}")
    public void changeEmployeeState(@PathVariable Long id) {

        userService.changeUserState(id);

    }


}
