package com.sante.store.services.implementations;

import com.sante.store.entities.Role;
import com.sante.store.entities.User;
import com.sante.store.repositories.RoleRepository;
import com.sante.store.repositories.UserRepository;
import com.sante.store.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        User user = userRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }



//    @Override
//    public User findByEmail(String email) {
//        User user = userRepository.findByEmail(email);
//        if (user == null) {
//            throw new RuntimeException("User not found");
//        }
//        return user;
//    }
//
//    @Override
//    public User findById(Long id) {
//        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
//    }
//
//    @Override
//    public User create(User user) {
//        User userToCheck = userRepository.findByEmail(user.getEmail());
//        if (userToCheck != null) {
//            throw new RuntimeException("User already exists");
//        }
//        return userRepository.save(user);
//    }
//
//    @Override
//    public User update(User user) {
//        User userToUpdate = findByEmail(user.getEmail());
//        if (userToUpdate == null) {
//            throw new RuntimeException("User not found");
//        }
//        userToUpdate.setFirstName(user.getFirstName());
//        userToUpdate.setLastName(user.getLastName());
//        userToUpdate.setPassword(user.getPassword());
//        userToUpdate.setAddress(user.getAddress());
//        userToUpdate.setPhone(user.getPhone());
//        return userRepository.save(userToUpdate);
//    }
//
//    @Override
//    public void delete(String email) {
//        User user = findByEmail(email);
//        if (user == null) {
//            throw new RuntimeException("User not found");
//        }
//        userRepository.delete(user);
//    }
//
//    @Override
//    public User getReference(Long id) {
//        return userRepository.getReferenceById(id);
//    }

}
