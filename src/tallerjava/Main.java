/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerjava;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ElPCdeClaudio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexion conexion = Conexion.getSingletonInstance();
        
        Connection connection = conexion.getConnection();
        
        try {
            if(connection.isClosed()){
                System.out.println("Conexion esta cerrada");
                
            }else{
                System.out.println("Conexion esta abierta");
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
