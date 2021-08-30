package main;

import javax.swing.*;

public class ppl {
    public static void main(String[] args) throws NullPointerException{

        Club socios = new Club();
        int opc;

        do {
            opc = Integer.parseInt(JOptionPane.showInputDialog(null, "Escoja una opcion: \n"
                    + "1. para registrar un socio regular \n"
                    + "2. para registrar un socio VIP\n"
                    + "3. para ingresar fondos\n"
                    + "4. para realizar un consumo\n"
                    + "5. para pagar un consumo\n"
                    + "7. para eliminar un socio \n"
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
                    socios.ingresaFondos(JOptionPane.showInputDialog("Ingrese cedula del socio para el ingreso de fondos."));
                    break;
                }
                case 4: {
                        socios.ingresarConsumo(JOptionPane.showInputDialog("Por favor ingrese la cedula del socio o persona para realizar un consumo."));
                    break;
                }
                case 5: {
                    socios.pagarFacturas();

                    break;
                }
                case 7: {
                    socios.eliminarSocio(JOptionPane.showInputDialog("Ingrese cedula del socio a eliminar."));
                    break;
                }
            }
        } while (opc != 9);


    }
}
