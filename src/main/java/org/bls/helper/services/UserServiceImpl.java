package org.bls.helper.services;

import org.bls.helper.dao.UserRepository;
import org.bls.helper.entities.BLSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserRepository userRepository;


    @Override
    public BLSUser addUser(BLSUser user) {

        return userRepository.save(user);
    }

    @Override
    public BLSUser getUserByEmail(String email) {
        BLSUser user=userRepository.getByEmail(email);
        return user;
    }

    @Override
    public BLSUser getUserById(Long id) {
        BLSUser user=userRepository.findById(id).get();
        return user;
    }

    @Override
    public void removeUser(Long id) {
         userRepository.deleteById(id);
    }

    @Override
    public List<BLSUser> getAllUsers() {

        List<BLSUser>  allUsers=userRepository.findAll();
        if(CollectionUtils.isEmpty(allUsers)) return null;
        return allUsers;
    }
}
