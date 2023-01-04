package v;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import m.consommables;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class stock extends JDialog {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stock frame = new stock("cartouche");
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
	public stock(String type) {
		ImageIcon img = new ImageIcon("img/logo.png");
		setIconImage(img.getImage());
		setTitle("Top ink | Stock");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 575);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(34, 58, 89));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel stock_lbl = new JLabel("Stock :");
		stock_lbl.setForeground(new Color(255, 255, 255));
		stock_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		stock_lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		stock_lbl.setBounds(0, 11, 286, 42);
		contentPane.add(stock_lbl);
		
		
		DefaultListModel<String> l1 = new DefaultListModel<>();
		
		l1.addAll(consommables.all_conso_type(type));
		JList<String> stock_list = new JList<>(l1);
		JScrollPane scrollPane = new JScrollPane(stock_list);
		scrollPane.setBounds(10, 70, 270, 460);
		contentPane.add(scrollPane);
	}
}
