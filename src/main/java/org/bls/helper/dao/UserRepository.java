package org.bls.helper.dao;

import org.bls.helper.entities.BLSUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<BLSUser,Long> {

    BLSUser getByEmail(String email);


}