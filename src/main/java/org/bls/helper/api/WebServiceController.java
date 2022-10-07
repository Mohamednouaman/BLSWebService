package org.bls.helper.api;

import java.util.List;

import org.bls.helper.bo.Client;
import org.bls.helper.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WebServiceController {

	@Autowired
	private IClientService clientService;

	@PostMapping(value = "/helper/addclient",produces = MediaType.APPLICATION_JSON_VALUE)

	public void createClient(@RequestBody Client client){
		System.out.println(client.getFirstName()+" "+client.getLastName()+" "+client.getPassportNumber());
		clientService.addClient(client);

	}

	@GetMapping(value="/helper/clients/{lastName}")
	@ResponseBody
	public Client  getProductByName(@PathVariable String lastName){
		System.out.println("Get product");
		Client  client=clientService.getClientByLastName(lastName);
		
		return client;
		
	}

	@GetMapping(value="/helper/clients/loadAll")
	@ResponseBody
	public List<Client> getAllClient(){

		List<Client>   clients=clientService.loadAll();

		return clients;
	}

	@GetMapping("/helper/removeClient/{id}")
	public void removeClient(@PathVariable int id){

		clientService.removeClient(id);

	}


}
