package com.microservice.challenge.service;

import com.microservice.challenge.entity.Cliente;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ClienteService {

    Cliente save(Cliente cliente);

    List<Cliente> listClientes();

    Map<String, Double> kpideclientes();
}
