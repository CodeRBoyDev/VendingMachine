import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JSpinner;
import java.awt.Panel;
import javax.swing.SpinnerNumberModel;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SampleProject {

	public static JFrame frame;
	public JTextField txtQty;
	public JTextField txtEnterCash;
	public static JTextField txtCash;
	public static JTextField textTotal;
	public JTextField lblTotalPrice;
	public static JTextField textChange;
	public JTextField lblChange;
	public static JButton btnSprite;
	public static JButton btnPepsi;
	public static JButton btn7up;
	public static JButton btnCoke;
	public static JSpinner spinnerQty;
	public static JLabel cokeImg1;
	public static JLabel cokeImg2;
	public static JLabel cokeImg3;
	public static JLabel cokeImg4;
	public static JLabel spriteImg1;
	public static JLabel spriteImg2;
	public static JLabel spriteImg3;
	public static JLabel spriteImg4;
	public static JLabel pepsiImage1;
	public static JLabel pepsiImage2;
	public static JLabel pepsiImage3;
	public static JLabel pepsiImage4;
	public static JLabel up7Images1;
	public static JLabel up7Images2;
	public static JLabel up7Images3;
	public static JLabel up7Images4;
	public static JButton btnPay ;
	public static JButton btnCancel;
	public static double price = 0.0;
	private JButton btnHome;
	public static int occur;
	public static int cokeStock =100;
	public static int pepsiStock =100;
	public static int spriteStock =100;;
	public static int up7Stock =100;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SampleProject window = new SampleProject();
				window.frame.setVisible(true);
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public SampleProject() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		frame = new JFrame();
		
		frame.getContentPane().setBackground(Color.PINK);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		//Quantity Spinner
		spinnerQty = new JSpinner();
		spinnerQty.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent arg0) {
				int qty;
				double total;
				qty = (Integer) SampleProject.spinnerQty.getValue();
				total = qty * SampleProject.price;
				SampleProject.textTotal.setText(String.valueOf(total));
			}
		});
		
		btnHome = new JButton("<< BACK");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SampleProject.frame.setVisible(false);
				new Login().frame1.setVisible(true);
			}
		});
		btnHome.setBounds(4, 472, 86, 23);
		frame.getContentPane().add(btnHome);
		
		spinnerQty.setModel(new SpinnerNumberModel(0, 0, 4, 1));
		spinnerQty.setBounds(463, 261, 104, 20);
		frame.getContentPane().add(spinnerQty);
		
		//Enter Cash
		txtCash = new JTextField();
		txtCash.setBounds(361, 320, 206, 20);
		frame.getContentPane().add(txtCash);
		txtCash.setColumns(10);
		
		txtEnterCash = new JTextField();
		txtEnterCash.setForeground(new Color(255, 255, 255));
		txtEnterCash.setText("Enter Cash");
		txtEnterCash.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
		txtEnterCash.setColumns(10);
		txtEnterCash.setBorder(null);
		txtEnterCash.setBackground(new Color(255, 0, 0));
		txtEnterCash.setBounds(435, 290, 86, 23);
		frame.getContentPane().add(txtEnterCash);
		
		// PAY TRANS...
		
		 btnPay = new JButton("PAY");
		btnPay.addActionListener(new EventListener()); 
		
		btnPay.setBounds(371, 355, 86, 23);
		frame.getContentPane().add(btnPay);
		
		btnCancel = new JButton("CLEAR");
		btnCancel.addActionListener(new EventListener()); 
		
		btnCancel.setBounds(481, 355, 86, 23);
		frame.getContentPane().add(btnCancel);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(Color.RED);
		panel_1.setBounds(348, 10, 232, 245);
		frame.getContentPane().add(panel_1);
		
		
		//Sprite Button
		
		btnSprite = new JButton("\u20B1 23.00");
		btnSprite.setBounds(5, 63, 223, 53);
		btnSprite.addActionListener(new EventListener());
		panel_1.setLayout(null);
		
		
		//Coke Button
		
		btnCoke = new JButton("24.00");
		btnCoke.setBounds(5, 5, 223, 53);
		panel_1.add(btnCoke);
		btnCoke.addActionListener(new EventListener());
		btnCoke.setIcon(new ImageIcon (new ImageIcon(getClass().getResource("/Coca-Cola-Logo.png")).getImage().getScaledInstance(150, 45, Image.SCALE_SMOOTH)));
		panel_1.add(btnSprite);
		
		    btnSprite.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Sprite_logo.png")).getImage().getScaledInstance(150, 45, Image.SCALE_SMOOTH)));
			btnSprite.setSelectedIcon(null);
			btnSprite.setForeground(Color.BLACK);
			btnSprite.setBackground(Color.WHITE);
			
		//Pepsi Button
		
			btnPepsi = new JButton("\u20B1 26.00");
			btnPepsi.setBounds(5, 121, 223, 53);
			btnPepsi.addActionListener(new EventListener()); 	
			panel_1.add(btnPepsi);
			btnPepsi.setForeground(Color.BLACK);
			btnPepsi.setBackground(Color.WHITE);
			btnPepsi.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Pepsi-Logo.png")).getImage().getScaledInstance(150, 45, Image.SCALE_SMOOTH)));
			
			btnPepsi.setSelectedIcon(null);
			
		//7-up Button
			
			btn7up = new JButton("\u20B1 25.00");
			btn7up.setBounds(5, 179, 223, 53);
			btn7up.addActionListener(new EventListener()); 		
		
			panel_1.add(btn7up);
			btn7up.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/7Up-Logo.png")).getImage().getScaledInstance(150, 45, Image.SCALE_SMOOTH)));
			
			txtQty = new JTextField();
			txtQty.setForeground(new Color(255, 255, 255));
			txtQty.setBounds(424, 266, 36, 15);
			frame.getContentPane().add(txtQty);
			txtQty.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
			txtQty.setBorder(null);
			txtQty.setBackground(new Color(255, 0, 0));
			txtQty.setText("QTY:");
			txtQty.setColumns(10);
			
			JPanel panel = new JPanel();
			panel.setBackground(Color.RED);
			panel.setBounds(348, 255, 232, 137);
			frame.getContentPane().add(panel);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBackground(Color.RED);
			panel_2.setBounds(348, 400, 232, 95);
			frame.getContentPane().add(panel_2);
			panel_2.setLayout(null);
			
			lblTotalPrice = new JTextField();
			lblTotalPrice.setForeground(Color.WHITE);
			lblTotalPrice.setBounds(15, 7, 110, 16);
			lblTotalPrice.setText("Total Price:");
			lblTotalPrice.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
			lblTotalPrice.setColumns(10);
			lblTotalPrice.setBorder(null);
			lblTotalPrice.setBackground(Color.RED);
			panel_2.add(lblTotalPrice);
			
			textTotal = new JTextField();
			textTotal.setBounds(130, 5, 86, 20);
			textTotal.setColumns(10);
			panel_2.add(textTotal);
			
			lblChange = new JTextField();
			lblChange.setForeground(Color.WHITE);
			lblChange.setBounds(15, 32, 110, 16);
			lblChange.setText("Change:");
			lblChange.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
			lblChange.setColumns(10);
			lblChange.setBorder(null);
			lblChange.setBackground(Color.RED);
			panel_2.add(lblChange);
			
			textChange = new JTextField();
			textChange.setBounds(130, 30, 86, 20);
			textChange.setColumns(10);
			panel_2.add(textChange);
			
			JLabel lblVendingMachine = new JLabel("Vending Machine");
			lblVendingMachine.setForeground(Color.WHITE);
			lblVendingMachine.setFont(new Font("Tahoma", Font.BOLD, 32));
			lblVendingMachine.setBounds(31, 0, 314, 63);
			frame.getContentPane().add(lblVendingMachine);
			
			
			//Coke Images
			cokeImg1 = new JLabel("");
			cokeImg1.setVerticalAlignment(SwingConstants.TOP);
			cokeImg1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/48415195_349825042502054_6089951544814338048_n.png")).getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH)));
			cokeImg1.setBounds(85, 134, 71, 69);
			frame.getContentPane().add(cokeImg1);
			
			cokeImg2 = new JLabel("");
			cokeImg2.setVerticalAlignment(SwingConstants.TOP);
			cokeImg2.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/48415195_349825042502054_6089951544814338048_n.png")).getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH)));
			cokeImg2.setBounds(166, 134, 71, 69);
			frame.getContentPane().add(cokeImg2);
			
			cokeImg3 = new JLabel("");
			cokeImg3.setVerticalAlignment(SwingConstants.TOP);
			cokeImg3.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/48415195_349825042502054_6089951544814338048_n.png")).getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH)));
			cokeImg3.setBounds(240, 134, 71, 69);
			frame.getContentPane().add(cokeImg3);
			
			cokeImg4 = new JLabel("");
			cokeImg4.setVerticalAlignment(SwingConstants.TOP);
			cokeImg4.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/48415195_349825042502054_6089951544814338048_n.png")).getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH)));
			cokeImg4.setBounds(19, 134, 71, 69);
			frame.getContentPane().add(cokeImg4);
			
			
			//Sprite Images
			spriteImg1 = new JLabel("New label");
			spriteImg1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/58732994_571061753386142_8978672073371025408_n.png")).getImage().getScaledInstance(75, 60, Image.SCALE_SMOOTH)));
			spriteImg1.setBounds(100, 195, 56, 86);
			frame.getContentPane().add(spriteImg1);
			
			
			spriteImg2 = new JLabel("New label");
			spriteImg2.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/58732994_571061753386142_8978672073371025408_n.png")).getImage().getScaledInstance(75, 60, Image.SCALE_SMOOTH)));
			spriteImg2.setBounds(255, 195, 56, 86);
			frame.getContentPane().add(spriteImg2);
			
			spriteImg3 = new JLabel("New label");
			spriteImg3.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/58732994_571061753386142_8978672073371025408_n.png")).getImage().getScaledInstance(75, 60, Image.SCALE_SMOOTH)));
			spriteImg3.setBounds(181, 195, 56, 86);
			frame.getContentPane().add(spriteImg3);
			
			spriteImg4 = new JLabel("New label");
			spriteImg4.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/58732994_571061753386142_8978672073371025408_n.png")).getImage().getScaledInstance(75, 60, Image.SCALE_SMOOTH)));
			spriteImg4.setBounds(31, 195, 56, 86);
			frame.getContentPane().add(spriteImg4);
			
			//Pepsi Images
			
			pepsiImage1 = new JLabel("New label");
			pepsiImage1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/50549167_280431315955724_1550688068090986496_n.png")).getImage().getScaledInstance(40, 60, Image.SCALE_SMOOTH)));
			pepsiImage1.setBounds(45, 266, 42, 86);
			frame.getContentPane().add(pepsiImage1);
			
			pepsiImage2 = new JLabel("New label");
			pepsiImage2.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/50549167_280431315955724_1550688068090986496_n.png")).getImage().getScaledInstance(40, 60, Image.SCALE_SMOOTH)));
			pepsiImage2.setBounds(120, 265, 42, 86);
			frame.getContentPane().add(pepsiImage2);
			
			pepsiImage3 = new JLabel("New label");
			pepsiImage3.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/50549167_280431315955724_1550688068090986496_n.png")).getImage().getScaledInstance(40, 60, Image.SCALE_SMOOTH)));
			pepsiImage3.setBounds(269, 265, 42, 86);
			frame.getContentPane().add(pepsiImage3);
			
			pepsiImage4 = new JLabel("New label");
			pepsiImage4.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/50549167_280431315955724_1550688068090986496_n.png")).getImage().getScaledInstance(40, 60, Image.SCALE_SMOOTH)));
			pepsiImage4.setBounds(195, 265, 42, 86);
			frame.getContentPane().add(pepsiImage4);
			
			//7-UP images
		
			up7Images1 = new JLabel("New label");
			up7Images1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/78927939_534151110504281_7080905305793495040_n.png")).getImage().getScaledInstance(65, 75, Image.SCALE_SMOOTH)));
			up7Images1.setBounds(107, 336, 63, 86);
			frame.getContentPane().add(up7Images1);
			
			up7Images2 = new JLabel("New label");
			up7Images2.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/78927939_534151110504281_7080905305793495040_n.png")).getImage().getScaledInstance(65, 75, Image.SCALE_SMOOTH)));
			up7Images2.setBounds(182, 336, 63, 86);
			frame.getContentPane().add(up7Images2);
			
			up7Images3 = new JLabel("New label");
			up7Images3.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/78927939_534151110504281_7080905305793495040_n.png")).getImage().getScaledInstance(65, 75, Image.SCALE_SMOOTH)));
			up7Images3.setBounds(258, 336, 63, 86);
			frame.getContentPane().add(up7Images3);
			
			up7Images4 = new JLabel("New label");
			up7Images4.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/78927939_534151110504281_7080905305793495040_n.png")).getImage().getScaledInstance(65, 75, Image.SCALE_SMOOTH)));
			up7Images4.setBounds(33, 336, 63, 86);
			frame.getContentPane().add(up7Images4);
			
			JLabel ref = new JLabel("");
			ref.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/shelf_template_portrait_preview.png")).getImage().getScaledInstance(338, 452, Image.SCALE_SMOOTH)));
			ref.setBounds(4, 63, 338, 432);
			frame.getContentPane().add(ref);
			
 		frame.setBounds(100, 100, 606, 545);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
