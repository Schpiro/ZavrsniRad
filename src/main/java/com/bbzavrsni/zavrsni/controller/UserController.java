package com.bbzavrsni.zavrsni.controller;


import com.bbzavrsni.zavrsni.ZavrsniApplication;
import com.bbzavrsni.zavrsni.model.dto.MessageGroupDTO;
import com.bbzavrsni.zavrsni.model.dto.UserDTO;
import com.bbzavrsni.zavrsni.model.pojo.UserAuthentication;
import com.bbzavrsni.zavrsni.service.interfaces.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "http://localhost:4200")public class UserController {
    private static final Logger logger = LogManager.getLogger(ZavrsniApplication.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllMessages(Principal principal){
        logger.info(((UserAuthentication) principal).getPrincipal().getUID() + " fetching all users.");
        return userService.findAllUsers();
    }

    @GetMapping("/groups")
    public List<MessageGroupDTO> getAllUserGroups(Principal principal){
        logger.info(((UserAuthentication) principal).getPrincipal().getUID() + " fetching all users groups.");
        return userService.findAllGroups(((UserAuthentication) principal).getPrincipal().getUID());
    }
}
