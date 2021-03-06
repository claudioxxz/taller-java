/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerjava.modelo;

/**
 *
 * @author Javier Ortiz
 */
public class Cuenta {
    private int id;
    private String tipo;
    private double saldo;

    public Cuenta(String tipo, double saldo) {
        this.tipo = tipo;
        this.saldo = saldo;
    }
    
    public Cuenta(int id, String tipo, double saldo) {
        this.tipo = tipo;
        this.saldo = saldo;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    public double depositar(double deposito){
        return deposito + this.saldo;
    }
    
    public double retirar(double retiro){
        return this.saldo - retiro;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "id=" + id + ", tipo=" + tipo + ", saldo=" + saldo + '}';
    }
    
    
}
