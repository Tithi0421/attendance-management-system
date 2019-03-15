package stu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.sql.*;

 public class Student extends JFrame {

	/*public static void main(String args[]) {
		Student f=new Student("S2017009");
		f.addComponents();
		f.addEvents();
	}*/

JTabbedPane j;
String s;
DefaultListModel<String> l1 = new DefaultListModel<>();
JList<String> list;
String[] data=new String[15];
Panel p1,p2,p3,p4,p5,p6;
JButton lout,exit;
Label[] re=new Label[5];
Label[] per=new Label[5];
Label[] el=new Label[5];
Label[] his=new Label[5];
ResultSet rs,rs1,rs2;


		public Student(String u){
		this.setVisible(true);
		this.setSize(1000,600);
		this.setLayout(null);
		this.setTitle("Student");
//		this.setBackground(Color.BLACK);
		this.setLocation(500, 200);
	//	Font f=new Font("Serif",Font.BOLD,20);
		re[0]=new Label();re[1]=new Label();re[2]=new Label();re[3]=new Label();re[4]=new Label();
		per[0]=new Label();per[1]=new Label();per[2]=new Label();per[3]=new Label();per[4]=new Label();
		el[0]=new Label();el[1]=new Label();el[2]=new Label();el[3]=new Label();el[4]=new Label();
		his[0]=new Label();his[1]=new Label();his[2]=new Label();his[3]=new Label();his[4]=new Label();
		
		re[0].setForeground(Color.WHITE);re[1].setForeground(Color.WHITE);re[2].setForeground(Color.WHITE);
		re[3].setForeground(Color.WHITE);re[4].setForeground(Color.WHITE);
		per[0].setForeground(Color.WHITE);per[1].setForeground(Color.WHITE);per[2].setForeground(Color.WHITE);
		per[3].setForeground(Color.WHITE);per[4].setForeground(Color.WHITE);
		el[0].setForeground(Color.WHITE);el[1].setForeground(Color.WHITE);el[2].setForeground(Color.WHITE);
		el[3].setForeground(Color.WHITE);el[4].setForeground(Color.WHITE);
		his[0].setForeground(Color.WHITE);his[1].setForeground(Color.WHITE);his[2].setForeground(Color.WHITE);
		his[3].setForeground(Color.WHITE);his[4].setForeground(Color.WHITE);
				
		
	
		p1=new Panel();
		p2=new Panel();
		p3=new Panel();
		p4=new Panel();
		p5=new Panel();
		p6=new Panel();
		
		j=new JTabbedPane();
		

		lout=new JButton("Log out");
		exit=new JButton("Exit");
		
		p1.setLayout(null);p2.setLayout(null);p3.setLayout(null);p4.setLayout(null);p5.setLayout(null);
		
		p1.setBackground(Color.BLUE);
		p2.setBackground(Color.BLUE);
		p3.setBackground(Color.BLUE);
		p4.setBackground(Color.BLUE);
		p5.setBackground(Color.BLUE);
	
		//setBounds:
		j.setBounds(50,80,600,400);
       
	    lout.setBounds(690,437,100,40);
		exit.setBounds(820, 437, 100, 40);
	    re[0].setBounds(50, 100, 270, 20);re[1].setBounds(50, 100, 270, 20);re[2].setBounds(50, 100, 270, 20);
	    re[3].setBounds(50, 100, 270, 20);re[4].setBounds(50, 100, 270, 20);
	    per[0].setBounds(50, 140, 270, 20);per[1].setBounds(50, 140, 270, 20);per[2].setBounds(50, 140, 270, 20);
	    per[3].setBounds(50, 140, 270, 20);per[4].setBounds(50, 140, 270, 20);
	    el[0].setBounds(50, 180, 270, 20);el[1].setBounds(50, 180, 270, 20);el[2].setBounds(50, 180, 270, 20);
	    el[3].setBounds(50, 180, 270, 20);el[4].setBounds(50, 180, 270, 20);
	    his[0].setBounds(50, 220, 270, 20);his[1].setBounds(50, 220, 270, 20);his[2].setBounds(50, 220, 270, 20);
	    his[3].setBounds(50, 220, 270, 20);his[4].setBounds(50, 220, 270, 20);
	   
		exit.setBackground(Color.RED);
	 	    
		this.addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent windowEvent){
		      dispose();}
		    });
		
		//run connection for panels
		
		
		
		// set connection for student info
		try {
			
			
			 String database = 
		              "jdbc:ucanaccess://F://Database21.mdb;";
			 Connection conn = DriverManager.getConnection(database, "", "");
	            Statement s1 = conn.createStatement();
	            
	            String selTable1 = "SELECT Sname,RollNo,Deptname,EmailID,ContactNo FROM Student WHERE UCase(UserID)=\""+ u +"\"";
	            System.out.println(selTable1);
	            s1.execute(selTable1);
	            rs1 = s1.getResultSet();
	            rs1.next();
	         
	            //add text
	            l1.addElement("Student: " + rs1.getString(1));
	            l1.addElement("Roll No: " + rs1.getString(2));
	            l1.addElement("Department: " + rs1.getString(3));  
	            l1.addElement("Email: "+ rs1.getString(4));
	            l1.addElement("Contact: " + rs1.getString(5));
	           String d1=rs1.getString(3);
	           getDept(d1.toUpperCase());
	            s1.close();
	            conn.close();
	          
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
          
        
       
		JList<String> list = new JList<>(l1); 
		add(list);
		list.setFixedCellHeight(30);
	    list.setBounds(650, 100,300, 320);
		
	    
	   
		
	}
	
	public void addComponents(){
	
		
//		add(p6);
//		p6.add(ta);
		add(lout);
		add(exit);
			
		
		
		p1.add(re[0]);p2.add(re[1]);p3.add(re[2]);p4.add(re[3]);p5.add(re[4]);
		p1.add(per[0]);p2.add(per[1]);p3.add(per[2]);p4.add(per[3]);p5.add(per[4]);
		p1.add(el[0]);p2.add(el[1]);p3.add(el[2]);p4.add(el[3]);p5.add(el[4]);
		p1.add(his[0]);p2.add(his[1]);p3.add(his[2]);p4.add(his[3]);p5.add(his[4]);
		
		
		
	   
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
		        	 dispose();
		        	  }
		          }
		    });	
		
		}
	
	void getDept(String sub){
		
		try {
			
			
			 String database = 
		              "jdbc:ucanaccess://F://Database21.mdb;";
			 Connection conn = DriverManager.getConnection(database, "", "");
	           Statement s = conn.createStatement();
	           
	           String selTable = "SELECT Subname FROM Subject WHERE UCase(Deptname)=\""+ sub+"\"ORDER BY Subname" ;
	           System.out.println(selTable);
	           s.execute(selTable);
	           ResultSet rs2 = s.getResultSet();
	           
	           add(j);
	           rs2.next();
	           j.add(rs2.getString(1),p1);
	           rs2.next();
	           j.add(rs2.getString(1),p2);
	           rs2.next();
	           j.add(rs2.getString(1),p3);
	           rs2.next();
	           j.add(rs2.getString(1),p4);
	           rs2.next();
	           j.add(rs2.getString(1),p5);
	           
	          
	
	           s.close();
	           conn.close();
	         
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
		try {
			int j=0;
			
			 String database = 
		              "jdbc:ucanaccess://F://Database21.mdb;";
			 Connection conn = DriverManager.getConnection(database, "", "");
	            Statement s = conn.createStatement();
	            
	            String selTable = "SELECT Remark,Percentage,Eligibility,History FROM Main WHERE RollNo=\"" +rs1.getString(2)+"\"ORDER BY Subject";
	            s.execute(selTable);
	            rs = s.getResultSet();
	            
	          //add text
	            while((rs!=null) && (rs.next())){
	            	if(j==5)
	            		break;
	            re[j].setText("REMARK: " + rs.getString(1));
				per[j].setText("Presence Percentage: " + rs.getString(2));
				el[j].setText("Eligibility: "+ rs.getString(3));
				his[j].setText("Attendance History: " + rs.getString(4));
	           j++;
	            }
	            s.close();
	            conn.close();
	          
		} catch (Exception e) {
			e.printStackTrace();
		
		
		}
	}
	
	
}
