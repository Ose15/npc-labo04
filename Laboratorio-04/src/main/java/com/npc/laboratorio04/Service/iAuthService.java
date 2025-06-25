package com.npc.laboratorio04.Service;


import com.npc.laboratorio04.DTO.CreateUserDTO;
import com.npc.laboratorio04.DTO.KeycloakTokenResponse;
import jakarta.validation.Valid;

public interface iAuthService {

    KeycloakTokenResponse register(@Valid CreateUserDTO user) throws Exception;
    KeycloakTokenResponse login(String username, String password);
}
