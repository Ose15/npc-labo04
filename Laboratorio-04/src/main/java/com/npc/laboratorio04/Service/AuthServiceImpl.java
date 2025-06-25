package com.npc.laboratorio04.Service;

import com.npc.laboratorio04.Config.Keycloak.KeycloakProperties;
import com.npc.laboratorio04.DTO.CreateUserDTO;
import com.npc.laboratorio04.DTO.KeycloakTokenResponse;
import com.npc.laboratorio04.Keycloak.iKeycloakAdminClient;
import com.npc.laboratorio04.Keycloak.iKeycloakAuthClient;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

import static com.npc.laboratorio04.Utils.Mappers.GeneralMappers.createUserDtoToMap;
import static com.npc.laboratorio04.Utils.Mappers.GeneralMappers.loginToFormData;
import static com.npc.laboratorio04.Utils.UserIdFromKeycloak.getUserIdFromKeycloakResponse;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements iAuthService {

    private final iKeycloakAdminClient keycloakAdminClient;
    private final iKeycloakAuthClient keycloakAuthClient;
    private final KeycloakProperties keycloakProperties;

    public KeycloakTokenResponse register(CreateUserDTO user) throws Exception {
        Response response = keycloakAdminClient.createUser(createUserDtoToMap(user));
        if (response.status() != 201) throw new Exception("Failed to create user: " + new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8));
        String userId = getUserIdFromKeycloakResponse(response);
        return login(user.getUserName(), user.getPassword());
    }

    @Override
    public KeycloakTokenResponse login(String username, String password) {
        return keycloakAuthClient.getToken(loginToFormData(username, password, keycloakProperties.getClientId(), keycloakProperties.getClientSecret()));
    }
}
