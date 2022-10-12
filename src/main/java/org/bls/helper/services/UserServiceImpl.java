package org.bls.helper.services;

import org.bls.helper.dao.UserRepository;
import org.bls.helper.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserRepository userRepository;


    @Override
    public User addUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        User user=userRepository.getByEmail(email);
        return user;
    }

    @Override
    public User getUserById(Long id) {
        User user=userRepository.findById(id).get();
        return user;
    }

    @Override
    public void removeUser(Long id) {
         userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {

        List<User>  allUsers=userRepository.findAll();
        if(CollectionUtils.isEmpty(allUsers)) return null;
        return allUsers;
    }
}
