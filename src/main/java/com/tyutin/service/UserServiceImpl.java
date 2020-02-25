package com.tyutin.service;

import com.tyutin.model.User;
import com.tyutin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void  createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(User user, Long id){
        if(userRepository.findById(id).isPresent()){
            User intermediateUser = userRepository.findById(id).get();

            intermediateUser.setName(user.getName());
            intermediateUser.setSurname(user.getSurname());
            intermediateUser.setBirthday(user.getBirthday());
            intermediateUser.setLogin(user.getLogin());
            intermediateUser.setPassword(user.getPassword());

            User updatedUser = userRepository.save(intermediateUser);
            return updatedUser;
        }
        return null;
    }
}
