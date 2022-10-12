package org.bls.helper.services;

import org.bls.helper.entities.User;

import java.util.List;

public interface IUserService {

    User addUser(User user);
    User getUserByEmail(String email);
    User getUserById(Long id);
    void removeUser(Long id);
    List<User> getAllUsers();



}
