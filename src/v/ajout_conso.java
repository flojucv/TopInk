package v;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import c.connecteur;
import m.consommables;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class ajout_conso extends JDialog {

	private JPanel contentPane;
	private JTextField reference_txt;
	private JTextField quantite_txt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ajout_conso frame = new ajout_conso();
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
	public ajout_conso() {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 430);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(34, 58, 89));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel ajout_conso_lbl = new JLabel("Ajout de consommables :");
		ajout_conso_lbl.setForeground(new Color(255, 255, 255));
		ajout_conso_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		ajout_conso_lbl.setBounds(0, 11, 334, 38);
		ajout_conso_lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(ajout_conso_lbl);

		JLabel reference_lbl = new JLabel("Référence :");
		reference_lbl.setForeground(new Color(255, 255, 255));
		reference_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		reference_lbl.setBounds(0, 60, 334, 30);
		reference_lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(reference_lbl);

		reference_txt = new JTextField();
		reference_txt.setBounds(100, 101, 134, 30);
		reference_txt.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(reference_txt);
		reference_txt.setColumns(10);

		JLabel quantite_lbl = new JLabel("Quantité :");
		quantite_lbl.setForeground(new Color(255, 255, 255));
		quantite_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		quantite_lbl.setBounds(0, 142, 334, 30);
		quantite_lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(quantite_lbl);

		quantite_txt = new JTextField();
		quantite_txt.setBounds(100, 183, 134, 30);
		quantite_txt.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(quantite_txt);
		quantite_txt.setColumns(10);

		JLabel type_lbl = new JLabel("Type :");
		type_lbl.setForeground(new Color(255, 255, 255));
		type_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		type_lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		type_lbl.setBounds(0, 224, 334, 30);
		contentPane.add(type_lbl);
		
		String sql = "SELECT modele FROM modele";
		connecteur bdd = new connecteur();
		List<String> reponse = bdd.select(sql, 1);
		String[] arr = reponse.toArray(new String[0]);
		String[] Choice = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			Choice[i] = arr[i].substring(0, arr[i].length()-1);
		}
		JComboBox<String> comboBox = new JComboBox<>(Choice);
		comboBox.setBounds(100, 265, 134, 30);
		contentPane.add(comboBox);

		JButton ajouter_btn = new JButton("AJOUTER");
		ajouter_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						consommables.ajout_conso(reference_txt.getText(), Integer.parseInt(quantite_txt.getText()), comboBox.getItemAt(comboBox.getSelectedIndex()));
						JFrame frame = new JFrame("JOptionPane showMessageDialog Alert");                     
						JOptionPane.showMessageDialog(frame,"Sucess, Consommable ajouter.");
						dispose();
					}catch (Exception ex) {
						JFrame frame = new JFrame("JOptionPane showMessageDialog error");                     
						JOptionPane.showMessageDialog(frame,"Erreur, Vous devez entrer un nombre dans la case quantité");
					}				
			}
		});
		ajouter_btn.setFont(new Font("Tahoma", Font.BOLD, 20));
		ajouter_btn.setBounds(100, 306, 134, 38);
		contentPane.add(ajouter_btn);
	}
}
