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

   
static ArrayList  <Cuenta> listaCuentas = new ArrayList<Cuenta>();
    public static void  menu(){
        System.out.println("introduce el numero de la operacion a realizar"
                + " 1 nueva cuenta "
                + " 2 ingresar "
                + " 3 retirar "
                + " 4 transferencia"
                );
        Scanner lector = new Scanner(System.in);
        int menu = lector.nextInt();
        switch (menu){
          case 1:{            
              nuevoUsuario();
          }
              break;
          case 2:{
          Ingresar();
          }
              break;         
          case 3:{
           Retirar();
              break;
          }
          case 4:{
            Transferencia();
              break;             
          } 
          default:{
             System.out.println("introduce un valor valido");
             menu();
          }   
    }
}

    public static void Transferencia() {
        Scanner lector = new Scanner(System.in);
        String nombreOrigen;
        String nombreDestino;
        double transferencia;
        System.out.println("introduce el numero de cuenta origen");
        nombreOrigen=lector.nextLine();
        int salidaOrigen=buscarCuenta(nombreOrigen);
        if(salidaOrigen==-1){
            System.out.println("numero de cuenta no registrado en nuestro"
                    + "sistema");
            menu();
        }else{
            System.out.println("cantidad a transferir con 2 decimales"
                    + " separados por ,");
            transferencia=lector.nextDouble();
            nombreDestino=lector.nextLine();
            System.out.println("cuenta de destino");
            nombreDestino=lector.nextLine();
            int salidaDestino=buscarCuenta(nombreDestino);
            if(salidaDestino==-1){
                System.out.println("numero de cuenta no registrado en nuestro"
                        + "sistema");
                menu();
            }else{
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
        }menu();
    }
           public static int buscarCuenta(String busqueda){
           for(int i =0; i <listaCuentas.size();i++ ){
               if (listaCuentas.get(i).getNumeroCuenta().equals(busqueda)){
                   return i;                 
               }
           }
           return -1;
       }
           public static void nuevoUsuario(){
               Scanner lector = new Scanner(System.in);
               String nombre;
              String cuenta;
              double saldo;
              System.out.println("introduce el nombre de usuadio");
              nombre=lector.nextLine();           
              System.out.println("introduce el numero de cuenta");
              cuenta=lector.nextLine();
              System.out.println("introduce tu saldo con dos decimales");
              saldo=lector.nextDouble();
              listaCuentas.add(new Cuenta(nombre, cuenta,0.50,saldo));
              menu();
           }
           public static void Ingresar(){
               Scanner lector = new Scanner(System.in);
              String nombre;
              double ingreso;
              System.out.println("introduce el numero de cuenta");
              nombre=lector.nextLine();
              int salida=buscarCuenta(nombre);
              if(salida==-1){
                  System.out.println("numero de cuenta no registrado en nuestro"
                          + "sistema");
                  menu();
              }else{
              System.out.println("cantidad a ingresar 2 decimales separados por"
                      + ",");
              ingreso=lector.nextDouble();
              listaCuentas.get(salida).ingreso(ingreso);
              System.out.println("su saldo acutal es:"
                      +" " +listaCuentas.get(salida).getNombre()
                      +" " +listaCuentas.get(salida).getSaldo());
              menu();
            }
           }
           public static void Retirar(){
               Scanner lector = new Scanner(System.in);
                String nombre;
            double retirada;
              System.out.println("introduce el numero de cuenta");
              nombre=lector.nextLine();
              int salida=buscarCuenta(nombre);
              if(salida==-1){
                  System.out.println("numero de cuenta no registrado en nuestro"
                          + "sistema");
                  menu();
              }else{
              System.out.println("cantidad a ingresar 2 decimales separados por"
                      + ",");
              retirada=lector.nextDouble();
             if (listaCuentas.get(salida).reintegro(retirada)==false){
                 System.out.println("saldo insuficiente");
                 menu();
             }else{
              System.out.println("su saldo acutal es:"
                      +" "+listaCuentas.get(salida).getNombre()
                      +" "+listaCuentas.get(salida).getSaldo());
              menu();
             }
               
           }
        }
    public static void main(String[] args) {
        // TODO code application logic here
        menu();
    }   
}
