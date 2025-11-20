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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    //USER
    @Value("${edufy.user.keycloak.client-uuid}")
    private String userClientUuid;
    @Value("${edufy.user.keycloak.role-id.user-user}")
    private String userUserRoleId;
    @Value("${edufy.user.keycloak.role-name.user-user}")
    private String userUserRoleName;
    //Music
    @Value("${edufy.user.keycloak.music.client-uuid}")
    private String musicClientUuid;
    @Value("${edufy.user.keycloak.role-id.music-user}")
    private String musicUserRoleId;
    @Value("${edufy.user.keycloak.role-name.music-user}")
    private String musicUserRoleName;
    //Video
    @Value("${edufy.user.keycloak.video.client-uuid}")
    private String videoClientUuid;
    @Value("${edufy.user.keycloak.role-id.video-user}")
    private String videoUserRoleId;
    @Value("${edufy.user.keycloak.role-name.video-user}")
    private String videoUserRoleName;
    //Pod
    @Value("${edufy.user.keycloak.pod.client-uuid}")
    private String podClientUuid;
    @Value("${edufy.user.keycloak.role-id.pod-user}")
    private String podUserRoleId;
    @Value("${edufy.user.keycloak.role-name.pod-user}")
    private String podUserRoleName;


    @Autowired
    public KeycloakAdminServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String createUserAndAssignRoles(CreateUserDTO dto) {
        String accessToken = getAdminAccessToken();

        String userId = createUserInKeycloak(dto, accessToken);

        Set<String> services = new HashSet<>();

        services.add("USER");

        if(dto.getServices() != null){
            dto.getServices().forEach(s-> services.add(s.toUpperCase()));
        }

        for(String service : services) {
            assignServiceRole(userId, service, accessToken);
        }
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

    private void assignClientRole(String userId, String accessToken, String clientUuid, String roleId, String roleName){
        String url = keycloakBaseUrl + "/admin/realms/" + realm + "/users/" + userId + "/role-mappings/clients/" + clientUuid;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<Map<String, Object>> body = List.of(
                Map.of(
                        "id", roleId,
                        "name", roleName
                )
        );

        HttpEntity<List<Map<String, Object>>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);

        if(!response.getStatusCode().is2xxSuccessful()){
            throw new RuntimeException("Failed to assign client role to user in Keycloak. Status: " + response.getStatusCode());
        }

    }

    private void assignServiceRole(String userId, String service, String accessToken){
        String normalized = service.toUpperCase();

        switch (normalized) {
            case "USER" -> assignClientRole(
                    userId,
                    accessToken,
                    userClientUuid,
                    userUserRoleId,
                    userUserRoleName
            );
            case "MUSIC" -> assignClientRole(
                    userId,
                    accessToken,
                    musicClientUuid,
                    musicUserRoleId,
                    musicUserRoleName
            );
            case "VIDEO" -> assignClientRole(
                    userId,
                    accessToken,
                    videoClientUuid,
                    videoUserRoleId,
                    videoUserRoleName
            );
            case "POD" -> assignClientRole(
                    userId,
                    accessToken,
                    podClientUuid,
                    podUserRoleId,
                    podUserRoleName
            );
            default -> throw new IllegalArgumentException("Unknown service type: " + service);
        }

    }

}
