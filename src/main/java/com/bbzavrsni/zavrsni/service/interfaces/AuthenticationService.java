package com.bbzavrsni.zavrsni.service.interfaces;

import com.bbzavrsni.zavrsni.command.LoginCommand;
import com.bbzavrsni.zavrsni.model.dto.LoginDTO;

import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginCommand command);

    Optional<LoginDTO> register(LoginCommand command);
}
