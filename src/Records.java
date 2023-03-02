import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import java.awt.Panel;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;

import java.awt.Checkbox;

import javax.swing.JRadioButton;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.ScrollPane;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Records {

	public static JFrame frameRec;
	public static JTable table;
	public static Panel panel;
	public static JScrollPane scrollPane;
	public static JRadioButton rdbtnAscending;
	public static JRadioButton rdbtnDescending;
	public static String arrange = "";
	public static String navs = "";
	public static JButton btnGeneratePdf;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Records window = new Records();
					window.frameRec.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Records() {
		initialize();
	}
Connection connection = null;
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		connection = DbCon.dbconnector();
		frameRec = new JFrame();
		frameRec.getContentPane().setBackground(new Color(160, 82, 45));
		frameRec.setBounds(100, 100, 647, 397);
		frameRec.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameRec.getContentPane().setLayout(null);
		
		JButton btnByPrice = new JButton("DAILY SALES");
		btnByPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Records.rdbtnAscending.setEnabled(true);
					Records.rdbtnDescending.setEnabled(true);
					Records.navs = "dSales";
					String query = "SELECT customer_date AS 'Date', SUM(orderline_qty) AS 'Total Sold', SUM(orderline_total) AS 'Total Earn' FROM customer,orders, orderline, item WHERE customer.customer_Id = orders.customer_Id AND orders.order_Id = orderline.order_Id AND orderline.item_id = item.item_id GROUP by customer_date Order by customer_date ASC;";				
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					Records.table.setModel(DbUtils.resultSetToTableModel(rs));
										Records.panel.add(Records.scrollPane);
				}
				catch(Exception e){e.printStackTrace();}
			}
		});
		
		JButton btnTopSales = new JButton("BEST SELLER");
		btnTopSales.setBounds(503, 66, 121, 23);
		frameRec.getContentPane().add(btnTopSales);
		btnTopSales.setIcon(null);
		btnTopSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Records.rdbtnAscending.setEnabled(true);
					Records.rdbtnDescending.setEnabled(true);
					Records.navs = "tSales";
					String query = "";				
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery("SELECT SUM(orderline_total) AS 'Total Earn', item_name AS 'Item Name', SUM(orderline_qty) AS 'Total Sold', item.item_id AS 'Item ID' FROM customer,orders, orderline, item WHERE customer.customer_Id = orders.customer_Id AND orders.order_Id = orderline.order_Id AND orderline.item_id = item.item_id GROUP by item.item_name Order by SUM(orderline_total) DESC");
					Records.table.setModel(DbUtils.resultSetToTableModel(rs));
										Records.panel.add(Records.scrollPane);
				}
				catch(Exception e){e.printStackTrace();}	
			}
		});
		btnByPrice.setBounds(264, 66, 121, 23);
		frameRec.getContentPane().add(btnByPrice);
		

		
		panel = new Panel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(34, 95, 563, 237);
		frameRec.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 563, 237);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnLoad = new JButton("VIEW ALL");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Records.rdbtnAscending.setEnabled(false);
					Records.rdbtnDescending.setEnabled(false);
					Records.navs = "view";
					String query = "Select customer.customer_Id AS 'Customer No.', orders.order_Id AS 'Order No.', customer_date AS 'Order Date',orderline_qty AS 'Quantity', orderline_total AS 'Total Price', item_name AS 'Item Name', item_price AS Price FROM customer, orders, orderline, item WHERE customer.customer_Id = orders.customer_Id AND orders.order_Id = orderline.order_id AND orderline.item_id = item.item_id UNION SELECT 'ALL TOTALS',COUNT(orderline.orderline_Id),'',SUM(orderline.orderline_qty),SUM(orderline_total),'','' from orderline";				
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					Records.table.setModel(DbUtils.resultSetToTableModel(rs));
										Records.panel.add(Records.scrollPane);
				}
				catch(Exception e){e.printStackTrace();}
				
				
			}
		});
		btnLoad.setBounds(5, 66, 94, 23);
		frameRec.getContentPane().add(btnLoad);
		
		JLabel lblBubble = new JLabel("New label");
		lblBubble.setEnabled(false);
		lblBubble.setIcon((new ImageIcon(getClass().getResource("/859783_9a17e.gif"))));
		lblBubble.setBounds(310, 399, 345, 331);
		frameRec.getContentPane().add(lblBubble);
		
		JLabel lblBubble1 = new JLabel("New label");
		lblBubble1.setEnabled(false);
		lblBubble1.setBounds(0, 395, 332, 339);
		frameRec.getContentPane().add(lblBubble1);
		lblBubble1.setIcon((new ImageIcon(getClass().getResource("/859783_9a17e.gif"))));
		
		JButton btnLogout = new JButton("Log-out");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Records.frameRec.setVisible(false);
				new Login().frame1.setVisible(true);
			}
		});
		btnLogout.setBounds(532, 19, 89, 23);
		frameRec.getContentPane().add(btnLogout);
		
		JButton btnMostSold = new JButton("MOST SOLD");
		btnMostSold.setBounds(165, 66, 109, 23);
		frameRec.getContentPane().add(btnMostSold);
		
		JLabel lblWelcome = new JLabel("WELCOME !!!");
		lblWelcome.setForeground(Color.WHITE);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblWelcome.setBounds(25, 15, 190, 19);
		frameRec.getContentPane().add(lblWelcome);
		
		String ngalan = Login.userFname;
		String apelyedo = Login.userLname;
		
		JLabel lblUser = new JLabel(" Admin: "+ngalan +" "+apelyedo);
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUser.setBounds(203, 17, 355, 19);
		frameRec.getContentPane().add(lblUser);
		
		
		//ASCENDING ====================================
		rdbtnAscending = new JRadioButton("Ascending");
		rdbtnAscending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Records.arrange = "asc";
				
				if(Records.navs == "view"){					 
					 try{	
							String query = "Select customer.customer_Id AS 'Customer No.', orders.order_Id AS 'Order No.', customer_date AS 'Order Date',orderline_qty AS 'Quantity', orderline_total AS 'Total Price', item_name AS 'Item Name', item_price AS Price FROM customer, orders, orderline, item WHERE customer.customer_Id = orders.customer_Id AND orders.order_Id = orderline.order_id AND orderline.item_id = item.item_id UNION SELECT 'ALL TOTALS',COUNT(orderline.orderline_Id),'',SUM(orderline.orderline_qty),SUM(orderline_total),'','' from orderline";				
							PreparedStatement pst = connection.prepareStatement(query);
							ResultSet rs = pst.executeQuery();
							
							Records.table.setModel(DbUtils.resultSetToTableModel(rs));
												Records.panel.add(Records.scrollPane);
						}
						catch(Exception e){e.printStackTrace();}
				}
				
				if(Records.navs == "item"){
					try{
						String query = "SELECT item_stock AS 'Item Stocks', item_id AS 'Item ID', item_name AS 'Item Name', item_price AS 'Item Price' FROM item Order by item_stock ASC";				
						PreparedStatement pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						
						Records.table.setModel(DbUtils.resultSetToTableModel(rs));
											Records.panel.add(Records.scrollPane);
					}
					catch(Exception e){e.printStackTrace();}
				}
				
				if(Records.navs == "mSold"){
					try{
						String query = " SELECT SUM(orderline_qty)AS 'Total Qty',item_name AS 'Item Name', item_price AS 'Item Price', item_stock AS 'Item Stock'FROM orderline, item WHERE orderline.item_id = item.item_id GROUP by item.item_id Order by SUM(orderline_qty) ASC;";				
						PreparedStatement pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						
						Records.table.setModel(DbUtils.resultSetToTableModel(rs));
											Records.panel.add(Records.scrollPane);
					}
					catch(Exception e){e.printStackTrace();}
				}
				
				if(Records.navs == "dSales"){
					try{
						Records.navs = "dSales";
						String query = "SELECT customer_date AS 'Date', SUM(orderline_qty) AS 'Total Sold', SUM(orderline_total) AS 'Total Earn' FROM customer,orders, orderline, item WHERE customer.customer_Id = orders.customer_Id AND orders.order_Id = orderline.order_Id AND orderline.item_id = item.item_id GROUP by customer_date Order by customer_date ASC;";				
						PreparedStatement pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						
						Records.table.setModel(DbUtils.resultSetToTableModel(rs));
											Records.panel.add(Records.scrollPane);
					}
					catch(Exception e){e.printStackTrace();}	
				}
				
				if(Records.navs == "dCustom"){
					try{
						Records.navs = "dCustom";
						String query = "SELECT COUNT(customer_id) AS 'Number of Customers' , customer_date AS 'Date' from customer Group by customer_date Order by Count(customer_id) ASC";				
						PreparedStatement pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						
						Records.table.setModel(DbUtils.resultSetToTableModel(rs));
											Records.panel.add(Records.scrollPane);
					}
					catch(Exception e){e.printStackTrace();}
				}
				
				if(Records.navs == "tSales"){
				try{
					String query = "";				
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery("SELECT SUM(orderline_total) AS 'Total Earn', item_name AS 'Item Name', SUM(orderline_qty) AS 'Total Sold', item.item_id AS 'Item ID' FROM customer,orders, orderline, item WHERE customer.customer_Id = orders.customer_Id AND orders.order_Id = orderline.order_Id AND orderline.item_id = item.item_id GROUP by item.item_name Order by SUM(orderline_total) ASC");
					Records.table.setModel(DbUtils.resultSetToTableModel(rs));
										Records.panel.add(Records.scrollPane);
				}
				catch(Exception e){e.printStackTrace();}
				}
				
				
				
			}
		});
		rdbtnAscending.setBackground(Color.LIGHT_GRAY);
		rdbtnAscending.setBounds(488, 328, 109, 23);
		frameRec.getContentPane().add(rdbtnAscending);
		
		
		//DESCENDING=====================================
		rdbtnDescending = new JRadioButton("Descending");
		rdbtnDescending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Records.arrange = "desc";
				if(Records.navs == "view"){					 
					 try{	
						String query = "Select customer.customer_Id AS 'Customer No.', orders.order_Id AS 'Order No.', customer_date AS 'Order Date',orderline_qty AS 'Quantity', orderline_total AS 'Total Price', item_name AS 'Item Name', item_price AS Price FROM customer, orders, orderline, item WHERE customer.customer_Id = orders.customer_Id AND orders.order_Id = orderline.order_id AND orderline.item_id = item.item_id UNION SELECT 'ALL TOTALS',COUNT(orderline.orderline_Id),'',SUM(orderline.orderline_qty),SUM(orderline_total),'','' from orderline";				
						 PreparedStatement pst = connection.prepareStatement(query);
							ResultSet rs = pst.executeQuery();
							
							Records.table.setModel(DbUtils.resultSetToTableModel(rs));
												Records.panel.add(Records.scrollPane);
						}
						catch(Exception e){e.printStackTrace();}
				}
				
				
				
				if(Records.navs == "item"){
					try{
						String query = "SELECT item_stock AS 'Item Stocks', item_id AS 'Item ID', item_name AS 'Item Name', item_price AS 'Item Price' FROM item Order by item_stock DESC";				
						PreparedStatement pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						
						Records.table.setModel(DbUtils.resultSetToTableModel(rs));
											Records.panel.add(Records.scrollPane);
					}
					catch(Exception e){e.printStackTrace();}
				}
				
				if(Records.navs == "mSold"){
					try{
						String query = " SELECT SUM(orderline_qty)AS 'Total Qty',item_name AS 'Item Name', item_price AS 'Item Price', item_stock AS 'Item Stock'FROM orderline, item WHERE orderline.item_id = item.item_id GROUP by item.item_id Order by SUM(orderline_qty) DESC;";				
						PreparedStatement pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						
						Records.table.setModel(DbUtils.resultSetToTableModel(rs));
											Records.panel.add(Records.scrollPane);
					}
					catch(Exception e){e.printStackTrace();}
				}
				if(Records.navs == "dSales"){
					try{
						Records.navs = "dSales";
						String query = "SELECT customer_date AS 'Date', SUM(orderline_qty) AS 'Total Sold', SUM(orderline_total) AS 'Total Earn' FROM customer,orders, orderline, item WHERE customer.customer_Id = orders.customer_Id AND orders.order_Id = orderline.order_Id AND orderline.item_id = item.item_id GROUP by customer_date Order by customer_date DESC;";				
						PreparedStatement pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						
						Records.table.setModel(DbUtils.resultSetToTableModel(rs));
											Records.panel.add(Records.scrollPane);
					}
					catch(Exception e){e.printStackTrace();}	
				}
				
				if(Records.navs == "dCustom"){
					try{
						Records.navs = "dCustom";
						String query = "SELECT COUNT(customer_id) AS 'Number of Customers' , customer_date AS 'Date' from customer Group by customer_date Order by Count(customer_id) ASC";				
						PreparedStatement pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						
						Records.table.setModel(DbUtils.resultSetToTableModel(rs));
											Records.panel.add(Records.scrollPane);
					}
					catch(Exception e){e.printStackTrace();}
				}
				
				if(Records.navs == "tSales"){
				try{
				
					String query = "";				
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery("SELECT SUM(orderline_total) AS 'Total Earn', item_name AS 'Item Name', SUM(orderline_qty) AS 'Total Sold', item.item_id AS 'Item ID' FROM customer,orders, orderline, item WHERE customer.customer_Id = orders.customer_Id AND orders.order_Id = orderline.order_Id AND orderline.item_id = item.item_id GROUP by item.item_name Order by SUM(orderline_total) DESC");
					Records.table.setModel(DbUtils.resultSetToTableModel(rs));
										Records.panel.add(Records.scrollPane);
				}
				catch(Exception e){e.printStackTrace();}
				}
				
			}
		});
		rdbtnDescending.setBackground(Color.LIGHT_GRAY);
		rdbtnDescending.setBounds(379, 328, 109, 23);
		frameRec.getContentPane().add(rdbtnDescending);
		
		ButtonGroup rbtGroup = new ButtonGroup();
		rbtGroup.add(rdbtnAscending);
		rbtGroup.add(rdbtnDescending);
		
		JButton btnByItem = new JButton("STOCKS");
		btnByItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Records.rdbtnAscending.setEnabled(true);
					Records.rdbtnDescending.setEnabled(true);
					Records.navs = "item";
					String query = "SELECT item_stock AS 'Item Stocks', item_id AS 'Item ID', item_name AS 'Item Name', item_price AS 'Item Price' FROM item";				
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					Records.table.setModel(DbUtils.resultSetToTableModel(rs));
										Records.panel.add(Records.scrollPane);
				}
				catch(Exception e){e.printStackTrace();}
			}
		});
		btnByItem.setBounds(85, 66, 94, 23);
		frameRec.getContentPane().add(btnByItem);
		
		JButton btnByTotalPrice = new JButton("DAILY CUSTOMERS");
		btnByTotalPrice.setBounds(367, 66, 155, 23);
		frameRec.getContentPane().add(btnByTotalPrice);
		
		btnGeneratePdf = new JButton("Generate PDF");
		btnGeneratePdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					String path= "";
					String fileName = JOptionPane.showInputDialog("Insert New PDF Name: ");
					JFileChooser j = new  JFileChooser();
					j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int x = j.showSaveDialog(j);
				if(x == JFileChooser.APPROVE_OPTION){
					path = j.getSelectedFile().getPath();
				}
				
				Document doc = new Document();
				
				try {
					PdfWriter.getInstance(doc, new FileOutputStream(path+ fileName + ".pdf"));
					
					doc.open();
					PdfPTable tbl = new PdfPTable(7);	
					
					
					//Adding Header
					tbl.addCell("Customer No");
					tbl.addCell("Order No");
					tbl.addCell("Order Date");
					tbl.addCell("Quantity");
					tbl.addCell("Total Price");
					tbl.addCell("Item Name");
					tbl.addCell("Price");
					
					Paragraph p = new Paragraph("Vending Machine All Records For " + new EventListener().petsa );
					Paragraph space = new Paragraph("       " );
					
					for (int i = 0; i < Records.table.getRowCount(); i++){
						
						String ID = Records.table.getValueAt(i, 0).toString();
						String orderId = Records.table.getValueAt(i, 1).toString();
						String date = (String) Records.table.getValueAt(i, 2);
						String qty = (String) Records.table.getValueAt(i, 3).toString();
						String tPrice = (String) Records.table.getValueAt(i, 4).toString();
						String itemName = (String) Records.table.getValueAt(i, 5);
						String price = (String) Records.table.getValueAt(i, 6).toString();
						
						tbl.addCell(ID);
						tbl.addCell(orderId);
						tbl.addCell(date);
						tbl.addCell(qty);
						tbl.addCell(tPrice);
						tbl.addCell(itemName);
						tbl.addCell(price);		
					}
					
					doc.add(p);
					doc.add(space);
					doc.add(tbl);
				} catch (FileNotFoundException | DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally{
					System.out.print("View all Inserted");
				}
				
				
				
				//stock.pdf
				if (Records.navs == "item"){
					try {
						PdfWriter.getInstance(doc, new FileOutputStream(path+ fileName + ".pdf"));
						
						doc.open();
						PdfPTable tbl = new PdfPTable(4);	
						//Adding Header
						tbl.addCell("Item Stocks");
						tbl.addCell("Item ID");
						tbl.addCell("Item Name");
						tbl.addCell("Item Price");
						
						Paragraph p = new Paragraph("Vending Machine All Item Stocks For " + new EventListener().petsa );
						Paragraph space = new Paragraph("       " );
						
						for (int i = 0; i < Records.table.getRowCount(); i++){
							
							String itemStock = Records.table.getValueAt(i, 0).toString();
							String itemId = Records.table.getValueAt(i, 1).toString();
							String itemName = (String) Records.table.getValueAt(i, 2);
							String itemPrice = (String) Records.table.getValueAt(i, 3).toString();
				
							
							tbl.addCell(itemStock);
							tbl.addCell(itemId);
							tbl.addCell(itemName);
							tbl.addCell(itemPrice);
							
						}
						doc.add(p);
						doc.add(space);
						doc.add(tbl);
					} catch (FileNotFoundException | DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally{
						System.out.print("Stocks Inserted");
					}
					}
				
				
				//mostSold.pdf
				if (Records.navs == "mSold"){
					try {
						PdfWriter.getInstance(doc, new FileOutputStream(path+ fileName + ".pdf"));
						
						doc.open();
						PdfPTable tbl = new PdfPTable(4);	
						//Adding Header
						tbl.addCell("Total Quantity");
						tbl.addCell("Item Name");
						tbl.addCell("Item Price");
						tbl.addCell("Item Stock");
						
						Paragraph p = new Paragraph("Vending Machine Most Sold Item as of " + new EventListener().petsa );
						Paragraph space = new Paragraph("       " );
						
						for (int i = 0; i < Records.table.getRowCount(); i++){
							
							String quan = Records.table.getValueAt(i, 0).toString();
							String itemName = Records.table.getValueAt(i, 1).toString();
							String itemPrice =  Records.table.getValueAt(i, 2).toString();
							String itemStock =  Records.table.getValueAt(i, 3).toString();
				
							tbl.addCell(quan);
							tbl.addCell(itemName);
							tbl.addCell(itemPrice);
							tbl.addCell(itemStock);
							
						}
						doc.add(p);
						doc.add(space);
						doc.add(tbl);
					} catch (FileNotFoundException | DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally{
						System.out.print("Most Sold Inserted");
					}
					}
				
				
				
				//dailySales.pdf
				if (Records.navs == "dSales"){
					try {
						PdfWriter.getInstance(doc, new FileOutputStream(path+ fileName + ".pdf"));
						
						doc.open();
						PdfPTable tbl = new PdfPTable(3);	
						//Adding Header
						tbl.addCell("Date");
						tbl.addCell("Total Sold");
						tbl.addCell("Total Earn");
						
						Paragraph p = new Paragraph("Vending Machine Daily Sales Report as of " + new EventListener().petsa );
						Paragraph space = new Paragraph("       " );
						
						for (int i = 0; i < Records.table.getRowCount(); i++){
							
							String date = Records.table.getValueAt(i, 0).toString();
							String totalSold = Records.table.getValueAt(i, 1).toString();
							String totalEarn =  Records.table.getValueAt(i, 2).toString();
				
							tbl.addCell(date);
							tbl.addCell(totalSold);
							tbl.addCell(totalEarn);
							
							
						}
						doc.add(p);
						doc.add(space);
						doc.add(tbl);
					} catch (FileNotFoundException | DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally{
						System.out.print("Daily sales Inserted");
					}
					}
				
				//dailyCustomer.pdf
				if (Records.navs == "dCustom"){
					try {
						PdfWriter.getInstance(doc, new FileOutputStream(path+ fileName + ".pdf"));
						
						doc.open();
						PdfPTable tbl = new PdfPTable(2);	
						//Adding Header
						tbl.addCell("Number of Customers");
						tbl.addCell("Date");
						
						Paragraph p = new Paragraph("Vending Machine Daily Number of Customer Report as of " + new EventListener().petsa );
						Paragraph space = new Paragraph("       " );
						
						for (int i = 0; i < Records.table.getRowCount(); i++){
							
							String numCus = Records.table.getValueAt(i, 0).toString();
							String date = Records.table.getValueAt(i, 1).toString();
						
				
							tbl.addCell(numCus);
							tbl.addCell(date);				
						}
						doc.add(p);
						doc.add(space);
						doc.add(tbl);
					} catch (FileNotFoundException | DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally{
						System.out.print("Daily Customer Inserted");
					}
					}
				
				
				if (Records.navs == "tSales"){
					try {
						PdfWriter.getInstance(doc, new FileOutputStream(path+ fileName + ".pdf"));
						
						doc.open();
						PdfPTable tbl = new PdfPTable(4);	
						//Adding Header
						tbl.addCell("Total Earn");
						tbl.addCell("Item Name");
						tbl.addCell("Total Sold");
						tbl.addCell("Item ID");
						
						Paragraph p = new Paragraph("Vending Machine Daily Number of Customer Report as of " + new EventListener().petsa );
						Paragraph space = new Paragraph("       " );
						
						for (int i = 0; i < Records.table.getRowCount(); i++){
							
							String totalE = Records.table.getValueAt(i, 0).toString();
							String itemN = Records.table.getValueAt(i, 1).toString();
							String totalS = Records.table.getValueAt(i, 2).toString();
							String itemi  = Records.table.getValueAt(i, 3).toString();
						
				
							tbl.addCell(totalE);
							tbl.addCell(itemN);
							tbl.addCell(totalS);
							tbl.addCell(itemi);
						}
						doc.add(p);
						doc.add(space);
						doc.add(tbl);
					} catch (FileNotFoundException | DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally{
						System.out.print("Best Seller Inserted");
					}
					}
				
				doc.close();
			}
		});
		btnGeneratePdf.setBounds(5, 334, 136, 23);
		frameRec.getContentPane().add(btnGeneratePdf);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon((new ImageIcon(getClass().getResource("/MealyOrangeAgouti-size_restricted.gif"))));
		lblNewLabel.setBounds(0, 0, 644, 371);
		frameRec.getContentPane().add(lblNewLabel);
		btnByTotalPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Records.rdbtnAscending.setEnabled(true);
					Records.rdbtnDescending.setEnabled(true);
					Records.navs = "dCustom";
					String query = "SELECT COUNT(customer_id) AS 'Number of Customers' , customer_date AS 'Date' from customer Group by customer_date Order by Count(customer_id) ASC";				
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					Records.table.setModel(DbUtils.resultSetToTableModel(rs));
										Records.panel.add(Records.scrollPane);
				}
				catch(Exception e){e.printStackTrace();}
				
				
			}
		});
		btnMostSold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Records.rdbtnAscending.setEnabled(true);
					Records.rdbtnDescending.setEnabled(true);
					Records.navs = "mSold";
					String query = " SELECT SUM(orderline_qty)AS 'Total Qty',item_name AS 'Item Name', item_price AS 'Item Price', item_stock AS 'Item Stock' FROM orderline, item WHERE orderline.item_id = item.item_id GROUP by item.item_id Order by SUM(orderline_qty) DESC";				
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					Records.table.setModel(DbUtils.resultSetToTableModel(rs));
										Records.panel.add(Records.scrollPane);
				}
				catch(Exception e){e.printStackTrace();}
				
			}
		});
		
	}
}
