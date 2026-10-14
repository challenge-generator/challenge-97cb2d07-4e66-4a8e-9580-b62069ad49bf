package com.bank.transaction.infrastructure.adapter;

import com.bank.transaction.domain.port.AuthenticationService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OAuth2AuthenticationService implements AuthenticationService {

    private final Map<String, OAuth2Token> tokenStore = new ConcurrentHashMap<>();
    private final String clientId = "transaction-service";
    private final String clientSecret = "secret";
    private final String tokenEndpoint = "/oauth/token";

    @Override
    public String authenticate(String username, String password) {
        throw new UnsupportedOperationException("OAuth2 authentication requires client credentials flow");
    }

    public String authenticateWithClientCredentials(String clientId, String clientSecret) {
        if (!this.clientId.equals(clientId) || !this.clientSecret.equals(clientSecret)) {
            throw new SecurityException("Invalid client credentials");
        }
        
        String tokenId = UUID.randomUUID().toString();
        OAuth2Token token = new OAuth2Token(
                tokenId,
                Set.of("transaction:read", "transaction:write"),
                System.currentTimeMillis() + 3600000
        );
        
        tokenStore.put(tokenId, token);
        return tokenId;
    }

    @Override
    public Set<String> validateToken(String token) {
        OAuth2Token oauth2Token = tokenStore.get(token);
        
        if (oauth2Token == null) {
            return Set.of();
        }
        
        if (System.currentTimeMillis() > oauth2Token.expiresAt()) {
            tokenStore.remove(token);
            return Set.of();
        }
        
        return oauth2Token.roles();
    }

    @Override
    public boolean hasRole(String token, String requiredRole) {
        Set<String> roles = validateToken(token);
        return roles.contains(requiredRole);
    }

    public Map<String, Object> createTokenResponse(String token) {
        OAuth2Token oauth2Token = tokenStore.get(token);
        if (oauth2Token == null) {
            throw new SecurityException("Invalid token");
        }
        
        return Map.of(
                OAuth2ParameterNames.ACCESS_TOKEN, token,
                OAuth2ParameterNames.TOKEN_TYPE, "Bearer",
                OAuth2ParameterNames.EXPIRES_IN, 3600,
                "scope", String.join(" ", oauth2Token.roles())
        );
    }

    private record OAuth2Token(String tokenId, Set<String> roles, long expiresAt) {}
}