package interfazGrafica;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import Entidades.Inspeccion;
import Entidades.ConexionInspeccion;
import Entidades.ConexionObservaciones;
import Entidades.ListaInspecciones;
import Entidades.Validar;
import Entidades.ConexionVTV;
import Entidades.ConexioAuto;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ventanaListarInspecciones extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnModificar;
	private JTextField textIdInspeccion;


	/**
	 * Create the frame.
	 */
	public ventanaListarInspecciones() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				
				VentanaPrincipal ventana = new VentanaPrincipal();
				
				ventana.setVisible(true);
				dispose();
				
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 856, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ConexionInspeccion conexionInspeccion = new ConexionInspeccion();
				
				if(textIdInspeccion.getText().length() > 0 && Validar.soloEnteros(textIdInspeccion.getText()))
				{
					ventanaModificarInspeccion ventanaModificar;
					try {
						ventanaModificar = new ventanaModificarInspeccion(conexionInspeccion.obtenerInspeccion(Integer.parseInt(textIdInspeccion.getText())));
						ventanaModificar.setVisible(true);
						
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					dispose();
				}
				
			}
		});
		
		textIdInspeccion = new JTextField();
		textIdInspeccion.setColumns(10);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textIdInspeccion.getText().length() > 0 && Validar.soloEnteros(textIdInspeccion.getText()))
				{
					
					try {
						
						ConexionInspeccion conexionInspeccion = new ConexionInspeccion();
						ConexionObservaciones conexionObservacion = new ConexionObservaciones();
						ConexionVTV conexionVTV = new ConexionVTV();
						ConexioAuto conexionAuto = new ConexioAuto();
						
						Inspeccion inspeccion = conexionInspeccion.obtenerInspeccion(Integer.parseInt(textIdInspeccion.getText()));
						
						conexionObservacion.eliminarObservacion(inspeccion.inspeccion_observacion().id());
						conexionVTV.eliminarVTV(inspeccion.inspeccion_vtv().vtv_id());
						conexionAuto.eliminarAuto(inspeccion.vehiculo().id());	
						conexionInspeccion.eliminar(inspeccion.numero());
						
						
					} catch (NumberFormatException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					//conexionObservacion.
					
				}			
			}
		});
		
		JLabel lblTexto1 = new JLabel("ID de la Inspeccion");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(335)
					.addComponent(btnModificar, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addGap(279))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(400, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTexto1, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
						.addComponent(textIdInspeccion, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
					.addGap(320))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 885, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblTexto1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textIdInspeccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnModificar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
		);
		
		DefaultTableModel modelo = new DefaultTableModel();
		table = new JTable(modelo);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Numero de Inspeccion", "Fecha", "Estado", "Exento", "ID Vehiculo", "ID Inspector", "ID VTV", "Mediciones", "Estado", "ID Observaciones", "Detalle"
			}
		));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		ConexionInspeccion conexion = new ConexionInspeccion();
		
		ListaInspecciones lista = null;
		try {
			lista = conexion.obtenerLista();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
				
		//JOptionPane.showMessageDialog(null, "" + lista.size());
		
		for(int i=0;i<lista.size();i++)
		{
			String []info = new String[11];
			Inspeccion inspeccion = lista.obtener(i);
			
			info[0] = "" + inspeccion.numero();
			info[1] = inspeccion.fecha();
			info[2] = inspeccion.estado();
			info[3] = "" + inspeccion.exento();
			info[4] = "" + inspeccion.vehiculo().id();
			info[5] = "" + inspeccion.id_inspector();
			info[6] = "" + inspeccion.inspeccion_vtv().vtv_id();
			info[7] = "" + inspeccion.inspeccion_vtv().mediciones();
			info[8] = "" + inspeccion.inspeccion_vtv().estado();
			info[9] = "" + inspeccion.inspeccion_observacion().id();
			info[10] = "" + inspeccion.inspeccion_observacion().detalle();
			
			
			((DefaultTableModel)table.getModel()).addRow(info);
			
		}
		
	}
}
