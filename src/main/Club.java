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
        for (int i = 0; i < sociosVip.length; i++) {
            if (sociosVip[i] == null) {
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

            } else if (i == sociosVip.length - 1) {
                JOptionPane.showMessageDialog(null, "Capacidad llena de socios VIPS del club. Elimine un socio para continuar. ");
                break;
            }
        }
    }

    public void insertarRegular() {

        //Para regulares
        for (int i = 0; i < sociosRegulares.length; i++) {
            if (sociosRegulares[i] == null) {
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

            } else if (i == sociosRegulares.length - 1) {
                JOptionPane.showMessageDialog(null, "Capacidad llena de socios regulares del club. Elimine un socio para continuar. ");
                break;
            }
        }


    }

    public void InsertarPersonaAutorizada() {
        String cedulaSocio = JOptionPane.showInputDialog("Ingrese la cedula del socio al cual le va ingresar una nueva persona autorizada");

        for (int i = 0; i < sociosRegulares.length; i++) {
            if (sociosRegulares[i] != null && sociosRegulares[i].getCedula().equals(cedulaSocio)) {
                if (sociosRegulares[i].getFondos() > 0) {
                    String cedulaPersona = JOptionPane.showInputDialog("Ingrese la cedula de la persona autorizada a ingresar.");
                    if (sociosRegulares[i].cedulaSeRepite(cedulaPersona) == false) {
                        sociosRegulares[i].agregarPersonas(cedulaPersona);
                    }
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "Fondos insuficientes. Ingrese fondos para poder ingresar una persona.");
                }
            }
        }

        for (int i = 0; i < sociosVip.length; i++) {
            if (sociosVip[i] != null && sociosVip[i].getCedula().equals(cedulaSocio)) {
                if (sociosVip[i].getFondos() > 0) {
                    String cedulaPersona = JOptionPane.showInputDialog("Ingrese la cedula de la persona autorizada a ingresar.");
                    if (sociosVip[i].cedulaSeRepite(cedulaPersona) == false) {
                        sociosVip[i].agregarPersonas(cedulaPersona);
                    }
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "Fondos insuficientes. Ingrese fondos para poder ingresar una persona.");
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Lo sentimos. No se pudo ingresar a la persona autorizada. " +
                "Verique por favor si el socio al que quiere ingresar tiene fondos suficientes.");
    }



    public Boolean cedulaNoRepetir(String cedula) {
        Boolean seRepite = false;
        Socios personaAutorizada = new Socios();


        for (int i = 0; i < sociosRegulares.length; i++) {
            if (sociosRegulares[i] != null && sociosRegulares[i].getCedula().equals(cedula)) {
                seRepite = true;
                return seRepite;
            } else {
                seRepite = false;
            }
        }

        for (int i = 0; i < sociosVip.length; i++) {
            if (sociosVip[i] != null && sociosVip[i].getCedula().equals(cedula)) {
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
                if (sociosRegulares[i].existenConsumos() == false && sociosRegulares[i].existenAutorizados() == false) {
                    sociosRegulares[i] = null;
                    JOptionPane.showMessageDialog(null, "Socio elimnado con exito!");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Error. Estas intentando eliminar un socio que tiene autorizados.\n" +
                            "O tiene facturas a pagar, por favor consulta en el menu.");
                    return false;
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

    public void eliminarAutorizado() {
        String cedulaEliminar = JOptionPane.showInputDialog("Por favor ingrese la cedula de la persona autorizada que desee eliminar.");

        for (int i = 0; i < sociosRegulares.length; i++) {
            if (sociosRegulares[i] != null && sociosRegulares[i].buscarPersonasByCedula(cedulaEliminar)) {
                System.out.println("si se ejecuto");
                sociosRegulares[i].eliminarPersonaAutorizada(cedulaEliminar);
                JOptionPane.showMessageDialog(null,"Persona autorizada eliminada con exito!");
                return;
            }
        }

        for (int i = 0; i < sociosVip.length; i++) {
            if (sociosVip[i] != null && sociosVip[i].buscarPersonasByCedula(cedulaEliminar)) {
                sociosVip[i].eliminarPersonaAutorizada(cedulaEliminar);
                JOptionPane.showMessageDialog(null,"Persona autorizada eliminada con exito!");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Lo sentimos, no encontramos la persona correspondiente. \n" +
                "Por favor verique que la cedula ingresada sea correcta.");
    }

    public void ingresaFondos(String cedula) {

        if (sociosRegulares[0] == null && sociosVip[0] == null) {
            JOptionPane.showMessageDialog(null, "No hay socios registrados en el club. " +
                    "\nIntente ingresando un nuevo socio y vuelva a intentarlo.");
        }

        for (int i = 0; i < sociosRegulares.length; i++) {
            if (sociosRegulares[i] != null && sociosRegulares[i].getCedula().equals(cedula)) {
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

        for (int i = 0; i < sociosRegulares.length; i++) {
            if (sociosRegulares[i] != null && sociosRegulares[i].getCedula().equals(cedula)) {
                consumoExitoso = sociosRegulares[i].crearConsumoSocio(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el " +
                        "valor del consumo a realizar")), sociosRegulares[i].getNombre());
                if (consumoExitoso) {
                    socioEncontrado = true;
                    confirmacion(consumoExitoso);
                    return;
                }
            } else if (sociosRegulares[i] != null && sociosRegulares[i].buscarPersonasByCedula(cedula)) {
                    consumoExitoso = sociosRegulares[i].crearConsumoPersona(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el " +
                            "valor del consumo a realizar")) , cedula);
                    if (consumoExitoso) {
                        socioEncontrado = true;
                        confirmacion(consumoExitoso);
                        return;
                    }
            }
        }

        for (int i = 0; i < sociosVip.length; i++) {
            if (sociosVip[i] != null && sociosVip[i].getCedula().equals(cedula)) {
                consumoExitoso = sociosVip[i].crearConsumoSocio(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor del consumo a realizar")), sociosVip[i].getNombre());
                if (consumoExitoso) {
                    socioEncontrado = true;
                    confirmacion(consumoExitoso);
                    return;
                }
            } else if (sociosVip[i] != null && sociosVip[i].buscarPersonasByCedula(cedula)) {
                consumoExitoso = sociosVip[i].crearConsumoPersona(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el " +
                        "valor del consumo a realizar")) , cedula);
                if (consumoExitoso) {
                    socioEncontrado = true;
                    confirmacion(consumoExitoso);
                    return;
                }
            }
        }

        if (socioEncontrado == false) {
            JOptionPane.showMessageDialog(null, "No se encontro el socio o no se genero con exito el consumo, por favor verique si la cedula ingresada es correcta. \n" +
                    "Consumo realizado: " + consumoExitoso);
        }
    }

    public void listarFacturas(String cedula) {
        Boolean consumoExitoso = false;
        Boolean socioEncontrado = false;

        for (int i = 0; i < sociosRegulares.length; i++) {
            if (sociosRegulares[i] != null && sociosRegulares[i].getCedula().equals(cedula)) {
                sociosRegulares[i].listarFacturas();
                return;
            } else if (sociosRegulares[i] != null && sociosRegulares[i].buscarPersonasByCedula(cedula)) {
                sociosRegulares[i].listarFacturasPersonas(cedula);
                return;
            }
        }

        for (int i = 0; i < sociosVip.length; i++) {
            if (sociosVip[i] != null && sociosVip[i].getCedula().equals(cedula)) {
                sociosVip[i].listarFacturas();
                return;
            } else if (sociosVip[i] != null && sociosVip[i].buscarPersonasByCedula(cedula)) {
                sociosVip[i].listarFacturasPersonas(cedula);
                return;
            }
        }


        JOptionPane.showMessageDialog(null, "No se encontro el socio, por favor verique que la cedula sea correcta.");
        return;
    }

    public void pagarFacturas(String concepto) {
        Boolean consumoEliminado = false;

        for (int i = 0; i < sociosRegulares.length; i++) {
            if (sociosRegulares[i] != null ) {
                consumoEliminado = sociosRegulares[i].eliminarConsumoByConcepto(concepto);
                if (consumoEliminado) {
                    return;
                } else {
                    if (sociosRegulares[i].eliminarFacturaPersona(concepto)){
                        return;

                    }
                }
            }
        }

        for (int i = 0; i < sociosVip.length; i++) {
            if (sociosRegulares[i] != null) {
                consumoEliminado = sociosVip[i].eliminarConsumoByConcepto(concepto);
                if (consumoEliminado) {
                    return;
                } else {
                    if (sociosVip[i].eliminarFacturaPersona(concepto)) {
                        return;
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontro el concepto, pruebe a listar y verique que el nombre del concepto sea correcto. \n" +
                "Recuerde que segun el taller las personas no cuentan con fondos, por esto mismo, si desea pagar las facturas" +
                " de un autorizado tiene que ingresar desde ");
    }

    public void vecesConsumosByCedula(String cedula) {

        for (int i = 0; i < sociosRegulares.length; i++) {
            if (sociosRegulares[i] != null && sociosRegulares[i].getCedula().equals(cedula)) {
                int consumos = sociosRegulares[i].mostrarCantidadConsumos();
                JOptionPane.showMessageDialog(null, "La cantidad de consumos realizados por este socio son: " + consumos);
                return;
            } else if (sociosRegulares[i] != null && sociosRegulares[i].buscarPersonasByCedula(cedula) ) {
                JOptionPane.showMessageDialog(null, "La cantidad de consumos realizados por este socio son: " +
                        sociosRegulares[i].mostrarCantidadConsumosPersona(cedula)
                );

            }
        }

        for (int i = 0; i < sociosVip.length; i++) {
            if (sociosVip[i] != null && sociosVip[i].getCedula().equals(cedula)) {
                int consumos = sociosVip[i].mostrarCantidadConsumos();
                JOptionPane.showMessageDialog(null, "La cantidad de consumos realizados por este socio son: " + consumos);
                return;
            } else if (sociosVip[i] != null && sociosVip[i].buscarPersonasByCedula(cedula) ) {
                JOptionPane.showMessageDialog(null, "La cantidad de consumos realizados por este socio son: " +
                        sociosVip[i].mostrarCantidadConsumosPersona(cedula)
                );

            }
        }
    }


    //Metodos privados de clase


    private void confirmacion(Boolean consumoExitoso) {
        JOptionPane.showMessageDialog(null, "Consumo realizado con exito.  \n " +
                "Consumo realizado: " + consumoExitoso + "\n"
                + "Estado de la factura: Sin pagar."
        );


    }
}
