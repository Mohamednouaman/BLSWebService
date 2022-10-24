package org.bls.helper.services;

import org.bls.helper.entities.Client;

import java.util.List;

public interface IClientService {

         Client addClient(Client client);

         void removeClient(Long id);

         List<Client>  getClientByUserId(Long id);

         List<Client> getClientByUserEmail(String email) throws Exception;

         Client getClientByLastName(String lastName);

         Client getClientByEmail(String email);

         List<Client> loadAll();
}
