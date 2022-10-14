package org.bls.helper.api;

import java.lang.reflect.Executable;
import java.util.List;

import org.bls.helper.entities.Client;
import org.bls.helper.entities.User;
import org.bls.helper.services.IClientService;
import org.bls.helper.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

	@PostMapping(value = "/helper/addclient",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<? extends Object> createClient(@RequestBody Client client){
		System.out.println("TEST"+client);
		//System.out.println(client+" "+user);
		try{
			//client.setUser(user);
		    clientService.addClient(client);

	}catch (Exception e){

		return 	 ResponseEntity.status(HttpStatus.BAD_REQUEST).body(client);
	}
		return	ResponseEntity.status(HttpStatus.OK).body("Client added successfully");

	}

	@PostMapping(value = "/helper/adduser",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<? extends  Object> createUser(@RequestBody User user){
		try {
			userService.addUser(user);

		}catch (Exception e){

		return 	 ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
		}
		return	ResponseEntity.status(HttpStatus.OK).body("User added successfully");
	}



	@GetMapping(value="/helper/search/clients/{email}")
	@ResponseBody
	public Client  getClientByEmail(@PathVariable String email){

		Client  client=clientService.getClientByLastName(email);
		
		return client;
		
	}
	@GetMapping(value="/helper/users/{email}")
	@ResponseBody
	public ResponseEntity<? extends  Object>  getUserByEmail(@PathVariable String email){

		User  user=userService.getUserByEmail(email);
         if(user==null){
			 return 	 ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
		 }
		return ResponseEntity.status(HttpStatus.OK).body(user);

	}

	@GetMapping(value="/helper/clients/{userId}")
	@ResponseBody
	public ResponseEntity<? extends Object> getClientsOfUser(@PathVariable int userId){
        Long id=Long.valueOf(userId);
		List<Client>   clients=null;
		try {
			clients=clientService.getClientByUserId(id);
            }catch (Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(clients);
		   }
		return ResponseEntity.status(HttpStatus.OK).body(clients);
	}

	@GetMapping(value="/helper/getClients/{userEmail}")
	@ResponseBody
	public ResponseEntity<? extends Object> getClientsOfUserV2(@PathVariable String userEmail){

		List<Client>   clients=clientService.getClientByUserEmail(userEmail);
		if(clients==null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(clients);

		return ResponseEntity.status(HttpStatus.OK).body(clients);
	}

	@GetMapping(value="/helper/users/loadAll")
	@ResponseBody
	public List<User> getAllUsers(){

		List<User>   users=userService.getAllUsers();
		System.out.println(users);
		return users;
	}

	@GetMapping("/helper/removeClient/{id}")
	public void removeClient(@PathVariable Long id){

		clientService.removeClient(id);

	}

	@GetMapping("/helper/removeUser/{id}")
	public void removeUser(@PathVariable Long id){

		userService.removeUser(id);

	}


}
