package v;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import c.connecteur;
import m.imprimante;
import m.salle;
import m.seuil_alerte;
import m.utilisateur;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class parametre extends JDialog {

	private JPanel contentPane;
	private JLabel seuil_lbl;
	private JTextField seuil_txt;
	private JButton modifier_btn;
	private JLabel reference_impri_lbl;
	private JTextField reference_impri_txt;
	private JTextField marque_impri_txt;
	private JTextField batiment_txt;
	private JButton addImpri_btn;
	private JButton details_btn;
	private JTextField nom_btn;
	private JTextField prenom_btn;
	private JTextField section_btn;
	private JLabel utilisateur_lbl_1;
	private JTextField numSalle_txt;
	private JTextField modele_impri_txt;
	private JLabel modele_impri_lbl;
	private JTextField batiment_txt2;
	private JTextField numSalle_btn2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					parametre frame = new parametre();
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
	public parametre() {
		setResizable(false);
		setTitle("PARAMETRE");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 900);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(34, 58, 89));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String sql = "SELECT modele FROM modele";
		connecteur bdd = new connecteur();
		List<String> reponse = bdd.select(sql, 1);
		String[] arr = reponse.toArray(new String[0]);
		String[] Choice = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			Choice[i] = arr[i].substring(0, arr[i].length()-1);
		}
		//choiceBox
		JComboBox<String> comboBox = new JComboBox<>(Choice);
		comboBox.setBounds(196, 90, 139, 30);
		contentPane.add(comboBox);
		
		JLabel seuil_alerte_lbl = new JLabel("Seuil d'alerte:");
		seuil_alerte_lbl.setForeground(new Color(255, 255, 255));
		seuil_alerte_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		seuil_alerte_lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		seuil_alerte_lbl.setBounds(10, 10, 966, 30);
		contentPane.add(seuil_alerte_lbl);
		
		JLabel modele_seuil_lbl = new JLabel("Type :");
		modele_seuil_lbl.setForeground(new Color(255, 255, 255));
		modele_seuil_lbl.setBackground(new Color(255, 255, 255));
		modele_seuil_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		modele_seuil_lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		modele_seuil_lbl.setBounds(186, 55, 139, 30);
		contentPane.add(modele_seuil_lbl);
		
		seuil_lbl = new JLabel("Seuil :");
		seuil_lbl.setForeground(new Color(255, 255, 255));
		seuil_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		seuil_lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		seuil_lbl.setBounds(643, 50, 139, 30);
		contentPane.add(seuil_lbl);
		
		seuil_txt = new JTextField();
		seuil_txt.setFont(new Font("Tahoma", Font.BOLD, 15));
		seuil_txt.setColumns(10);
		seuil_txt.setBounds(643, 89, 139, 30);
		contentPane.add(seuil_txt);
		
		modifier_btn = new JButton("MODIFIER");
		modifier_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int error = seuil_alerte.definir_seuil(comboBox.getItemAt(comboBox.getSelectedIndex()), seuil_txt.getText());
				switch(error) {
					case 1:
						JFrame frame = new JFrame("JOptionPane showMessageDialog error");                     
						JOptionPane.showMessageDialog(frame,"Erreur, Le seuil doit être un chiffre.");
						break;
					case 2 :
						JFrame frame1 = new JFrame("JOptionPane showMessageDialog error");                     
						JOptionPane.showMessageDialog(frame1,"Erreur, Un problème est survenue avec le type.");
						break;
					case 0 :
						JFrame frame2 = new JFrame("JOptionPane showMessageDialog Alert");                     
						JOptionPane.showMessageDialog(frame2,"Sucess, Le seuil a été modifier.");
						break;
				}
			}
		});
		modifier_btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		modifier_btn.setBounds(404, 129, 150, 50);
		contentPane.add(modifier_btn);
		
		reference_impri_lbl = new JLabel("Reference:");
		reference_impri_lbl.setForeground(new Color(255, 255, 255));
		reference_impri_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		reference_impri_lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		reference_impri_lbl.setBounds(10, 248, 139, 30);
		contentPane.add(reference_impri_lbl);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(10, 189, 966, 5);
		contentPane.add(separator);
		
		reference_impri_txt = new JTextField();
		reference_impri_txt.setFont(new Font("Tahoma", Font.BOLD, 15));
		reference_impri_txt.setColumns(10);
		reference_impri_txt.setBounds(10, 289, 139, 30);
		contentPane.add(reference_impri_txt);
		
		JLabel marque_lbl = new JLabel("Marque:");
		marque_lbl.setForeground(new Color(255, 255, 255));
		marque_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		marque_lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		marque_lbl.setBounds(380, 248, 139, 30);
		contentPane.add(marque_lbl);
		
		JLabel batiment_lbl = new JLabel("Batiment:");
		batiment_lbl.setForeground(new Color(255, 255, 255));
		batiment_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		batiment_lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		batiment_lbl.setBounds(567, 248, 139, 30);
		contentPane.add(batiment_lbl);
		
		marque_impri_txt = new JTextField();
		marque_impri_txt.setFont(new Font("Tahoma", Font.BOLD, 15));
		marque_impri_txt.setColumns(10);
		marque_impri_txt.setBounds(380, 289, 139, 30);
		contentPane.add(marque_impri_txt);
		
		batiment_txt = new JTextField();
		batiment_txt.setFont(new Font("Tahoma", Font.BOLD, 15));
		batiment_txt.setColumns(10);
		batiment_txt.setBounds(567, 289, 139, 30);
		contentPane.add(batiment_txt);
		
		
		
		details_btn = new JButton("DETAILS");
		details_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				details impriDetail = new details("imprimante");
				impriDetail.setVisible(true);
			}
		});
		details_btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		details_btn.setBounds(525, 329, 150, 50);
		contentPane.add(details_btn);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(0, 0, 0));
		separator_1.setBounds(10, 390, 966, 4);
		contentPane.add(separator_1);
		
		JLabel nom_lbl = new JLabel("Nom:");
		nom_lbl.setForeground(new Color(255, 255, 255));
		nom_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		nom_lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		nom_lbl.setBounds(186, 442, 139, 30);
		contentPane.add(nom_lbl);
		
		JLabel prenom_lbl = new JLabel("Prénom:");
		prenom_lbl.setForeground(new Color(255, 255, 255));
		prenom_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		prenom_lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		prenom_lbl.setBounds(404, 442, 139, 30);
		contentPane.add(prenom_lbl);
		
		JLabel utilisateur_lbl = new JLabel("Utilisateur:");
		utilisateur_lbl.setForeground(new Color(255, 255, 255));
		utilisateur_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		utilisateur_lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		utilisateur_lbl.setBounds(394, 402, 160, 30);
		contentPane.add(utilisateur_lbl);
		
		JLabel section_lbl = new JLabel("Section:");
		section_lbl.setForeground(new Color(255, 255, 255));
		section_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		section_lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		section_lbl.setBounds(642, 442, 140, 30);
		contentPane.add(section_lbl);
		
		nom_btn = new JTextField();
		nom_btn.setFont(new Font("Tahoma", Font.BOLD, 15));
		nom_btn.setColumns(10);
		nom_btn.setBounds(186, 482, 139, 30);
		contentPane.add(nom_btn);
		
		prenom_btn = new JTextField();
		prenom_btn.setFont(new Font("Tahoma", Font.BOLD, 15));
		prenom_btn.setColumns(10);
		prenom_btn.setBounds(404, 482, 139, 30);
		contentPane.add(prenom_btn);
		
		section_btn = new JTextField();
		section_btn.setFont(new Font("Tahoma", Font.BOLD, 15));
		section_btn.setColumns(10);
		section_btn.setBounds(643, 482, 139, 30);
		contentPane.add(section_btn);
		
		
		
		JButton details_user_btn = new JButton("DETAILS");
		details_user_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				details impriDetail = new details("utilisateur");
				impriDetail.setVisible(true);
			}
		});
		details_user_btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		details_user_btn.setBounds(525, 522, 150, 50);
		contentPane.add(details_user_btn);
		
		utilisateur_lbl_1 = new JLabel("Imprimante :");
		utilisateur_lbl_1.setHorizontalAlignment(SwingConstants.CENTER);
		utilisateur_lbl_1.setForeground(Color.WHITE);
		utilisateur_lbl_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		utilisateur_lbl_1.setBounds(404, 204, 160, 30);
		contentPane.add(utilisateur_lbl_1);
		
		numSalle_txt = new JTextField();
		numSalle_txt.setFont(new Font("Tahoma", Font.BOLD, 15));
		numSalle_txt.setColumns(10);
		numSalle_txt.setBounds(779, 289, 160, 30);
		contentPane.add(numSalle_txt);
		
		JLabel numSalle_lbl = new JLabel("Numero salle:");
		numSalle_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		numSalle_lbl.setForeground(Color.WHITE);
		numSalle_lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		numSalle_lbl.setBounds(779, 248, 160, 30);
		contentPane.add(numSalle_lbl);
		
		
		
		modele_impri_txt = new JTextField();
		modele_impri_txt.setFont(new Font("Tahoma", Font.BOLD, 15));
		modele_impri_txt.setColumns(10);
		modele_impri_txt.setBounds(186, 289, 139, 30);
		contentPane.add(modele_impri_txt);
		
		modele_impri_lbl = new JLabel("Modèle:");
		modele_impri_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		modele_impri_lbl.setForeground(Color.WHITE);
		modele_impri_lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		modele_impri_lbl.setBounds(186, 248, 139, 30);
		contentPane.add(modele_impri_lbl);
		
		addImpri_btn = new JButton("AJOUTER");
		addImpri_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(reference_impri_txt.getText().length() == 0) {
					JFrame frame = new JFrame("JOptionPane showMessageDialog error");                     
					JOptionPane.showMessageDialog(frame,"Erreur, Vous n'avez pas rentrer de référence.");
				} else if(modele_impri_txt.getText().length() == 0) {
					JFrame frame = new JFrame("JOptionPane showMessageDialog error");                     
					JOptionPane.showMessageDialog(frame,"Erreur, Vous n'avez pas rentrer de modéle.");
				} else if(marque_impri_txt.getText().length() == 0) {
					JFrame frame = new JFrame("JOptionPane showMessageDialog error");                     
					JOptionPane.showMessageDialog(frame,"Erreur, Vous n'avez pas rentrer de marque.");
				} else if(batiment_txt.getText().length() == 0) {
					JFrame frame = new JFrame("JOptionPane showMessageDialog error");                     
					JOptionPane.showMessageDialog(frame,"Erreur, Vous n'avez pas rentrer de batiment.");
				} else if(numSalle_txt.getText().length() == 0) {
					JFrame frame = new JFrame("JOptionPane showMessageDialog error");                     
					JOptionPane.showMessageDialog(frame,"Erreur, Vous n'avez pas rentrer de numéro de salle.");
				} else {
					imprimante.ajout_impri(reference_impri_txt.getText(), modele_impri_txt.getText(), marque_impri_txt.getText(), batiment_txt.getText(), numSalle_txt.getText());
					JFrame frame5 = new JFrame("JOptionPane Alert error");                     
					JOptionPane.showMessageDialog(frame5,"Sucess, L'imprimante a été ajouter.");
				}
			}
		});
		addImpri_btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addImpri_btn.setBounds(290, 329, 150, 50);
		contentPane.add(addImpri_btn);
		
		JButton ajouter_user_btn = new JButton("AJOUTER");
		ajouter_user_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nom_btn.getText().length() == 0) {
					JFrame frame = new JFrame("JOptionPane showMessageDialog error");                     
					JOptionPane.showMessageDialog(frame,"Erreur, Vous n'avez pas rentrer de nom.");
				} else if(prenom_btn.getText().length() == 0) {
					JFrame frame = new JFrame("JOptionPane showMessageDialog error");                     
					JOptionPane.showMessageDialog(frame,"Erreur, Vous n'avez pas rentrer de prenom.");
				}else if(section_btn.getText().length() == 0) {
					JFrame frame = new JFrame("JOptionPane showMessageDialog error");                     
					JOptionPane.showMessageDialog(frame,"Erreur, Vous n'avez pas rentrer de section.");
				} else {
					utilisateur.ajouter_utilisateur(nom_btn.getText(), prenom_btn.getText(), section_btn.getText());
					JFrame frame5 = new JFrame("JOptionPane Alert error");                     
					JOptionPane.showMessageDialog(frame5,"Sucess, L'utilisateur a été ajouter.");
				}
			}
		});
		ajouter_user_btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ajouter_user_btn.setBounds(290, 522, 150, 50);
		contentPane.add(ajouter_user_btn);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(Color.BLACK);
		separator_1_1.setBounds(10, 602, 966, 4);
		contentPane.add(separator_1_1);
		
		JLabel salle_lbl = new JLabel("Salle:");
		salle_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		salle_lbl.setForeground(Color.WHITE);
		salle_lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		salle_lbl.setBounds(404, 616, 160, 30);
		contentPane.add(salle_lbl);
		
		batiment_txt2 = new JTextField();
		batiment_txt2.setFont(new Font("Tahoma", Font.BOLD, 15));
		batiment_txt2.setColumns(10);
		batiment_txt2.setBounds(283, 696, 139, 30);
		contentPane.add(batiment_txt2);
		
		JLabel Batiment_lbl2 = new JLabel("Batiment:");
		Batiment_lbl2.setHorizontalAlignment(SwingConstants.CENTER);
		Batiment_lbl2.setForeground(Color.WHITE);
		Batiment_lbl2.setFont(new Font("Tahoma", Font.BOLD, 20));
		Batiment_lbl2.setBounds(283, 656, 139, 30);
		contentPane.add(Batiment_lbl2);
		
		numSalle_btn2 = new JTextField();
		numSalle_btn2.setFont(new Font("Tahoma", Font.BOLD, 15));
		numSalle_btn2.setColumns(10);
		numSalle_btn2.setBounds(567, 696, 169, 30);
		contentPane.add(numSalle_btn2);
		
		JLabel nom_lbl_1_2 = new JLabel("Numero salle:");
		nom_lbl_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		nom_lbl_1_2.setForeground(Color.WHITE);
		nom_lbl_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		nom_lbl_1_2.setBounds(567, 656, 169, 30);
		contentPane.add(nom_lbl_1_2);
		
		JButton ajouter_user_btn_1 = new JButton("AJOUTER");
		ajouter_user_btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(batiment_txt2.getText().length() == 0) {
					JFrame frame = new JFrame("JOptionPane showMessageDialog error");                     
					JOptionPane.showMessageDialog(frame,"Erreur, Vous n'avez pas rentrer de batiment.");
				} else if(numSalle_btn2.getText().length() == 0) {
					JFrame frame = new JFrame("JOptionPane showMessageDialog error");                     
					JOptionPane.showMessageDialog(frame,"Erreur, Vous n'avez pas rentrer de salle.");
				}else {
					salle.ajout_salle(batiment_txt2.getText(), numSalle_btn2.getText());
					JFrame frame5 = new JFrame("JOptionPane Alert error");                     
					JOptionPane.showMessageDialog(frame5,"Sucess, L'utilisateur a été ajouter.");
				}
			}
		});
		ajouter_user_btn_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ajouter_user_btn_1.setBounds(334, 736, 150, 50);
		contentPane.add(ajouter_user_btn_1);
		
		JButton details_user_btn_1 = new JButton("DETAILS");
		details_user_btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				details impriDetail = new details("salle");
				impriDetail.setVisible(true);
			}
		});
		details_user_btn_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		details_user_btn_1.setBounds(525, 736, 150, 50);
		contentPane.add(details_user_btn_1);
	}
}
