import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.sql.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


public class Login{
	public static JFrame frame1;
	public static JPanel contentPane;
	public static JTextField textField;
	public static JPasswordField passwordField;
	public static String userFname, userLname;
	public static JSlider slideVolume;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame1.setVisible(true);
					String filepath = "music.wav";
					PlayMusic tunog = new PlayMusic();
					tunog.playMusic(filepath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
Connection connection = null;
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */


	public Login() {
		connection =DbCon.dbconnector();
		frame1 = new JFrame();
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setBounds(100, 100, 618, 395);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(160, 82, 45));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame1.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Are you thirsty ?");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Source Code Pro Black", Font.BOLD, 32));
		lblNewLabel_1.setBounds(127, 24, 357, 61);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Drink Now !!!");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(236, 103, 140, 25);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Dring Now !!!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//Login;
			new SampleProject().frame.setVisible(true);
			Login.frame1.setVisible(false);
			
			}
		});
		btnNewButton.setIcon((new ImageIcon(getClass().getResource("/button.png"))));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(190, 96, 221, 39);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2_1 = new JLabel("Log in :");
		lblNewLabel_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(24, 235, 140, 25);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("For admin only!!!");
		lblNewLabel_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_2_1_1.setBounds(24, 257, 140, 25);
		contentPane.add(lblNewLabel_2_1_1);
		
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(83, 306, 164, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Username:");
		lblNewLabel_2_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_2_1_1_1.setBounds(83, 282, 140, 25);
		contentPane.add(lblNewLabel_2_1_1_1);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Password:");
		lblNewLabel_2_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1_1_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_2_1_1_1_1.setBounds(285, 282, 140, 25);
		contentPane.add(lblNewLabel_2_1_1_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(Color.LIGHT_GRAY);
		passwordField.setBounds(285, 306, 164, 25);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_3 = new JLabel("Log-in");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(483, 311, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnLogin = new JButton("Log-in");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					
					Statement stmt = connection.createStatement();
					String sql = "SELECT * FROM users WHERE username='"+Login.textField.getText()+"'and password='"+Login.passwordField.getText().toString()+"'";             	
					ResultSet rs = stmt.executeQuery(sql);
					
					if(rs.next()){
						Login.userFname = rs.getString("user_fname");
						Login.userLname = rs.getString("user_lname");
						Login.frame1.setVisible(false);
						new Records().frameRec.setVisible(true);
						//JOptionPane.showMessageDialog(null,"Login Successfully");
					}
					else{
						JOptionPane.showMessageDialog(null,"Incorrect username or password");
						
					}
					
				}catch(Exception e){System.out.print(e);}
				
				
			}
		});
		btnLogin.setIcon((new ImageIcon(getClass().getResource("/button_black.png"))));
		btnLogin.setBounds(465, 307, 69, 23);
		contentPane.add(btnLogin);
		
		slideVolume = new JSlider();
		slideVolume.setMaximum(10);
		slideVolume.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
			if(Login.slideVolume.getValue()== 10){
				PlayMusic.gainControl.setValue(6.0f);
			}
			if(Login.slideVolume.getValue()== 9){
				PlayMusic.gainControl.setValue(4.0f);
			}
			if(Login.slideVolume.getValue()== 8){
				PlayMusic.gainControl.setValue(2.0f);
			}
			if(Login.slideVolume.getValue()== 7){
				PlayMusic.gainControl.setValue(0.0f);
			}
			if(Login.slideVolume.getValue()== 6){
				PlayMusic.gainControl.setValue(-5.0f);
			}
			if(Login.slideVolume.getValue()== 5){
				PlayMusic.gainControl.setValue(-10.0f);
			}
			if(Login.slideVolume.getValue()== 4){
				PlayMusic.gainControl.setValue(-15.0f);
			}
			if(Login.slideVolume.getValue()== 3){
				PlayMusic.gainControl.setValue(-20.0f);
			}
			if(Login.slideVolume.getValue()== 2){
				PlayMusic.gainControl.setValue(-25.0f);
			}
			if(Login.slideVolume.getValue()== 1){
				PlayMusic.gainControl.setValue(-80.0f);
			}
			}
		});
		slideVolume.setPaintTicks(true);
		slideVolume.setValue(10);
		slideVolume.setOrientation(SwingConstants.VERTICAL);
		slideVolume.setBackground(new Color(0, 0, 0));
		slideVolume.setBounds(575, 244, 17, 87);
		contentPane.add(slideVolume);
		
		JLabel label = new JLabel("   +");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setForeground(Color.WHITE);
		label.setBackground(Color.WHITE);
		label.setBounds(559, 225, 34, 14);
		contentPane.add(label);
		
		JLabel label_2 = new JLabel("   -");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		label_2.setBackground(Color.WHITE);
		label_2.setBounds(559, 330, 34, 14);
		contentPane.add(label_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon((new ImageIcon(getClass().getResource("/MealyOrangeAgouti-size_restricted.gif"))));
		lblNewLabel.setBounds(0, 0, 602, 356);
		contentPane.add(lblNewLabel);
	}
}
