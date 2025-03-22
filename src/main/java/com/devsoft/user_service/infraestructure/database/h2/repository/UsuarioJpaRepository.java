package com.devsoft.user_service.infraestructure.database.h2.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsoft.user_service.infraestructure.database.h2.entity.UsuarioEntity;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, UUID> {
}
