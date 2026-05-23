package com.lsouzadev.dscommerce.config;

import com.lsouzadev.dscommerce.entities.User;
import com.lsouzadev.dscommerce.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class SecurityUserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public SecurityUserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

      return user;

//        return User.builder()
//                .id(byEmail.getId())
//                .name(byEmail.getName())
//                .email(byEmail.getEmail())
//                .phone(byEmail.getPhone())
//                .birthDate(byEmail.getBirthDate())
//                .password(byEmail.getPassword())
//                .build();
    }
}
