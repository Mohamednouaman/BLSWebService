package org.bls.helper.dao;

import org.bls.helper.entities.BLSUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<BLSUser,Long> {

    BLSUser getByEmail(String email);

    @Modifying
    @Query("update BLSUser u set u.state = true where u.id = :id")
    void updateUserState(@Param(value = "id") Long id);


}