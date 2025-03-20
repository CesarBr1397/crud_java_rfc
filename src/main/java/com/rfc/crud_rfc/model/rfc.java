package com.rfc.crud_rfc.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class rfc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idUsuario;

    private String nombre;

    private String apellidoPa;

    private String apellidoMa;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaNac;

    private String rfc;

    public rfc(Long idUsuario, String nombre, String apellidoPa, String apellidoMa, Date fechaNac, String rFC) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidoPa = apellidoPa;
        this.apellidoMa = apellidoMa;
        this.fechaNac = fechaNac;
        rfc = rFC;
    }

    public rfc() {
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPa() {
        return apellidoPa;
    }

    public void setApellidoPa(String apellidoPa) {
        this.apellidoPa = apellidoPa;
    }

    public String getApellidoMa() {
        return apellidoMa;
    }

    public void setApellidoMa(String apellidoMa) {
        this.apellidoMa = apellidoMa;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getRFC() {
        return rfc;
    }

    public void setRFC(String rFC) {
        rfc = rFC;
    }

}
