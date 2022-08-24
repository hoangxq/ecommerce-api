package com.demo.ecommerce_api.security;

import com.demo.ecommerce_api.model.User;
import com.demo.ecommerce_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (new EmailValidator().isValid(username, null)){
            return userRepository
                    .findOneWithAuthoritiesByEmail(username)
                    .map(user -> createUserSecurity(user))
                    .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' is not exist in system"));
        }

        return userRepository
                .findOneWithAuthoritiesByUsername(username)
                .map(user -> createUserSecurity(user))
                .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' is not exist in system"));

    }

    private org.springframework.security.core.userdetails.User createUserSecurity(User user){
        Set<GrantedAuthority> securityAuthorities = user
                .getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), securityAuthorities);

    }

}
