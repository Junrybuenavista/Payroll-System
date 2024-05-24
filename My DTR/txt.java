import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.*;


public class txt extends JPanel implements ActionListener
			{   
				Timer th=new Timer(100,this);
				TextArea tx;
				String stxt; int i=0;
				public txt()
					{   
						tx=new TextArea(8,19);
						tx.setEditable(false);
						tx.setFont(new Font("",Font.BOLD,14));
					    add(tx);
						
					
					
					
						setBackground(Color.BLACK);
					}
				
				public void printme(String s)
				{   tx.setText("");
					stxt=s;
					th.start();
					i=0;
				}		
				public void actionPerformed(ActionEvent e)
				{
				   if(i==stxt.length()-1)th.stop();
   			    	tx.append(stxt.charAt(i++)+"");
				
				}	
			}