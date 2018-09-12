/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerjava.vistas;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import tallerjava.dao.CuentaDAO;
import tallerjava.modelo.Cuenta;
import tallerjava.modelo.Usuario;

/**
 *
 * @author ElPCdeClaudio
 */
public class VistaListaCuentas extends JFrame{
    private final JList<Object> listaCuentas;
    private final JLabel lblLista;
    private final JButton btnDepositarDinero, btnRetirarDinero, btnCrearCueta;
    private final JPanel pnllista, pnlbotones;
    private final Usuario usuario;
    
    private final DefaultListModel listModel;
    
    public VistaListaCuentas(Usuario usuario){
        super("Banco menu - Seleccionar cuenta");
        
        this.usuario = usuario;
        listModel = new DefaultListModel();
        listaCuentas = new JList<>(listModel);
        lblLista = new JLabel("Lista de cuentas");
        btnCrearCueta = new JButton("Crear cuenta");
        btnRetirarDinero = new JButton("Retirar dinero");
        btnDepositarDinero = new JButton("Depositar dinero");
        
        
        btnDepositarDinero.addActionListener(accionDepositarDinero);
        btnRetirarDinero.addActionListener(accionRetirarDinero);
        btnCrearCueta.addActionListener(accionCrearCuentaBancaria);
        
        listaCuentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaCuentas.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        JScrollPane listScroller = new JScrollPane(listaCuentas);
        listScroller.setPreferredSize(new Dimension(250, 400));
        
        pnllista = new JPanel();
        pnllista.setLayout(new BoxLayout(pnllista, BoxLayout.Y_AXIS));
        
        pnllista.add(lblLista);
        pnllista.add(listScroller);
        
        pnlbotones = new JPanel();
        pnlbotones.setLayout(new BoxLayout(pnlbotones, BoxLayout.Y_AXIS));
        
        pnlbotones.add(btnDepositarDinero);
        pnlbotones.add(btnRetirarDinero);
        pnlbotones.add(btnCrearCueta);
        
        
        Container container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        container.add(pnllista);
        container.add(pnlbotones);
        //Configuraciones del JFrame
        setLocationRelativeTo(null);
        cargarLista(usuario);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private final ActionListener accionCrearCuentaBancaria = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            VistaCrearCuentaBancaria vistaCrearCuenta = new VistaCrearCuentaBancaria(usuario);
            vistaCrearCuenta.setCuentaCreadaListener((Cuenta cuenta) -> {
                listModel.addElement(cuenta);
            });
        }
    };
    
    private final ActionListener accionRetirarDinero = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog("Dinero a retirar");
        }
    };
    
    private final ActionListener accionDepositarDinero = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog("Dinero a depositar");
        }
    };
    
    
    private void cargarLista(Usuario usuario){
        try {
            CuentaDAO cuentaDao = new CuentaDAO();
            List<Cuenta> cuentas = cuentaDao.obtenerCuentasPorUsuario(usuario.getId());
            for(Cuenta cuenta: cuentas){
                listModel.addElement(cuenta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaListaCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
}
