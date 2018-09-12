/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerjava.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tallerjava.Conexion;
import tallerjava.modelo.UsuarioDetalle;

/**
 *
 * @author ElPCdeClaudio
 */
public class UsuarioDetalleDao {
    private final Conexion con;
    private final Statement st;
    public UsuarioDetalleDao() throws SQLException{
         con = Conexion.getSingletonInstance();
         st = con.getConnection().createStatement();
    }
    
    public boolean insertar(UsuarioDetalle usuario) throws SQLException{
        String sql = String.format("insert into usuario_detalle (id, nombres, apellidos, email) values(%s, '%s', '%s', '%s')", 
                usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail());
        int i = st.executeUpdate(sql);
        return i > 0; 
    }
    
    public boolean eliminar(int id) throws SQLException{
        String sql = String.format("DELETE FROM usuario_detalle WHERE id= %s", id);
        int  i = st.executeUpdate(sql);
        return i>0;
    }
    
    public boolean actualizar(int id, UsuarioDetalle usuario) throws SQLException{
        String sql = String.format("UPDATE usuario_detalle SET nombres='%s', apellidos='%s', email='%s' WHERE id=%s", 
                usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), id);
        int i = st.executeUpdate(sql);
        return i>0;
    }
    
    public List<UsuarioDetalle> obtenerTodos() throws SQLException{
        ResultSet rs = st.executeQuery("SELECT id, nombres, apellidos, email FROM usuario_detalle");
        ArrayList<UsuarioDetalle> listaUsuarios = new ArrayList<>();
        while (rs.next()) {
            final UsuarioDetalle usuario = new UsuarioDetalle(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            listaUsuarios.add(usuario);
        }
        return listaUsuarios;
    }
    
    public UsuarioDetalle obtener(int id) throws SQLException{
        String sql = String.format("SELECT id, nombres, apellidos, email FROM usuario_detalle WHERE id= %s", id);
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        final UsuarioDetalle usuario = new UsuarioDetalle(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        return usuario;
    }
}
