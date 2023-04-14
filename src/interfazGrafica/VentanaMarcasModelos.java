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

import Entidades.ConexionMarcas;
import Entidades.ListaMarcas;
import Entidades.Marca;

@SuppressWarnings("serial")
public class VentanaMarcasModelos extends JFrame {

	private JPanel contentPane;
	private JTable table;



	/**
	 * Create the frame.
	 */
	public VentanaMarcasModelos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(13)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(155, Short.MAX_VALUE))
		);
		
		DefaultTableModel modelo = new DefaultTableModel();
		table = new JTable(modelo);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Marca ID", "Marca", "ID Modelo", "Modelo"
			}
		));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		ConexionMarcas conexion = new ConexionMarcas();
		ListaMarcas lista = null;
		try {
			lista = conexion.obtener_Marcas();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		for(int i=0;i<lista.size();i++)
		{
			String []info = new String[4];
			Marca marca = lista.obtener(i);
			
			info[0] = "" + marca.id();
			info[1] = marca.marca();
			info[2] = "" + marca.modelo().id();
			info[3] = "" + marca.modelo().modelo();
		
			((DefaultTableModel)table.getModel()).addRow(info);
			
		}
		
	}
}
