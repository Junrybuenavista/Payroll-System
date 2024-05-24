


import javax.swing.*;              import java.util.GregorianCalendar;
import java.io.*;                  import java.util.Calendar;
import java.awt.*;
import java.awt.event.*;

public class manual extends JFrame implements ActionListener 
{
	ObjectOutputStream outs;ObjectInputStream ins;
	JTextField txt;JComboBox com1,com2,com3;  String date;
	JButton b1,b2;String st[]={"AM","PM"}; GregorianCalendar gr = new GregorianCalendar();
	j j;
	pass2 pss;
	public String getMonth(int i)
    {
    	String ret="";
    	if(i==1)ret="Jan";
    	if(i==2)ret="Feb";
    	if(i==3)ret="Mar";
    	if(i==4)ret="Apr";
    	if(i==5)ret="May";
    	if(i==6)ret="Jun";
    	if(i==7)ret="Jul";
    	if(i==8)ret="Aug";
    	if(i==9)ret="Sep";
    	if(i==10)ret="Oct";
    	if(i==11)ret="Nov";
    	if(i==12)ret="Dec";
    	return ret;
    }  
	
	public manual(ObjectOutputStream outs,ObjectInputStream ins,j j)
	{
		
		this.outs=outs;this.ins=ins;
		Container con=getContentPane();
		con.setLayout(new BorderLayout());
		this.j=j;
		 date=getMonth((gr.get(Calendar.MONTH)+1))+" "+gr.get(Calendar.DATE);
		JPanel p1=new JPanel();
		p1.setLayout(new BorderLayout());
		JPanel pp1=new JPanel();
		JPanel pp2=new JPanel();
				
		pp1.add(new JLabel("Employee ID :"));
		pp1.add(txt=new JTextField(7));	
		pp2.add(new JLabel("Hour"));
		pp2.add(com1=new JComboBox());
		pp2.add(new JLabel("Minute"));
		pp2.add(com2=new JComboBox());
		pp2.add(com3=new JComboBox(st));	
		p1.add(pp1,BorderLayout.NORTH);
		p1.add(pp2,BorderLayout.CENTER);
		
		
		JPanel p2=new JPanel();
		b1=new JButton("Log In");		
		p2.add(b1);
		p2.add(b2=new JButton("Log Out"));
		b1.addActionListener(this);
		b2.addActionListener(this);
	
		load();
	
		
		con.add(new j2(),BorderLayout.NORTH);
		con.add(p1,BorderLayout.CENTER);
		con.add(p2,BorderLayout.SOUTH);
        pss=new pass2(this,outs,ins);
		setSize(400,300);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			
	  	    
	  	 for(int i=0;i<j.getcount();i++)
	  	    		    {
	  	    		    	if(j.getValue(i,1).equalsIgnoreCase(txt.getText()))
	  	    		    	{
	  	    		    	JOptionPane.showMessageDialog(this,"Employee ID Already Log In","",JOptionPane.WARNING_MESSAGE);
	  	    		    	txt.setText("");
	  	    		    	return;
	  	    		        }
	  	    		    }
	  	    
	  		try{
	  			
	  				outs.writeObject("Login");	  			
	  	    		outs.writeObject(txt.getText());
	  	    		outs.flush();
	  	    		String res=(String)ins.readObject();
	  	    		System.out.print(res);
	  	    		if(res.equals("no"))
	  	    			{
	  	    			  JOptionPane.showMessageDialog(this,"Employee ID not Found ","",JOptionPane.WARNING_MESSAGE);
	  	    			}
	  	    		else{
	  	    				    
	  	    			String name=(String)ins.readObject();
	  	    			String loos[]={name,txt.getText(),date,com1.getSelectedItem()+":"+com2.getSelectedItem()+" "+com3.getSelectedItem(),"???"};
	  	    			txt.setText("");	  	    			
	  	    		    j.insert(loos);
	  	    			outs.writeObject(com1.getSelectedItem()+":"+com2.getSelectedItem()+" "+com3.getSelectedItem());
	  	    			outs.flush();
	  	    			
	  	    			JOptionPane.showMessageDialog(this,"Proccess Complete","",JOptionPane.WARNING_MESSAGE);
						
	  	    		    }
			   }
			catch(Exception ee){ee.printStackTrace();}
		}
		if(e.getSource()==b2)
		{
			
	  		try{
	  			
	  			     
	  				outs.writeObject("Logout");
	  	    		outs.writeObject(txt.getText());
	  	    		outs.flush();
	  	    		String res=(String)ins.readObject();
	  	    		System.out.print(res);
	  	    		if(res.equals("no"))
	  	    			{
	  	    			 JOptionPane.showMessageDialog(this,"Employee ID not Found ","",JOptionPane.WARNING_MESSAGE);
	  	    			}
	  	    		else{
	  	    		     int ii=-1;
	  				   for(int i=0;i<j.getcount();i++)
	  	    		    {
	  	    		    	if(j.getValue(i,1).equalsIgnoreCase(txt.getText()))ii=1;
	  	    		    	
	  	    		    }
	  	    		    if(ii==-1){	JOptionPane.showMessageDialog(this,"You are Not Suppose to Log Out","",JOptionPane.WARNING_MESSAGE);
	  	    		    outs.writeObject("pospone");outs.flush();
	  	    		    	return;}
	  	    		    
	  	    			int row=-1;
	  	    		    for(int i=0;i<j.getcount();i++)
	  	    		    {
	  	    		    	if(j.getValue(i,1).equalsIgnoreCase(txt.getText())){row=i;break;}
	  	    		    }
	  	    		    j.setValue(row,4,com1.getSelectedItem()+":"+com2.getSelectedItem()+" "+com3.getSelectedItem());
	  	    		    
	  	    			outs.writeObject(com1.getSelectedItem()+":"+com2.getSelectedItem()+" "+com3.getSelectedItem());
	  	    			JOptionPane.showMessageDialog(this,"Proccess Complete","",JOptionPane.WARNING_MESSAGE);
						outs.flush();
				    	txt.setText("");
				    	
	  	    		    }
			   }
			catch(Exception ee){ee.printStackTrace();}
		}
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
    					g.drawString("Manual Log In/Out",40,70);
    				}
    		public Dimension getPreferredSize(){return new Dimension(400,110);}	
    			     
    	}
    public void load()
    {  
    
    	for(int i=1;i<=12;i++)
    	{
    		com1.addItem(i+"");
    	}
    	com2.addItem("00");
    		for(int i=1;i<=59;i++)
    	{  
    	   if(i<=9)com2.addItem("0"+i);
    	   else
    		com2.addItem(i+"");
    	}

    }	
	public static void main(String args[]){}//new manual();}
	
}