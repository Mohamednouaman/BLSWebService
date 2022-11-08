package org.bls.helper.dao;

import org.bls.helper.entities.Client;
import org.bls.helper.entities.BLSUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client getByEmail(String email);

    // @Query(value="select c from Client c where c.user=?1")
    List<Client> getByUser(BLSUser user);

    @Modifying
    @Query("DELETE from Client c WHERE c.user.id= :id")
    void removeClients(@Param(value = "id") Long id);


}
