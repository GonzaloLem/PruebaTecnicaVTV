package interfazGrafica;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import Entidades.ConexionInspector;
import Entidades.Inspector;
import Entidades.Validar;

@SuppressWarnings("serial")
public class ventanaModificarInspector extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textEdad;
	private JTextField textDni;
	private JTextField textCelular;
	private Inspector inspector;

	public ventanaModificarInspector() {
		this.inicializar();
		this.inspector = null;
	}

	public ventanaModificarInspector(Inspector inspector) {
		
		
		this.inspector = inspector;
		
		this.inicializar();
	}
	
	public Inspector inspectr()
	{
		return this.inspector;
	}
	
	private void inicializar() {
		setBounds(100, 100, 220, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 49, 46, 14);
		contentPane.add(lblApellido);
		
		textNombre = new JTextField();
		textNombre.setBounds(10, 26, 86, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 46, 14);
		contentPane.add(lblNombre);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(10, 65, 86, 20);
		contentPane.add(textApellido);
		
		textEdad = new JTextField();
		textEdad.setColumns(10);
		textEdad.setBounds(10, 101, 86, 20);
		contentPane.add(textEdad);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(10, 85, 46, 14);
		contentPane.add(lblEdad);
		
		textDni = new JTextField();
		textDni.setColumns(10);
		textDni.setBounds(103, 26, 86, 20);
		contentPane.add(textDni);
		
		textCelular = new JTextField();
		textCelular.setColumns(10);
		textCelular.setBounds(103, 65, 86, 20);
		contentPane.add(textCelular);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(105, 11, 46, 14);
		contentPane.add(lblDni);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setBounds(105, 49, 46, 14);
		contentPane.add(lblCelular);
		
		
		
		
		JButton btnModificarInspector = new JButton("Modificar");
		btnModificarInspector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textNombre.getText().length() != 0 && textApellido.getText().length() != 0)
				{
					if(Validar.soloEnteros(textEdad.getText()) && Validar.soloEnteros(textDni.getText()) && Validar.soloDobles(textCelular.getText()))
					{
						ConexionInspector conexion = new ConexionInspector();
						
						Inspector inspec = new Inspector
								(
									inspector.id(),
									textNombre.getText(),
									textApellido.getText(),
									Integer.parseInt(textEdad.getText()),
									Integer.parseInt(textDni.getText()),
									Double.parseDouble(textCelular.getText()),
									inspector.inspecciones()
								);
						
						try {
							
							//JOptionPane.showMessageDialog(null, inspec.celular());
							conexion.modificar(inspec);
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
		
		btnModificarInspector.setBounds(106, 100, 83, 20);
		contentPane.add(btnModificarInspector);
		
		if(this.inspector != null)
		{
			textNombre.setText(this.inspector.nombre());
			textApellido.setText(this.inspector.apellido());
			textEdad.setText(""+this.inspector.edad());
			textDni.setText(""+this.inspector.dni());
			textCelular.setText(""+this.inspector.celular());
		}


		
		
	}
}
