package org.bls.helper.dao;

import org.bls.helper.entities.Client;
import org.bls.helper.entities.BLSUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {

             Client     getByEmail(String email);
            // @Query(value="select c from Client c where c.user=?1")
             List<Client> getByUser(BLSUser user);


}
