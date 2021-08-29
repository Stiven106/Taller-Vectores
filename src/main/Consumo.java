package main;

import javax.swing.*;

public class Consumo {

    private String concepto;
    private double valor;
    private String nombre;

    public Consumo(double consumoValor) {
        this.concepto = JOptionPane.showInputDialog("Ingrese el concepto del consumo");
        this.valor = consumoValor;
        this.nombre = JOptionPane.showInputDialog("Nombre de socio o persona autorizada que realiza el consumo.");
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
