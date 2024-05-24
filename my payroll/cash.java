import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.net.*;

public class cash extends JInternalFrame implements ActionListener  {


	JFrame JFParentFrame;
	JDesktopPane desktop;
	private JPanel panel1;
	private JPanel panel2;
	private JButton AddBtn;
	
	private JButton ExitBtn;
	private JLabel  LblEmp_Name2, LblEmp_Desi,LblEmp_Add,LblEmp_No;
	private JTextField   TxtEmp_Name2;
    JComboBox Emp_Type ,com1;String ssss[]={"SSS Loan","Pag-Ibig Loan","Cash Advance"};
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
    				
    					
    				   g.setFont(new Font("Cooper Black",Font.BOLD,35));
    					g.drawString("Cash Advance And Loans",55,70);
    				}
    		public Dimension getPreferredSize(){return new Dimension(600,100);}	
    			     
    	}
    public cash(JFrame getParentFrame) 
    {
    	
    	super("Cash Advance And Loans",true,true,true,true);
    	setSize(400,800);
    	JFParentFrame = getParentFrame;
        panel1 = new JPanel();
    	panel1.setLayout(new BorderLayout());
    	JPanel panel11 = new JPanel();
    	panel11.setLayout(new BorderLayout());
	   
	
	    LblEmp_Name2 = new JLabel  					("Amount    :");
	    LblEmp_Desi  = new JLabel  					("Employee Code   :");
	   
	   
	    
	   
	    Emp_Type = new JComboBox();
	    com1=new JComboBox(ssss);
	    
       	Emp_Type.addActionListener(this);
    	Emp_Type.setEditable(false);
    	add_Cat_combo(Emp_Type);
     
	    TxtEmp_Name2 = new JTextField(20);
	    
	  
	  
	   
	    JPanel p=new JPanel();
	    p.add(LblEmp_Desi);
	    p.add(Emp_Type); 
	    panel1.add(p,BorderLayout.NORTH);

	    JPanel p2=new JPanel();
	    p2.add(new JLabel("Select Transaction   :"));
	    p2.add(com1);
	    p2.add(LblEmp_Name2);
	    p2.add(TxtEmp_Name2);
	    panel1.add(p2,BorderLayout.CENTER);
	   
	   
	    

	    
        panel2 = new JPanel();
   		panel2.setLayout(new FlowLayout());
   		AddBtn = new JButton("Ok");
       
		ExitBtn = new JButton("Exit");
        
        
   		panel2.add(AddBtn);
   		AddBtn.addActionListener(this);
   	
   	
        panel2.add(ExitBtn);
        ExitBtn.addActionListener(this);
        panel2.setOpaque(true);
        panel1.add(panel2,BorderLayout.SOUTH);
        panel11.add(new j2(),BorderLayout.NORTH);
        panel11.add(panel1,BorderLayout.CENTER);
        
        getContentPane().add(panel11);
    	 setFrameIcon(new ImageIcon( "images/backup.gif"));
       	 setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       	 setSize(600,280);	
       	 
       	

    }
    
    	public void actionPerformed(ActionEvent event)
		{
			
		
					
                	if ( event.getSource()==AddBtn)
    					{  
    						if(com1.getSelectedItem().equals("Cash Advance"))
    						{
    							try{
				        		conn = connect.setConnection(conn,"","");
   		  					    Statement stmt = conn.createStatement();
   		  					   
    					   		 int mycash=Integer.parseInt(TxtEmp_Name2.getText());
    			        		String temp2 = "select cash_advance from cashloan  where emp_code='"+Emp_Type.getSelectedItem()+"'";
    			        	
    			        		ResultSet  rs = stmt.executeQuery(temp2);         
                              
                            	 rs.next();
                      
                             	 int current=Integer.parseInt(rs.getString("Cash_advance"));
                              
                          
                          		 temp2 = "Update cashloan set cash_advance='"+(current+mycash)+"' where emp_code='"+Emp_Type.getSelectedItem()+"'";
                            	  stmt.execute(temp2); 
                             	TxtEmp_Name2.setText("");
                             	 JOptionPane.showMessageDialog((Component)null, "Transaction Complete", dialogs, dialogtype);
    			           			}catch(Exception e){	System.out.print("agoi");}
    		
    						}
    						if(com1.getSelectedItem().equals("SSS Loan"))
    						{
    							try{
				        		conn = connect.setConnection(conn,"","");
   		  					    Statement stmt = conn.createStatement();
   		  					   
    					   		 int mycash=Integer.parseInt(TxtEmp_Name2.getText());
    			        		String temp2 = "select loan from cashloan  where emp_code='"+Emp_Type.getSelectedItem()+"'";
    			        	
    			        		ResultSet  rs = stmt.executeQuery(temp2);         
                              
                            	 rs.next();
                      
                             	 int current=Integer.parseInt(rs.getString("loan"));
                                 if(current==0)
                                 {
                          
                          		  temp2 = "Update cashloan set loan='"+mycash+"', sssmonthly='"+mycash/24+"' where emp_code='"+Emp_Type.getSelectedItem()+"'";
                            	  stmt.execute(temp2); 
                             	  TxtEmp_Name2.setText("");
                             	 JOptionPane.showMessageDialog((Component)null, "Transaction Complete", dialogs, dialogtype);
                             	 }
                             	 else
                             	 {
                             	    JOptionPane.showMessageDialog((Component)null, "Sory you Still have a Current SSS Loan", dialogs, dialogtype);	
                             	 }
    			           		 }catch(Exception e){	System.out.print("agoi");}
    		
    						}
    							if(com1.getSelectedItem().equals("Pag-Ibig Loan"))
    						{
    							try{
				        		conn = connect.setConnection(conn,"","");
   		  					    Statement stmt = conn.createStatement();
   		  					   
    					   		 int mycash=Integer.parseInt(TxtEmp_Name2.getText());
    			        		String temp2 = "select loan2 from cashloan  where emp_code='"+Emp_Type.getSelectedItem()+"'";
    			        	
    			        		ResultSet  rs = stmt.executeQuery(temp2);         
                              
                            	 rs.next();
                      
                             	 int current=Integer.parseInt(rs.getString("loan2"));
                                 if(current==0)
                                 {
                          
                          		  temp2 = "Update cashloan set loan2='"+mycash+"' ,pagmonthly='"+mycash/24+"' where emp_code='"+Emp_Type.getSelectedItem()+"'";
                            	  stmt.execute(temp2); 
                             	  TxtEmp_Name2.setText("");
                             	 JOptionPane.showMessageDialog((Component)null, "Transaction Complete", dialogs, dialogtype);
                             	 }
                             	 else
                             	 {
                             	    JOptionPane.showMessageDialog((Component)null, "Sory you Still have a Current Pag-Ibig  Loan", dialogs, dialogtype);	
                             	 }
    			           		 }catch(Exception e){	System.out.print("agoi");}
    		
    						}
    						
    				  	}
    				  	
    		else if(event.getSource()  == ExitBtn)	
			{
				setVisible (false);
				dispose();
			}
                
                     
			
		
			
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
                	

                	cmb.addItem(rs.getString(1)); 
                	
                }	
                	conn.close();
          }
                
        
        catch(Exception ex)
          {
          	
          	}		
          	
          }
    
    
	


}
