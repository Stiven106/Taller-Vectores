package main;

import javax.swing.*;

public class Consumo {

    private String concepto;
    private double valor;
    private String nombre;

    public Consumo(double consumoValor, String nombre) {
        this.concepto = JOptionPane.showInputDialog("Ingrese el concepto del consumo");
        this.valor = consumoValor;
        this.nombre = nombre;
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

    public void listarConsumos() {

        JOptionPane.showMessageDialog(null,
                "valor del consumo : " + valor +
                "\nconcepto del consumo : " + concepto
        );
    }


}
