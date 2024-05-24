import java.sql.*;
import javax.swing.*;import javax.swing.table.DefaultTableModel;
import java.awt.*;import java.util.*;import java.util.GregorianCalendar;import java.util.Calendar;
import java.awt.event.*;import java.awt.geom.*;

 
 
 class j extends JPanel
    	{   
    	   
    	JTable tb;                     String arr[][]=new String[0][0];
    	 DefaultTableModel mod;        String arr1[]={"Date","Emp-Code","Log-In","Log-Out"};
    	 JPopupMenu pop;
    	 JMenuItem i1,i2;
    		public j()
    			{
    			
    		  mod=new DefaultTableModel(arr,arr1);
	    	  tb=new JTable(mod)
    			{
    	   	                  public boolean isCellEditable(int row, int column)
    	   	   	{
    	   	   		return false;
    	   	   	}
    	   	   	
    	   	    };
    		
    			
    				
    	
    		   
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
	       		
    			tb.setFont(new Font("Curz MT",Font.BOLD,16));    			
              	tb.setRowHeight(30);
                tb.setAutoResizeMode( JTable.AUTO_RESIZE_NEXT_COLUMN);
                add(JTable.createScrollPaneForTable(tb));
               
               
    			}
    				public void insert(String []a)
    					{
    						mod.insertRow(mod.getRowCount(), a); 
    					}
    				public void delete(int a)
    					{
    						mod.removeRow(a); 
    					}
    				public int getcount(){return mod.getRowCount();}
    		    	public String getValue(int r,int c){return (String)mod.getValueAt(r,c);}
    		    	public void setValue(int r,int c,String s){mod.setValueAt(s,r,c);}
    		        public void setData(String[][] ss,String[] s){mod.setDataVector(ss,s);}		
    		public Dimension getPreferredSize(){return new Dimension(300,200);}	
    			     
    	}