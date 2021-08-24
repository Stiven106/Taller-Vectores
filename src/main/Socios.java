package main;

import javax.swing.*;
import java.util.ArrayList;


public class Socios {

    private String cedula;
    private float fondos;
    private String suscripcion;
    private String facturas;
    private ArrayList<String> listaPersonasAutorizadas;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public float getFondos() {
        return fondos;
    }

    public void setFondos(float fondos) {
        this.fondos = fondos;
    }

    public String getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(String suscripcion) {
        this.suscripcion = suscripcion;
    }

    public String getFacturas() {
        return facturas;
    }

    public void setFacturas(String facturas) {
        this.facturas = facturas;
    }

    public ArrayList<String> getListaPersonasAutorizadas() {
        return listaPersonasAutorizadas;
    }

    public void setListaPersonasAutorizadas(ArrayList<String> listaPersonasAutorizadas) {
        this.listaPersonasAutorizadas = listaPersonasAutorizadas;
    }

    public Socios(String cedula) {

        String opcion = null;
        this.cedula = cedula;
        System.out.println(this.cedula);

        do {
            opcion = JOptionPane.showInputDialog(null, "Ingrese '1' sí el usuario es VIP. \n " +
                    "Ingrese '2' sí el usuario es REGULAR.");

            switch (opcion) {
            case "1": {
                opcion = "VIP";
                System.out.println("Suscripcion agregada con exito");

                break;
            }
            case "2": {
                opcion = "REGULAR";
                System.out.println("Suscripcion agregada con exito");
                break;
            } default:
                opcion = null;
                JOptionPane.showMessageDialog(null,"Opcion ingresada no valida, por favor intente nuevamente.");
        }} while (opcion == null);

        this.suscripcion = opcion;

        this.fondos = Float.parseFloat(JOptionPane.showInputDialog(null, "¿Cuanto dinero desea ingresar?"));

        //Validar los fondos minimos.
        if (opcion == "REGULAR") {
            if (this.fondos <= 50000 || this.fondos > 1000000) {
                JOptionPane.showMessageDialog(null,"Error, restriccion generada: Minimo 50.000. Maximo 1.000.000");

            }
        } else if(opcion == "VIP") {
            if(this.fondos <= 100000 || this.fondos > 5000000) {
                JOptionPane.showMessageDialog(null,"Error, restriccion generada: Minimo 100.000. Maximo 5.000.000");

            }
        }


        this.facturas = null;
        this.listaPersonasAutorizadas = listaPersonasAutorizadas;
    }

    public void agregarPersonasAutorizadas() {

    }

    public void noRepetirCedula(String cedula, Socios[] socios) throws NullPointerException {

    }

}
