package main;

import javax.swing.*;

public class test {
    public static void main(String[] args) throws NullPointerException{

        Socios socios[] = new Socios[3];
        int opc;

        do {
            opc = Integer.parseInt(JOptionPane.showInputDialog(null, "Escoja una opcion: \n"
                    + "1. para registrar un socio al club\n"
                    + "2. para eliminar libro del catalogo\n"));

            switch (opc) {
                case 1: {

                    for (int i = 0; i < socios.length; i++) {
                        if (socios[i] == null) {
                            String cedula = JOptionPane.showInputDialog("Ingrese la cedula");
                            Boolean seRepite = true;

                            //Validando que la cedula no se repita.
                                for (int j = 0; j < socios.length; j++) {
                                    System.out.println("valor actual de " + j);
                                    if (socios[j] != null && socios[j].getCedula().equals(cedula) )  {
                                        //Si esto se cumple, quiere decir que cedula se repite. Por tanto el valor es true.
                                        //No hay break debido a que tiene que iterar todas las posiciones de vector socios en busca de algun repetido.
                                        seRepite = true;
                                        break;
                                    } else {
                                        System.out.println("Cedula no se ha ingresado aun, exito!");
                                        seRepite = false;
                                    }
                                }
                            System.out.println(seRepite);
                            //Agregar nuevo socio
                            if (seRepite) {
                                JOptionPane.showMessageDialog(null, "Cedula ingresa ya existe.");
                            } else {
                                socios[i] = new Socios(cedula);
                            }
                            break;

                        } else if (i == socios.length-1){
                            JOptionPane.showMessageDialog(null, "Capacidad llena de socios del club. Elimine un socio para continuar. ");
                            break;
                        }

                    }
                }
                case 2: {

                    break;
                }
            }
        } while (opc != 3);


    }
}
