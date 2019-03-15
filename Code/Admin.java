package admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import java.sql.*;

public class Admin extends JFrame {

	/*public static void main(String[] args){
		Admin a=new Admin();
		a.addEvents();
		a.addComponents();
	}*/


Label date,sub;
JButton up;
JRadioButton selected;
JRadioButton[] r=new JRadioButton[30];
ButtonGroup bg=new ButtonGroup();
ResultSet rs,rs1;
int j=0;
Panel p1;
DefaultListModel<String> l3 = new DefaultListModel<>();
JList<String> jl;
JButton lout,exit;
boolean mult=true;

	public Admin(){
		
		this.setVisible(true);
		this.setSize(900,800);
		this.setLayout(null);
		this.setTitle("Admin");
		setBackground(Color.DARK_GRAY);
		
		p1=new Panel();
		date=new Label("DATE");
		sub=new Label("SUBJECT");
		lout=new JButton("Log out");
		exit=new JButton("Exit");
		up=new JButton("Update");
	    l3.addElement("You are logged in as Admin");
	    l3.addElement("The following list displays the attendance");
		l3.addElement("entries by different teachers for");
		l3.addElement("their respective subjects and dates.");
		l3.addElement("Select one from the list and click Update.");
		l3.addElement("Wait for that entry to disappear.");
		
		up.setBounds(400, 50,100, 30);
		date.setBounds(70, 50, 100, 30);
		sub.setBounds(200, 50, 100, 30);
		lout.setBounds(570,400,100,40);
		exit.setBounds(680, 400, 100, 40);
		exit.setBackground(Color.RED);
		
		
		this.addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent windowEvent){
		    	  mult=false;
		    	  dispose();}
		    });
		

		try {
		
		
		 String database = 
	              "jdbc:ucanaccess://F://Database21.mdb;";
		 Connection conn = DriverManager.getConnection(database, "", "");
         Statement s1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         
         String selTable1 = "SELECT DISTINCT Format([Date0],\"short date\"),Subject FROM Temp WHERE flag=0";
         System.out.println(selTable1);
         s1.execute(selTable1);
         rs1 = s1.getResultSet();
                
         while((rs1!=null) && (rs1.next())){
         r[j]=new JRadioButton(rs1.getString(1)+"                            "+rs1.getString(2));
         r[j].setBounds(50, 100+(j*30), 400, 30);
        
         bg.add(r[j]);
         add(r[j]);
         j++;
         }
         s1.close();
         conn.close();
       
	} catch (Exception e) {
		e.printStackTrace();
	}
	

	    
	}
	
	public void addComponents(){
		 add(up);
		 add(p1);
		 add(date);
		 add(sub);
		 add(lout);
		 add(exit);
		 JList<String> list = new JList<>(l3); 
			add(list);
			list.setFixedCellHeight(30);
		   list.setBounds(550, 50,250, 180);
		   list.setVisible(true);
	}
	
	 public void addEvents(){
		 
		 exit.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e){
			    	  JFrame f=new JFrame();
			    	  int a;
			          a = JOptionPane.showConfirmDialog(f,"Do You Want To Exit The System?","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
			      
			          if(a==JOptionPane.YES_OPTION)
			        	  {
			        	  System.exit(0);
			        	  }
			          }
			    });
		
		lout.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e){
		    	  JFrame f=new JFrame();
		    	  int a;
		          a = JOptionPane.showConfirmDialog(f,"Do You Want To Logout?","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
		      
		          if(a==JOptionPane.YES_OPTION)
		        	  {
		        	  mult=false;
		        	 dispose();
		        	  }
		          }
		    });
		
		up.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e){
		    	
		    	
		    	
		    	try {
		    		
		    	rs1.beforeFirst();		
		    	j=0;
		    	int c=0;
		   		 String database = 
		   	              "jdbc:ucanaccess://F://Database21.mdb;";
		   		 Connection conn = DriverManager.getConnection(database, "", "");
		            Statement s1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		          //get value of j for which the button is selected
			    	while(true){
			    		rs1.next();
			    		if(r[j].isSelected()){
			    			r[j].setVisible(false);
			    			break;
			    		}
			    		j++;
			    	}
		            
		            String selTable1 = "SELECT RollNo,Subject,Attendance,Remark FROM Temp WHERE Date0 = DateValue('"+rs1.getString(1)+"') AND Subject=\""+rs1.getString(2)+"\"";
		            System.out.println(selTable1);
		            s1.execute(selTable1);
			    	rs = s1.getResultSet();
		                   
			    	String query2="UPDATE Temp set Flag=1 WHERE Date0 = DateValue('"+rs1.getString(1)+"') AND Subject=\""+rs1.getString(2)+"\"";
			    	System.out.println(query2);
			    	s1.execute(query2);
			    	
		            while((rs!=null) && (rs.next())){
		            	if(rs.getString(3).equals("P")){
		            		c=1;
		            		
		            	}
		            	else if(rs.getString(3).equals("A")){
		            		c=0;
		            		
		            	}
		            		
		            
		            String query1="UPDATE Main SET Remark=\""+rs.getString(4)+"\",History=Nz(History,\"\") & \" \" & \""+rs.getString(3)+"\",Counter=Counter +"+c+" WHERE RollNo=\""+rs.getString(1)+"\" AND Subject=\""+rs.getString(2)+"\"";
		            System.out.println(query1);
		            
		            s1.execute(query1);
		            }
		            s1.close();
		            conn.close();
		          
		   	} catch (Exception ex) {
		   		ex.printStackTrace();
		   	}
		    	
		    
		    setPercentage();	
		    	}
			    });	
	 }
	 
	 public void setPercentage(){
		 String e="";
		 try{
			 rs.beforeFirst();
			 
			 String database = 
		              "jdbc:ucanaccess://F://Database21.mdb;";
			 Connection conn = DriverManager.getConnection(database, "", "");
			 Statement s1 = conn.createStatement();
			 
			 while((rs!=null) && (rs.next())){
			 String q="SELECT Counter FROM Main WHERE RollNo=\""+rs.getString(1)+"\" AND Subject=\""+rs.getString(2)+"\"";
			 System.out.println(q);
			 s1.execute(q);
			 ResultSet local=s1.getResultSet();		//local result set only for counter value
			 local.next();
			 double n=Double.parseDouble(local.getString(1));
			 
			 double f=(100*n/15);System.out.println(f);   //there are 15 lecs for each subject 
			 int p=(int)(f+0.5); 		//to round the value of percentage
			 
			 //check eligibility
			 if(p>=75){
				 e="ELIGIBLE";
			 }
			 else{
				 e="NOT ELIGIBLE";
			 }
			 
			 String q1="UPDATE Main SET Percentage="+p+",Eligibility=\""+e+"\" WHERE RollNo=\""+rs.getString(1)+"\" AND Subject=\""+rs.getString(2)+"\"";
			 System.out.println(q1);
			 s1.execute(q1);
			 }
			 
			 s1.close();
	         conn.close();
		 }catch(Exception ex){
			 System.out.println(ex);
		 }
		 
	 }
	 
	 public boolean mflag(){
			return mult;
		}
}
