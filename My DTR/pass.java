

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
public class pass extends JFrame implements ActionListener
{  
    String pass="";	JPasswordField pp;j j;JTextField txt;
    ObjectOutputStream outs;ObjectInputStream ins;String date,time,name;
    txt area;
    int set1=0;
     
       public   pass(JTextField txt,txt area,j j,final ObjectOutputStream outs,ObjectInputStream ins)
      {  
        this.txt=txt;
    	this.area=area;
    	this.j=j;
    	this.ins=ins;this.outs=outs;
    	
        	JPanel p=new JPanel();
    	JButton b=new JButton("Ok");
    	p.add(new JLabel("Enter Password :"));
    
    	p.add(pp=new JPasswordField(20));
    	p.add(b);
        getContentPane().add(p);
        b.addActionListener(this);
       
        setSize(250,120);
        setLocation(400,400);
        setResizable(false);
        addWindowListener(new WindowAdapter()
        {
        	public void windowClosing(WindowEvent e)
        	{ try
        	   {
        		outs.writeObject("Exit");outs.flush();
        	   }catch(Exception ee){}
        	}
        });	
      }
     public void setPass(String ss,String date,String time,String name)
      {
      	pass=ss;
      	this.date=date;
      	this.time=time;
      	this.name=name;
      }	
        public void setPass2(String ss,String time)
      {
      	pass=ss;
      	
      	this.time=time;
      
      }	
      public void set1(int i)
      {
      	set1=i;
      }
     public void actionPerformed(ActionEvent e)
        { 
         if(set1==0)
         {
           try{  
           	  	    			
	  	    			if(!pp.getText().equals(pass)){JOptionPane.showMessageDialog(this,"Incorect Password","",JOptionPane.WARNING_MESSAGE);	pp.setText("");return;}
	  	    			String loos[]={name,txt.getText(),date,time,"???"};
	  	    			txt.setText("");	  	    			
	  	    		    j.insert(loos);
	  	    			outs.writeObject(time);
	  	    			outs.flush();
	  	    			
	  	    			area.printme("Succesfully Log in\nThank You");
	  	    			setVisible(false);
	  	    			pp.setText("");
	  	    }catch(Exception ee){ee.printStackTrace();}		
        }
        if(set1==1)
        {
        	
        	   try{  
           	  	    			
	  	    			if(!pp.getText().equals(pass)){JOptionPane.showMessageDialog(this,"Incorect Password","",JOptionPane.WARNING_MESSAGE);return;}
	  	    			
	  	    			int row=-1;
	  	    		    for(int i=0;i<j.getcount();i++)
	  	    		    {
	  	    		    	if(j.getValue(i,1).equalsIgnoreCase(txt.getText())){row=i;break;}
	  	    		    }
	  	    		    j.setValue(row,4,time);
	  	    		    
	  	    			outs.writeObject(time);
	  	    			area.printme("Succesfully Log out\nThank You");
						outs.flush();
				    	txt.setText("");
				    	
	  	    			setVisible(false);
	  	           }catch(Exception ee){ee.printStackTrace();}		
        }
      }
    
    
    
	
}