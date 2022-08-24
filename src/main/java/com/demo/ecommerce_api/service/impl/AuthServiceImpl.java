package com.demo.ecommerce_api.service.impl;

import com.demo.ecommerce_api.exception.EntityNotFoundException;
import com.demo.ecommerce_api.model.Authority;
import com.demo.ecommerce_api.model.User;
import com.demo.ecommerce_api.payload.request.LoginRequest;
import com.demo.ecommerce_api.payload.request.SignupRequest;
import com.demo.ecommerce_api.payload.response.JwtRespone;
import com.demo.ecommerce_api.repository.AuthorityRepository;
import com.demo.ecommerce_api.repository.UserRepository;
import com.demo.ecommerce_api.security.jwt.JwtUtils;
import com.demo.ecommerce_api.service.AuthService;
import com.demo.ecommerce_api.service.component.MappingHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final MappingHelper mappingHelper;

    @Override
    public JwtRespone authenticateUser(LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtUtils.generateJwtToken(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            List<String> authorities = userDetails.getAuthorities()
                    .stream()
                    .map(authority -> authority.getAuthority())
                    .collect(Collectors.toList());

            return new JwtRespone(jwtToken, "Bearer", userDetails.getUsername(), authorities);

        } catch (AuthenticationException authenticationException){
            throw new UsernameNotFoundException("err.api.entity-not-found");
        }

    }

    @Override
    public void registerUser(SignupRequest signupRequest) {
        User user =  new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        Set<String> authoritiesString = signupRequest.getAuthorities();
        Set<Authority> authorities = new HashSet<>();

        if (authoritiesString == null){
            authorities.add(authorityRepository.findById("ROLE_USER").orElseThrow(
                    () -> new EntityNotFoundException(Authority.class.getName(), "ROLE_USER")
            ));
        }
        else {
            authoritiesString.forEach(authority -> authorities.add(authorityRepository.findById(authority).orElseThrow(
                    () -> new EntityNotFoundException(Authority.class.getName(), authority)
            )));
        }

        user.setAuthorities(authorities);

        userRepository.save(user);
    }

    @Override
    public boolean checkUser(String username) {
        return userRepository.existsByUsernameOrEmail(username, username);
    }
}
