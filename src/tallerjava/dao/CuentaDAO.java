/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerjava.dao;
import tallerjava.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
    
    public int insertarCuenta(Cuenta cuenta) throws SQLException{
        int filas = 0;
        try{
           filas = st.executeUpdate("Insert into cuenta values ('" + cuenta.getTipo() + "', " + cuenta.getSaldo() + ")");
        }catch(SQLException error){
            throw new SQLException(error.getMessage()); 
        }
        return filas;
    }
    
    public int editarCuenta(Cuenta cuenta) throws SQLException{
        int filas = 0;
        try{
            filas = st.executeUpdate("update cuenta set tipo='" + cuenta.getTipo() + "', saldo=" + cuenta.getSaldo() + " where id=" +cuenta.getId() );
        }catch(SQLException error){
            throw new SQLException(error.getMessage());
        }
        return filas;
    }
    
    public List<Cuenta> obtenerCuentas() throws SQLException{
        
        ArrayList<Cuenta> listaCuenta = new ArrayList<>();
        try{
            ResultSet rs;
            rs = st.executeQuery("Select * from cuenta");
            while(rs.next()){
                final Cuenta cuenta = new Cuenta(rs.getInt(1), rs.getString(2), rs.getDouble(3));
                listaCuenta.add(cuenta);
            }
        }catch(SQLException error){
            throw new SQLException(error.getMessage());
        }
        return listaCuenta;
    }
    
    public Cuenta obtenerCuentaPorId(int id) throws SQLException{
        
        ResultSet rs;
        try{
            
            rs = st.executeQuery("Select * from cuenta where id=" + id);
        }catch(SQLException error){
            throw new SQLException(error.getMessage());
        }
        rs.next();
        Cuenta cuenta = new Cuenta(rs.getInt(1), rs.getString(2), rs.getDouble(3));
        return cuenta;
    }
    
    public int borrarCuenta(int id) throws SQLException{
        int filas = 0;
        try{
            filas = st.executeUpdate("delete cuenta where id=" + id);
        }catch(SQLException error){
            throw new SQLException(error.getMessage());
        }
        return filas;
    }
}
