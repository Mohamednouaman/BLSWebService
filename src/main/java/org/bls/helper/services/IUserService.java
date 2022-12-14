package org.bls.helper.services;

import org.bls.helper.entities.BLSUser;

import java.util.List;

public interface IUserService {

    BLSUser addUser(BLSUser user);
    BLSUser getUserByEmail(String email) throws Exception;
    BLSUser getUserById(Long id);
    void removeUser(Long id);
    List<BLSUser> getAllUsers();


    void changeUserState(Long id);
}
