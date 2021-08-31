package main;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.ArrayList;


public class Socios {

    private Consumo consumos[] = new Consumo[20];
    private Persona personas[] = new Persona[10];
    private String cedula;
    private String tipoDeSuscripcion;
    private String nombre;
    private double fondos;

    public Socios() {
    }

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

    public boolean crearConsumoSocio(double consumoValor, String nombreConsumidor) {

        for (int i = 0; i < consumos.length; i++) {
            if (consumos[i] == null) {
                if (this.fondos >= consumoValor) {
                    consumos[i] = new Consumo(consumoValor, nombreConsumidor);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Fondos insuficientes.");
                    return false;
                }
            } else if(i == consumos.length-1){
                JOptionPane.showMessageDialog(null, "Limite de consumos excedidos, por favor pagar facturas para realizar un consumo.");
            }
        }
        return false;
    }

    public Boolean crearConsumoPersona(double consumoValor, String cedula) {
        String nombreConsumidor = retornarNombrePersona(cedula);
        if (this.fondos >= consumoValor) {
            for (int i = 0; i < personas.length; i++) {
                if(personas[i] != null && personas[i].getCedula().equals(cedula)) {
                   personas[i].ingresarConsumos(consumoValor, nombreConsumidor);
                    return true;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Fondos insuficientes.");
            return false;
        }
        return false;

    }

    public Boolean eliminarConsumoByConcepto(String concepto) {

        for (int i = 0; i < consumos.length; i++) {
            if (consumos[i] != null && consumos[i].getConcepto().equals(concepto)) {
                    if ((this.fondos - consumos[i].getValor()) >= 0) {
                        this.fondos = this.fondos - consumos[i].getValor();
                        consumos[i] = null;
                        JOptionPane.showMessageDialog(null,"Factura pagada con exito.");
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null,"Fondos insuficientes para realizar la transaccion, pruebe ingresando fondos.");
                        return false;
                    }
            }
        }


        return false;
    }

    public void listarFacturas() {
        Boolean esExito = false;
                esExito = true;
        for (int i = 0; i < consumos.length; i++) {
            if (consumos[i] != null ) {
                consumos[i].listarConsumos();
            }
        }
        if (esExito) {
                return;
        } else {
            JOptionPane.showMessageDialog(null, "No existen facturas, por favor vuelva al menu..");

        }
    }

    public Boolean eliminarFacturaPersona(String concepto) {
        for (int i = 0; i < personas.length; i++) {
            if(personas[i] != null) {
                double valorConsumo = personas[i].eliminarConsumos(concepto);
                if ((this.fondos - valorConsumo) >= 0) {
                    this.fondos = this.fondos - valorConsumo;
                    JOptionPane.showMessageDialog(null,"Factura pagada con exito.");
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public void listarFacturasPersonas(String cedula) {
        Boolean esExito = false;
        for (int i = 0; i < personas.length; i++) {
            if(personas[i] != null && personas[i].getCedula().equals(cedula) ) {
                esExito = personas[i].listarConsumos();
                return;
            }
        }
        if (esExito) {
            JOptionPane.showMessageDialog(null, "Factura pagada con exito." + "\nSaldo actual: " + getFondos());
            return;
        } else {
            JOptionPane.showMessageDialog(null, "No existen facturas, por favor vuelva al menu..");

        }

    }

    public void agregarPersonas(String cedula) {
        for (int i = 0; i < this.personas.length; i++) {
            if (this.personas[i] == null) {
                Club metodosClub = new Club();
                Boolean seRepite = metodosClub.cedulaNoRepetir(cedula);
                if (seRepite) {
                    JOptionPane.showMessageDialog(null, "La persona que usted esta intentando ingresar ya se encuentra en nuestra base de datos." );
                } else {
                    this.personas[i] = new Persona(cedula);
                    System.out.println("exito desde socios:" + personas[i].getCedula());
                    System.out.println("exito desde socios:" + personas[i].getNombre());
                    JOptionPane.showMessageDialog(null, "Persona autorizada creada con exito!");
                    return ;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Limite de personas excedidos, por favor elimine lista de personas " +
                "autorizadas para ingresar una nueva.");
    }

    public Boolean buscarPersonasByCedula(String cedula) {
        for (int i = 0; i < personas.length; i++) {
            if (personas[i] != null && personas[i].getCedula().equals(cedula)) {
                return true;
            }
        }
        return false;
    }

    public String retornarNombrePersona(String cedula) {
        for (int i = 0; i < personas.length; i++) {
            if (personas[i] != null && personas[i].getCedula().equals(cedula)) {
                return personas[i].getNombre();
            }
        }
        return null;
    }

    public Boolean cedulaSeRepite(String cedula) {
        Boolean seRepite = false;
        for (int i = 0; i < personas.length; i++) {
            if (personas[i] != null && personas[i].getCedula().equals(cedula)) {
                seRepite = true;
                break;
            }
        }

        if (seRepite) {
                JOptionPane.showMessageDialog(null, "Cedula ingresada ya existe dentro de la base de datos.");
        }

        return seRepite;
    }

    public Boolean existenAutorizados() {
        for (int i = 0; i < personas.length; i++) {
            if (personas[i] != null) {
                //si existen
                return true;
            }
        }
        //no existen retornar true
        return false;
    }
    public Boolean existenConsumos() {
        for (int i = 0; i < consumos.length; i++) {
            if (consumos[i] != null) {
                return true;
            }
        }
        return false;
    }

    public int mostrarCantidadConsumos() {
        int contador = 0;

        for (int i = 0; i < consumos.length; i++) {
            if (consumos[i] != null) {
                contador++;
            }
        }
        return contador;
    }

    public int mostrarCantidadConsumosPersona(String cedula) {
        for (int i = 0; i < personas.length; i++) {
            if (personas[i] != null && personas[i].getCedula().equals(cedula)) {
                return personas[i].mostrarCantidadConsumosPersona();
            }
        }
        return 0;
    }

    public Boolean eliminarPersonaAutorizada(String cedula) {

        for (int i = 0; i < personas.length; i++) {
            if (personas[i] != null && personas[i].getCedula().equals(cedula)) {
                personas[i] = null;
                return true;
            }
        }
        return false;
    }

}
