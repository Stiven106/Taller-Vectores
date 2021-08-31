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

    public Boolean ingresarConsumos(double consumoValor, String nombreConsumidor) {
        for (int i = 0; i < consumos.length; i++) {
            if (consumos[i] == null) {
                consumos[i] = new Consumo(consumoValor, nombreConsumidor);
                return true;
            } else if(i == consumos.length-1){
                JOptionPane.showMessageDialog(null, "Limite de consumos excedidos, por favor pagar facturas para realizar un consumo.");
                return false;
            }
        }
        return false;
    }

    public Boolean listarConsumos() {
        Boolean esExito = false;

        for (int i = 0; i < consumos.length; i++) {
            if (consumos[i] != null ) {
                esExito = true;
                consumos[i].listarConsumos();
            }
        }

        return esExito;
    }

    public Double eliminarConsumos(String concepto) {
        for (int i = 0; i < consumos.length; i++) {
            if (consumos[i] != null && consumos[i].getConcepto().equals(concepto) ) {
                double descontar = consumos[i].getValor();
                consumos[i] = null;
                return descontar;
            }
        }

        JOptionPane.showMessageDialog(null, "El concepto no coincide con ningun consumo.");
        return null;

    }

    public int mostrarCantidadConsumosPersona() {
        int contador = 0;

        for (int i = 0; i < consumos.length; i++) {
            if (consumos[i] != null) {
                contador++;
            }
        }
        return contador;
    }



}
