/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import tallerjava.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import tallerjava.modelo.Cuenta;

/**
 *
 * @author Javier Ortiz
 */
public class CuentaDAO {
    
    Statement st;
    
    public CuentaDAO() throws ClassNotFoundException, SQLException{
        try{
            Conexion con = Conexion.getSingletonInstance();
            Connection cn = con.getConnection();
            st = cn.createStatement();
        }catch(SQLException error){
            throw new SQLException(error.getMessage()); 
        }
        
        
    }
    
    public int InsertarCuenta(Cuenta cuenta) throws SQLException{
        int filas = 0;
        try{
           filas = st.executeUpdate("Insert into cuenta values ('" + cuenta.getTipo() + "', " + cuenta.getSaldo() + ")");
        }catch(SQLException error){
            throw new SQLException(error.getMessage()); 
        }
        return filas;
    }
    
    public int EditarCuenta(Cuenta cuenta) throws SQLException{
        int filas = 0;
        try{
            filas = st.executeUpdate("update cuenta set tipo='" + cuenta.getTipo() + "', saldo=" + cuenta.getSaldo() + " where id=" +cuenta.getId() );
        }catch(SQLException error){
            throw new SQLException(error.getMessage());
        }
        return filas;
    }
    
    public ResultSet ObtenerCuentas() throws SQLException{
        ResultSet rs;
        try{
            rs = st.executeQuery("Select * from cuenta");
        }catch(SQLException error){
            throw new SQLException(error.getMessage());
        }
        return rs;
    }
    
    public ResultSet ObtenerCuentaPorId(int id) throws SQLException{
        ResultSet rs;
        try{
            rs = st.executeQuery("Select * from cuenta where id=" + id);
        }catch(SQLException error){
            throw new SQLException(error.getMessage());
        }
        return rs;
    }
    
    public int BorrarCuenta(int id) throws SQLException{
        int filas = 0;
        try{
            filas = st.executeUpdate("delete cuenta where id=" + id);
        }catch(SQLException error){
            throw new SQLException(error.getMessage());
        }
        return filas;
    }
}
