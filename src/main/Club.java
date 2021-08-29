package main;

import javax.swing.*;

public class Club {
    private Socios[] sociosRegulares = new Socios[2];
    private Socios[] sociosVip = new Socios[1];

    public Socios[] getSociosVip() {
        return sociosVip;
    }

    public void setSociosVip(Socios[] sociosVip) {
        this.sociosVip = sociosVip;
    }

    public Socios[] getSocios() {
        return sociosRegulares;
    }

    public void setSocios(Socios[] socios) {
        this.sociosRegulares = socios;
    }

    public void insertarVip() {


        //Para vips
        for (int i = 0; i < sociosVip.length ; i++) {
            if (sociosVip[i] == null ) {
                String cedula = JOptionPane.showInputDialog("Ingrese la cedula");
                Boolean seRepite = cedulaNoRepetir(cedula);
                if (seRepite) {
                    JOptionPane.showMessageDialog(null, "Cedula ingresada ya existe dentro de la base de datos.");
                } else {
                    sociosVip[i] = new Socios(cedula);
                    sociosVip[i].setTipoDeSuscripcion("VIP");
                    sociosVip[i].ingresarFondos(sociosVip[i].getTipoDeSuscripcion());
                    System.out.println("VIP create");

                }
                break;

            } else if (i == sociosVip.length-1){
                JOptionPane.showMessageDialog(null, "Capacidad llena de socios VIPS del club. Elimine un socio para continuar. ");
                break;
            }
        }
    }

    public void insertarRegular() {


        //Para regulares
        for (int i = 0; i < sociosRegulares.length ; i++) {
            if (sociosRegulares[i] == null ) {
                String cedula = JOptionPane.showInputDialog("Ingrese la cedula");
                Boolean seRepite = cedulaNoRepetir(cedula);
                if (seRepite) {
                    JOptionPane.showMessageDialog(null, "Cedula ingresada ya existe dentro de la base de datos.");
                } else {
                    sociosRegulares[i] = new Socios(cedula);
                    sociosRegulares[i].setTipoDeSuscripcion("REGULAR");
                    sociosRegulares[i].ingresarFondos(sociosRegulares[i].getTipoDeSuscripcion());
                    System.out.println("regular create");

                }
                break;

            } else if (i == sociosRegulares.length-1){
                JOptionPane.showMessageDialog(null, "Capacidad llena de socios regulares del club. Elimine un socio para continuar. ");
                break;
            }
        }


    }

    public Boolean cedulaNoRepetir(String cedula) {
        Boolean seRepite = false;

        for (int i = 0; i < sociosRegulares.length ; i++) {
            if (sociosRegulares[i] != null && sociosRegulares[i].getCedula().equals(cedula)){
                seRepite = true;
                break;
            } else {
                seRepite = false;
            }
        }
        if (seRepite) {
            return seRepite;
        }
        for (int j = 0; j < sociosVip.length; j++) {
            if (sociosVip[j] != null && sociosVip[j].getCedula().equals(cedula) ) {
                seRepite = true;
                break;
            } else {
                seRepite = false;
            }

        }
        return seRepite;
    }

    public Boolean eliminarSocio(String cedula) {

        for (int i = 0; i < sociosRegulares.length; i++) {
            if (sociosRegulares[i].getCedula().equals(cedula)) {
                sociosRegulares[i] = null;
                return true;
            }
        }

        for (int i = 0; i < sociosVip.length; i++) {
            if (sociosVip[i].getCedula().equals(cedula)) {
                sociosVip[i] = null;
                JOptionPane.showMessageDialog(null, "Estas intentando eliminar un socio VIP. Esto no es posible. Lo sentimos!");
                return false;
            }
        }

        JOptionPane.showMessageDialog(null, "Lo sentimos. La cedula no coincide con ningun socio.");

    return false;
    }

    public void ingresaFondos(String cedula) {

        if (sociosRegulares[0] == null && sociosVip[0] == null) {
            JOptionPane.showMessageDialog(null, "No hay socios registrados en el club. " +
                    "\nIntente ingresando un nuevo socio y vuelva a intentarlo.");
        }


        if (sociosRegulares[0] != null) {
            for (int i = 0; i < sociosRegulares.length; i++) {
                if (sociosRegulares[i].getCedula().equals(cedula)) {
                    JOptionPane.showMessageDialog(null,
                            "Nombre del socio: " + sociosRegulares[i].getNombre()
                                    + "\nFondos: " + sociosRegulares[i].getFondos());
                    sociosRegulares[i].ingresarFondos(sociosRegulares[i].getTipoDeSuscripcion());
                    JOptionPane.showMessageDialog(null,
                            "Nombre del socio: " + sociosRegulares[i].getNombre()
                                    + "\nFondos actualizados: " + sociosRegulares[i].getFondos());
                    return;
                } else if(sociosRegulares[i].getCedula() != cedula){
                    JOptionPane.showMessageDialog(null, "La cedula ingresada no coincide con ningun registro. " +
                            "\nVerique la cedula y vuelva a intentar.");
                    return;
                }
            }
        }

        if (sociosVip[0] != null) {
            for (int i = 0; i < sociosVip.length; i++) {
                if (sociosVip[i].getCedula().equals(cedula)) {
                    JOptionPane.showMessageDialog(null,
                            "Nombre del socio: " + sociosVip[i].getNombre()
                                    + "\nFondos: " + sociosVip[i].getFondos());
                    sociosVip[i].ingresarFondos(sociosVip[i].getTipoDeSuscripcion());
                    JOptionPane.showMessageDialog(null,
                            "Nombre del socio: " + sociosVip[i].getNombre()
                                    + "\nFondos actualizados: " + sociosVip[i].getFondos());
                    return;
                } else if(sociosVip[i].getCedula() != cedula){
                    JOptionPane.showMessageDialog(null, "La cedula ingresada no coincide con ningun registro. " +
                            "\nVerique la cedula y vuelva a intentar.");
                    return;
                }
            }
        }




    }



}
