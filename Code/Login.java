import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import java.sql.*;

import stu.Student;
import tea.Teacher;
import admin.Admin;
import multi.EntryCount;

public class Login extends Frame{

	public static void main(String args[]) {
		Login l=new Login();
	    l.addComponents();
	    l.addEvents();
	    
	}
Label title,log,pass;
final TextField t1,t2;
Panel p1;
JButton enter,exit;
Student newframe;
Resource ob=new Resource();
Admin newframe2;
JRadioButton a,t,s;
ButtonGroup bg;
ResultSet rs;


static boolean bflag=false; //set when teacher tries to enter, and set to false when teacher exits

	public Login(){
	this.setSize(500, 400);
	setLayout(null);
	setBackground(Color.BLUE);
	setLocation(700, 300);
	this.setTitle("Login");
	
	p1=new Panel();
	//p1.setBackground(Color.BLACK);
	//p1.setBounds(5, 5, 40, 400);
	
	log=new Label("Login");
	pass=new Label("Password");
	title=new Label("Attendance System");
	
	Font f=new Font("Serif",Font.BOLD,30);
	title.setFont(f);
	title.setForeground(Color.white);
	
	t1=new TextField();
	t2=new TextField();
	t2.setEchoChar('*');
	
	enter=new JButton("Enter");
	exit=new JButton("Exit");
	exit.setBackground(Color.RED);
	
	a=new JRadioButton("Administrator");
	t=new JRadioButton("Teacher");
	s=new JRadioButton("Student");
	bg=new ButtonGroup();
		
	//setBounds
	title.setBounds(134,50,300,30);
	log.setBounds(150, 170, 100, 20);
	pass.setBounds(150, 200, 100, 20);
	t1.setBounds(260, 170, 100, 20);
	t2.setBounds(260, 200, 100, 20);
	enter.setBounds(170, 240, 70, 30);
	exit.setBounds(260, 240, 70, 30);
	
	a.setBounds(70,100,120,30);
	a.setBackground(Color.lightGray);
	t.setBounds(200,100,100,30);
	t.setBackground(Color.LIGHT_GRAY);
	s.setBounds(310,100,100,30);
	s.setBackground(Color.LIGHT_GRAY);
	
	 this.addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent windowEvent){
	      System.exit(0);}
	    });
	
	 try {
		 String database = 
	              "jdbc:ucanaccess://F://Database21.mdb;";
		 Connection conn = DriverManager.getConnection(database, "", "");
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); //So that the cursor can move backwards also
            String selTable = "SELECT userid,password FROM Password";
            s.execute(selTable);
            rs = s.getResultSet();     
	
	 
        s.close();
        conn.close();
      
	} catch (Exception ex) {
	ex.printStackTrace();
}
	
	}//constructor ends
	
	public void addComponents(){
	
	add(title);	
	add(log);
	add(pass);
	add(t1);
	add(t2);
	add(enter);
	add(exit);
	bg.add(a);bg.add(t);bg.add(s);
	add(a);
	add(t);
	add(s);
	
	this.setVisible(true);
	}

	public void addEvents(){

		
		exit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e){
		          System.exit(0);
		        }
		    });	
		
		        
	    enter.addActionListener(new ActionListener() {
	    		    public void actionPerformed(ActionEvent e){
	    		    int flag=0;	
	    		    String n=t1.getText();
	    		    String p=t2.getText();
	    		    	
	    		    try {   
	    		 	rs.beforeFirst();	 // required so that after a "logout",another user can login and cursor checks from first row
	    	
	    		    	 while((rs!=null) && (rs.next()))
	    		            {
	    		    		 
	    		    		 if(n.equalsIgnoreCase(rs.getString(1)) && p.equals(rs.getString(2))){
	    		            	  flag =1; 
	    		                 break;
	    		              }
	    		            	
	    		            }
	    		    	 
	    		    } catch (Exception ex) {
	    		    	ex.printStackTrace();
	    		    }
	    		    	 
	    		        
	    		    	 
	    		    if(flag==1 && s.isSelected() ){
	    		    		if(n.startsWith("s") || n.startsWith("S")) {
	    		    	newframe= new Student(n.toUpperCase());	
	    		    	newframe.setVisible(true);
	    		    	newframe.addComponents();
	    		    	newframe.addEvents();
	    		    	t1.setText("");
	    		    	t2.setText("");
	    		    	bg.clearSelection();}
	    		    	    
	          				else{JFrame f=new JFrame();
	          				JOptionPane.showMessageDialog(f, "Button Is Selected For Student", "Error", JOptionPane.ERROR_MESSAGE);
	    		    			}
	    		    	}
	    		    	
	    		    if(flag==1 && t.isSelected() ){
    		    		if(n.startsWith("t") || n.startsWith("T")) {
    		    			
    		    			new TeacherMultiThread(n,ob);
    		    			bflag=true;
    		    			t1.setText("");
    		    	    	t2.setText("");
    		    	    	bg.clearSelection();
    		    		
    		    	}
    		    	    
          				else{JFrame f=new JFrame();
          				JOptionPane.showMessageDialog(f, "Button Is Selected For Teacher", "Error", JOptionPane.ERROR_MESSAGE);
    		    			}
    		    	}
	    		    
	    		    if(flag==1 && a.isSelected() ){
    		    		if(n.startsWith("a") || n.startsWith("A")) {
    		    	
    		    			new AdminMultiThread(n,ob);
    		    	t1.setText("");
    		    	t2.setText("");
    		    	bg.clearSelection();}
    		    	    
          				else{JFrame f=new JFrame();
          				JOptionPane.showMessageDialog(f, "Button Is Selected For Admin", "Error", JOptionPane.ERROR_MESSAGE);
    		    			}
    		    	}
	    		    
	    		    if(flag==0){
	    		    		JFrame f=new JFrame();
	    			    	JOptionPane.showMessageDialog(f, "The Login Or Password Is Incorrect", "Error", JOptionPane.ERROR_MESSAGE);
	    		    			    		    		
	    		    	        }
	    		   }
	    		});
	           
		} //addevents
		
}//login class

//Implementation of multithreading
class TeacherMultiThread implements Runnable{
Thread t;
Resource ob;
String id;
	TeacherMultiThread(String id,Resource ob){
		this.id=id;
		this.ob=ob;
		t=new Thread(this);
		t.start();
	}
	
	public void run(){
		ob.tea(id);
	}
}

class AdminMultiThread implements Runnable{
Thread t;
Resource ob;
String id;
	AdminMultiThread(String id,Resource ob){
		this.id=id;
		this.ob=ob;
		t=new Thread(this);
		t.start();
	}

	public void run(){
		ob.adm(id);
	}
}

class Resource{
	  boolean flag=true;

	EntryCount ec=new EntryCount();
	Teacher newframe1;
	Admin newframe2;
	
	synchronized void tea(String n){
		
	/*	if(!flag){
			JFrame jf=new JFrame();
			jf.setVisible(true);
			jf.setSize(100, 100);
			jf.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e){
					jf.dispose();
				}
			});
		} */ 
		while(!flag){
			try{
				wait();
			}catch(InterruptedException e){
				System.out.println("Interrupted Exception caught");
			}finally{
				
			}
			}
		
		System.out.println("Teacher entering");
	    ec.incCount();
		newframe1= new Teacher(n.toUpperCase());	
    	newframe1.setVisible(true);
    	newframe1.addComponents();
    	newframe1.addEvents();
   /* 	while(newframe1.mflag()){
    		System.out.print("");
    	}
*/    	
    	
    //	flag=false;
    	Login.bflag=false;
    	System.out.println(Login.bflag);
    	
	}
	
	synchronized void adm(String n){
		flag=false;
		
		if(ec.getCount()!=0){
			JFrame f=new JFrame();
	    	JOptionPane.showMessageDialog(f,ec.getCount()+ " Teacher/s are currently working. You will be logged in automatically.", "WAIT", JOptionPane.INFORMATION_MESSAGE);
		}
		while(ec.getCount()!=0){
			if(Login.bflag){
				display();
				Login.bflag=false;
			}
			System.out.print("");
		}
		
		System.out.println("Admin entering");
		
		newframe2= new Admin();	
    	newframe2.setVisible(true);
    	newframe2.addComponents();
    	newframe2.addEvents();
    	while(newframe2.mflag()){
    		if(Login.bflag){
				display();
				Login.bflag=false;
			}
    	System.out.print("");
    	}
    	System.out.println("in here");
    	flag=true;
    	 
    	notifyAll();
	}
	void display(){
	JFrame f=new JFrame();
	JOptionPane.showMessageDialog(f,"Admin is currently working. You will be logged in automatically.", "WAIT", JOptionPane.INFORMATION_MESSAGE);
}
}