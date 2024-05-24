import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.net.*;

public class Editwindow extends JInternalFrame implements ActionListener  {

	JFrame JFParentFrame;
	JDesktopPane desktop;
	private JPanel panel1;
	private JPanel panel2;
	private JButton FindBtn;
	private JButton EditBtn;
	private JButton ExitBtn;
	private JLabel LblEmp_Code,LblEmp_Name1, LblEmp_Name2, LblEmp_Desi,LblEmp_Add,LblEmp_No ,lbl1,lbl2;
	private JTextField TxtEmp_Code, TxtEmp_Name1, TxtEmp_Name2, TxtEmp_Desi,TxtEmp_Add, TxtEmp_No ;
	int dialogtype = JOptionPane.PLAIN_MESSAGE;
    String dialogmessage;
    String dialogs;
      
    JPasswordField pass; 
    private JComboBox Emp_Type ,txt1 ,txt2; 
	
//Class Variables
clsSettings settings 	= new clsSettings(); 
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
    					g.drawString("Edit Employee",60,70);
    				}
    		public Dimension getPreferredSize(){return new Dimension(600,110);}	
    			     
    	}

    public Editwindow(JFrame getParentFrame) 
    {
    	
    	super("Edit - Employee ",true,true,true,true);
    	setSize(400,800);
    	JFParentFrame = getParentFrame;
        panel1 = new JPanel();
    	panel1.setLayout(new GridLayout(10,7));
    	
	    LblEmp_Code = new JLabel                    (" Employee Code :");
	    LblEmp_Desi = new JLabel                    (" Designation   :");
	    LblEmp_Name1 = new JLabel  					(" First Name    :");	
	    LblEmp_Name2 = new JLabel  					(" Last Name     :");
	    LblEmp_Add = new JLabel  					(" Address       :");
	    LblEmp_No = new JLabel  					(" Contact No    :");
	    
	    TxtEmp_Code = new JTextField(20);
	    Emp_Type = new JComboBox();
	    txt1=new JComboBox();
	    txt2=new JComboBox();
       	Emp_Type.addActionListener(this);
    	Emp_Type.setEditable(false);
    	add_Cat_combo(Emp_Type);
    	TxtEmp_Name1 = new JTextField(20);
	    TxtEmp_Name2 = new JTextField(20);
	    TxtEmp_Add = new JTextField(20);
	    TxtEmp_No = new JTextField(20);
	     pass=new JPasswordField(20);
	    
	    TxtEmp_Name1.setEditable(false);
	    TxtEmp_Name2.setEditable(false);
	    TxtEmp_Add.setEditable(false);
	    TxtEmp_No.setEditable(false);
	     txt1.setEditable(false);
	    txt2.setEditable(false);
	    
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
	    panel1.add(txt1);	    
	     panel1.add(new JLabel("No. of Dependence"));
	    panel1.add(txt2);
	      panel1.add(new JLabel("Password"));
	     panel1.add(pass);
	    
        panel2 = new JPanel();
   		panel2.setLayout(new FlowLayout());
   		FindBtn = new JButton("Find");
   		EditBtn = new JButton("Update");
        ExitBtn = new JButton("Exit");
        
        panel2.add(FindBtn);
        FindBtn.addActionListener(this);
   		panel2.add(EditBtn);
   		EditBtn.addActionListener(this);
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
			
            String sEmp_Code = "";
			String sEmp_Name1 = "";
			String sEmp_Name2 = "";
			String sEmp_Desi = "";
			String sEmp_Add  = "";
			String sEmp_No   = "";    
				 if(source.equals(FindBtn))
				 {
				 	
				 	
					
		sEmp_Code = TxtEmp_Code.getText().trim();
		
		try {
                conn = connect.setConnection(conn,"","");
            }
            catch(Exception e)
            {
            }
				 try {
			    		
			    Statement stmt = conn.createStatement();          
    		
         if (!sEmp_Code.equals(""))
         {	
     			String query = "SELECT * FROM EMPLOYEE WHERE Emp_Code='" + sEmp_Code +"'";
                ResultSet rs = stmt.executeQuery(query);
                int foundrec = 0;
                while (rs.next())
                {
                		sEmp_Name1 = rs.getString(2).trim();
						sEmp_Name2 = rs.getString(3).trim();
						sEmp_Desi = rs.getString(4).trim();
						sEmp_Add = rs.getString(5).trim();
						sEmp_No = rs.getString(6).trim();
		                txt1.setSelectedItem(rs.getString(9).trim());
		                txt2.setSelectedItem(rs.getString(10).trim());
		                pass.setText(rs.getString(11).trim());
		                
                	TxtEmp_Name1.setText(sEmp_Name1);
                	TxtEmp_Name1.setEditable(true);
                
                	
                	TxtEmp_Name2.setText(sEmp_Name2);
                	TxtEmp_Name2.setEditable(true);
                	
                	Emp_Type.setSelectedItem(sEmp_Desi);
                	
                	TxtEmp_Add.setText(sEmp_Add);
                	TxtEmp_Add.setEditable(true);
                	
                	TxtEmp_No.setText(sEmp_No);
                	TxtEmp_No.setEditable(true);
                	
                    foundrec = 1;
                    
                }
                
     		if (foundrec == 0)
                {
                    dialogmessage = "No Such Employuee";
                                   
                    dialogtype = JOptionPane.WARNING_MESSAGE;
                    JOptionPane.showMessageDialog((Component)null, dialogmessage, dialogs, dialogtype);
                   ResetRecord();
                   
                }
                
         }
         else
         {
         			dialogmessage = "No Blank Field Allowed";
                                   
                    dialogtype = JOptionPane.WARNING_MESSAGE;
                    JOptionPane.showMessageDialog((Component)null, dialogmessage, dialogs, dialogtype);
                   ResetRecord();
                   
         }    
		  conn.close();
          
     		
			 }
				 catch(Exception e)
            {
                System.out.println("\nUnknown Error");
            }
           
           
				   
		 }	
				 	
			else if(source  == EditBtn)
			{
				
		sEmp_Code = TxtEmp_Code.getText().trim();
		sEmp_Name1 = TxtEmp_Name1.getText().trim();
		sEmp_Name2 = TxtEmp_Name2.getText().trim();
		sEmp_Desi = (String)Emp_Type.getSelectedItem();
		sEmp_Add = TxtEmp_Add.getText().trim();
		sEmp_No = TxtEmp_No.getText().trim();
		
		
		try {
                conn = connect.setConnection(conn,"","");
            }
            catch(Exception e)
            {
            }
				 try {
				
               
			    
                Statement stmt = conn.createStatement();
    		
         if (!sEmp_Code.equals("") &&
     		!sEmp_Name1.equals("")&&
     		!sEmp_Name2.equals("")&&
     		!sEmp_Desi.equals("") &&
     		!sEmp_Add.equals("") &&
     		!sEmp_No.equals("") )
     		
 					    	
     		{
     				String temp ="UPDATE EMPLOYEE SET "+
                   				"Emp_Code = '"+sEmp_Code+
     							"',Emp_Name1 = '"+sEmp_Name1+
     							"',Emp_Name2 = '"+sEmp_Name2+
     							"',Emp_Desi = '"+sEmp_Desi+
     							"',Emp_Add = '"+sEmp_Add+
     							"',Emp_No = '"+sEmp_No+	
     							"',Emp_status = '"+txt1.getSelectedItem()+	
     							"',Number_of_Dependent = '"+txt2.getSelectedItem()+"',"+	
     							"pass='"+pass.getText()+"'"+			
     							"WHERE Emp_Code = '" +sEmp_Code+"'";
     							
     				 int result = stmt.executeUpdate( temp );
     				if (result == 1)
     				{
     				//load();
                	dialogmessage = "Record Updated!!!";
                    dialogtype = JOptionPane.INFORMATION_MESSAGE;
                    JOptionPane.showMessageDialog((Component)null, dialogmessage, dialogs, dialogtype);
                     ResetRecord();
                  
                    
     				}
     				else
     				{
     				dialogmessage = "NO SUCH EMPLOYEE!!!";
                    dialogtype = JOptionPane.WARNING_MESSAGE;
                    JOptionPane.showMessageDialog((Component)null, dialogmessage, dialogs, dialogtype);	
     					
     				}
                
     		}
     		
     		else 
     		{
     			dialogmessage = "NULL VALUES OCCURED IN TEXTFIELD!!!";
                    dialogtype = JOptionPane.WARNING_MESSAGE;
                    JOptionPane.showMessageDialog((Component)null, dialogmessage, dialogs, dialogtype);	
                    ResetRecord(); 	
     		}
     	 
     	 conn.close();
     		
     		
     		}
     			catch(Exception ex)
          {
          	JOptionPane.showMessageDialog(null,"GENERAL EXCEPTION", "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
          	System.out.println("Error in Edi Btn" + ex);
          	}				    		
     		
     				
     			  
            }
          		
			
			else if(source  == ExitBtn)	
			{
				
				setVisible (false);
				dispose();
			}
			
		}

	
    private void ResetRecord()
    {
		TxtEmp_Code.setText("");
	    TxtEmp_Name1.setText(""); 
	    TxtEmp_Name2.setText(""); 
	    TxtEmp_Add.setText("");
	    TxtEmp_No.setText(""); 
	    	
	    
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
                	

                	String Txtcmb = rs.getString(1).trim();
                	
                	
                	cmb.addItem(Txtcmb); 
                	
                }	
                	conn.close();
          }
            
        
        catch(Exception ex)
          {
          	
          	}		
          	 txt1.addItem("Single");txt1.addItem("Married");
          txt2.addItem("None");txt2.addItem("One");txt2.addItem("Two");txt2.addItem("Three");txt2.addItem("Four");txt2.addItem("Five");   
    }
    public void load()
    {
       	     try{      
                Statement stmt = conn.createStatement();
                
                String query = "SELECT * FROM Employee";
                ResultSet rs = stmt.executeQuery(query);
                rs.next();
                String s1=rs.getString("Emp_status");
                String s2=rs.getString("Number_of_dependent");
                
                for(int i=0;i<txt1.getItemCount();i++)
                {
                	if(((String)txt1.getItemAt(i)).equalsIgnoreCase(s1))txt1.setSelectedItem(i);
                }
                for(int i=0;i<txt2.getItemCount();i++)
                {
                	if(((String)txt2.getItemAt(i)).equalsIgnoreCase(s2))txt2.setSelectedItem(i);
                }
                }catch(Exception e){}
                
    }
    
        	 
    
	


}
