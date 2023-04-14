package interfazGrafica;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entidades.Validar;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import Entidades.Cliente;
import Entidades.ConexionCliente;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class ventanaAltaCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textEdad;
	private JTextField textEmail;
	private JTextField textApellido;
	private JTextField textDni;



	/**
	 * Create the frame.
	 */
	public ventanaAltaCliente() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				
				VentanaPrincipal ventana = new VentanaPrincipal();
						ventana.setVisible(true);
				
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 256, 177);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textNombre = new JTextField();
		textNombre.setBounds(10, 25, 86, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textEdad = new JTextField();
		textEdad.setColumns(10);
		textEdad.setBounds(10, 68, 86, 20);
		contentPane.add(textEdad);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(106, 68, 124, 20);
		contentPane.add(textEmail);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(106, 25, 124, 20);
		contentPane.add(textApellido);
		
		textDni = new JTextField();
		textDni.setColumns(10);
		textDni.setBounds(10, 111, 86, 20);
		contentPane.add(textDni);
		
		JLabel lblNewLabel = new JLabel("Edad");
		lblNewLabel.setBounds(10, 50, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(106, 50, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(10, 98, 46, 14);
		contentPane.add(lblDni);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(106, 11, 46, 14);
		contentPane.add(lblApellido);
		
		JButton btnAltaCliente = new JButton("Alta Cliente");
		btnAltaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textNombre.getText().length() != 0 && textApellido.getText().length() != 0 && textEmail.getText().length() > 0)
				{
					if(Validar.soloEnteros(textEdad.getText()) && Validar.soloEnteros(textDni.getText()))
					{
			
						try {
							
							ConexionCliente conexion = new ConexionCliente();
							
							Cliente cliente = new Cliente
									(
										textNombre.getText(),
										textApellido.getText(),
										Integer.parseInt(textEdad.getText()),
										Integer.parseInt(textDni.getText()),
										textEmail.getText()
									);
							
							conexion.insertarCliente(cliente);
							dispose();
							
						} 
						catch (SQLException e1) 
						{
							e1.printStackTrace();
						}
						
						//JOptionPane.showMessageDialog(null, inspector.apellido());
					}
					
				}
				
			}
		});
		btnAltaCliente.setBounds(106, 110, 124, 23);
		contentPane.add(btnAltaCliente);
	}

}
