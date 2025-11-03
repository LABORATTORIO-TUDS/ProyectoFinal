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
public class TicketCompra {
    private int codTicket;
    private Date fechaCompra;
    private double montoTotal;
    private Comprador comprador;
    private Proyeccion proyeccion;
    private List<DetalleTicket> detalles;

    public TicketCompra() {
    }

    public TicketCompra(int codTicket, Date fechaCompra, double montoTotal, Comprador comprador, Proyeccion proyeccion, List<DetalleTicket> detalles) {
        this.codTicket = codTicket;
        this.fechaCompra = fechaCompra;
        this.montoTotal = montoTotal;
        this.comprador = comprador;
        this.proyeccion = proyeccion;
        this.detalles = detalles;
    }

    public int getCodTicket() {
        return codTicket;
    }

    public void setCodTicket(int codTicket) {
        this.codTicket = codTicket;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Proyeccion getProyeccion() {
        return proyeccion;
    }

    public void setProyeccion(Proyeccion proyeccion) {
        this.proyeccion = proyeccion;
    }

    public List<DetalleTicket> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleTicket> detalles) {
        this.detalles = detalles;
    }
    
    
    
}
