/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class Sala {
    private int nroSala;
    private boolean apta3D;
    private int capacidad;
    private String estado;

    public Sala() {
    }

    public Sala(boolean apta3D, int capacidad, String estado) {
        this.apta3D = apta3D;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    public Sala(int nroSala, boolean apta3D, int capacidad, String estado) {
        this.nroSala = nroSala;
        this.apta3D = apta3D;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    public int getNroSala() {
        return nroSala;
    }

    public void setNroSala(int nroSala) {
        this.nroSala = nroSala;
    }

    public boolean isApta3D() {
        return apta3D;
    }

    public void setApta3D(boolean apta3D) {
        this.apta3D = apta3D;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        String tipo = apta3D ? "3D" : "2D";
        return "Sala " + nroSala + " (" + tipo + ") - Cap: " + capacidad;
    }
}