package interfazGrafica;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Entidades.ConexionCliente;
import Entidades.Cliente;
import Entidades.ListaPersonas;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class ventanaListarClientes extends JFrame {

	private JPanel contentPane;
	private JTable table;

	
	
	public ventanaListarClientes() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {			


			@Override
			public void windowClosed(WindowEvent e) {
				VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
				ventanaPrincipal.setVisible(true);
			}
		});
		setBounds(100, 100, 577, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
		);
		
		DefaultTableModel modelo = new DefaultTableModel();
		table = new JTable(modelo);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Apellido", "Edad", "DNI", "Email"
			}
		));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		ConexionCliente conexion = new ConexionCliente();
		ListaPersonas lista = null;
		try {
			lista = conexion.obtenercliente();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		for(int i=0;i<lista.size();i++)
		{
			String []info = new String[6];
			Cliente cliente = (Cliente)lista.obtener(i);
			
			info[0] = "" + cliente.id();
			info[1] = cliente.nombre();
			info[2] = cliente.apellido();
			info[3] = "" + cliente.edad();
			info[4] = "" + cliente.dni();
			info[5] = "" + cliente.email();
			
			
			((DefaultTableModel)table.getModel()).addRow(info);
			
		}
		
	}
}
