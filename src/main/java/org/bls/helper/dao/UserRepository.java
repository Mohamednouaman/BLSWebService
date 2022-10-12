package org.bls.helper.dao;

import org.bls.helper.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User getByEmail(String email);


}