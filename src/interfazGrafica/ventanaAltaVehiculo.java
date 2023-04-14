package interfazGrafica;

import Entidades.Validar;
import Entidades.Auto;
import Entidades.ConexionMarcas;
import Entidades.ConexionVehiculo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class ventanaAltaVehiculo extends JFrame {

	private JPanel contentPane;
	private JTextField textPatente;
	private JTextField textNombrePropietario;
	private JTextField textIdPropietario;
	private JTextField textModeloId;



	/**
	 * Create the frame.
	 */
	public ventanaAltaVehiculo() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				
				VentanaPrincipal ventana = new VentanaPrincipal();
				ventana.setVisible(true);
				dispose();
				
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 273, 188);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPatente = new JLabel("Patente");
		lblPatente.setBounds(106, 51, 46, 14);
		contentPane.add(lblPatente);
		
		textPatente = new JTextField();
		textPatente.setBounds(106, 69, 135, 20);
		contentPane.add(textPatente);
		textPatente.setColumns(10);
		
		textNombrePropietario = new JTextField();
		textNombrePropietario.setColumns(10);
		textNombrePropietario.setBounds(106, 27, 135, 20);
		contentPane.add(textNombrePropietario);
		
		JLabel lblNombreDelPropietario = new JLabel("Nombre del Propietario");
		lblNombreDelPropietario.setBounds(106, 11, 156, 14);
		contentPane.add(lblNombreDelPropietario);
		
		textIdPropietario = new JTextField();
		textIdPropietario.setColumns(10);
		textIdPropietario.setBounds(5, 27, 91, 20);
		contentPane.add(textIdPropietario);
		
		textModeloId = new JTextField();
		textModeloId.setColumns(10);
		textModeloId.setBounds(5, 69, 86, 20);
		contentPane.add(textModeloId);
		
		JLabel lblIdDelPropietario = new JLabel("ID Del Propietario");
		lblIdDelPropietario.setBounds(10, 11, 103, 14);
		contentPane.add(lblIdDelPropietario);
		
		JButton btnAltaVehiculo = new JButton("Dar de Alta");
		btnAltaVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//ConexionVehiculo conexionVehiculo = new ConexionVehiculo();
				ConexionMarcas conexionMarcas = new ConexionMarcas();
				ConexionVehiculo conexionAuto = new ConexionVehiculo();
				
				if(textNombrePropietario.getText().length() > 0 && textPatente.getText().length() > 0)
				{
					if(Validar.soloEnteros(textIdPropietario.getText()) && Validar.soloEnteros(textModeloId.getText()))
					{
							try {
								
								Auto auto = new Auto
										(
												textPatente.getText(),
												conexionMarcas.obtener(Integer.parseInt(textModeloId.getText())),
												textNombrePropietario.getText(),
												Integer.parseInt(textIdPropietario.getText())		
										);
								
								conexionAuto.insertar(auto);
								dispose();
								
							} catch (NumberFormatException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						//conexionVehiculo.insertarVehiculo(vehiculo);
					}
				}
				
			}
		});
		btnAltaVehiculo.setBounds(5, 123, 231, 23);
		contentPane.add(btnAltaVehiculo);
		
		JButton btnListarMarcasModelos = new JButton("Listar Marcas y Modelos");
		btnListarMarcasModelos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaMarcasModelos ventanaMarcasModelos = new VentanaMarcasModelos();
				ventanaMarcasModelos.setVisible(true);
				
			}
		});
		btnListarMarcasModelos.setBounds(5, 100, 236, 20);
		contentPane.add(btnListarMarcasModelos);
		

		
		JLabel lblModeloid = new JLabel("Modelo(ID)");
		lblModeloid.setBounds(15, 51, 68, 14);
		contentPane.add(lblModeloid);
	}

}
