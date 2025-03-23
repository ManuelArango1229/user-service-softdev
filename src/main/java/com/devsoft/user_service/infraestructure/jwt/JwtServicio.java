package com.devsoft.user_service.infraestructure.jwt;

import com.devsoft.user_service.domain.entities.Usuario;
import org.springframework.stereotype.Service;

@Service
public class JwtServicio {
    private static final String SECRET = "secret";
    private static final String TOKEN_PREFIX = "Bearer ";
}
