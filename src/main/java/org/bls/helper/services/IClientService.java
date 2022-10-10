package org.bls.helper.services;

import org.bls.helper.bo.Client;

import java.util.List;

public interface IClientService {
       //This method allows to add client
         boolean addClient(Client client);
         void removeClient(int id);

         Client  getClientById(int id);

         Client getClientByLastName(String lastName);

         List<Client> loadAll();
}
