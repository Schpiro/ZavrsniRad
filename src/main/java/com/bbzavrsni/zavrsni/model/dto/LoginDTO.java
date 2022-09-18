package com.bbzavrsni.zavrsni.model.dto;

import com.bbzavrsni.zavrsni.util.GsonUtil;
import lombok.Data;

@Data
public class LoginDTO {
    private final String jwt;

    public LoginDTO(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this);
    }
}
