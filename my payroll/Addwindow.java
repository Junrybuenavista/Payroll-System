import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.net.*;

public class Addwindow extends JInternalFrame implements ActionListener  {


	JFrame JFParentFrame;
	JDesktopPane desktop;
	private JPanel panel1;
	private JPanel panel2;
	private JButton AddBtn;
	private JButton ResetBtn;
	private JButton ExitBtn;
	private JLabel LblEmp_Code,LblEmp_Name1, LblEmp_Name2, LblEmp_Desi,LblEmp_Add,LblEmp_No;
	private JTextField TxtEmp_Code, TxtEmp_Name1, TxtEmp_Name2,TxtEmp_Add, TxtEmp_No; 
	private JComboBox Emp_Type;
	String dialogmessage;
    String dialogs;
     int dialogtype = JOptionPane.PLAIN_MESSAGE;
       JComboBox box1 ,box2;String sbox1[]={"Single","Married"}; 
       String sbox2[]={"None","One","Two","Three","Four","Five"}; 
    	 public static int record;
     		String Emp_Code = "";
			String Emp_Name1 = "";
			String Emp_Name2 = "";
			String Emp_Desi = "";
			String Emp_Add  = "";
			String Emp_No   = ""; 
     
     
    JPasswordField pass; 
     clsSettings settings = new clsSettings();
      clsConnection connect = new clsConnection();
      
      Connection conn;
   
      class j2 extends JPanel
    	{   
    		public j2()
    			{  
    			setLayout(new FlowLayout(0,0,0));
    				   
    			        
    		        		setBackground(new Color(36,119,183));	 
    			}
    			public void paint(Graphics g)
    				{
    					super.paint(g);
    				
    					
    				   g.setFont(new Font("Cooper Black",Font.BOLD,40));
    					g.drawString("ADD EMPLOYEE",60,70);
    				}
    		public Dimension getPreferredSize(){return new Dimension(600,110);}	
    			     
    	}
    public void  getme()
    { 
        try{ 
               
    	         conn = connect.setConnection(conn,"","");
    	         Statement stmt = conn.createStatement();   
    	         
    		    String query = "SELECT MAX(Emp_code) AS a FROM Employee";
                ResultSet rs = stmt.executeQuery(query);
                rs.next();
                String sme=rs.getString("a");
                if(sme==null)
                {
                	 TxtEmp_Code.setText("101");
                }
                else
                {
                	TxtEmp_Code.setText((Integer.parseInt(sme)+1)+"");
                }
            }catch(Exception e){e.printStackTrace();}
            
            
    }	
    public Addwindow(JFrame getParentFrame) 
    {
    	
    	super("Add - Employee ",true,true,true,true);
    	setSize(400,800);
    	JFParentFrame = getParentFrame;
        panel1 = new JPanel();
    	panel1.setLayout(new GridLayout(10,7));
    	box1=new JComboBox(sbox1); 
	    LblEmp_Code = new JLabel                    (" Employee Code :");
	    LblEmp_Name1 = new JLabel  					(" First Name    :");	
	    LblEmp_Name2 = new JLabel  					(" Last Name     :");
	    LblEmp_Desi  = new JLabel  					(" Designation   :");
	    LblEmp_Add = new JLabel  					(" Address       :");
	    LblEmp_No = new JLabel  					(" Contact No    :");
	    
	    TxtEmp_Code = new JTextField(20);
	    TxtEmp_Code.setEditable(false);getme();
	    Emp_Type = new JComboBox();
	    box2 = new JComboBox(sbox2);
       	Emp_Type.addActionListener(this);
    	Emp_Type.setEditable(false);
    	add_Cat_combo(Emp_Type);
	    TxtEmp_Name1 = new JTextField(20);
	    TxtEmp_Name2 = new JTextField(20);
	    pass=new JPasswordField(20);
	    TxtEmp_Add = new JTextField(20);
	    TxtEmp_No = new JTextField(20);
	    
	    panel1.add(LblEmp_Code);
	    panel1.add(TxtEmp_Code);
	    
	    panel1.add(LblEmp_Desi);
	    panel1.add(Emp_Type); 
	    
	    panel1.add(LblEmp_Name1);
	    panel1.add(TxtEmp_Name1);
	    
	    panel1.add(LblEmp_Name2);
	    panel1.add(TxtEmp_Name2);
	    
	    panel1.add(LblEmp_Add);
	    panel1.add(TxtEmp_Add);
	    
	    panel1.add(LblEmp_No);
	    panel1.add(TxtEmp_No);
	    panel1.add(new JLabel("Status"));
	    panel1.add(box1);
	    panel1.add(new JLabel("No of Dependence"));
	     panel1.add(box2);
	      panel1.add(new JLabel("Password"));
	     panel1.add(pass);
	    panel1.setOpaque(true);
	    
        panel2 = new JPanel();
   		panel2.setLayout(new FlowLayout());
   		AddBtn = new JButton("Add");
        ResetBtn = new JButton("Reset");
		ExitBtn = new JButton("Exit");
        
        
   		panel2.add(AddBtn);
   		AddBtn.addActionListener(this);
   		panel2.add(ResetBtn);
   		ResetBtn.addActionListener(this);
        panel2.add(ExitBtn);
        ExitBtn.addActionListener(this);
        panel2.setOpaque(true);
        
           	 
    	 getContentPane().setLayout(new FlowLayout());
    	  getContentPane().add(new j2()); 
    	 getContentPane().add(panel1);
    	 getContentPane().add(panel2);
    	 setFrameIcon(new ImageIcon( "images/backup.gif"));
       	 setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       	 setSize(600,500);	
       	 
       	settings.Numvalidator(TxtEmp_No);

    }
    
    	public void actionPerformed(ActionEvent event)
		{
			
			Object source = event.getSource();
					
                	if ( source.equals(Emp_Type))
    	{
				
    			Emp_Desi = (String)Emp_Type.getSelectedItem();
    			
    			
    		
    	}
                
                     
				 if(source.equals(AddBtn))
				 {
					
			Emp_Code = "";
			Emp_Name1 = "";
			Emp_Name2 = "";
			Emp_Desi = "";
			Emp_Add  = "";
			Emp_No   = ""; 
				
		
		Emp_Code = TxtEmp_Code.getText().trim();
		Emp_Name1 = TxtEmp_Name1.getText().trim();
		Emp_Name2 = TxtEmp_Name2.getText().trim();
		Emp_Desi = (String)Emp_Type.getSelectedItem();
		Emp_Add = TxtEmp_Add.getText().trim();
		Emp_No = TxtEmp_No.getText().trim();
		
		 try {
                conn = connect.setConnection(conn,"","");
            }
            catch(Exception e)
            {
            }
				 try {
		
    		 Statement stmt = conn.createStatement();
         if (!Emp_Code.equals("") &&
     		!Emp_Name1.equals("")&&
     		!Emp_Name2.equals("")&&
     		!Emp_Desi.equals("") &&
     		!Emp_Add.equals("") &&
     		!Emp_No.equals("") )
     		
     		{
     			
     			
     		String query = "SELECT * FROM EMPLOYEE WHERE Emp_Code='" + Emp_Code+"'";
                ResultSet rs = stmt.executeQuery(query);
                int foundrec = 0;
                while (rs.next())
                {
                    dialogmessage = "Record Already Exists in DataBase!!!";
                    dialogtype = JOptionPane.WARNING_MESSAGE;
                    JOptionPane.showMessageDialog((Component)null, dialogmessage, dialogs, dialogtype);
                    
                    foundrec = 1;
                    
                }
                if (foundrec == 0)
                {
                
                String temp = "INSERT INTO EMPLOYEE VALUES ('"+Emp_Code +"','" 
     														+Emp_Name1 +"','"  
     														+Emp_Name2 +"','"	
     														+Emp_Desi +"','"
     														+Emp_Add + "','"	
     														+Emp_No  + "','0','0','"+box1.getSelectedItem()+"','"+box2.getSelectedItem()+"','"+pass.getText()+"')"	;
     														
     														
     														
     			
     			  int result = stmt.executeUpdate( temp );
                                 if ( result == 1 )
                                 {  
                                 
                                 ResetRecord();
                                   String temp2 = "INSERT INTO cashloan VALUES ('"+Emp_Code +"','0','0','0','0','0')";
     								stmt.executeUpdate( temp2 );					 
     														
     													
     													
     														;
                           		
                           			
                           		JOptionPane.showMessageDialog((Component)null, "   Recorded Added", dialogs, dialogtype);
                    
                                 }
                                 else {
                                 		dialogmessage = "Failed To Insert";
                    JOptionPane.showMessageDialog(null, "Failed To Insert in DataBase",
                            "WARNING!!",JOptionPane.WARNING_MESSAGE);
                            	
                    
                                 }
                }
                
               
                	
     		}
     		
     		else
     		{
     				 dialogmessage = "Empty Record !!!";
                    dialogtype = JOptionPane.WARNING_MESSAGE;
                    JOptionPane.showMessageDialog((Component)null, dialogmessage, dialogs, dialogtype);
     			
     		}
     		
     		 conn.close();	
     			
     		}
     		
     		catch(Exception ex)
          {
          	JOptionPane.showMessageDialog(null,"GENERAL EXCEPTION", "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
          	}		
     		
			 
				   
		 }	
				 	
			else if(source  == ResetBtn)
			{
				ResetRecord();
			}
			else if(source  == ExitBtn)	
			{
				setVisible (false);
				dispose();
			}
			
		}
		

    private void ResetRecord()
    {  
        int sme2 =Integer.parseInt(TxtEmp_Code.getText())+1;
		TxtEmp_Code.setText(sme2+"");
	    TxtEmp_Name1.setText(""); 
	    TxtEmp_Name2.setText(""); 
	    TxtEmp_Add.setText(""); 
	    TxtEmp_No.setText(""); 
	    pass.setText("");
    }
    
    
    	   public void add_Cat_combo(JComboBox cmb)
        {
        	     
                   
            try {
                conn = connect.setConnection(conn,"","");
            }
            catch(Exception e)
            {
            }
            try{
             	
				           
                Statement stmt = conn.createStatement();
                
                String query = "SELECT * FROM Settings";
                ResultSet rs = stmt.executeQuery(query);
                               
                while (rs.next())
                {
                	

                	cmb.addItem(rs.getString("Category_Name")); 
                	
                }	
                	conn.close();
          }
                
        
        catch(Exception ex)
          {
          	
          	}		
          	
    }
    
    
	


}
