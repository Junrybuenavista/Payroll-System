import java.io.*;                  import javax.swing.*;
import java.awt.*;
import java.net.*;                 import java.text.DateFormat;
import java.sql.*;                 import java.util.Date;
import java.util.StringTokenizer;
public class DTRSERVER extends Thread
{  
	
   Socket soc;
   ObjectOutputStream outs;                     
   ObjectInputStream ins;                                       String sLogin,time;Date td;String date;
   ServerSocket ser;
   clsConnection connect = new clsConnection();
   Connection conn;
   ResultSet rs; Statement stm;Statement stm2;
   
	public DTRSERVER()
	{  
	
	    
	    
	    td=new Date();
		 sLogin = DateFormat.getDateTimeInstance().format(td);
	    date=sLogin.substring(0,5);
	 try{
	   
	  
	   conn = connect.setConnection(conn,"",""); 
	   stm=conn.createStatement();
	   stm2=conn.createStatement();
	   }catch(Exception e){}
	 	
	  
	       
	
	}
	public void run()
	{
	   connectme();
	   processme();
	}
	public void connectme()
	{ try
	  { 
	  	 ser=new ServerSocket(5000);
	   while(true)
	   { 
	    
	   
	    soc=ser.accept();
	    System.out.print("yes");
	
		outs=new ObjectOutputStream(soc.getOutputStream());
		ins=new ObjectInputStream(soc.getInputStream());
		
	
	
		
		processme();
		ins.close();
		outs.close();
		soc.close();
	   }
		
	  }catch(Exception e){}
	}
	public void processme()
	{ 
	  try
	   {
			do
			{
				String s=(String)ins.readObject();
				if(s.equals("Connected"))
				{    
				      System.out.println("DRT Conected");
					  JOptionPane.showMessageDialog((Component)null, "DTR is Connected", "",JOptionPane.PLAIN_MESSAGE);
				}
				if(s.equals("Login"))
					{   String pass="";
					    String name=""; 
						String id=(String)ins.readObject();
						int result=-1;
						rs=stm.executeQuery("select * from Employee ");
						while(rs.next())
							{   
								if(id.equalsIgnoreCase(rs.getString("emp_code"))){result=1;name=rs.getString("emp_name2");pass=rs.getString("pass");outs.flush();break;}
								
							}						
						if(result==-1){outs.writeObject("no");outs.flush();System.out.print("no");}
						else 
							{   
								outs.writeObject(pass);outs.flush();
								outs.writeObject(name);outs.flush();
								String time=(String)ins.readObject();
								
								if(!time.equals("Exit"))stm.executeUpdate("Insert into DTR values('"+date+"','"+id+"','"+time+"','???','???')");
							
							}
										
					
					
					}
				if(s.equals("permit"))
				{       String resu="-1";
				        String permit=(String)ins.readObject();
						rs=stm.executeQuery("select * from login ");
						while(rs.next())
						{
							if(rs.getString("password").equals(permit)){resu="1";break;}
						}
						outs.writeObject(resu);outs.flush();
				}	
				if(s.equals("Logout"))
					{   
					     String pass="";
						String id=(String)ins.readObject();
						int result=-1;
						rs=stm.executeQuery("select * from Employee ");
						while(rs.next())
							{
								if(id.equalsIgnoreCase(rs.getString("emp_code"))){result=1;pass=rs.getString("pass");outs.flush();break;}
							}						
						if(result==-1){outs.writeObject("no");outs.flush();System.out.print("no");}
						else 
							{
								outs.writeObject(pass);outs.flush();System.out.print("ok");
									String time=(String)ins.readObject();
									if(time.equals("pospone")||time.equals("Exit")){System.out.println("fsffs");}
									else{
										
											stm.executeUpdate("update DTR set  time_out='"+time+"' , Inn='???' where imp_id='"+id+"'");
																			
								            getWorkingTime(id);
								        	
								           
							            }
							}
										
					
					
					}
					if(s.equals("Load"))
					{   
					   rs=stm.executeQuery("SELECT COUNT(*) AS num FROM DTR WHERE date='"+date+"'");
					   rs.next();
					   outs.writeObject(rs.getString("num"));outs.flush();
				
					   	rs=stm.executeQuery("select * from DTR where date='"+date+"'");
					 
					   	while(rs.next())
					   	{
					   	String wa=rs.getString("imp_id");
					   	
					   	outs.writeObject(rs.getString("date")+"~"+wa+"~"+m1(wa)+"~"+rs.getString("time_in")+"~"+rs.getString("time_out"));
					   	outs.flush();
					   	}
					   	outs.writeObject("stop");
					   	outs.flush();
					}
					if(s.equals("salary"))
					{
						rs=stm.executeQuery("select basic_pay from setting ");
						rs.next();
						int sal=Integer.parseInt(rs.getString("basic_pay"));
			
						
						rs=stm.executeQuery("select * from DTR");
						rs.next();
						int timein=Integer.parseInt(rs.getString("time_in"));
						int timeout=Integer.parseInt(rs.getString("time_out"));					     
					}
			}while(true);
 	   }catch(Exception e){e.printStackTrace();}
	}
	
	
	public String m1(String s)
	{ String ret="";
	  try
	  { ResultSet rs2;
		rs2=stm2.executeQuery("select * from employee where emp_code='"+s+"' ");
		rs2.next();
		 ret= rs2.getString("emp_name2");
	   }catch(Exception e){e.printStackTrace();}
	   return ret;
	}
	
	
	
	
	
	
	
    public void getWorkingTime(String EmpId)
    { 	String timein="",timeout="";int basic=0;
       try{
    		rs=stm.executeQuery("select * from DTR where imp_id='"+EmpId+"'");
			rs.next();
			
						 timein=rs.getString("time_in");
						 timeout=rs.getString("time_out");
						 
			rs=stm.executeQuery("select Emp_desi from Employee where Emp_code='"+EmpId+"'");
			rs.next();
			
						 String sss=rs.getString("Emp_desi");
						 			 
			
    		rs=stm.executeQuery("select basic_pay from settings where category_name='"+sss+"'");
			rs.next();basic=(Integer.parseInt(rs.getString("basic_pay"))/26)/8;		 
		  }catch(Exception e){e.printStackTrace();}			
						
    	 
    	StringTokenizer tok1=new StringTokenizer(timein,": ");
    	int inh=Integer.parseInt(tok1.nextToken());
    	int inm=Integer.parseInt(tok1.nextToken());
    	String inst =tok1.nextToken();
    	
    	StringTokenizer tok2=new StringTokenizer(timeout,": ");
    	int outh=Integer.parseInt(tok2.nextToken());
    	int outm=Integer.parseInt(tok2.nextToken());
    	String outst =tok2.nextToken();
    	
    	int totalTime=0;
    	if(inst.equals("AM")&&outst.equals("PM"))
    	{  int count=inh;
    	   if(inh==outh){totalTime=12;}
    	   else
    	   {
    		while(count!=outh)
    		{   
    		    count=count%12;
    		    count++;
    			totalTime++;
    		}
    	   }
    		if(inm>outm)
    		totalTime=totalTime-1;
    	   
    	}
    	
    	int empOver=0,empUnder=0;
    	
    	try
    	{
    			rs=stm.executeQuery("select * from Employee where emp_code='"+EmpId+"'");
				rs.next();
			
						
						 empOver=Integer.parseInt(rs.getString("overTime"));
						 empUnder=Integer.parseInt(rs.getString("underTime"));
    	}catch(Exception e){e.printStackTrace();}
    	
    	if((inst.equals("AM")&&outst.equals("AM"))||(inst.equals("PM")&&outst.equals("PM")))
    	{  
    	   if(inm>outm)
    		totalTime=outh-inh-1;
    	   else totalTime=outh-inh;
    	}
    	
    	if(totalTime<8){empUnder+=(8-totalTime)*basic;}
    	else if(totalTime>8){empOver+=(totalTime-8)*basic;};
    
						 
		try
		{				 
    	stm.executeUpdate("update employee set    undertime='"+empUnder+"' where emp_code='"+EmpId+"'");
        }catch(Exception e){System.out.print("error");}
    	
    	
    	
    	System.out.println(inh+":"+inm+" "+inst);
    	System.out.println(outh+":"+outm+" "+outst);
    	System.out.println("consume time ,"+totalTime);
    	System.out.println("Salary "+basic);
    	

    }
	public static void main(String args[])
	{
		DTRSERVER ser=new DTRSERVER();
	     System.out.print(ser.m1("102"));
	}
	
	
	
	
 
}
