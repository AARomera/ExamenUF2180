package vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Centro;
import modelo.Departamento;
import net.miginfocom.swing.MigLayout;

public class VentanaMostrarDepartamentos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Controlador controlador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMostrarDepartamentos frame = new VentanaMostrarDepartamentos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaMostrarDepartamentos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[][grow][]"));
		
		JLabel lblNewLabel = new JLabel("Lista de departamentos: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel, "cell 0 0,aligny baseline");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 1,grow");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cod Departamento", "Cod Centro", "Tipo Dir", "Presupuesto", "Nombre"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnMostrar = new JButton("Mostrar");
		contentPane.add(btnMostrar, "cell 0 2,alignx center");
	}

	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
		
	}

	public void setListaDepartamentos(ArrayList<Departamento> listaDepartamentos) {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		for (Departamento d : listaDepartamentos) {
			Object fila [] = {
					d.getCodDepartamento(),
					d.getCodCentro(),
					d.getTipoDir(),
					d.getPresupuesto(),
					d.getNombre()
			};
			modelo.addRow(fila);
		}
		
	}

}
