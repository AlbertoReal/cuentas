/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuentabancaria;

import java.util.Scanner;

/**
 *
 * @author Alumnat
 */
public class Cuenta {
    private String nombre;
    private String numeroCuenta;
    private double tipoIntres;
    private double saldo;

    public Cuenta() {
    }

    public Cuenta(String nombre, String numeroCuenta, Double tipoIntres, Double saldo) {
        this.nombre = nombre;
        this.numeroCuenta = numeroCuenta;
        this.tipoIntres = tipoIntres;
        this.saldo = saldo;
    }
    public Cuenta(Cuenta p1){
        this.nombre = p1.nombre;
        this.numeroCuenta = p1.numeroCuenta;
        this.tipoIntres = p1.tipoIntres;
        this.saldo = p1.saldo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public Double getTipoIntres() {
        return tipoIntres;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setTipoIntres(Double tipoIntres) {
        this.tipoIntres = tipoIntres;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    public boolean ingreso(double saldo){
        double nuevoSaldo;
        double saldoActual;
        if (saldo>0){
            saldoActual=this.getSaldo();
            nuevoSaldo=saldoActual+saldo;
            this.setSaldo(nuevoSaldo); 
            return true;
        }else{
            return false;
        }
    }
    public boolean reintegro(double reintegro){
        double nuevoSaldo;
        double saldoActual;
        if (this.getSaldo()>reintegro){
            saldoActual=this.getSaldo();
            nuevoSaldo=saldoActual-reintegro;
            this.setSaldo(nuevoSaldo);
            return true;
        }else{
            return false;
        }
    }
    public boolean transferencia(double cantidad, Cuenta destino){
        double nuevoSaldo;
        double saldoActual;
        if (this.getSaldo()>cantidad){
            saldoActual=this.getSaldo();
            nuevoSaldo=saldoActual-cantidad;
            this.setSaldo(nuevoSaldo);
            saldoActual=destino.getSaldo();
            nuevoSaldo=saldoActual+cantidad;
            destino.setSaldo(nuevoSaldo);           
            return true;
        }else{
            return false;
        }
    }
    public void nuevoUsuario(){
        System.out.println("escribe el nombre");
        Scanner entrada = new Scanner(System.in);
        this.setNombre(entrada.nextLine());
        System.out.println("numero de cuenta");
        this.setNumeroCuenta(entrada.nextLine());
        System.out.println("saldo ");
        this.setSaldo(entrada.nextDouble());
        this.setTipoIntres(0.25);
    }
    public void mostrardatos(){
        System.out.println("nombre:" + this.getNombre());
        System.out.println("cuenta:" + this.getNumeroCuenta());
        System.out.println("interes:" +this.getTipoIntres());
        System.out.println("saldo actual:" +this.getSaldo());
    }
}
