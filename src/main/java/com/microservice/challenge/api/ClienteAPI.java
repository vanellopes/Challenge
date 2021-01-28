package com.microservice.challenge.api;

import com.microservice.challenge.entity.Cliente;
import com.microservice.challenge.exception.ClientException;
import com.microservice.challenge.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cliente")
public class ClienteAPI {

    @Autowired
    ClienteService clienteService;

    @RequestMapping(value = "/creacliente", method = RequestMethod.POST)
    public Cliente updateOrSave(@RequestBody Cliente cliente){
        Cliente clienteRes = new Cliente();
        try {
            clienteRes = clienteService.save(cliente);
        } catch (ConstraintViolationException k) {
            throw new ClientException("no se admiten valores nulos");
        }
        return clienteRes;
    }

    @RequestMapping(value="/kpideclientes", method = RequestMethod.GET)
    public Map<String, Double> kpideclientes(){

        return clienteService.kpideclientes();
    }

    @RequestMapping(value="/listclientes", method = RequestMethod.GET)
    public List<Cliente> listclientes(){
        return clienteService.listClientes();
    }
}
