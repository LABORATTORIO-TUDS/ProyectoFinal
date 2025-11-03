/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;
import java.util.List;

/**
 *
 * @author elias
 */
public class Comprador {
    private int dni;
    private String nombre;
    private Date fechaNac;
    private String password;
    private List<TicketCompra> tickets;

    public Comprador() {
    }

    public Comprador(int dni, String nombre, Date fechaNac, String password, List<TicketCompra> tickets) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.password = password;
        this.tickets = tickets;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TicketCompra> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketCompra> tickets) {
        this.tickets = tickets;
    }

    
    
    
    
}
