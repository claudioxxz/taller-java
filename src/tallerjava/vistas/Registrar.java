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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import tallerjava.dao.UsuarioDao;
import tallerjava.dao.UsuarioDetalleDao;
import tallerjava.modelo.Usuario;
import tallerjava.modelo.UsuarioDetalle;

/**
 *
 * @author Javier Ortiz
 */
public class Registrar extends JDialog implements ActionListener{
    
    private JLabel labelNombre, labelApellido, labelEmail, labelRut, labelContraseña, labelConfirmar;
    private JTextField textNombre, textApellido, textEmail, textRut;
    private JPasswordField passContrasena, passConfirmar;
    private JButton btnCrearUsuario, btnCancelar;
    private JPanel panNombreApellido, panEmailRut, panContrasena, panBotones;
    
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
        pack();
        setSize(600, 400);
        setVisible(true);
        setTitle("Registrar");
        setModal(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(btnCrearUsuario == e.getSource()){
            if(String.valueOf(passContrasena.getPassword()).equals(String.valueOf(passConfirmar.getPassword()))){
                boolean textFieldState = estaVacio();
                if(textFieldState == true){
                    if(textEmail.getText().matches(EMAIL_PATTERN)){
                        Usuario usuario = new Usuario(textRut.getText(), String.valueOf(passContrasena.getPassword()));
                        try{
                            UsuarioDao db = new UsuarioDao();
                            boolean resultado = db.insertar(usuario);
                            if(resultado){
                                int id = db.idUltimaInsercion();
                                UsuarioDetalle detalle = new UsuarioDetalle(id, textNombre.getText(), textApellido.getText(), textEmail.getText());
                                UsuarioDetalleDao uddb = new UsuarioDetalleDao();
                                resultado = uddb.insertar(detalle);
                                if(resultado){
                                    JOptionPane.showMessageDialog(null, "Usuario creado correctamente");
                                    limpiar();
                                }
                            }
                        }catch(SQLException error){
                            JOptionPane.showMessageDialog(null, "error al crear usuario");
                            System.out.println(error.getMessage());
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "El Email esta en un formato incorrecto");
                        textEmail.requestFocus();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos para crear un usuario");
                    textNombre.requestFocus();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Las contraseñas ingresadas deben ser iguales");
                passContrasena.setText("");
                passConfirmar.setText("");
                passContrasena.requestFocus();
            }
        }
        if(btnCancelar == e.getSource()){
            limpiar();
        }
    }
        
    public void limpiar(){
        textNombre.setText("");
        textApellido.setText("");
        textEmail.setText("");
        textRut.setText("");
        passContrasena.setText("");
        passConfirmar.setText("");
        textNombre.requestFocus();
    }
    
    private boolean estaVacio(){
        boolean resultado = false;
        if(textNombre.getText().isEmpty() && textApellido.getText().isEmpty() && textEmail.getText().isEmpty() && textRut.getText().isEmpty() 
                && String.valueOf(passContrasena.getPassword()).isEmpty() && String.valueOf(passConfirmar.getPassword()).isEmpty()){
            resultado = false;
        }else{
            resultado = true;
        }
        return resultado;
    }
    
    private void setGridLayout(){
        setLayout(new GridLayout(4, 1));
        add(panNombreApellido);
        add(panEmailRut);
        add(panContrasena);
        add(panBotones);
    }
    private void setLayouts(){
        panNombreApellido.setLayout(new FlowLayout(FlowLayout.LEADING, 45, 10));
        panNombreApellido.add(labelNombre);
        panNombreApellido.add(textNombre);
        panNombreApellido.add(labelApellido);
        panNombreApellido.add(textApellido);
        panEmailRut.setLayout(new FlowLayout(FlowLayout.LEADING, 50, 10));
        panEmailRut.add(labelEmail);
        panEmailRut.add(textEmail);
        panEmailRut.add(labelRut);
        panEmailRut.add(textRut);
        panContrasena.setLayout(new FlowLayout(FlowLayout.LEADING, 40, 10));
        panContrasena.add(labelContraseña);
        panContrasena.add(passContrasena);
        panContrasena.add(labelConfirmar);
        panContrasena.add(passConfirmar);
        panBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
        panBotones.add(btnCrearUsuario);
        panBotones.add(btnCancelar);        
    }
    private void setJTextFields(){
        textNombre = new JTextField("", 10);
        textApellido = new JTextField("", 10);
        textEmail = new JTextField("", 10);
        textRut = new JTextField("", 10);
    }
    private void setJLabels(){
        labelNombre = new JLabel("Nombre");
        labelApellido = new JLabel("Apellido");
        labelEmail = new JLabel("Email");
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
        panNombreApellido = new JPanel();
        panEmailRut = new JPanel();
        panContrasena = new JPanel();
        panBotones = new JPanel();
    }

    
}
