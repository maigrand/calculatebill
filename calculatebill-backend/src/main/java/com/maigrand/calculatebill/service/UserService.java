package com.maigrand.calculatebill.service;

import com.maigrand.calculatebill.entity.UserEntity;
import com.maigrand.calculatebill.exception.EntityExistsException;
import com.maigrand.calculatebill.payload.user.UserDetails;
import com.maigrand.calculatebill.repository.UserRepository;
import com.maigrand.calculatebill.validator.group.OnCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }

    @Validated(OnCreate.class)
    public UserEntity create(String email, String password, Boolean active) {
        if (this.userRepository.existsByEmail(email)) {
            throw new EntityExistsException("user exists");
        }
        UserEntity entity = new UserEntity();

        entity.setEmail(email);
        entity.setPassword(this.passwordEncoder.encode(password));

        return this.userRepository.save(entity);
    }

    @Validated(OnCreate.class)
    public UserEntity create(UserDetails details) {
        return create(details.getEmail(), details.getPassword(), true);
    }
}
