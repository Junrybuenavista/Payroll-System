

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
public class pass2 extends JFrame implements ActionListener
{  
    String pass="";	JPasswordField pp;j j;JTextField txt;
    ObjectOutputStream outs;ObjectInputStream ins;String date,time,name;
    txt area;
    JFrame f;
     
       public   pass2(JFrame f,ObjectOutputStream outs,ObjectInputStream ins)
      { this.f=f;
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
        	
      }
    
     public void actionPerformed(ActionEvent e)
        {
          try
          {
          	outs.writeObject("permit");
          	outs.writeObject(pp.getText());
          	String gg=(String)ins.readObject();
          	if(gg.equals("-1")){JOptionPane.showMessageDialog(this,"Incorect Password","",JOptionPane.WARNING_MESSAGE);pp.setText("");	pp.setText("");}
          	else{JOptionPane.showMessageDialog(this,"Ok","",JOptionPane.WARNING_MESSAGE);
					f.show();setVisible(false);	}
						pp.setText("");
          }catch(Exception ee){}
        }
  
	
}