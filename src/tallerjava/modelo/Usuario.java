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
public class Usuario {
    private String rut;
    private String contraseña;
    private int id;
    
    public Usuario(String rut, String contraseña) {
        this.rut = rut;
        this.contraseña = contraseña;
    }

    public Usuario(int id, String rut, String contraseña) {
        this.rut = rut;
        this.contraseña = contraseña;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
}
