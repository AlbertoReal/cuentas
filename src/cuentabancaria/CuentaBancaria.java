/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuentabancaria;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Alumnat
 */
public class CuentaBancaria {

    static ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();

    public static void mostrarMenu() {
        System.out.println("introduce el numero de la operacion a realizar"
                + " 1 nueva cuenta "
                + " 2 login "
        );
        Scanner lector = new Scanner(System.in);
        int menu = lector.nextInt();
        switch (menu) {
            case 1: {
                nuevoUsuario();
            }
            break;
            case 2: {
               iniciarSesion(listaCuentas);
            }
            break;
            default: {
                System.out.println("introduce un valor valido");
                mostrarMenu();
            }
        }
    }
    public static void mostrarSubMenu(int posicion){
        System.out.println("introduce opcion 1 para ingresar 2 para retirar \n"
                + "3 para volver al menu anterior");
        Scanner lector = new Scanner(System.in);
        int subMenu = lector.nextInt();
        switch (subMenu) {
            case 1:{
                Ingresar(posicion);
                mostrarSubMenu(posicion);
                break;
            }case 2:{
                Retirar(posicion);
                mostrarSubMenu(posicion);
                break;
            }case 3:{
                mostrarMenu();
                break;
            }
            
        }
    }
    public static void Transferencia() {
        Scanner lector = new Scanner(System.in);
        String nombreOrigen;
        String nombreDestino;
        double transferencia;
        System.out.println("introduce el numero de cuenta origen");
        nombreOrigen = lector.nextLine();
        int salidaOrigen = buscarCuenta(nombreOrigen);
        if (salidaOrigen == -1) {
            System.out.println("numero de cuenta no registrado en nuestro"
                    + "sistema");
            mostrarMenu();
        } else {
            System.out.println("cantidad a transferir con 2 decimales"
                    + " separados por ,");
            transferencia = lector.nextDouble();
            nombreDestino = lector.nextLine();
            System.out.println("cuenta de destino");
            nombreDestino = lector.nextLine();
            int salidaDestino = buscarCuenta(nombreDestino);
            if (salidaDestino == -1) {
                System.out.println("numero de cuenta no registrado en nuestro"
                        + "sistema");
                mostrarMenu();
            } else {
                listaCuentas.get(salidaOrigen).transferencia(transferencia, listaCuentas.get(salidaDestino));
                System.out.println(" saldo cuenta origen "
                        + listaCuentas.get(salidaOrigen).getNombre()
                        + " "
                        + listaCuentas.get(salidaOrigen).getSaldo()
                        + " "
                        + " saldo destino "
                        + listaCuentas.get(salidaDestino).getNombre()
                        + " "
                        + listaCuentas.get(salidaDestino).getSaldo()
                        + " "
                );

            }
        }
        mostrarMenu();
    }

    public static int buscarCuenta(String busqueda) {
        for (int i = 0; i < listaCuentas.size(); i++) {
            if (listaCuentas.get(i).getNumeroCuenta().equals(busqueda)) {
                return i;
            }
        }
        return -1;
    }

    public static int buscarCuentas(String busqueda, int pin) {
        int i = 0;
        boolean salida = false;
        int resultado = -1;
        while (salida==false){
            if(i==listaCuentas.size()){
              salida=true; 
            }
            else if(listaCuentas.get(i).getNumeroCuenta().equals(busqueda) && listaCuentas.get(i).getPin() == (pin)){
            resultado = i;
            salida=true;
        }else{
                i++;
            }       
        }
        return resultado;
    }
    public static void nuevoUsuario() {
        Scanner lector = new Scanner(System.in);
        String nombre;
        String cuenta;
        double saldo;
        int pin;
        System.out.println("introduce el nombre de usuadio");
        nombre = lector.nextLine();
        System.out.println("introduce el numero de cuenta");
        cuenta = lector.nextLine();
        System.out.println("introduce tu saldo con dos decimales");
        saldo = lector.nextDouble();
        System.out.println("introduce el codigo de seguridad PIN");
        pin = lector.nextInt();
        listaCuentas.add(new Cuenta(nombre, cuenta, 0.50, saldo, pin));
        mostrarMenu();
    }

    public static void Ingresar(int posicion) {
        Scanner lector = new Scanner(System.in);
        double ingreso;
        System.out.println("introduce el numero de cuenta");
        if (posicion == -1) {
            System.out.println("numero de cuenta no registrado en nuestro"
                    + "sistema");
            mostrarMenu();
        } else {
            System.out.println("cantidad a ingresar 2 decimales separados por"
                    + ",");
            ingreso = lector.nextDouble();
            listaCuentas.get(posicion).ingreso(ingreso);
            System.out.println("su saldo acutal es:"
                    + " " + listaCuentas.get(posicion).getNombre()
                    + " " + listaCuentas.get(posicion).getSaldo());
            mostrarMenu();
        }
    }

    public static void Retirar(int posicion) {
        Scanner lector = new Scanner(System.in);

        double retirada;
        if (posicion == -1) {
            System.out.println("numero de cuenta no registrado en nuestro"
                    + "sistema");
            mostrarMenu();
        } else {
            System.out.println("cantidad a ingresar 2 decimales separados por"
                    + ",");
            retirada = lector.nextDouble();
            if (listaCuentas.get(posicion).reintegro(retirada) == false) {
                System.out.println("saldo insuficiente");
                mostrarMenu();
            } else {
                System.out.println("su saldo acutal es:"
                        + " " + listaCuentas.get(posicion).getNombre()
                        + " " + listaCuentas.get(posicion).getSaldo());
                mostrarMenu();
            }

        }
    }

    public static void iniciarSesion(ArrayList<Cuenta> cuentas) {
        Scanner lector = new Scanner(System.in);
        String nombre;
        int pin;
        int salida;
        System.out.println("iniciar sesion numero de cuenta:");
        nombre = lector.nextLine();
        System.out.println(" introduce la contraseña");
        pin = lector.nextInt();
        salida = buscarCuentas(nombre, pin);
        if (salida == -1) {
            System.out.println("usuario o contraseña invalidos");
            mostrarMenu();
        } else {
            cuentas.get(salida).mostrardatos();
               
           }
            mostrarSubMenu(salida);
        }
    

    public static void main(String[] args) {
        // TODO code application logic here
        mostrarMenu();
    }
}
