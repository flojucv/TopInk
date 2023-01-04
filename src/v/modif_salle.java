package v;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import m.imprimante;
import m.salle;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class modif_salle extends JDialog {
	private JTextField batiment_txt;
	private JTextField numSalle_txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			modif_salle dialog = new modif_salle(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public modif_salle(String InfoSalle) {
		ImageIcon img = new ImageIcon("img/logo.png");
		setIconImage(img.getImage());
		setTitle("Top ink | Modifier salle");
		getContentPane().setBackground(new Color(34, 58, 89));
		setResizable(false);
		String[] SplitInfo = InfoSalle.split(" ");
		setBounds(100, 100, 320, 150);
		getContentPane().setLayout(null);
		
		batiment_txt = new JTextField();
		batiment_txt.setText(SplitInfo[0]);
		batiment_txt.setColumns(10);
		batiment_txt.setBounds(32, 56, 96, 19);
		getContentPane().add(batiment_txt);
		
		JLabel batiment_lbl = new JLabel("Batiment:");
		batiment_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		batiment_lbl.setForeground(Color.WHITE);
		batiment_lbl.setBounds(32, 33, 96, 13);
		getContentPane().add(batiment_lbl);
		
		JLabel Modif_lbl = new JLabel("MODIFIER SALLE");
		Modif_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		Modif_lbl.setForeground(Color.WHITE);
		Modif_lbl.setBounds(0, 10, 306, 13);
		getContentPane().add(Modif_lbl);
		
		numSalle_txt = new JTextField();
		numSalle_txt.setText(SplitInfo[1]);
		numSalle_txt.setColumns(10);
		numSalle_txt.setBounds(185, 56, 96, 19);
		getContentPane().add(numSalle_txt);
		
		JLabel numSalle_lbl = new JLabel("Numero de la salle:");
		numSalle_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		numSalle_lbl.setForeground(Color.WHITE);
		numSalle_lbl.setBounds(185, 33, 96, 13);
		getContentPane().add(numSalle_lbl);
		
		JButton modif_btn = new JButton("Modifier");
		modif_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(batiment_txt.getText().length() == 0 || numSalle_txt.getText().length() == 0 ) {
					JFrame frame = new JFrame("JOptionPane showInputDialog Alert");                     
					JOptionPane.showMessageDialog(frame,"⚠, Une des case n'ai pas remplis.");
				} else {
					salle.modif_salle(batiment_txt.getText(), numSalle_txt.getText(), SplitInfo[0], SplitInfo[1]);
					JFrame frame = new JFrame("JOptionPane showInputDialog Alert");                     
					JOptionPane.showMessageDialog(frame,"Sucess, Imprimante modifier");
					dispose();
				}
			}
		});
		modif_btn.setBounds(119, 85, 85, 21);
		getContentPane().add(modif_btn);
	}

}
