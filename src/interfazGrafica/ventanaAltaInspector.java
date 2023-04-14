package interfazGrafica;
import Entidades.Validar;
import Entidades.Inspector;
import Entidades.ConexionInspector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class ventanaAltaInspector extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textEdad;
	private JTextField textDni;
	private JTextField textCelular;


	/**
	 * Create the frame.
	 */
	public ventanaAltaInspector() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
				
				ventanaPrincipal.setVisible(true);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 240, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textNombre = new JTextField();
		textNombre.setBounds(10, 27, 86, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		final JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(10, 106, 46, 14);
		contentPane.add(lblEdad);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(10, 75, 86, 20);
		contentPane.add(textApellido);
		
		JLabel lblApellido_1 = new JLabel("Apellido");
		lblApellido_1.setBounds(10, 58, 46, 14);
		contentPane.add(lblApellido_1);
		
		textEdad = new JTextField();
		textEdad.setColumns(10);
		textEdad.setBounds(10, 119, 86, 20);
		contentPane.add(textEdad);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(106, 11, 46, 14);
		contentPane.add(lblDni);
		
		textDni = new JTextField();
		textDni.setColumns(10);
		textDni.setBounds(106, 27, 86, 20);
		contentPane.add(textDni);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setBounds(105, 58, 46, 14);
		contentPane.add(lblCelular);
		
		textCelular = new JTextField();
		textCelular.setColumns(10);
		textCelular.setBounds(106, 75, 86, 20);
		contentPane.add(textCelular);
		
		JButton btnDarAltaInspector = new JButton("Dar de Alta");
		btnDarAltaInspector.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
						
			
				
				if(textNombre.getText().length() != 0 && textApellido.getText().length() != 0)
				{
					if(Validar.soloEnteros(textEdad.getText()) && Validar.soloEnteros(textDni.getText()) && Validar.soloDobles(textCelular.getText()))
					{
						ConexionInspector conexion = new ConexionInspector();
						
						Inspector inspector = new Inspector
								(
									textNombre.getText(),
									textApellido.getText(),
									Integer.parseInt(textEdad.getText()),
									Integer.parseInt(textDni.getText()),
									Double.parseDouble(textCelular.getText())
								);
						
						try {
							
							conexion.insertarInspector(inspector);
							
						} 
						catch (SQLException e1) 
						{
							e1.printStackTrace();
						}
						
						//JOptionPane.showMessageDialog(null, inspector.apellido());
					}
					
				}

					//JOptionPane.showMessageDialog(null, "Error!!!!");
				
				
			}
		});

		btnDarAltaInspector.setBounds(106, 118, 89, 23);
		contentPane.add(btnDarAltaInspector);
	}
}
