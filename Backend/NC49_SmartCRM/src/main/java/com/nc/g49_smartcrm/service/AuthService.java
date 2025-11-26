package com.nc.g49_smartcrm.service;

import com.nc.g49_smartcrm.dto.AuthRequest;
import com.nc.g49_smartcrm.dto.AuthResponse;
import com.nc.g49_smartcrm.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse authenticate(AuthRequest request);

    AuthResponse refreshToken(String refreshToken);
}