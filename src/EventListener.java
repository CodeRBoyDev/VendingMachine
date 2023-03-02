import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

public class EventListener  implements ActionListener{
	Connection connection = null;
	public static String petsa;
	public static int lastCust;
	public static int lastOrd;
	public static int itemId;
	
	public EventListener(){
		connection = DbCon.dbconnector();
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		   LocalDateTime now = LocalDateTime.now();  
		  this.petsa = (dtf.format(now));
	}

	public void actionPerformed(ActionEvent e) {
	
		//Button
	if(e.getSource() == SampleProject.btnCoke){
		double pricer;
		pricer = SampleProject.price = 24.00;
		SampleProject.btnSprite.setEnabled(false);
		SampleProject.btnPepsi.setEnabled(false);
		SampleProject.btn7up.setEnabled(false);
		
		int qty;
		double total;
		qty = (Integer) SampleProject.spinnerQty.getValue();
		total = qty * SampleProject.price;
		SampleProject.textTotal.setText( String.valueOf(total));
	}
	
	if (e.getSource() == SampleProject.btnSprite){
		double pricer;
		pricer = SampleProject.price = 23.00;
		
		SampleProject.btnCoke.setEnabled(false);
		SampleProject.btnPepsi.setEnabled(false);
		SampleProject.btn7up.setEnabled(false);
		
		int qty;
		double total;
		qty = (Integer) SampleProject.spinnerQty.getValue();
		total = qty * SampleProject.price;
		SampleProject.textTotal.setText( String.valueOf(total));
	}
	
	if (e.getSource() == SampleProject.btnPepsi){
		double pricer = SampleProject.price = 26.00;
		
		SampleProject.btnSprite.setEnabled(false);
		SampleProject.btnCoke.setEnabled(false);
		SampleProject.btn7up.setEnabled(false);
		
		int qty;
		double total;
		qty = (Integer) SampleProject.spinnerQty.getValue();
		total = qty * SampleProject.price;
		SampleProject.textTotal.setText( String.valueOf(total));
	}
	
	if (e.getSource() == SampleProject.btn7up){
		double pricer = SampleProject.price = 25.00;
		SampleProject.btnSprite.setEnabled(false);
		SampleProject.btnCoke.setEnabled(false);
		SampleProject.btnPepsi.setEnabled(false);
		
		int qty;
		double total;
		qty = (Integer) SampleProject.spinnerQty.getValue();
		total = qty * SampleProject.price;
		SampleProject.textTotal.setText( String.valueOf(total));
	}
	
	//Payment
	if (e.getSource() == SampleProject.btnPay){
		
		String selectedItem = "";
		int qty = (Integer)SampleProject.spinnerQty.getValue();
		//Filtering
		JFrame f = new JFrame();
		if (SampleProject.price == 0.0 ){
			JOptionPane.showMessageDialog(f, "Please Select Your Item");  
		}
		else if(qty == 0){
			JOptionPane.showMessageDialog(f, "Please Select Quantity");  
		}
		else{
		String totalS = SampleProject.textTotal.getText();
		double totalP = Double.parseDouble(totalS);	
		
	//For Change
		double change;
		String cashS = SampleProject.txtCash.getText();
	
		if(cashS.isEmpty()){
			JOptionPane.showMessageDialog(f, "Enter Cash");
			
		}else{
			try{
				double cash = Double.parseDouble(cashS);
				change = cash - totalP;
				
				if (change < 0.0){
					SampleProject.textChange.setText("");
					JOptionPane.showMessageDialog(f, "Your cash is not enough, add "+ (totalP-cash));
					
				}else{
				SampleProject.textChange.setText(String.valueOf(change));
			
					//Coke
				if (SampleProject.price == 24){
					selectedItem = "Coke";
					this.itemId = 1;
					if(qty == 1)
					{
						SampleProject.cokeImg4.setEnabled(false);
					}
					
					else if(qty == 2)
					{
						SampleProject.cokeImg4.setEnabled(false);
						SampleProject.cokeImg1.setEnabled(false);
					}
					else if (qty == 3)
					{
						SampleProject.cokeImg4.setEnabled(false);
						SampleProject.cokeImg1.setEnabled(false);
						SampleProject.cokeImg2.setEnabled(false);
					
					}	
					else
					{
						SampleProject.cokeImg4.setEnabled(false);
						SampleProject.cokeImg1.setEnabled(false);
						SampleProject.cokeImg2.setEnabled(false);
						SampleProject.cokeImg3.setEnabled(false);
					}
						
								}
					//Pepsi
				if (SampleProject.price == 26){
					selectedItem = "Pepsi";
					this.itemId = 2;
					if(qty == 1)
					{
						SampleProject.pepsiImage1.setEnabled(false);
					}
					
					else if(qty == 2)
					{
						SampleProject.pepsiImage1.setEnabled(false);
						SampleProject.pepsiImage2.setEnabled(false);
					}
					else if (qty == 3)
					{
						SampleProject.pepsiImage1.setEnabled(false);
						SampleProject.pepsiImage2.setEnabled(false);
						SampleProject.pepsiImage4.setEnabled(false);
					
					}	
					else
					{
						SampleProject.pepsiImage1.setEnabled(false);
						SampleProject.pepsiImage2.setEnabled(false);
						SampleProject.pepsiImage4.setEnabled(false);
						SampleProject.pepsiImage3.setEnabled(false);
					}
						
								}
				//Sprite 
				if (SampleProject.price == 23){
					selectedItem = "Sprite";
					this.itemId = 3;
					if(qty == 1)
					{
						SampleProject.spriteImg1.setEnabled(false);
					}
					
					else if(qty == 2)
					{
						SampleProject.spriteImg1.setEnabled(false);
						SampleProject.spriteImg2.setEnabled(false);
					}
					else if (qty == 3)
					{
						SampleProject.spriteImg1.setEnabled(false);
						SampleProject.spriteImg2.setEnabled(false);
						SampleProject.spriteImg4.setEnabled(false);
					
					}	
					else
					{
						SampleProject.spriteImg1.setEnabled(false);
						SampleProject.spriteImg2.setEnabled(false);
						SampleProject.spriteImg4.setEnabled(false);
						SampleProject.spriteImg3.setEnabled(false);
					}
						
								}
				//7-up
				if (SampleProject.price == 25){
					selectedItem = "7-Up";
					this.itemId = 4;
					if(qty == 1)
					{
						SampleProject.up7Images4.setEnabled(false);
					}
					
					else if(qty == 2)
					{
						SampleProject.up7Images4.setEnabled(false);
						SampleProject.up7Images1.setEnabled(false);
					}
					else if (qty == 3)
					{
						SampleProject.up7Images4.setEnabled(false);
						SampleProject.up7Images1.setEnabled(false);
						SampleProject.up7Images2.setEnabled(false);
					
					}	
					else
					{
						SampleProject.up7Images4.setEnabled(false);
						SampleProject.up7Images1.setEnabled(false);
						SampleProject.up7Images2.setEnabled(false);
						SampleProject.up7Images3.setEnabled(false);
					}
						
								}
				String qI = "";
				double presyo = SampleProject.price;
				String grandP = SampleProject.textTotal.getText();
				double grandT = Double.parseDouble(grandP);
				String sukliP = SampleProject.textChange.getText();
				double sukliT = Double.parseDouble(sukliP);
				if (qty <= 1){
					qI = "piece";
				}else{
					qI = "pieces";
				}
				
			if (SampleProject.occur == 0){	
				try {	
					String sql = "INSERT INTO `customer`(`customer_date`) VALUES ('"+this.petsa+"')";
					PreparedStatement customer = connection.prepareStatement(sql);                         
					long lastInsertedID = customer.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
					ResultSet rs = customer.getGeneratedKeys();
					if(rs.next()){
						this.lastCust = (int) rs.getLong(1);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally{
					//JOptionPane.showMessageDialog(f, "Inserted to Database"+ this.lastCust +" ");
				}
				
				try {		
					String sql = "INSERT INTO `orders`(`customer_id`) VALUES ('"+this.lastCust+"')";
					PreparedStatement orders = connection.prepareStatement(sql);                         
					long lastInsertedID = orders.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
					ResultSet rs = orders.getGeneratedKeys();
					if(rs.next()){
						this.lastOrd = (int) rs.getLong(1);
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally{
					//JOptionPane.showMessageDialog(f, "Inserted to Database"+ this.lastOrd);
				}
			    }
				
				try {		
					
					PreparedStatement orderline = connection.prepareStatement("INSERT INTO `orderline`(`order_id`, `orderline_qty`, `orderline_total`, `orderline_change`, `item_id`) VALUES ('"+this.lastOrd+"','"+qty+"','"+grandT+"','"+sukliT+"','"+this.itemId+"')");                         
					orderline.executeUpdate();
					
				
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally{
					//JOptionPane.showMessageDialog(f, "Inserted to Database");
				}
				
				
				try {		
					PreparedStatement item = connection.prepareStatement("UPDATE `item` SET `item_stock`= (SELECT item_stock FROM item WHERE item_id = '"+this.itemId+"')-'"+qty+"' WHERE item_id = '"+this.itemId+"'");                         
					item.executeUpdate();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally{
				//	JOptionPane.showMessageDialog(f, "Inserted to Database");
				}
				
				
				int c = JOptionPane.showConfirmDialog(f, "Thank You For Buying "+qty +" "+qI+" of "+selectedItem+"!!!,\n Do You Want Buy Again?","Ask?", JOptionPane.YES_NO_OPTION);
				
				
				if (c == JOptionPane.YES_OPTION){
					SampleProject.occur += 1;
					double pricer; 
					pricer = SampleProject.price = 0.0;
					
					SampleProject.btn7up.setEnabled(true);
					SampleProject.btnCoke.setEnabled(true);
					SampleProject.btnPepsi.setEnabled(true);
					SampleProject.btnSprite.setEnabled(true);
					
					SampleProject.spinnerQty.setValue(0);
					SampleProject.textChange.setText("");
					SampleProject.textTotal.setText("");
					SampleProject.txtCash.setText("");
					
					SampleProject.cokeImg4.setEnabled(true);
					SampleProject.cokeImg1.setEnabled(true);
					SampleProject.cokeImg2.setEnabled(true);
					SampleProject.cokeImg3.setEnabled(true);
					
					SampleProject.pepsiImage1.setEnabled(true);
					SampleProject.pepsiImage2.setEnabled(true);
					SampleProject.pepsiImage4.setEnabled(true);
					SampleProject.pepsiImage3.setEnabled(true);
					
					SampleProject.spriteImg1.setEnabled(true);
					SampleProject.spriteImg2.setEnabled(true);
					SampleProject.spriteImg4.setEnabled(true);
					SampleProject.spriteImg3.setEnabled(true);
					
					SampleProject.up7Images4.setEnabled(true);
					SampleProject.up7Images1.setEnabled(true);
					SampleProject.up7Images2.setEnabled(true);
					SampleProject.up7Images3.setEnabled(true);
				}else{
					SampleProject.frame.setVisible(false);
					Login.frame1.setVisible(true);
				}
				}
				}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(f, "Invalid Input, please input numeric value only","Warning", JOptionPane.WARNING_MESSAGE);
				}
		}
			}
	}
	
	//Clear
	if(e.getSource()== SampleProject.btnCancel){
		double pricer; 
		pricer = SampleProject.price = 0.0;
		
		SampleProject.btn7up.setEnabled(true);
		SampleProject.btnCoke.setEnabled(true);
		SampleProject.btnPepsi.setEnabled(true);
		SampleProject.btnSprite.setEnabled(true);
		
	
		
		SampleProject.spinnerQty.setValue(0);
		SampleProject.textChange.setText("");
		SampleProject.textTotal.setText("");
		SampleProject.txtCash.setText("");
	}

	
	}
	
	}
				
		
		
	
	
	
		
	
	
