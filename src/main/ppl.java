package main;

import javax.swing.*;

public class ppl {
    public static void main(String[] args) {

        Club socios = new Club();
        int opc;

        do {
            opc = Integer.parseInt(JOptionPane.showInputDialog(null, "Escoja una opcion: \n"
                    + "1. para registrar un socio regular \n"
                    + "2. para registrar un socio VIP\n"
                    + "3. para registrar una persona autorizada\n"
                    + "4. para eliminar una persona autorizada\n"
                    + "5. para ingresar fondos\n"
                    + "6. para realizar un consumo\n"
                    + "7. para pagar o listar facturas\n"
                    + "Aclaracion: si consulta por socio solo \n" +
                    "le mostrara los consumos de socio\n"
                    +"Si quiere consultar por los consumos \n" +
                    "de personas autorizadas tiene que ingresar\n" +
                    " la cedula de ellos. \n"
                    + "8. para eliminar un socio \n"
                    + "9. para ver cantidad de consumos por cedula \n"
            ));

            switch (opc) {
                case 1: {
                    socios.insertarRegular();
                    break;
                }
                case 2: {
                    socios.insertarVip();
                    break;
                }
                case 3: {
                    socios.InsertarPersonaAutorizada();
                    break;
                }

                case 4: {
                    socios.eliminarAutorizado();
                    break;
                }
                case 5: {
                    socios.ingresaFondos(JOptionPane.showInputDialog("Ingrese cedula del socio para el ingreso de fondos."));
                    break;
                }
                case 6: {
                        socios.ingresarConsumo(JOptionPane.showInputDialog("Por favor ingrese la cedula del socio o persona para realizar un consumo."));
                    break;
                }
                case 7: {
                    Boolean pagar = false;
                    socios.listarFacturas(JOptionPane.showInputDialog("Ingrese la cedula del socio o persona para consultar facturas."));
                    pagar = Boolean.parseBoolean(JOptionPane.showInputDialog(
                            "Ingrese 'true' para proceder a pagar una factura \n" +
                                    "Ingrese 'false' para volver al menu"));
                    if (pagar) {
                        String concepto = JOptionPane.showInputDialog("Ingrese el concepto de la factura que quiere eliminar");
                        socios.pagarFacturas(concepto);
                        break;
                    } else {
                        break;
                    }

                }
                case 8: {
                    socios.eliminarSocio(JOptionPane.showInputDialog("Ingrese cedula del socio a eliminar."));
                    break;
                }
                case 9: {
                    socios.vecesConsumosByCedula(JOptionPane.showInputDialog("Ingrese la cedula del socio a buscar sus consumos."));
                    break;
                }
            }
        } while (opc != 10);


    }
}
