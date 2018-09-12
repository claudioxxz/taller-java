/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerjava.vistas;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import tallerjava.dao.UsuarioDao;
import tallerjava.modelo.Usuario;

/**
 *
 * @author ElPCdeClaudio
 */
public class VistaIngreso extends JFrame{
    
    private final JTextField txtRut;
    private final JPasswordField txtPass;
    private final JButton btnIniciarSesion, btnCrearCuenta;
    private final JPanel pnlIngreso, pnlCrear;
    private final JLabel lblRut, lblPass;
    
    public VistaIngreso(){
        super("Ingreso");
        txtRut = new JTextField(20);
        txtPass = new JPasswordField(20);
        btnIniciarSesion = new JButton("Inicia Sesion");
        btnCrearCuenta = new JButton("Crear cuenta");
        lblRut = new JLabel("Rut");
        lblPass = new JLabel("Contraseña");
        
        pnlIngreso = new JPanel();
        pnlCrear = new JPanel();
        
        btnIniciarSesion.addActionListener(accionIngreso);
        btnCrearCuenta.addActionListener(accionCrearCuenta);
                
        pnlIngreso.setLayout(new BoxLayout(pnlIngreso, BoxLayout.Y_AXIS));
        
        pnlIngreso.add(lblRut);
        pnlIngreso.add(txtRut);
        
        pnlIngreso.add(lblPass);
        pnlIngreso.add(txtPass);
        pnlIngreso.add(btnIniciarSesion);
        
        pnlCrear.add(btnCrearCuenta);
        
        Container container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        container.add(pnlIngreso);
        container.add(pnlCrear);
        
        //Configuraciones del JFrame
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    private final ActionListener accionIngreso = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String rut = txtRut.getText();
                String pass = String.valueOf(txtPass.getPassword());
                Usuario usuario = new Usuario(rut, pass);
                UsuarioDao usuarioDao = new UsuarioDao();
                
                if(usuarioDao.verificarUsuario(usuario)){
                    JOptionPane.showMessageDialog(null, "Usuario correcto");
                    VistaListaCuentas vistaListaCuentas = new VistaListaCuentas(usuario);
                    
                    setVisible(false);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Usuario incorrecto o no existe", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(VistaIngreso.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    };
    
    
    private final ActionListener accionCrearCuenta = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Registrar reg = new Registrar();
        }
    };
}
