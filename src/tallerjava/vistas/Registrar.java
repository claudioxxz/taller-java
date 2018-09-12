/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerjava.vistas;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
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
 * @author Javier Ortiz
 */
public class Registrar extends JDialog implements ActionListener{
    
    private JLabel labelRut, labelContraseña, labelConfirmar;
    private JTextField textRut;
    private JPasswordField passContrasena, passConfirmar;
    private JButton btnCrearUsuario, btnCancelar;
    private JPanel panEmailRut, panBotones;
    
    private static final String EMAIL_PATTERN = 
    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
   
    public Registrar(){
        setJLabels();
        setJTextFields();
        setJPasswordField();
        setJButton();
        setJPanel();
        setLayouts();
        setGridLayout();
        btnCrearUsuario.addActionListener(this);
        btnCancelar.addActionListener(this);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
        setTitle("Registrar");
        pack();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(btnCrearUsuario == e.getSource()){
            if(!validarPass()){
                JOptionPane.showMessageDialog(this, "Las contraseñas ingresadas deben ser iguales");
                passContrasena.setText("");
                passConfirmar.setText("");
                passContrasena.requestFocus();
                return;
            }
            if(estaVacio()){
                JOptionPane.showMessageDialog(this, "Debe rellenar todos los campos para crear un usuario");
                textRut.requestFocus();
                return;
            }
            
            Usuario usuario = new Usuario(textRut.getText(), String.valueOf(passContrasena.getPassword()));
            try{
                UsuarioDao db = new UsuarioDao();
                boolean resultado = db.insertar(usuario);
                if(resultado){
                    JOptionPane.showMessageDialog(this, "Usuario creado correctamente");
                    limpiar();
                }
            }catch(SQLException error){
                JOptionPane.showMessageDialog(this, "error al crear usuario");
                System.out.println(error.getMessage());
            }
        }
        if(btnCancelar == e.getSource()){
            limpiar();
        }
    }
    
    public boolean validarPass(){
        return String.valueOf(passContrasena.getPassword()).equals(String.valueOf(passConfirmar.getPassword()));
    }
        
    public void limpiar(){
        textRut.setText("");
        passContrasena.setText("");
        passConfirmar.setText("");
        textRut.requestFocus();
    }
    
    private boolean estaVacio(){
        return textRut.getText().isEmpty() 
                && String.valueOf(passContrasena.getPassword()).isEmpty() 
                && String.valueOf(passConfirmar.getPassword()).isEmpty();
    }
    
    private void setGridLayout(){
        setLayout(new GridLayout(4, 1));
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        add(panEmailRut);
    }
    private void setLayouts(){
        panEmailRut.setLayout(new BoxLayout(panEmailRut,BoxLayout.Y_AXIS));
        panEmailRut.add(labelRut);
        panEmailRut.add(textRut);
        panEmailRut.add(labelContraseña);
        panEmailRut.add(passContrasena);
        panEmailRut.add(labelConfirmar);
        panEmailRut.add(passConfirmar);
                panEmailRut.add(panBotones);

        panBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panBotones.add(btnCrearUsuario);
        panBotones.add(btnCancelar);      
    }
    private void setJTextFields(){
        textRut = new JTextField("", 10);
    }
    private void setJLabels(){
        labelRut = new JLabel("Rut");
        labelContraseña = new JLabel("Contraseña");
        labelConfirmar = new JLabel("Confirmar Contraseña");
    }
    private void setJPasswordField(){
        passContrasena = new JPasswordField("", 10);
        passConfirmar = new JPasswordField("", 10);
    }
    private void setJButton(){
        btnCrearUsuario = new JButton("Crear Usuario");
        btnCancelar = new JButton("Cancelar");
    }
    private void setJPanel(){
        panEmailRut = new JPanel();
        panBotones = new JPanel();
    }

    
}
