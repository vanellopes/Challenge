package com.microservice.challenge.service.impl;

import com.microservice.challenge.dao.ClienteRepository;
import com.microservice.challenge.entity.Cliente;
import com.microservice.challenge.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ClienteServiceImpl implements ClienteService {
    private static int ESPERANZA_VIDA = 80;

    @Autowired
    ClienteRepository clienteRepository;
    public Cliente save(Cliente cliente){
        return clienteRepository.saveAndFlush(cliente);
    }

    public List<Cliente> listClientes(){
        List<Cliente> clientes = new ArrayList<>();
        clientes = clienteRepository.findAll();

        for (Cliente cliente : clientes) {
            cliente.setEdad(calculateEdad(cliente.fechaNacimiento));
            cliente.setFechaEstMuerte(calculateFechaMuerte(cliente.getFechaNacimiento(), cliente.getEdad()));
        }

        return clientes;
    }

    public Map<String, Double> kpideclientes(){
        Map<String, Double> result = new HashMap<>();
        List<Cliente> clientes = new ArrayList<>();
        clientes = this.listClientes();

        OptionalDouble promedioEdad = clientes.stream().mapToInt(Cliente::getEdad).average();
        Double desviacion = desviacionEstandar(clientes, promedioEdad);

        result.put("Promedio de edades", promedioEdad.getAsDouble());
        result.put("Desviacion estandar", desviacion);

        return result;

    }

    private int calculateEdad(Date fechaNacimiento ){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNac = fechaNacimiento.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate ahora = LocalDate.now();

        Period periodo = Period.between(fechaNac, ahora);
        return periodo.getYears();
    }

    private Date calculateFechaMuerte(Date fechaNacimiento, int edad ){
        Date now = new Date();
        Date fechaEstMuerte;
        if (edad < ESPERANZA_VIDA){
            int anios_vida = ESPERANZA_VIDA-edad;
            fechaEstMuerte = setearAnio(now, anios_vida);

        }else{
            fechaEstMuerte = setearAnio(now, 5);
        }
        return fechaEstMuerte;
    }

    private static Date setearAnio(Date fecha, int anios_vida){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.YEAR, anios_vida);
        return calendar.getTime();
    }

    private double desviacionEstandar(List<Cliente> clientes, OptionalDouble promedioEdad){
        int sum = 0;

        for (Cliente cliente: clientes ) {
            sum += Math.pow (cliente.getEdad() - promedioEdad.getAsDouble(), 2);
        }

        return Math.sqrt ( sum / clientes.size() );
    }
}
