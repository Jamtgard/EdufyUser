package com.example.EdufyUser.convertes;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Value("${edufy.user.client.id}")
    private String userClientId;

    @Override
    public AbstractAuthenticationToken convert(@Nonnull Jwt source) {
        Collection<GrantedAuthority> authorities = Stream.concat(
                jwtGrantedAuthoritiesConverter.convert(source).stream(),
                extractRoles(source).stream())
                .collect(Collectors.toSet()
        );

        return new JwtAuthenticationToken(source, authorities);
    }

    private Collection<? extends GrantedAuthority> extractRoles(Jwt jwt) {
        Map<String, Object> resourceAccess;
        Map<String, Object> resources;
        Collection<String> resourceRoles;

        if(!jwt.hasClaim("resource_access")) {
            return Set.of();
        }

        resourceAccess = jwt.getClaimAsMap("resource_access");

        if(!resourceAccess.containsKey(userClientId)) {
            return Set.of();
        }

        resources = (Map<String, Object>) resourceAccess.get(userClientId);

        if(!resources.containsKey("roles")) {
            return Set.of();
        }

        resourceRoles = (Collection<String>) resources.get("roles");

        System.out.println("Resource Roles: " + resourceRoles);

        return resourceRoles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role)).collect(Collectors.toSet());
    }
}
