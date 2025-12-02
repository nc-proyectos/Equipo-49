package com.nc.g49_smartcrm.controller;

import com.nc.g49_smartcrm.config.JwtService;
import com.nc.g49_smartcrm.dto.AuthRequest;
import com.nc.g49_smartcrm.dto.AuthResponse;
import com.nc.g49_smartcrm.dto.RefreshTokenRequest;
import com.nc.g49_smartcrm.dto.RegisterRequest;
import com.nc.g49_smartcrm.repository.UserRepository;
import com.nc.g49_smartcrm.service.AuthService;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
//TODO agregar dominio
@CrossOrigin(origins = "https://*******")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "End points para autenticacion y registro")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Operation(summary = "Registrar a un usuario")
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        logger.info("Register request received");
        return ResponseEntity.ok(authService.register(request));
    }

    @Operation(summary = "Autenticar a un usuario y devuelve un jwt")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        logger.info("Authenticate request received");
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @Operation(summary = "Refrescar un jwt")
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshTokenRequest request) {
        logger.info("Refresh request received");
        String refreshToken = request.getRefreshToken();
        String email;

        try {
            email = jwtService.getUserName(refreshToken);
        } catch (ExpiredJwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (email == null || !jwtService.isTokenValid(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var user = userRepository.findByEmail(email).orElseThrow();
        var accessToken = jwtService.generateToken(new HashMap<>(), user);

        return ResponseEntity.ok(AuthResponse.builder()
                .token(accessToken)
                .refreshToken(refreshToken)
                .build());
    }
}
