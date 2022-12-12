package v;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import m.imprimante;
import m.utilisateur;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class modif_utilisateur extends JDialog {

	private JPanel contentPane;
	private JTextField nom_txt;
	private JTextField prenom_txt;
	private JTextField section_txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modif_utilisateur frame = new modif_utilisateur(null);
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
	public modif_utilisateur(String InfoUser) {
		String[] SplitInfo = InfoUser.split(" ");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 490, 190);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(34, 58, 89));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Modif_lbl = new JLabel("MODIFIER UTILISATEUR");
		Modif_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		Modif_lbl.setForeground(Color.WHITE);
		Modif_lbl.setBounds(0, 10, 471, 13);
		contentPane.add(Modif_lbl);
		
		JLabel nom_lbl = new JLabel("Nom:");
		nom_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		nom_lbl.setForeground(Color.WHITE);
		nom_lbl.setBounds(25, 51, 96, 13);
		contentPane.add(nom_lbl);
		
		nom_txt = new JTextField();
		nom_txt.setText(SplitInfo[0]);
		nom_txt.setEditable(false);
		nom_txt.setColumns(10);
		nom_txt.setBounds(25, 74, 96, 19);
		contentPane.add(nom_txt);
		
		JLabel prenom_lbl = new JLabel("Prenom:");
		prenom_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		prenom_lbl.setForeground(Color.WHITE);
		prenom_lbl.setBounds(193, 51, 96, 13);
		contentPane.add(prenom_lbl);
		
		prenom_txt = new JTextField();
		prenom_txt.setText(SplitInfo[1]);
		prenom_txt.setEditable(false);
		prenom_txt.setColumns(10);
		prenom_txt.setBounds(193, 74, 96, 19);
		contentPane.add(prenom_txt);
		
		JLabel section_lbl = new JLabel("Section:");
		section_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		section_lbl.setForeground(Color.WHITE);
		section_lbl.setBounds(370, 51, 96, 13);
		contentPane.add(section_lbl);
		
		section_txt = new JTextField();
		section_txt.setText(SplitInfo[2].substring(1, SplitInfo[2].length()-1));
		section_txt.setEditable(false);
		section_txt.setColumns(10);
		section_txt.setBounds(370, 74, 96, 19);
		contentPane.add(section_txt);
		
		JButton modif_btn = new JButton("Modifier");
		modif_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nom_txt.getText().length() == 0 || prenom_txt.getText().length() == 0 || section_txt.getText().length() == 0 ) {
					JFrame frame = new JFrame("JOptionPane showInputDialog Alert");                     
					JOptionPane.showMessageDialog(frame,"⚠, Une des case n'ai pas remplis.");
				} else {
					utilisateur.modifier_utilisateur(nom_txt.getText(), prenom_txt.getText(), section_txt.getText());
					JFrame frame = new JFrame("JOptionPane showInputDialog Alert");                     
					JOptionPane.showMessageDialog(frame,"Sucess, Imprimante modifier");
					dispose();
				}
			}
		});
		modif_btn.setBounds(193, 122, 96, 21);
		contentPane.add(modif_btn);
	}
}
