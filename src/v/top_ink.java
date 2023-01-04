package v;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumn;

import c.connecteur;
import m.consommables;

import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;

public class top_ink extends JFrame {

	private JPanel contentPane;
	private JTextField date_txt;
	private JTextField qte_txt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					top_ink frame = new top_ink();
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
	public String[] affichageJComboBox(String sql) {
		;
		connecteur bdd = new connecteur();
		List<String> reponse = bdd.select(sql, 1);
		String[] arr = reponse.toArray(new String[0]);
		String[] Choice = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			
			Choice[i] = arr[i].substring(0, arr[i].length()-1);
		}	
		
		return Choice;
	}
	
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
	public top_ink() {
		ImageIcon img = new ImageIcon("img/logo.png");
		setIconImage(img.getImage());
		setTitle("Top ink");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(34, 58, 89));
		contentPane.setToolTipText("");
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel gest_conso_lbl = new JLabel("Gestionnaire de consommables");
		gest_conso_lbl.setForeground(new Color(255, 255, 255));
		gest_conso_lbl.setBounds(339, 10, 314, 25);
		gest_conso_lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(gest_conso_lbl);
		
		JLabel ref_lbl = new JLabel("Références :");
		ref_lbl.setForeground(new Color(255, 255, 255));
		ref_lbl.setHorizontalAlignment(SwingConstants.TRAILING);
		ref_lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		ref_lbl.setBounds(60, 50, 100, 30);
		contentPane.add(ref_lbl);
		
		JLabel batiment_lbl = new JLabel("Salle:");
		batiment_lbl.setForeground(new Color(255, 255, 255));
		batiment_lbl.setHorizontalAlignment(SwingConstants.TRAILING);
		batiment_lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		batiment_lbl.setBounds(180, 93, 160, 30);
		contentPane.add(batiment_lbl);
		
		JLabel date_lbl = new JLabel("Date :");
		date_lbl.setForeground(new Color(255, 255, 255));
		date_lbl.setHorizontalAlignment(SwingConstants.TRAILING);
		date_lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		date_lbl.setBounds(407, 53, 53, 25);
		contentPane.add(date_lbl);
		
		date_txt = new JTextField();
		Date date = new Date();
		SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
		String stringDate= DateFor.format(date);
		date_txt.setText(stringDate);
		date_txt.setBounds(470, 55, 150, 21);
		contentPane.add(date_txt);
		date_txt.setColumns(10);
		
		JLabel nom_lbl = new JLabel("Utilisateur :");
		nom_lbl.setForeground(new Color(255, 255, 255));
		nom_lbl.setHorizontalAlignment(SwingConstants.TRAILING);
		nom_lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		nom_lbl.setBounds(646, 53, 144, 25);
		contentPane.add(nom_lbl);
			
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(10, 248, 966, 25);
		contentPane.add(separator);
		
		JLabel stock_lbl = new JLabel("Mon stock:");
		stock_lbl.setForeground(new Color(255, 255, 255));
		stock_lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		stock_lbl.setBounds(10, 259, 89, 30);
		contentPane.add(stock_lbl);
		
		DefaultListModel<String> l1 = new DefaultListModel<>();
		l1.addAll(consommables.afficher_conso());
		JList<String> stock_list = new JList<>(l1);
		stock_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		stock_list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {

		            // Double-click detected
		            int index = list.locationToIndex(evt.getPoint());
		            String[] tempSplit = l1.get(index).split(" ");
		            stock frameStock = new stock(tempSplit[0]);
		            frameStock.setVisible(true);
		        } else if (evt.getClickCount() == 3) {

		            // Triple-click detected
		            int index = list.locationToIndex(evt.getPoint());
		        }
		    }
		});
		stock_list.setVisibleRowCount(20);
		stock_list.setFont(new Font("Tahoma", Font.BOLD, 12));
		stock_list.setBounds(51, 289, 877, 229);
		contentPane.add(stock_list);
		
		
		
		JLabel qte_lbl = new JLabel("Quantite :");
		qte_lbl.setForeground(new Color(255, 255, 255));
		qte_lbl.setHorizontalAlignment(SwingConstants.TRAILING);
		qte_lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		qte_lbl.setBounds(532, 96, 121, 25);
		contentPane.add(qte_lbl);
		
		qte_txt = new JTextField();
		qte_txt.setColumns(10);
		qte_txt.setBounds(663, 100, 150, 21);
		contentPane.add(qte_txt);
		
		
		String[] Choice = affichageJComboBox("SELECT reference_conso FROM consommable WHERE stock > 0");
		String[] Choice1 = affichageJComboBoxL2("SELECT batiment, designation FROM salle WHERE visible = 1");
		String[] Choice2 = affichageJComboBoxL2("SELECT nom, prenom FROM utilisateur WHERE visible = 1");
		JComboBox<String> comboBox_salle = new JComboBox<>(Choice1);
		comboBox_salle.setBounds(350, 100, 150, 21);
		contentPane.add(comboBox_salle);
		
		JComboBox<String> comboBox_ref = new JComboBox<>(Choice);
		comboBox_ref.setBounds(170, 57, 150, 21);
		contentPane.add(comboBox_ref);
		
		JComboBox<String> comboBox_utilisateur = new JComboBox<>(Choice2);
		comboBox_utilisateur.setBounds(800, 57, 150, 21);
		contentPane.add(comboBox_utilisateur);
		
		JButton add_btn = new JButton("Ajouter Consommable");
		add_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajout_conso consoFrame = new ajout_conso();
				consoFrame.setVisible(true);
			}
		});
		add_btn.setFont(new Font("Tahoma", Font.BOLD, 10));
		add_btn.setBounds(779, 518, 150, 34);
		contentPane.add(add_btn);
		
		JButton supp_btn = new JButton("SUPRIMMER");
		supp_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String[] splitSalle = comboBox_salle.getItemAt(comboBox_salle.getSelectedIndex()).split(" ");
					String[] splitUtilisateur = comboBox_utilisateur.getItemAt(comboBox_utilisateur.getSelectedIndex()).split(" ");
					int retirer_conso = consommables.retire_conso(comboBox_ref.getItemAt(comboBox_ref.getSelectedIndex()), date_txt.getText(), splitSalle[0], splitSalle[1], splitUtilisateur[0], splitUtilisateur[1], Integer.parseInt(qte_txt.getText()));
					switch(retirer_conso) {
						case 1:
							JFrame frame = new JFrame("JOptionPane showMessageDialog error");                     
							JOptionPane.showMessageDialog(frame,"Erreur, Cette référence est inexistante.");
							break;
						case 2:
							JFrame frame1 = new JFrame("JOptionPane showMessageDialog error");                     
							JOptionPane.showMessageDialog(frame1,"Erreur, Le bâtiment ou la salle n'existe pas.");
							break;
						case 3:
							JFrame frame2 = new JFrame("JOptionPane showMessageDialog error");                     
							JOptionPane.showMessageDialog(frame2,"Erreur, Cette pièce n'est pas équipée d'une imprimante.");
							break;
						case 4:
							JFrame frame3 = new JFrame("JOptionPane showMessageDialog error");                     
							JOptionPane.showMessageDialog(frame3,"Erreur, Cette personne n'est pas répertoriée.");
							break;
						case 5:
							JFrame frame4 = new JFrame("JOptionPane showMessageDialog error");                     
							JOptionPane.showMessageDialog(frame4,"Erreur, Les consommables disponibles pour cette référence sont insuffisants.");
							break;
						case 6:
							JFrame frame6 = new JFrame("JOptionPane showMessageDialog error");                     
							JOptionPane.showMessageDialog(frame6,"⚠, Vous avez atteint le seuil, pensez a recharger le stock.");
							l1.removeAllElements();
							l1.addAll(consommables.afficher_conso());
							break;
						case 0:
							JFrame frame5 = new JFrame("JOptionPane Alert error");                     
							JOptionPane.showMessageDialog(frame5,"Sucess, Les consommables ont bien été retirer.");
							l1.removeAllElements();
							l1.addAll(consommables.afficher_conso());
						break;
					}
				}catch (Exception ex) {
					JFrame frame = new JFrame("JOptionPane showMessageDialog error");                     
					JOptionPane.showMessageDialog(frame,"Erreur, Vous devez entrer un nombre dans la case quantité");
				}
				
			}
		});
		supp_btn.setFont(new Font("Tahoma", Font.BOLD, 10));
		supp_btn.setBounds(430, 190, 100, 50);
		contentPane.add(supp_btn);
		
		JButton historique_btn = new JButton("Historique");
		historique_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				historique_v histo = new historique_v();
				histo.setVisible(true);
			}
		});
		historique_btn.setFont(new Font("Tahoma", Font.BOLD, 10));
		historique_btn.setBounds(51, 518, 94, 34);
		contentPane.add(historique_btn);
		
		JButton parametre_btn = new JButton("PARAMETRES");
		parametre_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parametre para = new parametre();
				para.setVisible(true);
			}
		});
		parametre_btn.setFont(new Font("Tahoma", Font.BOLD, 10));
		parametre_btn.setBounds(876, 0, 110, 35);
		contentPane.add(parametre_btn);
		
		JButton actualiser_btn = new JButton("Actualiser");
		actualiser_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				l1.removeAllElements();
				l1.addAll(consommables.afficher_conso());
			}
		});
		actualiser_btn.setBounds(818, 266, 110, 21);
		contentPane.add(actualiser_btn);
		
		
		
		
	}
}
