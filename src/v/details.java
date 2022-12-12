package v;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import c.connecteur;
import m.consommables;
import m.imprimante;
import m.salle;
import m.utilisateur;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class details extends JDialog {
	
	int index;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			details dialog = new details("utilisateur");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public details(String mode) {
		setResizable(false);
		getContentPane().setBackground(new Color(34, 58, 89));
		setTitle("DETAIL " + mode.toUpperCase());
		setBounds(100, 100, 463, 330);
		getContentPane().setLayout(null);
		DefaultListModel<String> l1 = new DefaultListModel<>();
		switch(mode) {
			case "imprimante":
				l1.removeAllElements();
				l1.addAll(imprimante.afficher_imprimante());
				break;
			case "utilisateur":
				l1.removeAllElements();
				l1.addAll(utilisateur.afficher_utilisateur());
				break;
			case "salle":
				l1.removeAllElements();
				l1.addAll(salle.afficher_salle());
				break;
			default : 
				break;
		}//test
		
		
		
		
		
		JLabel title_lbl = new JLabel("DETAIL " + mode.toUpperCase());
		title_lbl.setForeground(new Color(255, 255, 255));
		title_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		title_lbl.setBounds(0, 10, 459, 13);
		getContentPane().add(title_lbl);
		
		
		JButton supp_btn = new JButton("Supprimer");
		supp_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(mode) {
					case "imprimante" :
						String[] splitLigne = l1.elementAt(index).split(" ");
						imprimante.supprimer_impri(splitLigne[0]);
						l1.removeAllElements();
						l1.addAll(imprimante.afficher_imprimante());
						JFrame frame = new JFrame("JOptionPane showInputDialog Alert");                     
						JOptionPane.showMessageDialog(frame,"Sucess, L'imprimante a été supprimer.");
						break;
					case "utilisateur" :
						String[] splitLigne1 = l1.elementAt(index).split(" ");
						System.out.println(splitLigne1[0] + splitLigne1[1]);
						utilisateur.supprimer_utilisateur(splitLigne1[0], splitLigne1[1]);
						l1.removeAllElements();
						l1.addAll(utilisateur.afficher_utilisateur());
						JFrame frame1 = new JFrame("JOptionPane showInputDialog Alert");                     
						JOptionPane.showMessageDialog(frame1,"Sucess, L'utilisateur a été supprimer.");
					case "salle":
						String[] splitLigne2 = l1.elementAt(index).split(" ");
						System.out.println(splitLigne2[0] + splitLigne2[1]);
						salle.supprimer_salle(splitLigne2[0], splitLigne2[1]);
						l1.removeAllElements();
						l1.addAll(utilisateur.afficher_utilisateur());
						JFrame frame2 = new JFrame("JOptionPane showInputDialog Alert");                     
						JOptionPane.showMessageDialog(frame2,"Sucess, L'utilisateur a été supprimer.");
					default:
						
						break;
				}
				
			}
		});
		supp_btn.setEnabled(false);
		supp_btn.setBounds(321, 263, 118, 21);
		getContentPane().add(supp_btn);
		
		JButton modif_btn = new JButton("Modifier");
		modif_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (mode) {
					case "imprimante" :
						modif_impri modifierImprimante = new modif_impri(l1.get(index));
						modifierImprimante.setVisible(true);
						break;
					case "utilisateur" :
						modif_utilisateur modifierUtilisateur = new modif_utilisateur(l1.get(index));
						modifierUtilisateur.setVisible(true);
						break;
					case "salle" :
						modif_salle modifierSalle = new modif_salle(l1.get(index));
						modifierSalle.setVisible(true);
					default:
						break;
				}
				
			}
		});
		modif_btn.setEnabled(false);
		modif_btn.setBounds(10, 263, 85, 21);
		getContentPane().add(modif_btn);
		
		
		JList<String> list = new JList<>(l1);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(10, 38, 426, 215);
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		    	index = list.locationToIndex(evt.getPoint());
		    	modif_btn.setEnabled(true);
		    	supp_btn.setEnabled(true);
		    }
		});
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(10, 38, 426, 215);
		getContentPane().add(scrollPane);
		
		JButton actualiser_btn = new JButton("Actualiser");
		actualiser_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				l1.removeAllElements();
				switch(mode) {
					case "imprimante":
						l1.addAll(imprimante.afficher_imprimante());
						break;
					case "utilisateur":
						l1.addAll(utilisateur.afficher_utilisateur());
						break;
					default :
						break;
				}
				
			}
		});
		actualiser_btn.setBounds(335, 25, 101, 13);
		getContentPane().add(actualiser_btn);
	}
}
