package com.sante.store.services.implementations;

import com.sante.store.entities.User;
import com.sante.store.repositories.UserRepository;
import com.sante.store.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }

    @Override
    public Collection<User> findAllByRole(String role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    public User create(User user) {
        User userToCheck = userRepository.findByEmail(user.getEmail());
        if (userToCheck != null) {
            throw new RuntimeException("User already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User userToUpdate = findByEmail(user.getEmail());
        if (userToUpdate == null) {
            throw new RuntimeException("User not found");
        }
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setRole(user.getRole());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setAddress(user.getAddress());
        userToUpdate.setPhone(user.getPhone());
        return userRepository.save(userToUpdate);
    }

    @Override
    public void delete(String email) {
        User user = findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        userRepository.delete(user);
    }
}
