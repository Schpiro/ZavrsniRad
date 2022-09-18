package com.bbzavrsni.zavrsni.controller;


import com.bbzavrsni.zavrsni.ZavrsniApplication;
import com.bbzavrsni.zavrsni.command.MessageGroupCommand;
import com.bbzavrsni.zavrsni.model.dto.MessageGroupDTO;
import com.bbzavrsni.zavrsni.model.dto.UserDTO;
import com.bbzavrsni.zavrsni.model.pojo.UserAuthentication;
import com.bbzavrsni.zavrsni.service.interfaces.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "https://localhost:4200")
public class UserController {
    private static final Logger logger = LogManager.getLogger(ZavrsniApplication.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers(Principal principal) {
        logger.info(((UserAuthentication) principal).getPrincipal().getUID() + " fetching all users.");
        return userService.findAllUsers();
    }

    @GetMapping("/groups")
    public List<MessageGroupDTO> getAllUserGroups(Principal principal) {
        logger.info(((UserAuthentication) principal).getPrincipal().getUID() + " fetching all users groups.");
        return userService.findAllGroups(((UserAuthentication) principal).getPrincipal().getUID());
    }

    @GetMapping("/groups/{id}")
    public List<UserDTO> getUsersInGroup(Principal principal, @PathVariable("id") Long groupId) {
        logger.info(((UserAuthentication) principal).getPrincipal().getUID() + " fetching all users in group.");
        return userService.getUsersInGroup(groupId);
    }

    @PostMapping("/groups")
    public MessageGroupDTO createMessageGroup(@Valid @RequestBody final MessageGroupCommand messageGroupCommand, Principal principal) {
        logger.info("Creating new group '" + messageGroupCommand.getGroupName() +
                    "' with users: " + messageGroupCommand.getGroupParticipants().toString() +
                    ". User creating:" + ((UserAuthentication) principal).getPrincipal().getUID());
        return userService.saveMessageGroup(messageGroupCommand);
    }
}
