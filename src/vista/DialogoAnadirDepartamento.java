package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import modelo.Centro;
import modelo.Departamento;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.ButtonGroup;

public class DialogoAnadirDepartamento extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtcodigo;
	private JTextField txtnombre;
	private Controlador controlador;
	private JSpinner spinner;
	private JComboBox comboBox;
	private JRadioButton rdbtnpropiedad;
	private JRadioButton rdbtnfunciones;
	private final ButtonGroup TipoDir = new ButtonGroup();


	/**
	 * Create the dialog.
	 */
	public DialogoAnadirDepartamento() {
		setTitle("Insertar departamento");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Detalles del centro", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			((TitledBorder)  panel.getBorder()).setTitleFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(panel, "cell 0 0,grow");
			panel.setLayout(new MigLayout("", "[][][grow][grow]", "[][grow][][grow][][grow][][grow][][grow]"));
			{
				JLabel lblNewLabel_3 = new JLabel("");
				lblNewLabel_3.setIcon(new ImageIcon(DialogoAnadirDepartamento.class.getResource("/images/editar32.png")));
				panel.add(lblNewLabel_3, "cell 0 0 1 9");
			}
			{
				JLabel lblNewLabel = new JLabel("C\u00F3digo:");
				panel.add(lblNewLabel, "cell 1 0,alignx trailing");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			{
				txtcodigo = new JTextField();
				panel.add(txtcodigo, "cell 2 0 2 1,growx");
				txtcodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
				txtcodigo.setColumns(10);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Centro: ");
				panel.add(lblNewLabel_2, "cell 1 2,alignx trailing");
			}
			{
				comboBox = new JComboBox();
				panel.add(comboBox, "cell 2 2 2 1,growx");
			}
			{
				JLabel lblNewLabel_4 = new JLabel("Tipo Dirección");
				panel.add(lblNewLabel_4, "cell 1 4");
			}
			{
				rdbtnpropiedad = new JRadioButton("Propiedad");
				rdbtnpropiedad.setSelected(true);
				TipoDir.add(rdbtnpropiedad);
				panel.add(rdbtnpropiedad, "cell 2 4");
			}
			{
				rdbtnfunciones = new JRadioButton("En funciones");
				TipoDir.add(rdbtnfunciones);
				panel.add(rdbtnfunciones, "cell 3 4");
			}
			{
				JLabel lblNewLabel_5 = new JLabel("Presupuesto: ");
				panel.add(lblNewLabel_5, "cell 1 6");
			}
			{
				spinner = new JSpinner();
				spinner.setModel(new SpinnerNumberModel(5, 1, 100, 1));
				panel.add(spinner, "cell 2 6");
			}
			{
				JLabel lblNewLabel_6 = new JLabel("(en miles de €)");
				panel.add(lblNewLabel_6, "cell 3 6");
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Nombre:");
				panel.add(lblNewLabel_1, "cell 1 8,alignx trailing");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			{
				txtnombre = new JTextField();
				panel.add(txtnombre, "cell 2 8 2 1,growx");
				txtnombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
				txtnombre.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						recogerDatos();
					}
				});
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}


	protected void recogerDatos() {
		int cod_departamento=Integer.parseInt(txtcodigo.getText());
		int cod_centro = (int) comboBox.getSelectedItem();
		String nombre = txtnombre.getText();
		String tipoDir="";
		if(rdbtnpropiedad.isSelected())tipoDir="P";
		else tipoDir="F";
		int presupuesto=(int) spinner.getValue();
			
		Departamento departamento = new Departamento(cod_departamento, cod_centro, tipoDir, presupuesto, nombre);
		controlador.insertaDepartamento(departamento);
		
	}


	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
	}
	public void setListaCentros(ArrayList<Centro> lista) {
		for (Centro c : lista) {
			comboBox.addItem(c);
		}
	}
}
