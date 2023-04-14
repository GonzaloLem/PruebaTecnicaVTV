package interfazGrafica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entidades.ConexioAuto;
import Entidades.ConexionInspeccion;
import Entidades.ConexionObservaciones;
import Entidades.ConexionVTV;
import Entidades.ConexionVehiculo;
import Entidades.ExepcionesInspeccion;
import Entidades.Inspeccion;
import Entidades.Observacion;
import Entidades.Validar;
import Entidades.VerificacionTecnicaVehicular;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class ventanaModificarInspeccion extends JFrame {

	private JPanel contentPane;
	private JTextField textFecha;
	private JTextField textIdVehiculo;
	private JTextField textIdInspector;
	private Inspeccion inspeccion;


	
	public ventanaModificarInspeccion() {
		this.inspeccion = null;
		this.inicializarComponentes();
	}
	
	public ventanaModificarInspeccion(Inspeccion inspeccion) {
		this.inspeccion = inspeccion;
		this.inicializarComponentes();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void inicializarComponentes()
	{
		setBounds(100, 100, 374, 276);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFecha = new JTextField();
		textFecha.setBounds(152, 88, 86, 20);
		contentPane.add(textFecha);
		textFecha.setColumns(10);
		
		JLabel lblTexto1 = new JLabel("Fecha");
		lblTexto1.setBounds(152, 68, 46, 14);
		contentPane.add(lblTexto1);
		
		
		final JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] {"Apto", "Condicional", "Rechazado"}));
		comboBoxEstado.setBounds(152, 35, 80, 22);
		contentPane.add(comboBoxEstado);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(152, 11, 46, 14);
		contentPane.add(lblEstado);
		
		JCheckBox chckbxExento = new JCheckBox("Exento");
		chckbxExento.setBounds(252, 144, 97, 23);
		contentPane.add(chckbxExento);
		
		textIdVehiculo = new JTextField();
		textIdVehiculo.setColumns(10);
		textIdVehiculo.setBounds(252, 88, 56, 20);
		contentPane.add(textIdVehiculo);
		
		textIdInspector = new JTextField();
		textIdInspector.setColumns(10);
		textIdInspector.setBounds(152, 145, 56, 20);
		contentPane.add(textIdInspector);
		
		JLabel lblIdVehiculo = new JLabel("ID Vehiculo");
		lblIdVehiculo.setBounds(242, 68, 80, 14);
		contentPane.add(lblIdVehiculo);
		
		JLabel lblIdInspector = new JLabel("ID Inspector");
		lblIdInspector.setBounds(152, 120, 73, 14);
		contentPane.add(lblIdInspector);
		
		final JComboBox comboBoxMediones = new JComboBox();
		comboBoxMediones.setModel(new DefaultComboBoxModel(new String[] {"Apto", "Condicional", "Rechazado"}));
		comboBoxMediones.setBounds(242, 35, 80, 22);
		contentPane.add(comboBoxMediones);
		
		JLabel lblMediciones = new JLabel("Mediciones");
		lblMediciones.setBounds(242, 11, 86, 14);
		contentPane.add(lblMediciones);
		
		final JEditorPane editorObservaciones = new JEditorPane();
		editorObservaciones.setBounds(10, 35, 132, 191);
		contentPane.add(editorObservaciones);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(21, 10, 121, 14);
		contentPane.add(lblObservaciones);
		
		JButton btnModificarInspeccion = new JButton("Modificar");
		btnModificarInspeccion.addActionListener(new ActionListener() {
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
						
						System.out.print(fechaActual);
						
						
						ExepcionesInspeccion exepciones = new ExepcionesInspeccion();
						
							exepciones.validarIdVehiculo(Integer.parseInt(textIdVehiculo.getText()));
							exepciones.validarIdInspector(Integer.parseInt(textIdInspector.getText()));
							
						Observacion observacion = new Observacion
						(
								inspeccion.inspeccion_observacion().id(),
								editorObservaciones.getText(),
								comboBoxEstado.getSelectedItem().toString(),
								Integer.parseInt(textIdInspector.getText())
								
						);
						
						//String mediciones = calcularMediciones();
						
						VerificacionTecnicaVehicular vtv = new VerificacionTecnicaVehicular(inspeccion.inspeccion_vtv().vtv_id(), observacion, comboBoxMediones.getSelectedItem().toString(), asignarEstado(observacion.aprobacion(), comboBoxEstado.getSelectedItem().toString()));
						
						
						Inspeccion inspecc= new Inspeccion
								(
										inspeccion.numero(),
										textFecha.getText(),
										vtv.estado(),
										vtv.exento(),
										conexionVehiculo.obtener(Integer.parseInt(textIdVehiculo.getText())),
										Integer.parseInt(textIdInspector.getText())
										
								);
						
						//modifi
						
						conexionObservaciones.modificar(observacion);
						conexionVTV.modificar(vtv, Integer.parseInt(textIdVehiculo.getText()));
						conexionInspeccion.insertar(inspecc, conexionaAuto.obtener(Integer.parseInt(textIdVehiculo.getText())));
						
					} catch (NumberFormatException | SQLException | ExepcionesInspeccion e1 ) {
						
						JOptionPane.showMessageDialog(null, e1.toString());
					}
					
					
				}
				
			}
		});
		btnModificarInspeccion.setBounds(149, 183, 169, 43);
		contentPane.add(btnModificarInspeccion);
		
			if(inspeccion != null)
			{
				editorObservaciones.setText(inspeccion.inspeccion_observacion().detalle());
				comboBoxEstado.setSelectedItem(inspeccion.estado());
				comboBoxMediones.setSelectedItem(inspeccion.inspeccion_vtv().mediciones());
				textFecha.setText(inspeccion.fecha());
				textIdVehiculo.setText(""+inspeccion.vehiculo().id());
				textIdInspector.setText(""+inspeccion.id_inspector());
				chckbxExento.setSelected(inspeccion.exento());
				
			}
		
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

	public Inspeccion inspeccion()
	{
		return this.inspeccion;
	}
	
}
