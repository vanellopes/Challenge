package com.microservice.challenge.dao;

import com.microservice.challenge.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}