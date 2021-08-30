package main;

import javax.swing.*;

public class Club<T> {
    private Socios[] sociosRegulares = new Socios[32];
    private Socios[] sociosVip = new Socios[3];

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

    public void InsertarPersonaAutorizada() {
        String cedulaSocio = JOptionPane.showInputDialog("Ingrese la cedula del socio al cual le va ingresar una nueva persona autorizada");

        for (int i = 0; i < sociosRegulares.length ; i++) {
            if (sociosRegulares[i] != null && sociosRegulares[i].getCedula().equals(cedulaSocio)){
                if (sociosRegulares[i].getFondos() > 0) {
                    String cedulaPersona = JOptionPane.showInputDialog("Ingrese la cedula de la persona autorizada a ingresar.");
                    Boolean seRepite = cedulaNoRepetir(cedulaPersona);
                    if (seRepite) {
                        JOptionPane.showMessageDialog(null, "Cedula ingresada ya existe dentro de la base de datos.");
                    }else {
                        sociosRegulares[i].agregarPersonas(cedulaPersona);
                    }
                    return;
                } else {
                    JOptionPane.showMessageDialog(null,"Fondos insuficientes. Ingrese fondos para poder ingresar una persona.");
                }
            }
        }
        for (int i = 0; i < sociosVip.length; i++) {
            if (sociosVip[i] != null && sociosVip[i].getCedula().equals(cedulaSocio) ) {
                if (sociosVip[i].getFondos() > 0) {
                    String cedulaPersona = JOptionPane.showInputDialog("Ingrese la cedula de la persona autorizada a ingresar.");
                    Boolean seRepite = cedulaNoRepetir(cedulaPersona);
                    if (seRepite) {
                        JOptionPane.showMessageDialog(null, "Cedula ingresada ya existe dentro de la base de datos.");
                    }else {
                        sociosVip[i].agregarPersonas(cedulaPersona);
                    }
                    return;
                }else {
                    JOptionPane.showMessageDialog(null,"Fondos insuficientes. Ingrese fondos para poder ingresar una persona.");
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Lo sentimos. No se pudo ingresar a la persona autorizada. " +
                "Verique por favor si tiene fondos suficientes.");
    }

    public void eliminarPersonaAutorizada() {
        String cedulaPerAutorizada = JOptionPane.showInputDialog("Ingrese la cedula de la persona autorizada que desee eliminar");

        for (int i = 0; i < sociosRegulares.length ; i++) {
            if (sociosRegulares[i] != null){
                sociosRegulares[i].eliminarPersonas(cedulaPerAutorizada);
                JOptionPane.showMessageDialog(null, "Persona autorizada eliminada con exito!" );
                return;
            }
        }

        for (int i = 0; i < sociosVip.length; i++) {
            if (sociosVip[i] != null ) {
                sociosVip[i].eliminarPersonas(cedulaPerAutorizada);
                JOptionPane.showMessageDialog(null, "Persona autorizada eliminada con exito!" );
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Lo sentimos. " +
                "No se encontro la persona que desea eliminar" );
        return ;
    }

    public Boolean cedulaNoRepetir(String cedula) {
        Boolean seRepite = false;
        Socios personaAutorizada = new Socios();


        for (int i = 0; i < sociosRegulares.length ; i++) {
            if (sociosRegulares[i] != null && sociosRegulares[i].getCedula().equals(cedula)){
                seRepite = true;
                return seRepite;
            } else {
                seRepite = false;
            }
        }

        for (int i = 0; i < sociosVip.length; i++) {
            if (sociosVip[i] != null && sociosVip[i].getCedula().equals(cedula) ) {
                seRepite = true;
                return seRepite;
            } else {
                seRepite = false;
            }
        }
        return seRepite;
    }

    public Boolean eliminarSocio(String cedula) {

        for (int i = 0; i < sociosRegulares.length; i++) {
            if (sociosRegulares[i] != null && sociosRegulares[i].getCedula().equals(cedula)) {
                if (sociosRegulares[i].getConsumos() != null && sociosRegulares[i].existenAutorizados()) {
                    sociosRegulares[i] = null;
                    JOptionPane.showMessageDialog(null,"Socio elimnado con exito!");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null,"Error. Estas intentando eliminar un socio que debe facturas.");
                }

            }
        }

        for (int i = 0; i < sociosVip.length; i++) {
            if (sociosVip[i] != null && sociosVip[i].getCedula().equals(cedula)) {
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

            for (int i = 0; i < sociosRegulares.length; i++) {
                if (sociosRegulares[i] != null  && sociosRegulares[i].getCedula().equals(cedula)) {
                    JOptionPane.showMessageDialog(null,
                            "Nombre del socio: " + sociosRegulares[i].getNombre()
                                    + "\nFondos: " + sociosRegulares[i].getFondos());
                    sociosRegulares[i].ingresarFondos(sociosRegulares[i].getTipoDeSuscripcion());
                    JOptionPane.showMessageDialog(null,
                            "Nombre del socio: " + sociosRegulares[i].getNombre()
                                    + "\nFondos actualizados: " + sociosRegulares[i].getFondos());
                    return;
                }
            }

            for (int i = 0; i < sociosVip.length; i++) {
                if (sociosVip[i] != null && sociosVip[i].getCedula().equals(cedula)) {
                    JOptionPane.showMessageDialog(null,
                            "Nombre del socio: " + sociosVip[i].getNombre()
                                    + "\nFondos: " + sociosVip[i].getFondos());
                    sociosVip[i].ingresarFondos(sociosVip[i].getTipoDeSuscripcion());
                    JOptionPane.showMessageDialog(null,
                            "Nombre del socio: " + sociosVip[i].getNombre()
                                    + "\nFondos actualizados: " + sociosVip[i].getFondos());
                    return;
                }
            }
        JOptionPane.showMessageDialog(null, "La cedula ingresada no coincide con ningun registro. " +
                "\nVerique la cedula y vuelva a intentar.");
        return;
    }

    public void ingresarConsumo(String cedula) {
            Boolean socioEncontrado = false;
            Boolean consumoExitoso = false;

            for (int i = 0; i < sociosRegulares.length ; i++) {
                if (sociosRegulares[i] != null && sociosRegulares[i].getCedula().equals(cedula) ) {
                    consumoExitoso = sociosRegulares[i].crearConsumo(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor del consumo a realizar")), sociosRegulares[i].getNombre());
                    if ( consumoExitoso ) {
                        socioEncontrado = true;
                        Boolean siPagar = getSiPagar(consumoExitoso);
                        eliminarConsumo(siPagar, sociosRegulares[i]);
                        return;
                    }
                }
            }

            for (int i = 0; i < sociosVip.length; i++) {
                if (sociosVip[i] != null && sociosVip[i].getCedula().equals(cedula) ) {
                    consumoExitoso = sociosVip[i].crearConsumo(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor del consumo a realizar")), sociosVip[i].getNombre());
                    if ( consumoExitoso ) {
                        socioEncontrado = true;
                        Boolean siPagar = getSiPagar(consumoExitoso);
                        eliminarConsumo(siPagar, sociosVip[i]);
                        return;
                    }
                }
            }

        if (socioEncontrado == false) {
            JOptionPane.showMessageDialog(null, "No se encontro el socio o no se genero con exito el consumo, por favor verique si la cedula ingresada es correcta. \n" +
                    "Consumo realizado: " + consumoExitoso);
        }
    }

    public Socios listarFacturas(String cedula) {

        for (int i = 0; i < sociosRegulares.length ; i++) {
            if (sociosRegulares[i] != null && sociosRegulares[i].getCedula().equals(cedula)){
                sociosRegulares[i].listarFacturas();
                return sociosRegulares[i];
            }
        }
        for (int i = 0; i < sociosVip.length; i++) {
            if (sociosVip[i] != null && sociosVip[i].getCedula().equals(cedula) ) {
                return sociosVip[i];
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontro el socio, por favor verique que la cedula sea correcta.");
        return null;
    }

    public void pagarFacturas(Socios socio) {
        Boolean consumoEliminado;

        consumoEliminado = socio.consumoEliminado();
        if(consumoEliminado) {
            JOptionPane.showMessageDialog(null, "Factura pagada con exito." + "\nsaldo actual: "
                    + socio.getFondos());
        } else {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error el pagar la factura. Verique que si cuente con " +
                    "fondos para realizar el pago e intente nuevamente.");

        }

    }

    public void vecesConsumosByCedula(String cedula) {

        for (int i = 0; i < sociosRegulares.length; i++) {
            if (sociosRegulares[i] != null && sociosRegulares[i].getCedula().equals(cedula)) {
                int consumos = sociosRegulares[i].mostrarConsumos();
                JOptionPane.showMessageDialog(null, "La cantidad de consumos realizados por este socio son: " + consumos);
                return;
            }
        }

        for (int i = 0; i < sociosVip.length; i++) {
            if (sociosVip[i] != null && sociosVip[i].getCedula().equals(cedula)) {
                int consumos = sociosVip[i].mostrarConsumos();
                JOptionPane.showMessageDialog(null, "La cantidad de consumos realizados por este socio son: " + consumos);
                return;
            } else {
                JOptionPane.showMessageDialog(null, "Error, no se encontro el socio" +
                        "\nVerique que la cedula ingresada sea correcta.");
            }
        }
    }


    //Metodos privados de clase

    private void eliminarConsumo(Boolean siPagar, Socios socio) {
        Boolean consumoEliminado = false;

        if (siPagar) {
            consumoEliminado = socio.consumoEliminado();
            if(consumoEliminado) {
                JOptionPane.showMessageDialog(null, "Factura pagada con exito.");
            } else {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error el pagar el consumo.");

            }
        }
    }

    private Boolean getSiPagar(Boolean consumoExitoso) {
        JOptionPane.showMessageDialog(null, "Consumo realizado con exito.  \n " +
                "Consumo realizado: " + consumoExitoso + "\n"
                + "Estado de la factura: " /*Si esta paga o no, todavia no lo puedo implementar, por eso el comment*/
        );
        return Boolean.parseBoolean(JOptionPane.showInputDialog("¿Desea pagar ahora mismo la factura? \n" +
                "Digite 'true' Para pagar. \n" +
                "Digite 'false' Para pagar mas tarde."));
    }


}
