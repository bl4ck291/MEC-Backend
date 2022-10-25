package com.sante.store.services;

import com.sante.store.entities.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

public interface UserService {
    User findByEmail(String email);

    Collection<User> findAllByRole(String role);

    User create(User user);

    User update(User user);

    void delete(String email);
}