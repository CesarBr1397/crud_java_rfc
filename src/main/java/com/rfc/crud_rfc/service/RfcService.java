package com.rfc.crud_rfc.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rfc.crud_rfc.model.rfc;
import com.rfc.crud_rfc.repository.RfcRepository;

@Service
public class RfcService {

    @Autowired
    RfcRepository rfcRepository;

    public String RFC(rfc rfc) {
        List<String> prohibidas = Arrays.asList("BUEI", "CACA", "CAGA", "CAKA", "COGE", "COJE",
                "COJO", "FETO", "JOTO", "KACO", "KAGO", "KOJO", "KULO", "MAMO", "MEAS", "MION", "MULA",
                "PEDO", "PUTA", "QULO", "BUEY", "CACO", "CAGO", "CAKO", "COJA", "COJI", "CULO", "GUEY",
                "KACA", "KOGE", "KAKA", "MAME", "MEAR", "MEON", "MOCO", "PEDA", "PENE", "PUTO", "RATA");

        List<String> nombresComunes = Arrays.asList("MARIA", "MA.", "MA", "JOSE", "J", "J.");

        List<Character> vocales = Arrays.asList('A', 'E', 'I', 'O', 'U');
        List<Character> consonantes = Arrays.asList('B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L',
                'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z');


        //Validar si el Apellido Paterno comienza con  Ñ
        String apellidoPa = rfc.getApellidoPa().toUpperCase();
        if (apellidoPa.startsWith("Ñ")) {
            apellidoPa = apellidoPa.replace("Ñ", "X");
        }

        //Validar si el apellido la segunda letra es vocal o consonante
        if (vocales.contains(apellidoPa.charAt(1))) {
            apellidoPa = apellidoPa.substring(0, 2);
        } else {
            apellidoPa = apellidoPa.substring(0, 1) + apellidoPa.charAt(2);
        }

        //Validar si la tercera letra es vocal o consonante
        if (consonantes.contains(apellidoPa.charAt(1))) {
            apellidoPa = apellidoPa.substring(0, 1) + "X";
        }

        String apellidoMa = rfc.getApellidoMa().substring(0, 1).toUpperCase();

        String nombre = rfc.getNombre().substring(0, 1).toUpperCase();

        //validar si el primer nombre es un nombre comun
        for (String nombreComun : nombresComunes) {
            String[] nombresSeparados = rfc.getNombre().toUpperCase().split(" ");
            if (nombresSeparados[0].equals(nombreComun)) {
                if (nombresSeparados.length > 1) {
                    nombre = nombresSeparados[1].substring(0, 1);
                }
                break;
            }
        }

        LocalDate fecha = rfc.getFechaNac();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        String fechaNac = fecha.format(formatter);


        String rfcCreado = apellidoPa + apellidoMa + nombre;

        if (prohibidas.contains(rfcCreado)) {
            rfcCreado = rfcCreado.substring(0, rfcCreado.length() - 1) + "X";
        }

        String rfcFinal = rfcCreado + fechaNac;

        return rfcFinal;
    }

    public List<rfc> ObtenerTodos() {
        return rfcRepository.findAll();
    }

    public rfc Agregar(rfc rfc) {
        rfc.setRFC(RFC(rfc));
        ;
        return rfcRepository.save(rfc);
    }

    public rfc ObtenerId(Long id) {
        Optional<rfc> optionalRfc = rfcRepository.findById(id);
        return optionalRfc.orElse(null);
    }

    public rfc Actualizar(Long id, rfc rfcActualizado) {
        Optional<rfc> optionalRfc = rfcRepository.findById(id);
        if (optionalRfc.isPresent()) {
            rfc rfcExistente = optionalRfc.get();

            rfcExistente.setApellidoPa(rfcActualizado.getApellidoPa());
            rfcExistente.setApellidoMa(rfcActualizado.getApellidoMa());
            rfcExistente.setNombre(rfcActualizado.getNombre());
            rfcExistente.setFechaNac(rfcActualizado.getFechaNac());
            rfcExistente.setRFC(RFC(rfcActualizado));

            return rfcRepository.save(rfcExistente);
        } else {
            return null;
        }
    }

    public String Eliminar(Long id) {
        if (rfcRepository.existsById(id)) {
            rfcRepository.deleteById(id);
            return "Se borró el usuario con el ID " + id;
        } else {
            return "No se encontró el usuario con el ID " + id;
        }
    }

    public List<rfc> BuscarPorApellidoPa(String apellidoPa) {
        return rfcRepository.findByApellidoPaContainingIgnoreCase(apellidoPa);
    }

    public List<rfc> BuscarPorNombre(String nombre) {
        return rfcRepository.findByNombreContainingIgnoreCase(nombre);
    }
}
