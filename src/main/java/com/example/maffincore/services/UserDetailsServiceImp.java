package com.example.maffincore.services;

import com.example.maffincore.entities.UserEntity;
import com.example.maffincore.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepo userRepo;

    public UserDetailsServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username %s not found.", username)));

        return new UserDetailsImp(user);
    }
}
