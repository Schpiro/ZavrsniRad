package com.bbzavrsni.zavrsni.service;

import com.bbzavrsni.zavrsni.command.LoginCommand;
import com.bbzavrsni.zavrsni.model.dto.LoginDTO;
import com.bbzavrsni.zavrsni.model.pojo.Authority;
import com.bbzavrsni.zavrsni.model.pojo.User;
import com.bbzavrsni.zavrsni.repository.interfaces.UserRepository;
import com.bbzavrsni.zavrsni.service.interfaces.AuthenticationService;
import com.bbzavrsni.zavrsni.service.interfaces.JwtService;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PersistenceContext
    private EntityManager entityManager;

    public AuthenticationServiceImpl(JwtService jwtService, UserRepository userRepository, EntityManager entityManager) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }


    @Override
    public Optional<LoginDTO> login(LoginCommand command) {
        Optional<User> user = userRepository.findByUsername(command.getUsername());

        if (user.isEmpty() || !isMatchingPassword(command.getPassword(), user.get().getPassword())) {
            return Optional.empty();
        }

        return Optional.of(
                new LoginDTO(jwtService.createJwt(user.get()))
        );
    }

    @Override
    public Optional<LoginDTO> register(LoginCommand command) {
        try {
            User user = userRepository.save(mapCommandToUser(command));
            return Optional.of(
                    new LoginDTO(jwtService.createJwt(user)
                    ));
        }catch (Exception exception){
            return Optional.empty();
        }//Treba handleat ako vec postoji user

    }

    private User mapCommandToUser(LoginCommand command) {
        return new User(command.getUsername(),
                bCryptPasswordEncoder.encode(command.getPassword()),
                Collections.singleton(entityManager.getReference(Authority.class, Long.valueOf(2))));
    }

    private boolean isMatchingPassword(String rawPassword, String encryptedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encryptedPassword);
    }
}
