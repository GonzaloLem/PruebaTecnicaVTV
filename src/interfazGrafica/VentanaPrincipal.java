package interfazGrafica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;




@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;

	
	
	
	

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		
	
		
	/*	ConexionVehiculo conexionVehiculo = new ConexionVehiculo();
		ConexionInspeccion conexionInspeccion = new ConexionInspeccion();
		
		Date fc = new Date();
		
		try {
			Inspeccion inspeccion = new Inspeccion
					(
							fc,
							"Apto",
							false,
							conexionVehiculo.obtener(5),
							1
					);
			
			conexionInspeccion.insertar(inspeccion, conexionVehiculo.obtener(5));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		setTitle("Prueba Tecnica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_Titulo1 = new JLabel("Verificaciones Tecnica Vehicular");
		lbl_Titulo1.setBounds(153, 11, 333, 44);
		lbl_Titulo1.setFont(new Font("Elephant", Font.PLAIN, 20));
		contentPane.add(lbl_Titulo1);
		
		JButton btnAltaInspector = new JButton("Alta Inspector");
		btnAltaInspector.setBounds(12, 60, 151, 43);
		btnAltaInspector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaAltaInspector altaInspector = new ventanaAltaInspector();
				
				altaInspector.setVisible(true);
				dispose();
				
			}
		});
		contentPane.add(btnAltaInspector);
		
		JButton btnAltaCliente = new JButton("Alta Cliente");
		btnAltaCliente.setBounds(173, 60, 138, 43);
		btnAltaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ventanaAltaCliente ventanaAltaCliente = new ventanaAltaCliente();
				ventanaAltaCliente.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnAltaCliente);
		
		JButton btnAltaVehiculo = new JButton("Alta Vehiculo");
		btnAltaVehiculo.setBounds(321, 60, 125, 43);
		btnAltaVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ventanaAltaVehiculo ventanaAltaVehiculo = new ventanaAltaVehiculo();
				ventanaAltaVehiculo.setVisible(true);
				dispose();
				
			}
		});
		contentPane.add(btnAltaVehiculo);
		
		JButton btnListarClientes = new JButton("Listar Clientes");
		btnListarClientes.setBounds(173, 107, 138, 44);
		btnListarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ventanaListarClientes VentanaListarclientes = new ventanaListarClientes();
				VentanaListarclientes.setVisible(true);
				dispose();
				
			}
		});
		
		JButton btnListarInspectores = new JButton("Listar Inspectores");
		btnListarInspectores.setBounds(12, 107, 151, 44);
		btnListarInspectores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaListarInspector listarInspectores = new ventanaListarInspector();
				
				listarInspectores.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnListarInspectores);
		contentPane.add(btnListarClientes);
		
		JButton btnListarInspecciones = new JButton("Listar Inspecciones");
		btnListarInspecciones.setBounds(456, 108, 125, 43);
		btnListarInspecciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ventanaListarInspecciones VentanaListarInspecciones = new ventanaListarInspecciones();
				VentanaListarInspecciones.setVisible(true);
				dispose();
				
			}
		});
		
		JButton btnRealizarInspeccion = new JButton("Realizar Inspeccion");
		btnRealizarInspeccion.setBounds(456, 59, 125, 44);
		btnRealizarInspeccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ventanaInspeccion ventanainspeccion = new ventanaInspeccion();
				
				ventanainspeccion.setVisible(true);
				dispose();
				
			}
		});
		contentPane.add(btnRealizarInspeccion);
		contentPane.add(btnListarInspecciones);
		
		JButton btnListarVehiculos = new JButton("Listar Vehiculos");
		btnListarVehiculos.setBounds(321, 107, 125, 44);
		btnListarVehiculos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ventanaListarVehiculos ventanaListarVehiculos = new ventanaListarVehiculos();
				
				ventanaListarVehiculos.setVisible(true);
				dispose();
				
			}
		});
		contentPane.add(btnListarVehiculos);
	}
}
