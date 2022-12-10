package com.sante.store.services;

import com.sante.store.entities.Role;
import com.sante.store.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
//    User findByEmail(String email);
//
//    User findById(Long id);
//
//    User create(User user);
//
//    User update(User user);
//
//    void delete(String email);
//
//    User getReference(Long id);

    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String email, String roleName);
    User getUser(String email);
    User getUserById(Long id);
    Page<User> getUsers(Pageable pageable);
}
