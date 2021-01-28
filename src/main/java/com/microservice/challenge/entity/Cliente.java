package com.microservice.challenge.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long cliente_id;
    @NotNull
    public String nombre;
    @NotNull
    public String apellido;
    @NotNull
    public Date   fechaNacimiento;

    @Transient
    public int edad;
    @Transient
    public Date fechaEstMuerte;

    public Long getCliente_id() {
        return cliente_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Date getFechaEstMuerte() {
        return fechaEstMuerte;
    }

    public void setFechaEstMuerte(Date fechaEstMuerte) {
        this.fechaEstMuerte = fechaEstMuerte;
    }
}

