package com.bank.transaction.infrastructure.adapter;

import com.bank.transaction.domain.port.AuthenticationService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class JwtAuthenticationService implements AuthenticationService {

    @Override
    public String authenticate(String username, String password) {
        throw new UnsupportedOperationException("Implementar autenticación JWT");
    }

    @Override
    public Set<String> validateToken(String token) {
        throw new UnsupportedOperationException("Implementar validación de token JWT");
    }

    @Override
    public boolean hasRole(String token, String requiredRole) {
        throw new UnsupportedOperationException("Implementar verificación de rol JWT");
    }
}