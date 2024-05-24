import java.awt.print.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;   import java.text.DateFormat;                import java.util.Date;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
public class Info extends JInternalFrame implements ActionListener 
{  
  
    
    ResultSet set;	JTable tb;
   JPopupMenu pop; JMenuItem i1,i2,i3;
   String arr1[]={"EMPlOYEE CODE.","FIRST NAME","LAST NAME","DESIGNATION","ADDRESS","NUMBER"};
    int i=0;String arr[][];
     String sLogin,time;Date td;String date;
    clsConnection connect = new clsConnection();
    Connection conn;Statement stm;ResultSet rs; 
     
    
    public Info() 
    {      
       super("Employee Information",false,true,false,true);
        td=new Date();
		sLogin = DateFormat.getDateTimeInstance().format(td);
	    date=sLogin.substring(0,5);
       try
       {
       	conn = connect.setConnection(conn,"","");
       	stm=conn.createStatement();
       }catch(Exception e){}
       
       i1=new JMenuItem("DTR Monitor");
       i2=new JMenuItem("Loans And Cash Advance");
       i3=new JMenuItem("Attendance Record");
       i1.addActionListener(this);
       i2.addActionListener(this);
       i3.addActionListener(this);
    	try{
           update();
           
          
    	}catch(Exception e){System.out.print("error");}
            setLayout(new BorderLayout());
            add(new j2(),BorderLayout.NORTH);
            add(new j(),BorderLayout.CENTER);
        show();
        setSize(900,600);    
        	
    }
    public void actionPerformed(ActionEvent e)
    { 
         if(e.getSource()==i1)
         {  JFrame f=new JFrame();
            JTextArea aa=new JTextArea(7,30);
            aa.setEditable(false);
            aa.setFont(new Font("Cooper Black",Font.BOLD,20));
            f.getContentPane().add(aa);
         	String rest="";
         	try
           {
         	String stt=(String)tb.getValueAt(tb.getSelectedRow(),0);
            
         	rs=stm.executeQuery("Select * from DTR where imp_id='"+stt+"' and date='"+date+"'");
         	rs.next();
            rest= "Date\t\t\t"+rs.getString("date")+"\n";
         	rest+="Employee ID\t\t\t"+rs.getString("imp_id")+"\n";
         	rest+="Time In\t\t\t"+rs.getString("time_in")+"\n";
         	rest+="Time Out\t\t\t"+rs.getString("time_out");
         	aa.setText(rest);
         	f.show();f.pack();
            }
            catch(SQLException se){JOptionPane.showMessageDialog(this,"Employee Is Not\nCurrently Log In", "", JOptionPane.PLAIN_MESSAGE);}
            catch(Exception ee){ee.printStackTrace();}
         	
         	  
         }
          if(e.getSource()==i3)
         {  
             
            JTextArea aa=new JTextArea(29,30);
            JFrame f=new frame(aa);
            aa.setEditable(false);
            aa.setFont(new Font("Cooper Black",Font.BOLD,20));
            
         	String rest="Date\t\tTime-In\tTime-Out\n";
         	try
           {
         	String stt=(String)tb.getValueAt(tb.getSelectedRow(),0);
            
         	rs=stm.executeQuery("Select * from DTR where imp_id='"+stt+"'");
         	
         		while(rs.next())
         		{
            	rest+=rs.getString("date")+"\t\t";
         		rest+=rs.getString("time_in")+"\t";
         		rest+=rs.getString("time_out")+"\n";
         	
         	
             	}
             aa.setText(rest);
         	f.show();f.setSize(655,800);
            }
            catch(SQLException se){JOptionPane.showMessageDialog(this,"Employee Is Not\nCurrently Log In", "", JOptionPane.PLAIN_MESSAGE);}
            catch(Exception ee){ee.printStackTrace();}
         	
         	  
         }
          if(e.getSource()==i2)
          {
          	  JFrame f=new JFrame();
            JTextArea aa=new JTextArea(7,30);
            aa.setEditable(false);
            aa.setFont(new Font("Cooper Black",Font.BOLD,20));
            f.getContentPane().add(aa);
          		String stt=(String)tb.getValueAt(tb.getSelectedRow(),0);
          			String rest="";
          	try
           	{
         	rs=stm.executeQuery("Select * from cashloan where emp_code='"+stt+"'");
         	rs.next();
            rest= "Cash Advance\t\t\t\t"+rs.getString("cash_advance")+"\n";
         	rest+="Current SSS Loan\t\t\t"+rs.getString("loan")+"\n";
         	rest+="Currrent Pag-Ibig Loans\t\t\t"+rs.getString("loan2")+"\n";
         	
            aa.setText(rest);
         	f.show();f.pack();
            }
            catch(SQLException se){JOptionPane.showMessageDialog(this,"Employee Is Not\nCurrently Log In", "", JOptionPane.PLAIN_MESSAGE);}
            catch(Exception ee){ee.printStackTrace();}
          }
    	
    }
    public void update()
    	{
    		 try{
        	
        
        
             Statement st = conn.createStatement();
            set=st.executeQuery("Select count(*) from EMPLOYEE ");
           
            
          set=st.executeQuery("SELECT COUNT(*) as N FROM EMPLOYEE");
           	set.next();int iii=Integer.parseInt(set.getString("N"));
             arr=new String[iii][6];
             
             arr[0][0]="Emp_Code"; arr[0][1]="Emp_Name1"; arr[0][2]="Emp_Name2";
            		 arr[0][3]="Emp_Desi"; arr[0][4]="Emp_add"; arr[0][5]="Emp_no";
             
            set=st.executeQuery("Select * from EMPLOYEE ");
            while(set.next())
            	{   
            	
            	    arr[i][0]=set.getString("Emp_Code"); arr[i][1]=set.getString("Emp_Name1"); arr[i][2]=set.getString("Emp_Name2");
            		 arr[i][3]=set.getString("Emp_Desi"); arr[i][4]=set.getString("Emp_add"); arr[i][5]=set.getString("Emp_no");
            		i++;
            	}
            	i=0;
            	
           }
           catch(Exception e){}
    	}
  
    
    class j extends JPanel
    	{   
    		public j()
    			{
    		    pop=new JPopupMenu();
    		    pop.add(i1);pop.add(i2);pop.add(i3);	
    			setLayout(new BorderLayout());
    			tb=new JTable(arr,arr1)
    			{
    	   	  			public boolean isCellEditable(int row, int column)
    	   	   			{
    	   	   				return false;
    	   	   			}	
    	     	};
    			tb.setFont(new Font("Curz MT",Font.BOLD,16));    			
              	tb.setRowHeight(30);
                tb.setAutoResizeMode( JTable.AUTO_RESIZE_NEXT_COLUMN);
                	   
    	   	    tb.addMouseListener(
	   	        		 new MouseAdapter()
		  					{
						public void mousePressed(MouseEvent e)
							{
						if(e.isPopupTrigger())pop.show(e.getComponent(),e.getX(),getY());
							}
               			 public void mouseReleased(MouseEvent e)
                			{
                		if(e.isPopupTrigger())pop.show(e.getComponent(),e.getX(),getY());
                			}
			     }
			     
	       	);  
                add(JTable.createScrollPaneForTable(tb));
    			}
    		public Dimension getPreferredSize(){return new Dimension(300,300);}	
    			     
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
    				
		 			    
    					g.setFont(new Font("Cooper Black",Font.BOLD,30));
    					g.drawString("EMPLOYEE INFORMATION",100,40);
    				
    				}
    			
    		public Dimension getPreferredSize(){return new Dimension(400,70);}	
    			     
    	}
 
  class frame extends JFrame implements Printable,ActionListener
  {
  	JButton b;
  	public frame(JTextArea a)
  	{   
  	   
  		JPanel p=new JPanel();
  		b=new JButton("Print");
  		b.addActionListener(this);
  		p.add(a);
  		p.add(b);
  		getContentPane().add(p);
  		
  	}
  	public void actionPerformed(ActionEvent e)
  	{
  		PrinterJob Job = PrinterJob.getPrinterJob();
  		   if (Job.printDialog()) {
            try {
                Job.print();  
                
            } catch (Exception PrintException) {
                PrintException.printStackTrace();
            }
               	
			
    		
    	     Job.cancel();	
    		
 
              }
  	}
  	public int print(Graphics g, PageFormat pf, int pi) throws PrinterException
        {
                      
            Graphics2D g2 = (Graphics2D) g;
            g2.translate(pf.getImageableX()+5, pf.getImageableY()+5);
            
    	    Font  f = new Font("Cooper Black",Font.BOLD,20);
    	    g2.setFont (f);
	    	paint (g2);
	    	
            return Printable.PAGE_EXISTS;
        }
  }
}