package interfazGrafica;
import Entidades.Validar;
import Entidades.ConexionPersona;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Entidades.ListaPersonas;
import Entidades.ConexionInspector;
import Entidades.Inspector;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ventanaListarInspector extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textIdInspector;
	
	
	public ventanaListarInspector() {
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosed(WindowEvent e) {
				VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
				
				ventanaPrincipal.setVisible(true);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 759, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ConexionInspector conexionInspector = new ConexionInspector();
				
				if(textIdInspector.getText().length() > 0 && Validar.soloEnteros(textIdInspector.getText()))
				{
					try {
						if(conexionInspector.existe(Integer.parseInt(textIdInspector.getText())))
						{
							ventanaModificarInspector ventanaModifcarInspector = new ventanaModificarInspector(conexionInspector.obtener_inspector(Integer.parseInt(textIdInspector.getText())));
									
							ventanaModifcarInspector.setVisible(true);
							dispose();
							
							//JOptionPane.showMessageDialog(null, "" + conexionInspector.obtener_inspector(Integer.parseInt(textIdInspector.getText())).apellido());
							
						}
					} catch (NumberFormatException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ConexionPersona conexionPersona = new ConexionPersona();
				
				if(textIdInspector.getText().length() > 0 && Validar.soloEnteros(textIdInspector.getText()))
				{
					try {
						
						if(conexionPersona.existe(Integer.parseInt(textIdInspector.getText())))
						{
							conexionPersona.eliminar(Integer.parseInt(textIdInspector.getText()));
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		textIdInspector = new JTextField();
		textIdInspector.setColumns(10);
		
		JLabel lblTexto1 = new JLabel("     INGRESAR ID");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(300)
					.addComponent(btnModificar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEliminar)
					.addContainerGap(283, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(329)
							.addComponent(lblTexto1, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(321, Short.MAX_VALUE)
							.addComponent(textIdInspector, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)))
					.addGap(308))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 711, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(13)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTexto1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textIdInspector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnModificar)
						.addComponent(btnEliminar))
					.addGap(6))
		);
		
		DefaultTableModel modelo = new DefaultTableModel();
		table = new JTable(modelo);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Apellido", "Edad", "DNI", "Celular", "Inspecciones"
			}
		));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		ConexionInspector conexion = new ConexionInspector();
		ListaPersonas lista = null;
		try {
			lista = conexion.obtenerInspectores();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		for(int i=0;i<lista.size();i++)
		{
			String []info = new String[7];
			Inspector inspector = (Inspector)lista.obtener(i);
			
			info[0] = "" + inspector.id();
			info[1] = inspector.nombre();
			info[2] = inspector.apellido();
			info[3] = "" + inspector.edad();
			info[4] = "" + inspector.dni();
			info[5] = "" + inspector.celular();
			info[6] = "" + inspector.inspecciones();
			
			
			((DefaultTableModel)table.getModel()).addRow(info);
			
		}
	}
}
