/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author elias
 */
public class DetalleTicket {
  
    private double subtotal;
    private TicketCompra ticketCompra;
    private Asiento asiento;

    public DetalleTicket() {
    }

    public DetalleTicket(double subtotal, TicketCompra ticketCompra, Asiento asiento) {
        this.subtotal = subtotal;
        this.ticketCompra = ticketCompra;
        this.asiento = asiento;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public TicketCompra getTicketCompra() {
        return ticketCompra;
    }

    public void setTicketCompra(TicketCompra ticketCompra) {
        this.ticketCompra = ticketCompra;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }
    
    
}
