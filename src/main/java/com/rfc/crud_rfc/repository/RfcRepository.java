package com.rfc.crud_rfc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rfc.crud_rfc.model.rfc;

public interface RfcRepository extends JpaRepository<rfc, Long> {
    List<rfc> findByApellidoPaContainingIgnoreCase(String apellidoPa);
    List<rfc> findByNombreContainingIgnoreCase(String nombre);
}