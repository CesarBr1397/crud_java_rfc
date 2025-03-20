package com.rfc.crud_rfc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rfc.crud_rfc.model.rfc;
import com.rfc.crud_rfc.service.RfcService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(path = "/api/v1/rfc")
public class RfcController {

    @Autowired
    RfcService rfcService;

    @GetMapping(path = "/")
    public List<rfc> Obtener() {
        return rfcService.ObtenerTodos();
    }

    @PostMapping
    public rfc Agregar(@RequestBody rfc rfc) {
        return rfcService.Agregar(rfc);
    }

    @GetMapping(path = "{id}")
    public rfc MostrarId(@PathVariable("id") Long id) {
        return rfcService.ObtenerId(id);
    }

    @PutMapping(path = "{id}")
    public rfc Editar(@PathVariable("id") Long idUsuario, @RequestBody rfc rfc) {
        return rfcService.Actualizar(idUsuario, rfc);
    }

    @DeleteMapping(path = "{id}")
    public String Borrar(@PathVariable("id") Long id) {
        return rfcService.Eliminar(id);
    }

}
