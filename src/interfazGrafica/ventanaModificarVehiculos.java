package interfazGrafica;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entidades.Auto;
import Entidades.Vehiculo;
import Entidades.ConexionMarcas;
import Entidades.Validar;
import Entidades.ConexionVehiculo;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ventanaModificarVehiculos extends JFrame {

	private JPanel contentPane;
	private JTextField textIdPropietario;
	private JTextField textNombrePropietario;
	private JTextField textIdModelo;
	private JTextField textPatente;
	private Vehiculo vehiculo;

	public ventanaModificarVehiculos() {
		this.vehiculo = null;
		this.iniciarlizarComponentes();
	}
	
	public ventanaModificarVehiculos(Vehiculo vehiculo)
	{
		this.vehiculo = vehiculo;
		this.iniciarlizarComponentes();
	}
	
	public void iniciarlizarComponentes()
	{
		setBounds(100, 100, 268, 228);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textIdPropietario = new JTextField();
		textIdPropietario.setBounds(10, 36, 101, 20);
		contentPane.add(textIdPropietario);
		textIdPropietario.setColumns(10);
		
		textNombrePropietario = new JTextField();
		textNombrePropietario.setColumns(10);
		textNombrePropietario.setBounds(121, 36, 112, 20);
		contentPane.add(textNombrePropietario);
		
		textIdModelo = new JTextField();
		textIdModelo.setColumns(10);
		textIdModelo.setBounds(10, 92, 101, 20);
		contentPane.add(textIdModelo);
		
		textPatente = new JTextField();
		textPatente.setColumns(10);
		textPatente.setBounds(121, 92, 112, 20);
		contentPane.add(textPatente);
		
		JLabel lblTexto1 = new JLabel("ID del Propietario");
		lblTexto1.setBounds(10, 11, 112, 14);
		contentPane.add(lblTexto1);
		
		JLabel lblNombreDelPropietario = new JLabel("Nombre del Propietario");
		lblNombreDelPropietario.setBounds(121, 11, 133, 14);
		contentPane.add(lblNombreDelPropietario);
		
		JLabel lblMarcaid = new JLabel("Modelo(ID)");
		lblMarcaid.setBounds(10, 67, 76, 14);
		contentPane.add(lblMarcaid);
		
		JLabel lblPatente = new JLabel("Patente");
		lblPatente.setBounds(121, 67, 46, 14);
		contentPane.add(lblPatente);
		
		JButton btnNewButton = new JButton("Listar Marcas y Modelos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaMarcasModelos ventanaMarcasModelos = new VentanaMarcasModelos();
				ventanaMarcasModelos.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(10, 123, 223, 23);
		contentPane.add(btnNewButton);
		
		JButton btnModificarVehiculo = new JButton("Modificar Vehiculo");
		btnModificarVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				ConexionMarcas conexionMarcas = new ConexionMarcas();
				ConexionVehiculo conexionVehiculo = new ConexionVehiculo();
				
				if(textNombrePropietario.getText().length() > 0 && textPatente.getText().length() > 0)
				{
					if(Validar.soloEnteros(textIdPropietario.getText()) && Validar.soloEnteros(textIdModelo.getText()))
					{
						
							try {
								
								Auto auto = new Auto
										(
												vehiculo.id(),
												textPatente.getText(),
												conexionMarcas.obtener(Integer.parseInt(textIdModelo.getText())),
												textNombrePropietario.getText(),
												Integer.parseInt(textIdPropietario.getText())		
										);
								
								conexionVehiculo.modificar(auto);
								VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
								ventanaPrincipal.setVisible(true);
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
		btnModificarVehiculo.setBounds(10, 157, 223, 23);
		contentPane.add(btnModificarVehiculo);
		
		
		if(this.vehiculo != null)
		{
			textIdPropietario.setText("" + this.vehiculo.id_propietario());
			textNombrePropietario.setText(this.vehiculo.nombre_propietario());
			textIdModelo.setText(""+this.vehiculo.marca().modelo().id());
			textPatente.setText(this.vehiculo.dominio());
		}
		
	}
	
	public Vehiculo vehiculo()
	{
		return this.vehiculo;
	}

}
