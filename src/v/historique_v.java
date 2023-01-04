package v;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import c.connecteur;
import m.historique;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JScrollPane;

public class historique_v extends JDialog {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					historique_v frame = new historique_v();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param type 
	 */
	public historique_v() {
		ImageIcon img = new ImageIcon("img/logo.png");
		setIconImage(img.getImage());
		setTitle("Top ink | Historique");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 998, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(34, 58, 89));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel histo_lbl = new JLabel("Historique :");
		histo_lbl.setForeground(new Color(255, 255, 255));
		histo_lbl.setBackground(new Color(255, 255, 255));
		histo_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		histo_lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		histo_lbl.setBounds(0, 11, 982, 42);
		contentPane.add(histo_lbl);
		
		DefaultListModel<String> listeFinal = new DefaultListModel<>();
		List<String> tempList = new ArrayList<String>();
		List<String> l1 = new ArrayList<String>();		
		l1.addAll(historique.afficher_histo());
		for (String ligne : l1) {
			String[] tempSplit = ligne.split(";");
			if(tempSplit[3].equals("Ajouter")) {
				tempList.add(0, "+| "+ tempSplit[1]+ " " + tempSplit[4]);
			} else if(tempSplit[3].equals("Supprimer")){
				connecteur bdd = new connecteur();
				String sql = "SELECT batiment, designation FROM salle WHERE id_salle = \"" + tempSplit[4] + "\"";
				String[] salle = bdd.select(sql, 2).toString().split(";");
				sql = "SELECT nom, prenom FROM utilisateur WHERE id_utilisateur = \"" + tempSplit[5] + "\"";
				String[] utilisateur = bdd.select(sql, 2).toString().split(";");
				sql = "SELECT nom_section FROM section WHERE id_section = \"" + tempSplit[4] + "\"";
				String[] section = bdd.select(sql, 1).toString().split(";");
				tempList.add(0, "-| " + tempSplit[1] + " " + tempSplit[8] +" " + salle[0].substring(1)+salle[1] + " " + utilisateur[0].substring(1, utilisateur[0].length()).toUpperCase() + " " + utilisateur[1] + " " + section[0].substring(1, section[0].length()));
			}
		}
		listeFinal.addAll(tempList);
		JList<String> stock_list = new JList<>(listeFinal);
		JScrollPane scrollPane = new JScrollPane(stock_list);
		scrollPane.setBounds(10, 70, 951, 460);
		contentPane.add(scrollPane);
	}
}
