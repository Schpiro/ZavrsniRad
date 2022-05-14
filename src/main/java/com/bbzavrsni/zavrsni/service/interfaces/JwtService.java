package com.bbzavrsni.zavrsni.service.interfaces;

import com.bbzavrsni.zavrsni.model.pojo.User;

public interface JwtService {

    boolean authenticate(String token);

    String createJwt(User user);

}
