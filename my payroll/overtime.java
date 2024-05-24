

import java.sql.*;
import javax.swing.*;              import java.util.GregorianCalendar;
import java.util.Calendar;
import java.awt.*;
import java.awt.event.*;

public class overtime extends JInternalFrame implements ActionListener 
{

	JTextField txt ,txt2; String date;
	JButton b1;String st[]={"AM","PM"}; GregorianCalendar gr = new GregorianCalendar();
	j j;
	clsConnection connect = new clsConnection();
    Connection conn;Statement stm;ResultSet rs; 

	public overtime()
	{
		super("Employee Information",false,true,false,true);
		try
		{
	    conn = connect.setConnection(conn,"","");
       	stm=conn.createStatement();
        }catch(Exception e){}
        
		Container con=getContentPane();
		con.setLayout(new BorderLayout());
		this.j=j;
		
		JPanel p1=new JPanel();
		p1.setLayout(new BorderLayout());
		JPanel pp1=new JPanel();
	
				
		pp1.add(new JLabel("Employee ID :"));
		pp1.add(txt=new JTextField(7));	
			
		p1.add(pp1,BorderLayout.NORTH);
	
		JPanel pk=new JPanel();
		pk.add(new JLabel("Number Of Hours :"));
		pk.add(txt2=new JTextField(7));
		p1.add(pk,BorderLayout.CENTER);
		
		
		JPanel p2=new JPanel();
		b1=new JButton("Approve");		
		p2.add(b1);
	
		b1.addActionListener(this);
	
		
		con.add(new j2(),BorderLayout.NORTH);
		con.add(p1,BorderLayout.CENTER);
		con.add(p2,BorderLayout.SOUTH);
       
		setSize(400,300);
	}
	
	public void actionPerformed(ActionEvent e)
	{
	       getWorkingTime(txt.getText());
	}
	
	
	public void getWorkingTime(String EmpId)
    { int basic=0;
       try{
    	
						 
			rs=stm.executeQuery("select Emp_desi from Employee where Emp_code='"+EmpId+"'");
			rs.next();
			
						 String sss=rs.getString("Emp_desi");
						 			 
			
    		rs=stm.executeQuery("select basic_pay from settings where category_name='"+sss+"'");
			rs.next();basic=(Integer.parseInt(rs.getString("basic_pay"))/26)/8;		 
		  }catch(Exception e){e.printStackTrace();}			
						
    	 
    
    	
    
    	int empOver=0;
    	
    	
    	try
    	{
    			rs=stm.executeQuery("select * from Employee where emp_code='"+EmpId+"'");
				rs.next();
			
						
						 empOver=Integer.parseInt(rs.getString("overTime"));
						
    	}catch(Exception e){e.printStackTrace();}
    	
    	empOver+=(basic*Integer.parseInt(txt2.getText()));
    
    
						 
		try
		{				 
    	stm.executeUpdate("update employee set   overtime='"+empOver+"'  where emp_code='"+EmpId+"'");
        }catch(Exception e){System.out.print("error");}
    	
    	
    	
      
    	JOptionPane.showMessageDialog(this,"Request Approved..!!", "", JOptionPane.PLAIN_MESSAGE);
        txt.setText("");txt2.setText("");
    }
	
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
    					g.drawString("Overtime",50,70);
    				}
    		public Dimension getPreferredSize(){return new Dimension(400,110);}	
    			     
    	}
 
	
	
}