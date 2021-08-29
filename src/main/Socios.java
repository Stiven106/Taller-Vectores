package main;

import javax.swing.*;
import java.util.ArrayList;


public class Socios {

    private Consumo consumos[] = new Consumo[20];
    private Persona personas[] = new Persona[10];

    private String cedula;
    private String tipoDeSuscripcion;
    private String nombre;
    private double fondos;

    public Socios(String cedula) {
        this.cedula = cedula;
        this.nombre = JOptionPane.showInputDialog("Ingrese el nombre del nuevo socio.");
    }

    public String getTipoDeSuscripcion() {
        return tipoDeSuscripcion;
    }

    public void setTipoDeSuscripcion(String tipoDeSuscripcion) {
        this.tipoDeSuscripcion = tipoDeSuscripcion;
    }

    public Consumo[] getConsumos() {
        return consumos;
    }

    public void setConsumos(Consumo[] consumos) {
        this.consumos = consumos;
    }

    public Persona[] getPersonas() {
        return personas;
    }

    public void setPersonas(Persona[] personas) {
        this.personas = personas;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getFondos() {
        return fondos;
    }

    public void setFondos(double fondos) {
        this.fondos = fondos;
    }

    public double ingresarFondos(String tipoDeSuscripcion) {
        double nuevoIngreso;

        do {
            nuevoIngreso = Double.parseDouble(JOptionPane.showInputDialog(null, "¿Cuanto dinero desea ingresar?"));
            nuevoIngreso = nuevoIngreso + this.fondos;
            if (tipoDeSuscripcion == "REGULAR" && (nuevoIngreso < 50000 || nuevoIngreso > 1000000)) {
                JOptionPane.showMessageDialog(null, "Error, restriccion generada: \n" +
                        "Minimo 50.000 y el maximo 1.000.000 --> REGULAR"+
                        "\n No se ha podido realizar el ingreso. Por favor Intentalo nuevamente");
                nuevoIngreso = 0;
            } else if(tipoDeSuscripcion == "VIP" && (nuevoIngreso < 100000 || nuevoIngreso > 5000000)) {
                JOptionPane.showMessageDialog(null, "Error, restriccion generada: \n" +
                        "Minimo 100.000 y el maximo 5.000.000 --> VIP" +
                        "\n No se ha podido realizar el ingreso. Por favor Intentalo nuevamente");
                nuevoIngreso = 0;
            }

        } while(nuevoIngreso == 0);

        this.fondos = nuevoIngreso;
        return this.fondos;

    }

    public void crearConsumo(double consumoValor) {

        for (int i = 0; i < consumos.length; i++) {
            if (consumos[i] == null) {
                if (this.fondos >= consumoValor) {
                    consumos[i] = new Consumo(consumoValor);
                }
            }
        }
    }

    public void pagarConsumo() {

    }
}
