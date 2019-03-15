package tea;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

import java.sql.*;
import multi.EntryCount;
public class Teacher extends JFrame {

    /* public static void main(String args[]) {
		Teacher t=new Teacher("T2017007");
		t.addComponents();
		t.addEvents();
	}*/
	

Panel p1,p2;
String d1,d2;
JButton lout,exit,sub1,sub2;
JButton[] up=new JButton[2];
JButton[] submit=new JButton[2];
JTextField ta;
DefaultListModel<String> l3 = new DefaultListModel<>();
JList<String> list;
ResultSet rs1,rs;
Label[] st1=new Label[6];
Label[] st2=new Label[6];
Label re;
Label[] date=new Label[2];
Label[] p=new Label[2];
Label[] q=new Label[2];
Label[] s=new Label[2];
Choice[] dd=new Choice[2];
Choice[] m=new Choice[2];
Choice[] y=new Choice[2];
Choice[] stu=new Choice[2];   
Checkbox[] l1=new Checkbox[6];
Checkbox[] l2=new Checkbox[6];
int i1,i2;
String[] at1=new String[6];
String[] at2=new String[6];
int x=0,z=0;
boolean mult=true;
EntryCount ec1=new EntryCount();

	public Teacher(String v){
	this.setVisible(true);
	this.setSize(900,650);
	this.setLayout(null);
//  this.setBackground(Color.BLACK);
//	this.setLocation(200, 200);
	this.setTitle("Teacher");

	
	ta=new JTextField();
	p1=new Panel();
	p2=new Panel();
	
	lout=new JButton("Log out");
	exit=new JButton("Exit");
	up[0]=new JButton("Update");up[1]=new JButton("Update");
	submit[0]=new JButton("Submit");submit[1]=new JButton("Submit");
	
	p1.setLayout(null);
	p1.setBackground(Color.CYAN);
	p2.setLayout(null);
	p2.setBackground(Color.CYAN);
	

	p[0]=new Label("Select the date for which the attendance is to be filled.");
	q[0]=new Label("Tick mark against the student present.");
	s[0]=new Label("Press Submit when done.");
	p[1]=new Label("Select the date for which the attendance is to be filled.");
	q[1]=new Label("Tick mark against the student present.");
	s[1]=new Label("Press Submit when done.");
	Font f=new Font("Serif",Font.BOLD,15);
	p[0].setFont(f);p[1].setFont(f);
	q[0].setFont(f);q[1].setFont(f);
	s[0].setFont(f);s[1].setFont(f);
	
	re=new Label("Remark");
	re.setFont(f);
	date[0]=new Label("Select Date");date[1]=new Label("Select Date");
	
	dd[0]=new Choice();dd[1]=new Choice();
	m[0]=new Choice();m[1]=new Choice();
	y[0]=new Choice();y[1]=new Choice();
	stu[0]=new Choice();stu[1]=new Choice();
	
	dd[0].add("date");
	m[0].add("month");
	y[0].add("year");
	dd[1].add("date");
	m[1].add("month");
	y[1].add("year");
	stu[0].add("Select Roll No");
	stu[1].add("Select Roll No");
	
	for(int i=1;i<=31;i++){
	String z=Integer.toString(i);
	dd[0].add(z);
	dd[1].add(z);
	}
	
	for(int i=1;i<=12;i++){
		String z=Integer.toString(i);
		m[0].add(z);
		m[1].add(z);
	}
	
	for(int i=2016;i<=2017;i++){
		String z=Integer.toString(i);
		y[0].add(z);
		y[1].add(z);}
		
	p1.setBounds(20,80,500, 400);
	p2.setBounds(20,80,500, 400);	
	
	submit[0].setBounds(350,337,100,40);submit[1].setBounds(350,337,100,40);
	lout.setBounds(570,400,100,40);
	exit.setBounds(680, 400, 100, 40);
	exit.setBackground(Color.RED);
	
	p[0].setBounds(20,5 ,400, 30);
	date[0].setBounds(30, 40, 70, 20);
    dd[0].setBounds(140,40,60,20);
    m[0].setBounds(210, 40, 80, 20);
    y[0].setBounds(300, 40, 60, 20);
	    
    q[0].setBounds(20, 80, 400, 30);
    
    s[0].setBounds(20, 310, 300, 30);
    
    p[1].setBounds(20,5 ,400, 30);
	date[1].setBounds(30, 40, 70, 20);
    dd[1].setBounds(140,40,60,20);
    m[1].setBounds(210, 40, 80, 20);
    y[1].setBounds(300, 40, 60, 20);
	    
    q[1].setBounds(20, 80, 400, 30);
    
    s[1].setBounds(20, 310, 300, 30);
    re.setBounds(20, 490, 80, 20);
    ta.setBounds(20, 520,500, 30);
    up[0].setBounds(530, 520, 120, 30);
    up[1].setBounds(530, 520, 120, 30);
    
    
	this.addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent windowEvent){
	      mult=false;
	      ec1.decCount();
	      dispose();}
	    });
	
	try {
		
		
		 String database = 
	              "jdbc:ucanaccess://F://Database21.mdb;";
		 Connection conn = DriverManager.getConnection(database, "", "");
           Statement s1 = conn.createStatement();
           
           String selTable1 = "SELECT Subname1,StdNum1,Subname2,StdNum2 FROM Teacher WHERE UCase(UserID)=\""+ v +"\"";
           System.out.println(selTable1);
           s1.execute(selTable1);
           rs1 = s1.getResultSet();
           rs1.next();
        
           //add text
           sub1=new JButton(rs1.getString(1));
           d1=rs1.getString(1);
           i1=Integer.parseInt(rs1.getString(2));
           sub2=new JButton(rs1.getString(3));
           d2=rs1.getString(3);
           i2=Integer.parseInt(rs1.getString(4));
           
           s1.close();
           conn.close();
         
	} catch (Exception e) {
		e.printStackTrace();
	}
	sub1.setBounds(30,30,150,40);
	sub2.setBounds(210,30,150,40);
	

	
	if (getDept(d1).startsWith("CO"))x=101;
	else if(getDept(d1).endsWith("RICAL"))x=201;
	else if(getDept(d1).endsWith("CS"))x=301;
	else if(getDept(d1).startsWith("ME"))x=401;
	else x=501;
	
	
	
	
	if (getDept(d2).startsWith("CO"))z=101;
	else if(getDept(d2).endsWith("RICAL"))z=201;
	else if(getDept(d2).endsWith("CS"))z=301;
	else if(getDept(d2).startsWith("ME"))z=401;
	else z=501;
	
	
	int k1=x;
	for(int j=0;j<i1;j++){
		
		st1[j]=new Label("Student No " + k1++);
		p1.add(st1[j]);
		st1[j].setBounds(30,120+(j*30),100,20);
	
		l1[j]=new Checkbox();
		p1.add(l1[j]);
		l1[j].setBounds(140,120+(j*30),20,20);
		
	}
	int k2=z;
	for(int j=0;j<i2;j++){
	
		st2[j]=new Label("Student No " + k2++);
		p2.add(st2[j]);
		st2[j].setBounds(30,120+(j*30),100,20);
	
		l2[j]=new Checkbox();
		p2.add(l2[j]);
		l2[j].setBounds(140,120+(j*30),20,20);
		
	}
	
	int k3=x;
	for(int i=0;i<i1;i++){
		String a=Integer.toString(k3++);
		stu[0].add(a);
		}
	
	int k4=z;
	for(int i=0;i<i2;i++){
		String a=Integer.toString(k4++);
		stu[1].add(a);
		
	}
	 stu[0].setBounds(110,490,120,20);
	 stu[1].setBounds(110,490,120,20);
	
	
	try {
		
		
		 String database = 
	              "jdbc:ucanaccess://F://Database21.mdb;";
		 Connection conn = DriverManager.getConnection(database, "", "");
           Statement s1 = conn.createStatement();
           
           String selTable1 = "SELECT Tname,Subname1,Subname2,EmailID,ContactNo,Format([DOJ],\"short date\") FROM Teacher WHERE UCase(UserID)=\""+ v +"\"";
           System.out.println(selTable1);
           s1.execute(selTable1);
           rs = s1.getResultSet();
           rs.next();
        
           //add text
           l3.addElement("Teacher: " + rs.getString(1));
           l3.addElement("Subject 1: " + rs.getString(2));
           l3.addElement("Subject 2: " + rs.getString(3)); 
           l3.addElement("EmailID: " + rs.getString(4));
           l3.addElement("ContactNo: " + rs.getString(5));
           l3.addElement("Joining Date: " + rs.getString(6));
           
           s1.close();
           conn.close();
         
	} catch (Exception e) {
		e.printStackTrace();
	}
	  
    JList<String> list = new JList<>(l3); 
	add(list);
	list.setFixedCellHeight(30);
   list.setBounds(550, 90,310, 300);
   list.setVisible(true);
	
	
 
	
}
	public void addComponents(){
		
		add(sub1);
		add(sub2);
		add(re);
		add(ta);
		add(up[0]);
		add(up[1]);
		add(stu[0]);
		add(stu[1]);
		add(lout);
		add(exit);
		add(p1);
		add(p2);
		
		p1.add(p[0]);
		p1.add(date[0]);
		p1.add(dd[0]);
		p1.add(m[0]);
		p1.add(y[0]);
		p1.add(q[0]);
		p1.add(s[0]);
		p1.add(submit[0]);
		
		p2.add(p[1]);
		p2.add(date[1]);
		p2.add(dd[1]);
		p2.add(m[1]);
		p2.add(y[1]);
		p2.add(q[1]);
		p2.add(s[1]);
		p2.add(submit[1]);
		
		 this.setVisible(true);
		    p1.setVisible(true);
		    p2.setVisible(false);
		    
	}
		
	public void addEvents(){
	
	
	sub1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e){	
		p1.setVisible(true);
		p2.setVisible(false);
		stu[0].setVisible(true);
		stu[1].setVisible(false);
		up[0].setVisible(true);
		up[1].setVisible(false);
		
		   }
				    });
	
	sub2.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e){	
	p2.setVisible(true);
	p1.setVisible(false);
	stu[1].setVisible(true);
	stu[0].setVisible(false);
	up[1].setVisible(true);
	up[0].setVisible(false);
	       }
			    });
	
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
	        	 ec1.decCount();
	        	  dispose();
	        	  }
	          }
	    });	
	
	submit[0].addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e){
	setAttendance1(at1, i1);
	for(int j=0;j<i1;j++)
	System.out.print(at1[j]);
	System.out.println("\n");
	try {
		int j=x;
		
		 String database = 
	              "jdbc:ucanaccess://F://Database21.mdb;";
		 Connection conn = DriverManager.getConnection(database, "", "");
          Statement s1 = conn.createStatement();
          for(int f=0;f<i1;f++){
          String selTable1 = "INSERT INTO Temp(RollNo,Date0,Subject,Attendance,Flag) VALUES("+j++ +",#"+m[0].getSelectedItem()+"/"+dd[0].getSelectedItem()+"/"+y[0].getSelectedItem()+"#,\""+ d1+"\",\""+at1[f]+"\",0)";
          System.out.println(selTable1);
          s1.execute(selTable1);}
          s1.close();
          conn.close();
        
	} catch (Exception ex) {
		JFrame f=new JFrame();
    	JOptionPane.showMessageDialog(f, "The Date has not been selected or The Entry for above data has already been done.", "Error", JOptionPane.ERROR_MESSAGE);
		
	}
	    }
		    });	
	
	submit[1].addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e){
	setAttendance2(at2, i2);  
	for(int j=0;j<i2;j++)
	System.out.print(at2[j]);
	System.out.println("\n");
	try {
		int j=z;
		
		 String database = 
	              "jdbc:ucanaccess://F://Database21.mdb;";
		 Connection conn = DriverManager.getConnection(database, "", "");
          Statement s1 = conn.createStatement();
          for(int f=0;f<i2;f++){
          String selTable1 = "INSERT INTO Temp(RollNo,Date0,Subject,Attendance,Flag) VALUES("+j++ +",#"+m[1].getSelectedItem()+"/"+dd[1].getSelectedItem()+"/"+y[1].getSelectedItem()+"#,\""+ d2+"\",\""+at2[f]+"\",0)";
          System.out.println(selTable1);
          s1.execute(selTable1);}
          s1.close();
          conn.close();
        
	} catch (Exception ex) {
		JFrame f=new JFrame();
    	JOptionPane.showMessageDialog(f, "The Date has not been selected or The Entry for above data has already been done.", "Error", JOptionPane.ERROR_MESSAGE);
	}
	    }
		    });	
	//add Events
	
	
	up[0].addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e){

	try {

		
		 String database = 
	              "jdbc:ucanaccess://F://Database21.mdb;";
		 Connection conn = DriverManager.getConnection(database, "", "");
          Statement s1 = conn.createStatement();
          
          String selTable1 = "UPDATE Temp SET Remark=\""+ta.getText()+"\" WHERE RollNo=" +stu[0].getSelectedItem()+" AND Subject=\""+d1+"\"";
          System.out.println(selTable1);
          s1.execute(selTable1);
          s1.close();
          conn.close();
        
	} catch (Exception ex) {
		ex.printStackTrace();
		}
	    }
		    });	
	
	up[1].addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e){

	try {
		
		
		 String database = 
	              "jdbc:ucanaccess://F://Database21.mdb;";
		 Connection conn = DriverManager.getConnection(database, "", "");
          Statement s1 = conn.createStatement();
          
          String selTable1 = "UPDATE Temp SET Remark=\""+ta.getText()+"\" WHERE RollNo=" +stu[1].getSelectedItem()+" AND Subject=\""+d2+"\"";
          System.out.println(selTable1);
          s1.execute(selTable1);
          s1.close();
          conn.close();
        
	} catch (Exception ex) {
		ex.printStackTrace();
		}
	    }
		    });	
	
}
	
	
	String getDept(String sub){
		String ss="";
		try {
			
			
			 String database = 
		              "jdbc:ucanaccess://F://Database21.mdb;";
			 Connection conn = DriverManager.getConnection(database, "", "");
	           Statement s = conn.createStatement();
	           
	           String selTable = "SELECT Deptname FROM Subject WHERE UCase(Subname)=\""+ sub+"\"";
	           System.out.println(selTable);
	           s.execute(selTable);
	           ResultSet rs = s.getResultSet();
	           rs.next();
	           
	           ss=rs.getString(1);
	           s.close();
	           conn.close();
	         
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ss.toUpperCase();
	}
	
	void setAttendance1(String[] at1,int i1){
		for(int j=0;j<i1;j++){
			if (l1[j].getState()) at1[j]="P";
			else at1[j]="A";
		}
	}
	
	void setAttendance2(String[] at2,int i2){
		for(int j=0;j<i2;j++){
			if (l2[j].getState()) at2[j]="P";
			else at2[j]="A";
		}
	}
	
	public boolean mflag(){
		return mult;
	}
	
}//frame end	
