package main;

import javax.swing.*;

public class Persona {
    private Consumo consumos[] = new Consumo[20];
    private String nombre;
    private String cedula;


    public Persona(String cedula) {
        this.nombre = JOptionPane.showInputDialog("Ingrese su nombre");
        this.cedula = cedula;
    }

    public String getCedula() {
        return cedula;
    }


    public Consumo[] getConsumos() {
        return consumos;
    }

    public void setConsumos(Consumo[] consumos) {
        this.consumos = consumos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}
