import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;import java.text.*;
import java.util.*;
import java.net.*;
import java.lang.String;
import java.awt.print.*;
import java.text.DateFormat;
import java.util.Date;


  public class Emprptwindow extends JInternalFrame implements ActionListener {
  
	JFrame JFParentFrame;
	
	static Date td  = new Date();
	static String sDate 	= DateFormat.getDateTimeInstance().format(td);
	
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	private JPanel panel5_1;
	private JPanel panel5_2;
	private JPanel panel6;
	private JPanel panel6_1;
	private JPanel panel6_2;
	private JPanel panel7;
	private JPanel panel8_1;
	private JPanel panel9;
	private JPanel panel10;
	private JPanel panel11;
	private JPanel panel12;
	private JPanel panel13;
	private JPanel panel14;
		
	private JButton GenerateBtn;
	private JButton PrintBtn;
	private JButton ExitBtn;
	
	
	private JTextField TxtCategory_Type,TxtCategory_Name;
	private JComboBox MonthCombo;	
	private JTextField TxtYear;
	private JLabel LblMonth;
	private String[] Month_Name =       {"January",
                                        "February",
                                        "March",
                                        "April",
                                        "May",
                                        "June",
                                        "July",
                                        "August",
                                        "September",
                                        "October",
                                        "November",
                                        "December"};
   
	
	String dialogmessage;double net;
    String dialogs,absent="",tot="",tax="";
    int dialogtype = JOptionPane.PLAIN_MESSAGE;
    public static int record;
    
        
    
     // Class Variables
     
     clsSettings settings = new clsSettings();
     clsConnection connect = new clsConnection();
     
     // Connection
     Connection conn;
     ResultSet rs;Statement stm;
     ////// Report Variables
     
     private JLabel Lblcollege1, Lblcollege2, Lblcollege3, Lbldate, LblSalary_Slip;
     private JLabel LblEmp_Name, LblEmp_Code, LblEmp_Desi, LblBasic_Pay, LblAllowance, LblDeduction;
     private JLabel LblDA, LblHRA, LblWA, LblGPF, LblIT, LblGIS, LblPF, LblLIC;
     private JLabel LblTot_Allowance, LblTot_Deduction, LblNet_Salary,lbl1,lbl2;
     
     private JTextField TxtDate, TxtEmp_Name1, TxtEmp_Name2 , TxtEmp_Code,TxtSalary_Month, TxtEmp_Desi, TxtBasic_Pay;
     private JTextField TxtDA, TxtHRA, TxtWA, TxtGPF, TxtIT, TxtGIS, TxtPF, TxtLIC;
     private JTextField TxtTot_Allowance, TxtTot_Deduction, TxtNet_Salary,txt1,txt2,txt3 ,txt4,txt5;
    
    
    public String sEmp_Code = "";
	public String sEmp_Name1 = "";
	public	String sEmp_Name2 = "";
	public	String sEmp_Desi = "";
	
    public String sCategory_Type = "";
    public String sCategory_Name = "";
    public String sBasic_Pay = "";
   
    public String sDA_Allow = "";
    public String sHRA_Allow = "";
    public String sWA_Allow = "";
    public String sGPF_Dedu = "";
    public String sIT_Dedu = "";
    public String sGIS_Dedu = "";
    public String sPF_Dedu = "";
    public String sLIC_Dedu = "";
    public String sAllow = "";
    public String sDedu = "";
    public String sNet_Salary = "";
    
    public String Emp_Month;
    public String Emp_Year;
    
    
    
    	public static int DA_Value,HRA_Value, WA_Value, GPF_Value, IT_Value, GIS_Value, PF_Value, LIC_Value;  
    	public static int Allow, Dedu, Net_Salary;
    
        
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
    					g.drawString("PAY SLIP",50,40);
    				}
    		public Dimension getPreferredSize(){return new Dimension(400,100);}	
    			     
    	}
     
    public Emprptwindow(JFrame getParentFrame) 
 
    {
       super("Employee PaySlip",false,true,false,true);
       setSize(600,700);
       JFParentFrame = getParentFrame;
       
      try
      { 
       conn = connect.setConnection(conn,"",""); 
	   stm=conn.createStatement(); 
	  }catch(Exception e){}            
    	panel1 = new JPanel();
    	panel1.setLayout(new FlowLayout());
    	LblEmp_Code = new JLabel("Employee Code: ");
    	TxtEmp_Code = new JTextField(10);
		LblMonth = new JLabel("For the Month :");
		
		
		MonthCombo = new JComboBox();
		MonthCombo.addActionListener(this);
				
    	for ( int i = 0; i <= 11; i++ )
    	{
    		MonthCombo.addItem(Month_Name[i]); 
    	}
    	TxtYear = new JTextField(5);    	
    	
    	panel1.add(LblEmp_Code);
    	panel1.add(TxtEmp_Code);
    	panel1.add(LblMonth);
    	panel1.add(MonthCombo);
    	panel1.add(TxtYear);
    	
    	
    	    	
    	panel2 = new JPanel();
    	panel2.setLayout(new FlowLayout());
    	
    	LblEmp_Name = new JLabel("Employee Name :");
    	TxtEmp_Name1 = new JTextField(10);
    	TxtEmp_Name2 = new JTextField(10);
    	
    	TxtEmp_Name1.setEditable(false);
    	TxtEmp_Name2.setEditable(false);
    	
    	panel2.add(LblEmp_Name);
    	panel2.add(TxtEmp_Name1);
    	panel2.add(TxtEmp_Name2);
    	
    	
    	panel3 = new JPanel();
    	panel3.setLayout(new FlowLayout());
    	
    	LblEmp_Desi = new JLabel("Designation :");
    	TxtEmp_Desi = new JTextField(20);
    	TxtEmp_Desi.setEditable(false);
    	
    
    	panel3.add(LblEmp_Desi);
    	panel3.add(TxtEmp_Desi);
    	
    
    	
    	panel4 = new JPanel();
    	panel4.setLayout(new FlowLayout());
    	
    	GenerateBtn = new JButton("Generate");
		GenerateBtn.addActionListener(this);
    	panel4.add(GenerateBtn);
    	
    

    	  ////// Report Variables
    
    	 
    	 

    	 Lblcollege2 = new JLabel("POWER 7 PAYROLL");
    	 Lbldate = new JLabel         ("    Date :");
    	 LblSalary_Slip = new JLabel  ("    Salary Slip :");
    	 LblBasic_Pay = new JLabel    ("    Basic Pay :");	
    	 LblAllowance = new JLabel    ("********** A D J U S T M E N T ********");
    	 LblDeduction = new JLabel    ("********** D  E  D  U  C  T  I  O  N ********");	
    	 LblDA  = new JLabel          ("    Food Allowance:");
    	 LblHRA = new JLabel          ("    Transportation Allowance:");
    	 LblWA  = new JLabel          ("    Overtime Pay :");
    	 
    	 LblGPF = new JLabel          ("    SSS :");
    	 LblIT  = new JLabel          ("    PhilHealth :");
    	 LblGIS = new JLabel          ("    Pag-Ibig :");
    	 LblPF  = new JLabel          ("    Tax :");
    	 lbl1=new JLabel              ("    SSS Loans : ");
    	 LblLIC = new JLabel          ("    Cash Advance :");
    	 lbl2=new JLabel               ("    UnderTime Deductions ; ");
    	 LblTot_Allowance = new JLabel("    Total Adjustment :");
    	 LblTot_Deduction = new JLabel("    Total Deduction  :");
    	 LblNet_Salary = new JLabel   ("    Net Salary       :");
    	   
    
    	 TxtDate = new JTextField(10);
    	 
    	 TxtSalary_Month = new JTextField(20);
    	 TxtBasic_Pay = new JTextField(10);
    	 TxtDA = new JTextField(5);
    	 TxtHRA = new JTextField(5);
    	 TxtWA = new JTextField(5);
    	 TxtGPF = new JTextField(5);
    	 TxtIT = new JTextField(5);
    	 TxtGIS = new JTextField(5);
    	 TxtPF = new JTextField(5);
    	 TxtLIC = new JTextField(5);
    	 txt1 = new JTextField(5);
    	 txt2 = new JTextField(5);
    	 txt3 = new JTextField(5);
    	 txt4 = new JTextField(5);
    	 txt5 = new JTextField(5);
    	 TxtTot_Allowance = new JTextField(6);
    	 TxtTot_Deduction = new JTextField(6);
    	 TxtNet_Salary = new JTextField(6);
    	 
    	 TxtDate.setEditable(false);
    	 TxtSalary_Month.setEditable(false);
    	 TxtBasic_Pay.setEditable(false);
    	 TxtDA.setEditable(false);
    	 TxtHRA.setEditable(false);
    	 TxtWA.setEditable(false);
    	 TxtGPF.setEditable(false);
    	 TxtIT.setEditable(false);
    	 TxtGIS.setEditable(false);
    	 TxtPF.setEditable(false);
    	 TxtLIC.setEditable(false);
    	 txt1.setEditable(false);
    	 txt2.setEditable(false);
    	 txt3.setEditable(false);
    	 txt4.setEditable(false);
    	 txt5.setEditable(false);
    	 
    	 TxtTot_Allowance.setEditable(false);
    	 TxtTot_Deduction.setEditable(false);
    	 TxtNet_Salary.setEditable(false);
    	 
    	 
    	 
    	 
    	 panel5 = new JPanel();
    	 panel5.setLayout(new FlowLayout());
    	 panel5.add(Lblcollege2, BorderLayout.CENTER);
    	 
    	 
    	 panel5_1 = new JPanel();
    	 panel5_1.setLayout(new GridLayout(3,3));
    	 panel5_1.add(Lbldate);
    	 panel5_1.add(TxtDate);
    	 
    	
    	 panel5_1.add(LblSalary_Slip, "CENTER");
    	 panel5_1.add(TxtSalary_Month,"RIGHT");
    	 
    	 
    	 
    	 panel5_1.add(LblBasic_Pay,"LEFT");
    	 panel5_1.add(TxtBasic_Pay,"CENTER");
    	 
    	
    	 
    	 
    	 panel8_1 = new JPanel();
    	 panel8_1.setLayout(new GridLayout(6,2));
    	 panel8_1.add(new JPanel());
    	 panel8_1.add(LblAllowance);
    	 panel8_1.add(LblDA);
    	 panel8_1.add(TxtDA);
    	 panel8_1.add(LblHRA);
    	 panel8_1.add(TxtHRA);
    	  panel8_1.add(new JLabel("    Other Allowance"));
    	 panel8_1.add(txt3);
    	 panel8_1.add(LblWA);
    	 panel8_1.add(TxtWA);
    	
    	 panel8_1.add(LblTot_Allowance);
    	 panel8_1.add(TxtTot_Allowance);
    	 
    
    	     	 
    	
    	 
    	 panel11 = new JPanel();
    	 panel11.setLayout(new GridLayout(11,7));
    	 panel11.add(new JPanel());
    	 panel11.add(LblDeduction);
    	 panel11.add(LblGPF);
    	 panel11.add(TxtGPF);
    	 panel11.add(LblIT);
    	 panel11.add(TxtIT);
    	 panel11.add(LblGIS);
    	 panel11.add(TxtGIS);
    	 panel11.add(LblPF);
    	 panel11.add(TxtPF);
    	 panel11.add(lbl1);
    	 panel11.add(txt1);
    	  panel11.add(new JLabel("    Pag-Ibig Loan"));
    	 panel11.add(txt5);
    	 panel11.add(LblLIC);
    	 panel11.add(TxtLIC);
    	 panel11.add(lbl2);
    	 panel11.add(txt2);
    	  panel11.add(new JLabel("    Absences Deduction :"));
    	 panel11.add(txt4);
    	 
    	 
    	 panel11.add(LblTot_Deduction);
    	 panel11.add(TxtTot_Deduction);
    	 
    	 
    
    	 
    	 panel13 = new JPanel();
    	 panel13.setLayout(new FlowLayout());
    	 panel13.add(LblNet_Salary);
    	 panel13.add(TxtNet_Salary);
    	 
    	 
		
		panel14 = new JPanel();
    	panel14.setLayout(new FlowLayout());
    	
    	PrintBtn = new JButton("Preview", new ImageIcon("images/prints.png"));
    	PrintBtn.addActionListener(this);
		ExitBtn = new JButton("Exit", new ImageIcon("images/exit2.png"));
		ExitBtn.addActionListener(this);
    	
    	panel14.add(PrintBtn);
    	panel14.add(ExitBtn);
    	
       
   			Container pane = getContentPane();
   			pane.setLayout(new BorderLayout());
   			JPanel ppp=new JPanel();
    		ppp.setLayout(new BoxLayout(ppp, BoxLayout.Y_AXIS));
    		
    		JPanel my=new JPanel();
    		my.setLayout(new BorderLayout());
    		my.add( panel8_1,BorderLayout.EAST);
    		my.add(panel11,BorderLayout.WEST);
    		
    		ppp.add(new j2());
			ppp.add(panel1);
			ppp.add(panel2);
			ppp.add(panel3);
			ppp.add(panel4);
			ppp.add(panel5);
			ppp.add(panel5_1);			
			ppp.add(my);
			ppp.add(panel13);
			ppp.add(panel14);
		
			
		
			pane.add(ppp,BorderLayout.CENTER);
		
       		setFrameIcon(new ImageIcon( "images/New.gif"));
       	 setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       setSize(1000,600)	;
       
     }
    
    public void actionPerformed(ActionEvent event)
    {
    		Object source = event.getSource();
    	
    				if ( source  == MonthCombo)
    	{
				
    			String Emp_Month = (String)MonthCombo.getSelectedItem();
    			
    			
    		
    	}
    	
    	if (source == PrintBtn)
    	{
    		printwindow prn = new printwindow();
    		prn.fill_data(sEmp_Code, Emp_Month , Emp_Year , sDate,absent,tot,getme(net+""),getme(tax));
       
       
       
       
    	}
    			
    	if (source == GenerateBtn)
    	{
    		sEmp_Code = TxtEmp_Code.getText().trim();
    		Get_Data(sEmp_Code);
    	    Generate_Report(sEmp_Desi);
    		
    		
    	}
    	   	
    	if (source == ExitBtn)
    	{
    		setVisible (false);
    		dispose();
    		    		
    	}
    		
    }
    

    public void Get_Data(String code)
    {
    			 	
		
		
		try {
                conn = connect.setConnection(conn,"","");
            }
            catch(Exception e)
            {
            
            }
				 try {
			    		
			    Statement stmt = conn.createStatement();          
    		
         if (!code.equals(""))
         {	
     			String query = "SELECT * FROM EMPLOYEE WHERE Emp_Code = '" + code +"'";
     		
                ResultSet rs = stmt.executeQuery(query);
                int foundrec = 0;
                while (rs.next())
                {
                		sEmp_Code = code;
                		sEmp_Name1 = rs.getString(2).trim();
                		sEmp_Name2 = rs.getString(3).trim();
						sEmp_Desi = rs.getString(4).trim();
						
						Emp_Month = (String)MonthCombo.getSelectedItem();
						Emp_Year = TxtYear.getText().trim();
						
						
						foundrec = 1;
                    	
                    	
                    	
                    
                }
                
     		if (foundrec == 0)
                {
                    dialogmessage = "No Such Employuee";
                                   
                    dialogtype = JOptionPane.WARNING_MESSAGE;
                    JOptionPane.showMessageDialog((Component)null, dialogmessage, dialogs, dialogtype);
                   
                   
                }
                
         }
         else
         {
         			dialogmessage = "No Blank Field Allowed";
                                   
                    dialogtype = JOptionPane.WARNING_MESSAGE;
                    JOptionPane.showMessageDialog((Component)null, dialogmessage, dialogs, dialogtype);
                   
                   
         }    
		  
          
     		
			 }
				 catch(Exception e)
            {
                System.out.println("\nUnknown Error at Genrate_Data");
            }
           
           
				   
		 }
		 
		 
		 public void Generate_Report(String Desi)
		 {
		 	
		 	
		 	
		try {
                conn = connect.setConnection(conn,"","");
            }
            catch(Exception e)
            {
            }
				 try {
			    		
			    Statement stmt = conn.createStatement();          
    		
         if (!Desi.equals(""))
         {	
     			String query = "SELECT * FROM Settings WHERE Category_Name = '" + Desi +"'";
     			 
                ResultSet rs = stmt.executeQuery(query);
                int foundrec = 0;
              
                while (rs.next())
                {
                	
            int vBasic_Pay;   
                sCategory_Name = rs.getString(1).trim();
                vBasic_Pay = rs.getInt(2); 
                	               
			
            int a1=Integer.parseInt(rs.getString(3)),a2=Integer.parseInt(rs.getString(4)),a3=Integer.parseInt(rs.getString(5));
            int b1=Integer.parseInt(rs.getString(6)),b2=Integer.parseInt(rs.getString(7)),b3=Integer.parseInt(rs.getString(8));  
            int c1,c2,c3;
            TxtDA.setText(a1+"");
    		TxtHRA.setText(a2+"");
    		txt3.setText(a3+"");
    		
    		TxtGPF.setText(b1+"");   	
    		TxtGIS.setText(b2+"");
    		String getMe=getme(getTax(TxtEmp_Code.getText())+"");
    		TxtPF.setText(getMe);
    		TxtIT.setText(b3+"");
    		
    			
    		   
    		
    			 query = "select * from employee  where emp_code='"+TxtEmp_Code.getText()+"'";
    			        	
    			             rs = stmt.executeQuery(query);         
                              
                              rs.next();
                      
                              TxtWA.setText(rs.getString("overtime"));
    		                  txt2.setText(rs.getString("undertime"));
    		                  
    			 
    			    			             
    			 query = "select * from cashloan  where emp_code='"+TxtEmp_Code.getText()+"'";
    			        	
    			             rs = stmt.executeQuery(query);         
                              
                              rs.next();
                      
                              c1=Integer.parseInt(rs.getString("sssmonthly"));
    		                  c2=Integer.parseInt(rs.getString("cash_advance"));
    		                  c3=Integer.parseInt(rs.getString("pagmonthly"));  
    		
    	
    		
    		
    		 txt1.setText(c1+"");
    		 TxtLIC.setText(c2+"");
    		 txt5.setText(c3+"");
    		 TxtDate.setText(sDate);
    		 TxtEmp_Name1.setText(sEmp_Name1);
    		 TxtEmp_Name2.setText(sEmp_Name2);
    		 TxtEmp_Code.setText(	sEmp_Code );
    		 TxtSalary_Month.setText("For the Month of " + Emp_Month + " , " + Emp_Year); 
    		 TxtEmp_Desi.setText(sEmp_Desi);
    		 TxtBasic_Pay.setText(vBasic_Pay+""); 	
    		 txt4.setText(getDays(TxtEmp_Code.getText())+"");	
    		TxtTot_Allowance.setText(a1+a2+a3+"");
    		tot=b1+b2+b3+Integer.parseInt(txt2.getText())+Integer.parseInt(txt4.getText())+	Double.parseDouble(TxtPF.getText())+c1+c2+c3+"";
    		TxtTot_Deduction.setText(tot);
    		net=vBasic_Pay+Double.parseDouble(TxtTot_Allowance.getText())-Double.parseDouble(TxtTot_Deduction.getText());
    		TxtNet_Salary.setText(getme(""+net));	
    			 
    			foundrec = 1;
                    
                }
                
     		if (foundrec == 0)
                {
                    dialogmessage = "No Such Employuee";
                                   
                    dialogtype = JOptionPane.WARNING_MESSAGE;
                    JOptionPane.showMessageDialog((Component)null, dialogmessage, dialogs, dialogtype);
                  
                   
                }
                
         }
         else
         {
         			dialogmessage = "No Blank Field Allowed";
                                   
                    dialogtype = JOptionPane.WARNING_MESSAGE;
                    JOptionPane.showMessageDialog((Component)null, dialogmessage, dialogs, dialogtype);
                   
                   
         }    
		  conn.close();
          
     		
			 }
				 catch(Exception e)
            {
               e.printStackTrace();
            }
           
           		   
		 }	
		
		 	 
	     public int getDays(String id)
	     {
	     	
	       int ret=0;	
	     	try
	     	{
	     		 rs=stm.executeQuery("SELECT COUNT(*) AS num FROM DTR WHERE imp_id='"+id+"'");
			     rs.next();
			     int days=Integer.parseInt(rs.getString("num"));  
			     
			     rs=stm.executeQuery("SELECT emp_desi  FROM employee WHERE emp_code='"+id+"'");
			     rs.next();String desi=rs.getString("Emp_desi");
			     
			    rs=stm.executeQuery("SELECT Basic_pay  FROM Settings WHERE category_name='"+desi+"'");
			     rs.next();
			     int basic=Integer.parseInt(rs.getString("basic_pay"))/13;
			     ret=(13-days)*basic;  
			     
			     absent=ret+"";
	     	}
	     	catch(Exception e){System.out.print("2");}
	     	return ret;
	     }
	     public double getTax(String id)
	     {
	        double ret;
	     	 int anual=0; int tot=0;
	       try{
	     	     rs=stm.executeQuery("SELECT emp_desi  FROM employee WHERE emp_code='"+id+"'");
			     rs.next();String desi=rs.getString("Emp_desi");
			     
			     rs=stm.executeQuery("SELECT *  FROM Settings WHERE category_name='"+desi+"'");
			     rs.next();
			      anual=Integer.parseInt(rs.getString(2))*24;
			      tot=anual-(((Integer.parseInt(rs.getString(6))+Integer.parseInt(rs.getString(7))+Integer.parseInt(rs.getString(8)))*24));
			  }catch(Exception e){e.printStackTrace();}  
			     if(anual<=50000)ret= 0;
			     else if(anual<=70000)ret =tot*0.15/12/4;
			     else if(anual<=140000)ret=tot*0.20/12/4;
			     else if(anual<=250000)ret=tot*0.25/12/4;
			     else if(anual<=500000)ret=tot*0.30/12/4;
			     else  ret=tot*0.32/12/4;
			     tax=ret+"";
			     return ret;
			     	
	     }
	     public String getme(String s)
	     {
	     	String ret="";int temp=0;
	     	for(int i=0;i<s.length();i++)
	     	{
	     		if(s.charAt(i)=='.')break;
	     		ret+=s.charAt(i);
	     		temp++;
	     	}
	     	ret+=".";
	     	ret+=s.charAt(temp+1);
	     	
	     	return ret;
	     }
	
	 
	
}	 	
	
  	
   	 	
		 
    

