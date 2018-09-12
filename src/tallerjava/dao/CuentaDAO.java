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
    
    private final Statement st;
    
    public CuentaDAO() throws SQLException{
        Conexion con = Conexion.getSingletonInstance();
        Connection cn = con.getConnection();
        st = cn.createStatement();
    }
    
     public int idUltimaInsercion() throws SQLException{
        ResultSet rs = st.executeQuery("Select Last_Insert_ID()");
        rs.next();
        int id = rs.getInt(1);
        return id;
    }
    
    public boolean insertarCuenta(int usuarioId, Cuenta cuenta) throws SQLException{
        int filas = st.executeUpdate("Insert into cuenta (usuario_id, tipo, saldo) values ("+usuarioId+", '" + cuenta.getTipo() + "', " + cuenta.getSaldo() + ")");
        return filas>0;
    }
    
    public boolean editarCuenta(Cuenta cuenta) throws SQLException{
        int filas = st.executeUpdate("update cuenta set tipo='" + cuenta.getTipo() + "', saldo=" + cuenta.getSaldo() + " where id=" +cuenta.getId() );
        return filas>0;
    }
    
    public List<Cuenta> obtenerCuentas() throws SQLException{
        ArrayList<Cuenta> listaCuenta = new ArrayList<>();
        ResultSet rs;
        rs = st.executeQuery("Select * from cuenta");
        while(rs.next()){
            final Cuenta cuenta = new Cuenta(rs.getInt(1), rs.getString(2), rs.getDouble(3));
            listaCuenta.add(cuenta);
        }
        return listaCuenta;
    }
    
    
    public List<Cuenta> obtenerCuentasPorUsuario(int usuarioId) throws SQLException{
        ArrayList<Cuenta> listaCuenta = new ArrayList<>();
        ResultSet rs = st.executeQuery("Select id, tipo, saldo from cuenta where usuario_id ="+usuarioId);
        while(rs.next()){
            final Cuenta cuenta = new Cuenta(rs.getInt(1), rs.getString(2), rs.getDouble(3));
            listaCuenta.add(cuenta);
        }
        return listaCuenta;
    }
    
    public Cuenta obtenerCuentaPorId(int id) throws SQLException{        
        ResultSet rs;
        rs = st.executeQuery("Select id, tipo, saldo from cuenta where id=" + id);
        rs.next();
        Cuenta cuenta = new Cuenta(rs.getInt(1), rs.getString(2), rs.getDouble(3));
        return cuenta;
    }
    
    public boolean borrarCuenta(int id) throws SQLException{
        int filas = st.executeUpdate("delete from cuenta where id=" + id);
        return filas>0;
    }
}
