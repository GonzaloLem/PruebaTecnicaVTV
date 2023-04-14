package interfazGrafica;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Entidades.Auto;
import Entidades.ConexionVehiculo;
import Entidades.ConexioAuto;
import Entidades.ListaVehiculos;
import Entidades.Validar;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class ventanaListarVehiculos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnModificarVehiculo;
	private JButton btnEliminarVehiculo;
	private JTextField textIdVehiculo;
	private JLabel lblTexto1;



	/**
	 * Create the frame.
	 */
	public ventanaListarVehiculos() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				
				VentanaPrincipal ventana = new VentanaPrincipal();
				
				ventana.setVisible(true);
				
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 703, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnModificarVehiculo = new JButton("Modificar Vehiculo");
		btnModificarVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ConexionVehiculo conexion = new ConexionVehiculo();
				
				if(textIdVehiculo.getText().length() > 0 && Validar.soloEnteros(textIdVehiculo.getText()))
				{
					
					try {
						ventanaModificarVehiculos ventanaModificar = new ventanaModificarVehiculos(conexion.obtener(Integer.parseInt(textIdVehiculo.getText())));
						
						ventanaModificar.setVisible(true);
						dispose();
						
					} catch (NumberFormatException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				
			}
		});
		
		btnEliminarVehiculo = new JButton("Eliminar Vehiculo");
		btnEliminarVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textIdVehiculo.getText().length() > 0 && Validar.soloEnteros(textIdVehiculo.getText()))
				{
					ConexionVehiculo conexion = new ConexionVehiculo();
					
					try {
						
						
						conexion.eliminarVehiculo(Integer.parseInt(textIdVehiculo.getText()));
						dispose();
						
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		textIdVehiculo = new JTextField();
		textIdVehiculo.setColumns(10);
		
		lblTexto1 = new JLabel("ID Del Vehiculo");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(199, Short.MAX_VALUE)
					.addComponent(btnModificarVehiculo)
					.addGap(18)
					.addComponent(btnEliminarVehiculo)
					.addGap(232))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(260)
					.addComponent(textIdVehiculo)
					.addGap(261))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(292, Short.MAX_VALUE)
					.addComponent(lblTexto1, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addGap(280))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(lblTexto1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textIdVehiculo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnModificarVehiculo)
						.addComponent(btnEliminarVehiculo)))
		);
		
		DefaultTableModel modelo = new DefaultTableModel();
		table = new JTable(modelo);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Dominio", "Marca", "Modelo", "ID Propietario", "Propietario", "Tiene alguna VTV"
			}
		));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		ConexionVehiculo conexion = new ConexionVehiculo();
		ConexioAuto conexionAuto = new ConexioAuto();
		
		ListaVehiculos lista = null;
		try {
			lista = conexion.obtenerLista();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<lista.size();i++)
		{
			String []info = new String[7];
			Auto auto = (Auto)lista.obtener(i);		

			try {
				info[0] = "" + auto.id();
				info[1] = auto.dominio();
				info[2] = auto.marca().marca();
				info[3] = "" + auto.marca().modelo().modelo();
				info[4] = "" + auto.id_propietario();
				info[5] = "" + auto.nombre_propietario();
				info[6] = this.conversor(conexionAuto.existe(auto.id()));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			((DefaultTableModel)table.getModel()).addRow(info);
			
		}
		
		
	}
	
	public String conversor(boolean valor)
	{
		String retorno = "No";
		
			if(valor == true)
			{
				retorno = "Si";
			}
		
		return retorno;
	}

}
