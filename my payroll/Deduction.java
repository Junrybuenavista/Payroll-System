import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.net.*;

public class Deduction extends JInternalFrame implements ItemListener,ActionListener  {


	JFrame JFParentFrame;
	JDesktopPane desktop;
	private JPanel panel1;
	private JPanel panel2;
	private JButton AddBtn;
	private JButton ResetBtn;
	private JButton ExitBtn;
	private JLabel LblEmp_Code, LblEmp_Name2, LblEmp_Desi,LblEmp_Add,LblEmp_No;
	private JTextField   TxtEmp_Name2,TxtEmp_Add, TxtEmp_No; 
    JComboBox Emp_Type;
	String dialogmessage;String sss;
    String dialogs;
     int dialogtype = JOptionPane.PLAIN_MESSAGE;

    	 public static int record;
     		String Emp_Code = "";
			String Emp_Name1 = "";
			String Emp_Name2 = "";
			String Emp_Desi = "";
			String Emp_Add  = "";
			String Emp_No   = ""; 
     
     
     
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
    					g.drawString("Daily Time Record",60,70);
    				}
    		public Dimension getPreferredSize(){return new Dimension(40,50);}	
    			     
    	}
    public Deduction(JFrame getParentFrame) 
    {
    	
    	super("Daily Time Records",true,true,true,true);
    	setSize(400,800);
    	JFParentFrame = getParentFrame;
        panel1 = new JPanel();
    	panel1.setLayout(new GridLayout(7,7));
    	
	   
	
	    LblEmp_Name2 = new JLabel  					("First Name     :");
	    LblEmp_Desi  = new JLabel  					("Employee Last Name   :");
	    LblEmp_Add = new JLabel  					("No.of Absenses       :");
	    LblEmp_No = new JLabel  					("Total Deduction    :");
	    
	   
	    Emp_Type = new JComboBox();
       	Emp_Type.addActionListener(this);
    	Emp_Type.setEditable(false);
    	add_Cat_combo(Emp_Type);
       Emp_Type.addItemListener(this);
	    TxtEmp_Name2 = new JTextField(20);
	    
	    TxtEmp_Add = new JTextField(20);
	    TxtEmp_No = new JTextField(20);
	    
	   
	    
	    panel1.add(LblEmp_Desi);
	    panel1.add(Emp_Type); 
	    

	    
	    panel1.add(LblEmp_Name2);
	    panel1.add(TxtEmp_Name2);
	    
	    panel1.add(LblEmp_Add);
	    panel1.add(TxtEmp_Add);
	    
	    panel1.add(LblEmp_No);
	    panel1.add(TxtEmp_No);
	    panel1.setOpaque(true);
	    
        panel2 = new JPanel();
   		panel2.setLayout(new FlowLayout());
   	
        ResetBtn = new JButton("Absent");
		ExitBtn = new JButton("Exit");
        
        
   		
   		panel2.add(ResetBtn);
   		ResetBtn.addActionListener(this);
        panel2.add(ExitBtn);
        ExitBtn.addActionListener(this);
        panel2.setOpaque(true);
        
           	 
    	 getContentPane().setLayout(new GridLayout(3,1));
    	  getContentPane().add(new j2(),"NORTH"); 
    	 getContentPane().add(panel1,"CENTER");
    	 getContentPane().add(panel2,"CENTER");
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
                
                     
			
				 	
			 if(source  == ResetBtn)
			{
				try{
					
					 sss=Emp_Type.getSelectedItem()+"";        
				     	conn = connect.setConnection(conn,"","");
   		  					    Statement stmt = conn.createStatement();
			     	 String query = "SELECT no_of_Days FROM deductions WHERE Emp_Name1='" + sss+"'";
   		  			  	
                        ResultSet  rs = stmt.executeQuery(query);
                       
                		rs.next();
                		int res=Integer.parseInt(rs.getString("no_of_Days"));
                		res++;
                	
                		String temp2 = "Update Deductions set no_of_days='"+res+"' where emp_name1='"+sss+"'";
                              stmt.execute(temp2);
                              
                       
                		  m1();
                		  m1();
                		  ResetBtn.setEnabled(false);
                           JOptionPane.showMessageDialog((Component)null, "    Deduction Absences Updated", dialogs, dialogtype);  
                                 
                         	     
                    }catch(Exception e){}       
			}
			else if(source  == ExitBtn)	
			{
				setVisible (false);
				dispose();
			}
			
		}
		
      public void  itemStateChanged(ItemEvent er)
   		  	{ 
   		  		if(er.getStateChange()==ItemEvent.SELECTED)
   		  			{
   		  			
   		  			   sss=Emp_Type.getSelectedItem()+"";
   		  			  try{
   		  			   		
   		  					    m1();
   		  			ResetBtn.setEnabled(true);
                		  }catch(Exception eee){System.out.println("ERROR");}
	 
                			   
     			    
   		  			}	   		  			
   		  	} 	
   public void m1()
   {
   			              try{ 	conn = connect.setConnection(conn,"","");
   		  					    Statement stmt = conn.createStatement();
   			    
   		  				 	String query = "SELECT * FROM Employee WHERE Emp_Name2='" + sss+"'";
   		  			  	
                            ResultSet rs = stmt.executeQuery(query);
                       
                	     	rs.next();
                		    
                			String fName=rs.getString(2);
                	    	String EmpDice=rs.getString(4);
                			
                	     //********************************************************************
                              	    
   		  				     query = "SELECT * FROM Settings WHERE Category_Name='" +EmpDice+"'";
   		  			  	
                              rs = stmt.executeQuery(query);
                       
                	     	rs.next();
                		    
                			int DD=Integer.parseInt(rs.getString(16));
                		 
   		  					    
   		  				//**********************************************************************	  
   		  			  	 query = "SELECT * FROM deductions WHERE Emp_Name1='" + sss+"'";
   		  			  	
                          rs = stmt.executeQuery(query);
                       
                		rs.next();
                		    
                			 TxtEmp_Name2.setText(fName);
                			 int i=Integer.parseInt(rs.getString("no_of_days")); 
                			 TxtEmp_Add.setText(i+""); 
                			 int tot=i*DD;
                			 TxtEmp_No.setText(tot+"");
                              String temp2 = "Update Deductions set total='"+tot+"' where emp_name1='"+sss+"'";
                              stmt.execute(temp2); 
                            }catch(Exception e){}
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
                
                String query = "SELECT * FROM Employee";
                ResultSet rs = stmt.executeQuery(query);
                               
                while (rs.next())
                {
                	

                	cmb.addItem(rs.getString("Emp_name2")); 
                	
                }	
                	conn.close();
          }
                
        
        catch(Exception ex)
          {
          	
          	}		
          	
          }
    
    
	


}
