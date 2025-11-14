package com.example.EdufyUser.services;


import com.example.EdufyUser.models.DTO.CreateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;

//ED-239-AWS
@Service
public class KeycloakAdminServiceImpl implements KeycloakAdminService {

    private final RestTemplate restTemplate;

    @Value("${edufy.user.keycloak.base-url}")
    private String keycloakBaseUrl;

    @Value("${edufy.user.keycloak.realm}")
    private String realm;

    @Value("${edufy.user.keycloak.admin.client-id}")
    private String adminClientId;

    @Value("${edufy.user.keycloak.admin.client-secret}")
    private String adminClientSecret;

    @Value("${edufy.user.keycloak.client-uuid}")
    private String clientUuid;

    @Value("${edufy.user.keycloak.role-id.user-user}")
    private String userUserRoleId;

    @Value("${edufy.user.keycloak.role-name.user-user}")
    private String userUserRoleName;

    @Autowired
    public KeycloakAdminServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String createUserAndAssignRole(CreateUserDTO dto) {
        String accessToken = getAdminAccessToken();

        String userId = createUserInKeycloak(dto, accessToken);

        assignClientRoleToUser(userId, accessToken);

        return userId;
    }


    private String getAdminAccessToken(){
        String tokenUrl = keycloakBaseUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        body.add("client_id", adminClientId);
        body.add("client_secret", adminClientSecret);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, requestEntity, Map.class);

        if(!response.getStatusCode().is2xxSuccessful() || response.getBody() == null){
            throw new RuntimeException("Failed to get access token from Keycloak. Status: " + response.getStatusCode());
        }

        Object token = response.getBody().get("access_token");
        if(token == null){
            throw new RuntimeException("No access_token in response from Keycloak");
        }

        return token.toString();
    }

    private String createUserInKeycloak(CreateUserDTO dto, String accessToken){
        String url = keycloakBaseUrl + "/admin/realms/" + realm + "/users";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of(
                "enabled", true,
                "username", dto.getUsername(),
                "email", dto.getEmail(),
                "emailVerified", true,
                "credentials", List.of(
                        Map.of(
                                "type", "password",
                                "value", dto.getPassword(),
                                "temporary", false
                        )
                )
        );

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(url, requestEntity, Void.class);

        if(response.getStatusCode() != HttpStatus.CREATED && response.getStatusCode() != HttpStatus.NO_CONTENT){
            throw new RuntimeException("Failed to create user in Keycloak. Status: " + response.getStatusCode());
        }

        URI location = response.getHeaders().getLocation();
        if(location == null){
            throw new RuntimeException("No location header returned when creating user in Keycloak");
        }

        String path = location.getPath();
        return path.substring(path.lastIndexOf("/") + 1);
    }

    private void assignClientRoleToUser(String userId, String accessToken){
        String url = keycloakBaseUrl + "/admin/realms/" + realm + "/users/" + userId + "/role-mappings/clients/" + clientUuid;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<Map<String, Object>> body = List.of(
                Map.of(
                        "id", userUserRoleId,
                        "name", userUserRoleName
                )
        );

        HttpEntity<List<Map<String, Object>>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);

        if(!response.getStatusCode().is2xxSuccessful()){
            throw new RuntimeException("Failed to assign client role to user in Keycloak. Status: " + response.getStatusCode());
        }

    }

}
