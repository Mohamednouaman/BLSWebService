package org.bls.helper.api;

import java.util.List;

import org.bls.helper.entities.Client;
import org.bls.helper.entities.User;
import org.bls.helper.services.IClientService;
import org.bls.helper.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	public void createClient(@RequestBody Client client){
		System.out.println(client.getFirstName()+" "+client.getLastName()+" "+client.getPassportNumber());
		clientService.addClient(client);

	}

	@PostMapping(value = "/helper/adduser",produces = MediaType.APPLICATION_JSON_VALUE)
	public void createUser(@RequestBody User user){
		userService.addUser(user);

	}



	@GetMapping(value="/helper/clients/{email}")
	@ResponseBody
	public Client  getClientByEmail(@PathVariable String email){

		Client  client=clientService.getClientByLastName(email);
		
		return client;
		
	}
	@GetMapping(value="/helper/users/{email}")
	@ResponseBody
	public User  getUserByEmail(@PathVariable String email){

		User  user=userService.getUserByEmail(email);

		return user;

	}

	@GetMapping(value="/helper/clients/loadAll")
	@ResponseBody
	public List<Client> getAllClients(){

		List<Client>   clients=clientService.loadAll();
		System.out.println(clients);
		return clients;
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
