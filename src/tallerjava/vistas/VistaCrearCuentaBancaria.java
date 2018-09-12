/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerjava.vistas;

import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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
    
    
    public VistaCrearCuentaBancaria(){
        
        btnCrear = new JButton("Crear cuenta");
        lblDeposito = new JLabel("Deposito o prestamo");
        txtDeposito = new JTextField();
        
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
        setModal(true);
        
    }
}
