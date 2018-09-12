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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import tallerjava.dao.CuentaDAO;
import tallerjava.modelo.Cuenta;
import tallerjava.modelo.Usuario;

/**
 *
 * @author ElPCdeClaudio
 */
public class VistaCrearCuentaBancaria extends JDialog{
    private final JRadioButton rbtCredito, rbtDebito;
    private final ButtonGroup btngTipo;
    private final JLabel lblDeposito;
    private final JButton btnCrear;
    private final JTextField txtDeposito;
    private final JPanel pnl1, pnlRadio;
    
    private final Usuario usuario;
    
    private CuentaCreadaListener cuentaCreadaListener;
    
    public VistaCrearCuentaBancaria(Usuario usuario){
        
        this.usuario = usuario;
        btnCrear = new JButton("Crear cuenta");
        lblDeposito = new JLabel("Deposito o prestamo");
        txtDeposito = new JTextField();
        
        btnCrear.addActionListener(accionCrearCuenta);
        
        rbtCredito = new JRadioButton("Credito");
        rbtDebito = new JRadioButton("Debito");
        btngTipo = new ButtonGroup();
        btngTipo.add(rbtCredito);
        btngTipo.add(rbtDebito);
        
        pnlRadio = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        pnlRadio.add(rbtCredito);
        pnlRadio.add(rbtDebito);
        
        pnl1 = new JPanel();
        pnl1.setLayout(new BoxLayout(pnl1, BoxLayout.Y_AXIS));
        pnl1.add(btnCrear);
        pnl1.add(pnlRadio);
        pnl1.add(lblDeposito);
        pnl1.add(txtDeposito);
        
        Container container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        
        container.add(pnl1);
        
        //Configuraciones de la vista
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        pack();
        setVisible(true);
        setTitle("Crear cuenta bancaria");
        
    }
    
    private final ActionListener accionCrearCuenta = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String tipo;
            if(rbtCredito.isSelected()){
                tipo = "credito";
            }else if (rbtDebito.isSelected()){
                tipo = "debito";
            }else{
                JOptionPane.showMessageDialog(null, "Debe seleccionar una opcion");
                return;
            }
            try{
                double saldo = Double.parseDouble(txtDeposito.getText());
                Cuenta cuenta = new Cuenta(tipo, saldo);
                CuentaDAO cuentaDao = new CuentaDAO();
                
                boolean resultado = cuentaDao.insertarCuenta(usuario.getId(), cuenta);
                if(resultado){
                    cuenta.setId(cuentaDao.idUltimaInsercion());
                    JOptionPane.showMessageDialog(null, "Cuenta creada con exito");
                    cuentaCreadaListener.onCuentaCreada(cuenta);
                }else{
                    JOptionPane.showMessageDialog(null, "Error al crear la cuenta");
                }
                
                
            }catch(NumberFormatException error){
                JOptionPane.showMessageDialog(null, "Debe ingresar un saldo valido");
            } catch (SQLException ex) {
                Logger.getLogger(VistaCrearCuentaBancaria.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    };
    
    public void setCuentaCreadaListener(CuentaCreadaListener cuentaCreadaListener){
        this.cuentaCreadaListener = cuentaCreadaListener;
    }
    
    public interface CuentaCreadaListener{
        void onCuentaCreada(Cuenta cuenta);
    }
}
