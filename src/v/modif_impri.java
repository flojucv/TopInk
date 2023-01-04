package v;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import c.connecteur;
import m.imprimante;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class modif_impri extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField reference_txt;
	private JTextField modele_txt;
	private JTextField marque_txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			modif_impri dialog = new modif_impri(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	public String[] affichageJComboBoxL2(String sql) {
		;
		connecteur bdd = new connecteur();
		List<String> reponse = bdd.select(sql, 2);
		String[] arr = reponse.toArray(new String[0]);
		String[] Choice = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String[] splitTemp = arr[i].split(";");
			Choice[i] = splitTemp[0].toUpperCase() + " " + splitTemp[1].toUpperCase();
		}	
		
		return Choice;
	}
	public modif_impri(String infoImpri) {
		ImageIcon img = new ImageIcon("img/logo.png");
		setIconImage(img.getImage());
		setTitle("Top ink | Modifier Imprimante");
		setResizable(false);
		String[] SplitInfo = infoImpri.split(" ");
		setBackground(new Color(34, 58, 89));
		setForeground(new Color(34, 58, 89));
		setBounds(100, 100, 485, 190);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(34, 58, 89));
		contentPanel.setForeground(new Color(34, 58, 89));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		reference_txt = new JTextField();
		reference_txt.setText(SplitInfo[0]);
		reference_txt.setEditable(false);
		reference_txt.setBounds(40, 70, 96, 19);
		contentPanel.add(reference_txt);
		reference_txt.setColumns(10);
		
		modele_txt = new JTextField();
		modele_txt.setText(SplitInfo[1]);
		modele_txt.setColumns(10);
		modele_txt.setBounds(185, 70, 96, 19);
		contentPanel.add(modele_txt);
		
		marque_txt = new JTextField();
		marque_txt.setText(SplitInfo[2]);
		marque_txt.setColumns(10);
		marque_txt.setBounds(330, 70, 96, 19);
		contentPanel.add(marque_txt);
		
		JLabel ref_lbl = new JLabel("Reference:");
		ref_lbl.setForeground(new Color(255, 255, 255));
		ref_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		ref_lbl.setBounds(40, 47, 96, 13);
		contentPanel.add(ref_lbl);
		
		JLabel modele_lbl = new JLabel("Modéle:");
		modele_lbl.setForeground(new Color(255, 255, 255));
		modele_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		modele_lbl.setBounds(185, 47, 96, 13);
		contentPanel.add(modele_lbl);
		
		JLabel marque_lbl = new JLabel("Marque");
		marque_lbl.setForeground(new Color(255, 255, 255));
		marque_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		marque_lbl.setBounds(330, 47, 96, 13);
		contentPanel.add(marque_lbl);
		
		JLabel batiment_lbl = new JLabel("Batiment:");
		batiment_lbl.setForeground(new Color(255, 255, 255));
		batiment_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		batiment_lbl.setBounds(117, 99, 96, 13);
		contentPanel.add(batiment_lbl);
		
		JLabel Modif_lbl = new JLabel("MODIFIER IMPRIMANTE");
		Modif_lbl.setForeground(new Color(255, 255, 255));
		Modif_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		Modif_lbl.setBounds(0, 10, 471, 13);
		contentPanel.add(Modif_lbl);
		
		String[] Choice = affichageJComboBoxL2("SELECT batiment, designation FROM salle WHERE visible = 1");
		JComboBox<String> comboBox = new JComboBox<>(Choice);
		comboBox.setBounds(195, 95, 96, 21);
		contentPanel.add(comboBox);
		
		JButton modif_btn = new JButton("Modifier");
		modif_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] splitSalle = comboBox.getItemAt(comboBox.getSelectedIndex()).split(" ");
				if(reference_txt.getText().length() == 0 || modele_txt.getText().length() == 0 || marque_txt.getText().length() == 0) {
					JFrame frame = new JFrame("JOptionPane showInputDialog Alert");                     
					JOptionPane.showMessageDialog(frame,"⚠, Une des case n'ai pas remplis.");
				} else {
					imprimante.modifier_imprimante(reference_txt.getText(), modele_txt.getText(), marque_txt.getText(), splitSalle[0], splitSalle[1]);
					JFrame frame = new JFrame("JOptionPane showInputDialog Alert");                     
					JOptionPane.showMessageDialog(frame,"Sucess, Imprimante modifier");
					dispose();
				}
			}
		});
		modif_btn.setBounds(196, 123, 85, 21);
		contentPanel.add(modif_btn);
		
		
	}
}
