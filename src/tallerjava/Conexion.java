/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ElPCdeClaudio
 */
public class Conexion {
    private final Connection cn;
    private static Conexion conexion;
    
    private final String USER = "root";
    private final String PASS = "";
    private final String HOST = "localhost:3306/";
    private final String DB = "taller-java";

    private Conexion() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        cn = DriverManager.getConnection("jdbc:mysql://"+HOST+DB, USER, PASS);
    }

    public static Conexion getSingletonInstance() {
        if (conexion == null) {
            try {
                conexion = new Conexion();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conexion;
    }
    
    public Connection getConnection(){
        return cn;
    }
}
