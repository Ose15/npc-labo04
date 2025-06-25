package com.npc.laboratorio04.Utils.Mappers;

import com.npc.laboratorio04.DTO.CreateUserDTO;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

public class GeneralMappers {
    public static Map<String, Object> createUserDtoToMap(CreateUserDTO user) {
        return Map.of(
                "username", user.getUserName(),
                "email", user.getEmail(),
                "firstName", user.getFirstName(),
                "lastName", user.getLastName(),
                "enabled", true,
                "emailVerified", true,
                "credentials", List.of(Map.of("type", "password", "value", user.getPassword(), "temporary", false))
        );
    }

    public static MultiValueMap<String, String> loginToFormData(String username, String password, String clientId, String clientSecret) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", username);
        formData.add("password", password);
        formData.add("grant_type", "password");
        formData.add("client_id", clientId);
        formData.add("client_secret", clientSecret);
        return formData;
    }
}
