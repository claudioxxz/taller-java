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
    
    Conexion con;
    
    public CuentaDAO(){
        con = Conexion.getSingletonInstance();     
    }
    
    public int insertarCuenta(Cuenta cuenta) throws SQLException{
        Connection cn = con.getConnection();
        Statement st = cn.createStatement();
        int filas = st.executeUpdate("Insert into cuenta values ('" + cuenta.getTipo() + "', " + cuenta.getSaldo() + ")");
        cn.close();
        return filas;
    }
    
    public int editarCuenta(Cuenta cuenta) throws SQLException{
        Connection cn = con.getConnection();
        Statement st = cn.createStatement();
        int filas = st.executeUpdate("update cuenta set tipo='" + cuenta.getTipo() + "', saldo=" + cuenta.getSaldo() + " where id=" +cuenta.getId() );
        cn.close();
        return filas;
    }
    
    public List<Cuenta> obtenerCuentas() throws SQLException{
        Connection cn = con.getConnection();
        Statement st = cn.createStatement();
        ArrayList<Cuenta> listaCuenta = new ArrayList<>();
        ResultSet rs;
        rs = st.executeQuery("Select * from cuenta");
        while(rs.next()){
            final Cuenta cuenta = new Cuenta(rs.getInt(1), rs.getString(2), rs.getDouble(3));
            listaCuenta.add(cuenta);
        }
        cn.close();
        return listaCuenta;
    }
    
    public Cuenta obtenerCuentaPorId(int id) throws SQLException{
        Connection cn = con.getConnection();
        Statement st = cn.createStatement();
        ResultSet rs;
        rs = st.executeQuery("Select * from cuenta where id=" + id);
        rs.next();
        Cuenta cuenta = new Cuenta(rs.getInt(1), rs.getString(2), rs.getDouble(3));
        cn.close();
        return cuenta;
    }
    
    public int borrarCuenta(int id) throws SQLException{
        Connection cn = con.getConnection();
        Statement st = cn.createStatement();
        int filas = st.executeUpdate("delete cuenta where id=" + id);
        cn.close();
        return filas;
    }
}
