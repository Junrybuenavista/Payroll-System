import java.io.*;
import java.net.*;                  import java.util.Calendar;
import javax.swing.*;               import java.util.GregorianCalendar;
import java.awt.geom.*;             import java.util.Calendar;	
import java.awt.event.*;            import java.util.Date;
import java.awt.*;                  import java.text.DateFormat;
import java.sql.*;
import java.util.StringTokenizer;
public class MyDTR extends JFrame implements ActionListener
{  
	JButton in,out,connect,b3;                        GregorianCalendar gr = new GregorianCalendar();
	JTextField txt;                                String datef, date,time,ip="";
	JLabel lab;                         	
	JTable table;
    j j;                           					 Socket soc;
	Date date1;                                    ObjectOutputStream outs;
	Timer timer;String name;
	txt area;
    manual man;
    pass pss;
    
                                   ObjectInputStream ins;
                                   
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
	public MyDTR()
	{    
	    
	      
    		
	    j=new j();
		timer=new Timer(500,this);
		timer.start();
		
		datef=getMonth((gr.get(Calendar.MONTH)+1))+"/"+gr.get(Calendar.DATE)+"/"+gr.get(Calendar.YEAR);
	    date=getMonth((gr.get(Calendar.MONTH)+1))+" "+gr.get(Calendar.DATE);
		
	    
		lab=new JLabel("Emp-ID");
		txt=new JTextField(10);
		in=new JButton("Log In");
		connect=new JButton("----CONNECT----");
		b3=new JButton     ("Manual Log in/out");
	    connect.addActionListener(this);
		in.addActionListener(this);
		out=new JButton("Log Out");
		out.addActionListener(this);
		b3.addActionListener(this);
		area=new txt();
		 in.setEnabled(false);out.setEnabled(false);b3.setEnabled(false);
		
	    Container con=getContentPane();
		con.setLayout(new BorderLayout());
		JPanel p3=new JPanel(){public Dimension getPreferredSize(){return new Dimension(400,100);}};
		p3.setBackground(Color.BLACK);
		
		JPanel p1=new JPanel(){public Dimension getPreferredSize(){return new Dimension(200,100);}};
		p1.setLayout(new FlowLayout());
		p1.add(lab);p1.add(txt);
		p1.add(in);p1.add(out);
		p1.add(connect);
		p1.add(b3);
		p1.add(new JScrollPane(area));
		
		add(p3,BorderLayout.NORTH);
		add(p1,BorderLayout.WEST);
		add(j,BorderLayout.CENTER);
		
		show();
		setSize(700,440);
		setLocation(400,400);
	
		  pss=new pass(txt,area,j,outs,ins);
		  
	}
	
	public void connectme()throws Exception
	{ 
	  	
	  	
		soc=new Socket(InetAddress.getByName(ip),5000);
		outs=new ObjectOutputStream(soc.getOutputStream());
		ins=new ObjectInputStream(soc.getInputStream());
	    load();
	    man=new manual(outs,ins,j);
		//processme();
		
	  
	}
	public void load()
	{ try
	  { 
	  
	    outs.writeObject("Load");
		outs.flush();
		String len=(String)ins.readObject();
		int len2=Integer.parseInt(len);
		System.out.println(len2+" ");
	    String vec1[][]=new String[len2][5];	    
	    String vec2[]={"LastName","Emp-Code","Date","Log-In","Log-Out"};
	    int temp=0;
		
	
	    while(true)
		{  String input=(String)ins.readObject();
		   	System.out.println(input);
		    if(input.equals("stop"))break;
			StringTokenizer tok=new StringTokenizer(input,"~");
			    
				vec1[temp][2]=tok.nextToken();
				vec1[temp][1]=tok.nextToken();
				vec1[temp][0]=tok.nextToken();
				vec1[temp][3]=tok.nextToken();
				vec1[temp][4]=tok.nextToken();
				temp++;
			
		}
	   j.setData(vec1,vec2);
	  }catch(Exception e){}
	}
	public void processme()
	{ 
	  try
	   {
			do
			{
				String s=(String)ins.readObject();
				System.out.print(s);
			}while(true);
 	   }catch(Exception e){}
	}
	public void actionPerformed(ActionEvent e)
	{  
	  if(e.getSource()==timer){
		repaint();}
	  if(e.getSource()==b3){man.pss.show();}
	  
	    if(e.getSource()==connect)
	    { try
	       {
	    	ip= JOptionPane.showInputDialog("Enter IP  :");
	    	connectme();
	       }catch(Exception ee){JOptionPane.showMessageDialog((Component)null, "Connection Error", "",JOptionPane.PLAIN_MESSAGE);return;}
	       
	       JOptionPane.showMessageDialog((Component)null, "Connection Succesfull", "",JOptionPane.PLAIN_MESSAGE);
	       in.setEnabled(true);out.setEnabled(true);b3.setEnabled(true);
	    }
	   
	   
	  if(e.getSource()==in)
	  	{   for(int i=0;i<j.getcount();i++)
	  	    		    {
	  	    		    	if(j.getValue(i,1).equalsIgnoreCase(txt.getText()))
	  	    		    	{
	  	    		    	area.printme("Employee ID already\nLog In");
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
	  	    			  area.printme("ID Not Found");	txt.setText("");
	  	    			}
	  	    		else{
	  	    		    name=(String)ins.readObject();
	  	    		    pss.set1(0);
	  	    			pss.show();
	  	    		    pss.setPass(res,date,time,name);
	  	    			  	    			
	  	    		  
						
	  	    		    }
			   }
			catch(Exception ee){ee.printStackTrace();}
		}
	  if(e.getSource()==out)	
	  	{   
	  		try{
	  				outs.writeObject("Logout");
	  	    		outs.writeObject(txt.getText());
	  	    		outs.flush();
	  	    		String res=(String)ins.readObject();
	  	    		System.out.print(res);
	  	    		if(res.equals("no"))
	  	    			{
	  	    			  area.printme("ID Not Found");	txt.setText("");
	  	    			}
	  	    		else{
	  	    		     int ii=-1;
	  				   for(int i=0;i<j.getcount();i++)
	  	    		    {
	  	    		    	if(j.getValue(i,1).equalsIgnoreCase(txt.getText()))ii=1;
	  	    		    	
	  	    		    }
	  	    		    if(ii==-1){	area.printme("You are Not Suppose \nto Log Out ");
	  	    		    outs.writeObject("pospone");outs.flush();
	  	    		    	return;}
	  	    		    
	  	    		    pss.set1(1);
	  	    		    pss.setPass2(res,time);
	  	    			pss.show();
	  	    			
	  	    		
				    	
	  	    		    }
			   }
			catch(Exception ee){ee.printStackTrace();}
		}	
	}
	
	
		public void paint(Graphics g)
    		{
    		super.paint(g);
    		date1=new Date();
    		Graphics2D g2=(Graphics2D)g;
    		
    	
    		g2.setStroke(new BasicStroke(3));
    	
    	
    		g.setFont(new Font("Cooper Black",Font.BOLD,18));
    		
    			g2.setColor(new Color(100,100,250));
    		String s2="",m="",h="";
    		if(date1.getSeconds()<=9)s2="0"+date1.getSeconds();
    		else s2=""+date1.getSeconds();
    	   	
    		if(date1.getMinutes()<=9)m="0"+date1.getMinutes();
    		else m=""+date1.getMinutes();
    		
    			
    		if(date1.getHours()<=9)h="0"+date1.getHours();
    		else h=""+date1.getHours();
    		
    		g2.drawString("Time :"+(Integer.parseInt(h)%12)+":"+m+":"+s2,15,55);
    		g2.drawString("Date :"+datef,15,80);
    		
    		if(Integer.parseInt(h)>12) 
    	             time=(Integer.parseInt(h)%12)+":"+m+" PM";
    	    else     time=h+":"+m+" AM";
    	     
    		}  	
	public static void main(String args[])
	{
		new MyDTR();
	}
	
	
	
	
 
}
