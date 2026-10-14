package com.bank.transaction.domain.port;

import java.util.Set;

public interface AuthenticationService {
    /**
     * Verifica las credenciales del usuario y retorna un token de autenticación.
     * 
     * @param username Nombre de usuario
     * @param password Contraseña del usuario
     * @return Token de autenticación válido
     * @throws com.bank.transaction.infrastructure.exception.AuthenticationException Si las credenciales son inválidas
     */
    String authenticate(String username, String password);

    /**
     * Valida un token de autenticación y retorna los roles asociados.
     * 
     * @param token Token a validar
     * @return Conjunto de roles del usuario
     * @throws com.bank.transaction.infrastructure.exception.AuthenticationException Si el token es inválido o ha expirado
     */
    Set<String> validateToken(String token);

    /**
     * Verifica si el usuario tiene el rol requerido.
     * 
     * @param token Token de autenticación
     * @param requiredRole Rol requerido
     * @return true si el usuario tiene el rol, false en caso contrario
     */
    boolean hasRole(String token, String requiredRole);
}