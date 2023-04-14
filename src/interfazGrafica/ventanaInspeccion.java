package interfazGrafica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import Entidades.Validar;
import Entidades.ConexioAuto;
import Entidades.ConexionInspeccion;
import Entidades.ExepcionesInspeccion;
import javax.swing.JEditorPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Entidades.ConexionObservaciones;
import Entidades.ConexionVTV;
import Entidades.ConexionVehiculo;
import Entidades.Observacion;
import Entidades.Inspeccion;
import Entidades.VerificacionTecnicaVehicular;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.util.Date;

import Entidades.Auto;

@SuppressWarnings("serial")
public class ventanaInspeccion extends JFrame {

	private JPanel contentPane;
	private JTextField textIdInspector;
	private JTextField textIdVehiculo;

	


	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ventanaInspeccion() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				
				VentanaPrincipal ventana = new VentanaPrincipal();
				ventana.setVisible(true);
				
			}
		});
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 364, 218);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textIdInspector = new JTextField();
		textIdInspector.setBounds(164, 25, 129, 20);
		contentPane.add(textIdInspector);
		textIdInspector.setColumns(10);
		
		textIdVehiculo = new JTextField();
		textIdVehiculo.setColumns(10);
		textIdVehiculo.setBounds(164, 81, 129, 20);
		contentPane.add(textIdVehiculo);
		
		JLabel lblIdInspector = new JLabel("ID Del Inspector");
		lblIdInspector.setBounds(195, 11, 86, 14);
		contentPane.add(lblIdInspector);
		
		JLabel lblIdDelVehiculo = new JLabel("ID Del Vehiculo");
		lblIdDelVehiculo.setBounds(195, 56, 86, 14);
		contentPane.add(lblIdDelVehiculo);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(41, 11, 86, 14);
		contentPane.add(lblObservaciones);
		
		final JEditorPane editorObservaciones = new JEditorPane();
		editorObservaciones.setBounds(10, 25, 144, 136);
		contentPane.add(editorObservaciones);
		
		final JComboBox<?> comboBoxEstado = new JComboBox();
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] {"Apto", "Condicional", "Rechazado"}));
		comboBoxEstado.setBounds(164, 112, 129, 22);
		contentPane.add(comboBoxEstado);
		
		JButton btnTerminarInspeccion = new JButton("Terminar Inspeccion");
		btnTerminarInspeccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textIdVehiculo.getText().length() > 0 && Validar.soloEnteros(textIdVehiculo.getText()) && textIdInspector.getText().length() > 0 && Validar.soloEnteros(textIdInspector.getText()))
				{
				
					
					ConexionVehiculo conexionVehiculo = new ConexionVehiculo();
					ConexioAuto conexionaAuto = new ConexioAuto();
					//ConexionInspector conexionInspector = new ConexionInspector();
					ConexionObservaciones conexionObservaciones = new ConexionObservaciones();
					ConexionVTV conexionVTV = new ConexionVTV();
					ConexionInspeccion conexionInspeccion = new ConexionInspeccion();

					
					try {
											
						Date fechaActual = new Date();
						
						///
						//System.out.print(comboBoxEstado.getSelectedItem().toString());
						
						
						ExepcionesInspeccion exepciones = new ExepcionesInspeccion();
						
							exepciones.validarIdVehiculo(Integer.parseInt(textIdVehiculo.getText()));
							exepciones.validarIdInspector(Integer.parseInt(textIdInspector.getText()));
							exepciones.validarVTVAuto(Integer.parseInt(textIdVehiculo.getText()));
							
						Observacion observacion = new Observacion
						(
								editorObservaciones.getText(),
								comboBoxEstado.getSelectedItem().toString(),
								Integer.parseInt(textIdInspector.getText())
								
						);
						
						String mediciones = calcularMediciones();
						
						VerificacionTecnicaVehicular vtv = new VerificacionTecnicaVehicular(observacion, mediciones, asignarEstado(observacion.aprobacion(), mediciones));
						
						
						Inspeccion inspeccion = new Inspeccion
								(
										fechaActual.toString(),
										vtv.estado(),
										vtv.exento(),
										conexionVehiculo.obtener(Integer.parseInt(textIdVehiculo.getText())),
										Integer.parseInt(textIdInspector.getText())
										
								);
						
						//inserts
						
						conexionObservaciones.insertar(observacion);
						conexionVTV.insertar(vtv, Integer.parseInt(textIdVehiculo.getText()));
						conexionaAuto.insertar((Auto)conexionaAuto.obtener(Integer.parseInt(textIdVehiculo.getText())));
						conexionInspeccion.insertar(inspeccion, conexionaAuto.obtener(Integer.parseInt(textIdVehiculo.getText())));
				
						
						
					} catch (NumberFormatException | SQLException | ExepcionesInspeccion e1 ) {
						
						JOptionPane.showMessageDialog(null, e1.toString());
					}
					
					
				}
				
				
			}
		});
		btnTerminarInspeccion.setBounds(164, 138, 129, 23);
		contentPane.add(btnTerminarInspeccion);
		

		

	}
	
	public String calcularMediciones()
	{
		
		String retorno = "Rechazado";
		int numero = (int)(Math.random() * 100);
		
			if(numero > 30 && numero < 60)
			{
				retorno = "Apto";
			}
			else if(numero > 60 && numero < 100)
			{
				retorno = "Condicional";
			}
			
		
		return retorno;
	}
	
	public String asignarEstado(String estadoInspector, String estadoMedicion)
	{
		String retorno = "Rechazado";
		
		if(estadoInspector == "Rechazado" || estadoMedicion == "Rechazado")
		{
			retorno = "Rechazado";
		}
		else if(estadoInspector == "Condicional" || estadoMedicion == "Condicional")
		{
			retorno = "Condicional";
		}
		else if(estadoInspector == "Apto" && estadoMedicion == "Apto")
		{
			retorno = "Apto";
		}
		
		return retorno;
	}

}
