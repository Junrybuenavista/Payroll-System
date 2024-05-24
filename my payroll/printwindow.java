import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.net.*;
import java.lang.String;
import java.awt.print.*;


  public class printwindow extends JFrame implements ActionListener , Printable  {
  
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
	
		
	
	private JTextField TxtYear;
	private JLabel LblMonth;
	
       clsConnection connect = new clsConnection();
      Connection conn;
      ResultSet rs;Statement stm;
     
     private JLabel Lblcollege2, Lbldate, LblSalary_Slip;
     private JLabel LblEmp_Name, LblEmp_Code, LblEmp_Desi, LblBasic_Pay, LblAllowance, LblDeduction;
     private JLabel LblDA, LblHRA, LblWA, LblGPF, LblIT, LblGIS, LblPF, LblLIC;
     private JLabel LblTot_Allowance, LblTot_Deduction, LblNet_Salary;
     
     private JTextField TxtDate, TxtEmp_Name1, TxtEmp_Name2 , TxtEmp_Code,TxtSalary_Month, TxtEmp_Desi, TxtBasic_Pay;
     private JTextField TxtDA, TxtHRA, TxtWA, TxtGPF, TxtIT, TxtGIS, TxtPF, TxtLIC, txt1 ,txtloan,txtunder ,txtabsent ,txtloan2;
     private JTextField TxtTot_Allowance, TxtTot_Deduction, TxtNet_Salary;
    
     private JButton PrintBtn, ExitBtn;
     
    public printwindow() 
 
    {
      try
      { 
        conn = connect.setConnection(conn,"",""); 
	   stm=conn.createStatement();
      }catch(Exception e){}
        setSize(600,700);
        setVisible(true);
        setLocation(490,185);
       	panel1 = new JPanel();
    	panel1.setLayout(new FlowLayout());
    	LblEmp_Code = new JLabel("Employee Code: ");
    	TxtEmp_Code = new JTextField(10);
		LblMonth = new JLabel("For the Month :");
		
		
    	TxtYear = new JTextField(5);    	
    	
    	panel1.add(LblEmp_Code);
    	panel1.add(TxtEmp_Code);
    	TxtEmp_Code.setEditable(false);
    	
    		
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
    
    	  ////// Report Variables
    
    	 Lblcollege2 = new JLabel("POWER 7 PAYROLL SYSTEM");
    	 Lbldate = new JLabel         ("   Date :");
    	 LblSalary_Slip = new JLabel  ("   Salary Slip :");
    	 LblBasic_Pay = new JLabel    ("   Basic Pay :");	
    	 LblAllowance = new JLabel    ("**********   A D J U S T M E N T  ********");
    	 LblDeduction = new JLabel    ("********** D  E  D  U  C  T  I  O  N ********");	
    	 LblDA  = new JLabel          ("    Food :");
    	 LblHRA = new JLabel          ("    Transportation :");
    	 LblWA  = new JLabel          ("    Other :");
    	 
    	 LblGPF = new JLabel          ("    SSS ::");
    	 LblIT  = new JLabel          ("    Philhealth :");
    	 LblGIS = new JLabel          ("    Pag-Ibig :");
    	 LblPF  = new JLabel          ("    Tax :");
    	 LblLIC = new JLabel          ("    Cash Advance :");
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
    	 TxtTot_Allowance = new JTextField(6);
    	 TxtTot_Deduction = new JTextField(6);
    	 TxtNet_Salary = new JTextField(6);
    	 txt1=new JTextField(6);
    	 txtloan=new JTextField(6);
    	 txtunder=new JTextField(6);
    	 txtabsent=new JTextField(6);
    	 txtloan2=new JTextField(6);
    	  
    	 txtloan2.setEditable(false); 
    	 txtabsent.setEditable(false); 
    	 txtunder.setEditable(false);
    	 txtloan.setEditable(false);
    	 txt1.setEditable(false);
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
    	 
    	 TxtTot_Allowance.setEditable(false);
    	 TxtTot_Deduction.setEditable(false);
    	 TxtNet_Salary.setEditable(false);
    	 
    	 PrintBtn = new JButton("Print" );
    	 ExitBtn = new JButton ("Exit");
    	 
    	 	
    	PrintBtn.addActionListener(this);
		ExitBtn.addActionListener(this);
    	 
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
    	 
    	 panel7 = new JPanel();
    	 panel7.setLayout(new FlowLayout());
    	    	 
    	 panel7.add(LblAllowance, "CENTER");
    	 
    	 
    	 panel8_1 = new JPanel();
    	 panel8_1.setLayout(new GridLayout(4,2));
    	 panel8_1.add(LblDA);
    	 panel8_1.add(TxtDA);
    	 panel8_1.add(LblHRA);
    	 panel8_1.add(TxtHRA);
    	 panel8_1.add(LblWA);
    	 panel8_1.add(TxtWA);
    	 
    	 panel8_1.add(new JLabel("    Overtime"));
    	 panel8_1.add(txt1);
    	 
    	 panel9 = new JPanel();
    	 panel9.setLayout(new GridLayout(1,3));
    	 
    	 panel9.add(LblTot_Allowance, "EAST");
    	 panel9.add(TxtTot_Allowance, "EAST");
    	     	 
    	 panel10 = new JPanel();
    	 panel10.setLayout(new FlowLayout());
    	 panel10.add(LblDeduction, "CENTER");
    	 
    	 panel11 = new JPanel();
    	 panel11.setLayout(new GridLayout(9,5));
    	 panel11.add(LblGPF);
    	 panel11.add(TxtGPF);
    	 panel11.add(LblIT);
    	 panel11.add(TxtIT);
    	 panel11.add(LblGIS);
    	 panel11.add(TxtGIS);
    	 panel11.add(LblPF);
    	 panel11.add(TxtPF);
    	 panel11.add(new JLabel("    SSS Loans"));
    	 panel11.add(txtloan);
    	 panel11.add(new JLabel("    Pag-Ibig Loans Loans"));
    	 panel11.add(txtloan2);
    	 panel11.add(LblLIC);
    	 panel11.add(TxtLIC);
    	 panel11.add(new JLabel("    Undertime"));
    	 panel11.add(txtunder);
    	 panel11.add(new JLabel("    Absences"));
    	 panel11.add(txtabsent);
    	 
    	 panel12 = new JPanel();
    	 panel12.setLayout(new GridLayout(1,3));
    	 
    	 panel12.add(LblTot_Deduction);
    	 panel12.add(TxtTot_Deduction);
    	 
    	 panel13 = new JPanel();
    	 panel13.setLayout(new FlowLayout());
    	 panel13.add(LblNet_Salary);
    	 panel13.add(TxtNet_Salary);
    	 
    	 	panel14 = new JPanel();
    		panel14.setLayout(new FlowLayout());
    		panel14.add(PrintBtn);
    		panel14.add(ExitBtn);
    	
    		Container pane = getContentPane();
    		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
			pane.add(panel1);
			pane.add(panel2);
			pane.add(panel3);
			pane.add(panel4);
			pane.add(panel5);
			pane.add(panel5_1);
			pane.add(panel7);
			pane.add(panel8_1);
			
			pane.add(panel9);
			pane.add(panel10);
			pane.add(panel11);
			pane.add(panel12);
			pane.add(panel13);
			pane.add(panel14);
			
		
       	 pack();
       	 
       	 addWindowListener(new WindowAdapter()
        {
           
        });	
    } 	 	
       	 	
	   		
	   		    public void actionPerformed(ActionEvent event)
    {
    		Object source = event.getSource();
    	
    			
    	
    	if (source == PrintBtn)
    	{
    		
    		
              try{
              	
               	conn = connect.setConnection(conn,"","");
   		  					    Statement stmt = conn.createStatement();
              
                                   
                                  rs=stm.executeQuery("Select *  from cashloan  where emp_code='"+TxtEmp_Code.getText()+"'");
                                      rs.next(); Double dloan=Double.parseDouble(rs.getString("loan"));Double dloan2=Double.parseDouble(rs.getString("loan2"));
                                                 Double sss=Double.parseDouble(rs.getString("sssmonthly"));Double pag=Double.parseDouble(rs.getString("pagmonthly"));
                                                 
                                    stm.executeUpdate("Update cashloan set loan='"+(dloan-sss)+"' , cash_advance='0' , loan2='"+(dloan2-pag)+"' where emp_code='"+TxtEmp_Code.getText()+"'");
                                    stm.executeUpdate("Update employee set overtime='0' , undertime='0'   where emp_code='"+TxtEmp_Code.getText()+"'"); 
                                    stm.executeUpdate("Delete from DTR where imp_id='"+TxtEmp_Code.getText()+"'"); 
                                  System.out.println("ee");
     								
 				}catch(Exception e){e.printStackTrace();}
    		PrintBtn.setVisible(false);
    		ExitBtn.setVisible(false);
    			
    			PrinterJob printJob = PrinterJob.getPrinterJob();
	
  		    // Get and change default page format settings if necessary.
     
        	printJob.setPrintable(this);
        if (printJob.printDialog()) {
            try {
                printJob.print();  
                
            } catch (Exception PrintException) {
                PrintException.printStackTrace();
            }
               	
			
    		
    		printJob.cancel();	
    		
 
              }
               
        
        printJob.cancel();
       		PrintBtn.setVisible(true);
    		ExitBtn.setVisible(true);
     
     
 
     
     
    	}
    			
    
    	if (source == ExitBtn)
    	{
    		setVisible (false);
    		dispose();
    		    		
    	}
    		
    }
    
	
     public void fill_data( String me,String month,String year,String date,String absent,String tot,String net,String tax)
     			{
     			
     			
     			String name1="",name2="",code="", desi="",add ="",no="",over="" ,under="" ,stat="" ,depen="";
     			String pay="",food="",tran="",other="",sss="",pagibig="",phil="",cash="",loan="",loan2="";
     			try
     			{
     			rs=stm.executeQuery("select * from employee where emp_code='"+me+"'");
     			rs.next();
     			code=rs.getString(1);
     			name1=rs.getString(2);
     			name2=rs.getString(3);
     			desi=rs.getString(4);
     			add=rs.getString(5);
     			no=rs.getString(6);
     			over=rs.getString(7);
     			under=rs.getString(8);
     			stat=rs.getString(9);
     			depen=rs.getString(10);
     			
     			rs=stm.executeQuery("select * from settings where category_name='"+desi+"'");
     			rs.next();
     				pay=rs.getString(2);
     				food=rs.getString(3);
     				tran=rs.getString(4);
     				other=rs.getString(5);
     				sss=rs.getString(6);
     				pagibig=rs.getString(7);
     				phil=rs.getString(8);
     				
     			rs=stm.executeQuery("select * from cashloan where Emp_code='"+me+"'");
     			rs.next();
     			   	cash=rs.getString(3);
     				loan=rs.getString("sssmonthly");
     				loan2=rs.getString("pagmonthly");
     				
     		    }catch(Exception e){System.out.print("ffffffff");}
     			
     			
     			
     							
     			TxtEmp_Name1.setText(name1);
    			TxtEmp_Name2.setText(name2);
    			TxtSalary_Month.setText("For the Month of " + month + " , " + year);
    			TxtEmp_Code.setText(code);
				TxtDate.setText(date);    		
    			TxtEmp_Desi.setText(desi);
    			
    			TxtBasic_Pay.setText(pay);
    			
    			TxtDA.setText(food);
    			TxtHRA.setText(tran);
    			TxtWA.setText(other);
    			TxtTot_Allowance.setText(Integer.parseInt(TxtDA.getText())+Integer.parseInt(TxtHRA.getText())+Integer.parseInt(TxtWA.getText())+"");
    			
    			TxtGPF.setText(sss);
    			TxtIT.setText(phil);
    			TxtGIS.setText(pagibig);
    			TxtPF.setText(tax);
    			
    			TxtLIC.setText(cash);
    			TxtTot_Deduction.setText(tot);
    			TxtNet_Salary.setText(net);
    			txt1.setText(over);
    			txtloan.setText(loan);
    			txtloan2.setText(loan2);
    			txtunder.setText(under);
    			txtabsent.setText(absent);
     					
     			}
    
    
       public int print(Graphics g, PageFormat pf, int pi) throws PrinterException
        {
                      
            Graphics2D g2 = (Graphics2D) g;
            g2.translate(pf.getImageableX()+5, pf.getImageableY()+5);
            
    	    Font  f = new Font("Monospaced",Font.PLAIN,12);
    	    g2.setFont (f);
	    	paint (g2);
	    	
            return Printable.PAGE_EXISTS;
        }
  		 	 
	
	
	 
	
}	 	
	
  	
   	 	
