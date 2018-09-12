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
import tallerjava.modelo.Usuario;

/**
 *
 * @author ElPCdeClaudio
 */
public class UsuarioDao {
    private final Conexion con;
    private final Statement st;
    public UsuarioDao() throws SQLException{
         con = Conexion.getSingletonInstance();
         st = con.getConnection().createStatement();
    }
    
    public boolean verificarUsuario(Usuario usuario) throws SQLException{
        List<Usuario> listaUsuarios = obtenerTodos();
        
        for(Usuario u: listaUsuarios){
            if(u.equals(usuario)){
                usuario.setId(u.getId());
                return true;
            }
        }
        return false;
    }
    
    public boolean insertar(Usuario usuario) throws SQLException{
        String sql = String.format("insert into usuario (rut, contraseña) values('%s', '%s')", usuario.getRut(), usuario.getContraseña());
        int i = st.executeUpdate(sql);
        return i > 0; 
    }
    
    public boolean eliminar(int id) throws SQLException{
        String sql = String.format("DELETE FROM usuario WHERE id= %s", id);
        int  i = st.executeUpdate(sql);
        return i>0;
    }
    
    public boolean actualizar(int id, Usuario usuario) throws SQLException{
        String sql = String.format("UPDATE usuario SET rut='%s', contraseña='%s' WHERE id=%s", usuario.getRut(), usuario.getContraseña(), id);
        int i = st.executeUpdate(sql);
        return i>0;
    }
    
    public List<Usuario> obtenerTodos() throws SQLException{
        ResultSet rs = st.executeQuery("SELECT id, rut, contraseña FROM usuario");
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        while (rs.next()) {
            final Usuario usuario = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3));
            listaUsuarios.add(usuario);
        }
        return listaUsuarios;
    }
    
    public Usuario obtener(int id) throws SQLException{
        String sql = String.format("SELECT id, rut, contraseña FROM usuario WHERE id= %s", id);
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        final Usuario usuario = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3));
        return usuario;
    }
}
