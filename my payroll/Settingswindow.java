import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.net.*;

  public class Settingswindow extends JInternalFrame implements ActionListener {

	JFrame JFParentFrame;
	JDesktopPane desktop;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	private JPanel panel6;
	private JPanel panel7;
	private JPanel panel8;
		
	private JButton AddBtn;
	
	private JButton ExitBtn;
	private JButton DeleteBtn;
	private JLabel LblHeading, LblBasic_Salary, LblAllowance, LblPercent1 ;
	private JLabel LblDeduction, LblPercent2;
	private JLabel LblDA, LblHRA, LblWA, LblGPF, LblIT,LblPF;
	private JLabel Emp_Type, SELECT1, SELECT2;
					
	private JTextField   TxtBasic, TxtDA1, TxtHRA1, TxtWA1, TxtGPF1, TxtIT1, TxtPF1; 
	private JTextField TxtCategory_Name;


    
    
    
    
    		
	String dialogmessage;
    String dialogs;
     int dialogtype = JOptionPane.PLAIN_MESSAGE;
     public static int record;
     
    
     
     clsSettings settings = new clsSettings();
     clsConnection connect = new clsConnection();
     
  
     Connection conn;
    private String sCategory_Type = "";
    private String sCategory_Name = "";
    private String sBasic_Pay = "";
   
    private String sDA_Allow = "";
    private String sHRA_Allow = "";
    private String sWA_Allow = "";
    private String sGPF_Dedu = "";
    private String sIT_Dedu = "";
    private String sGIS_Dedu = "";
    private String sPF_Dedu = "";
  
    public static boolean s;
    
    
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
    					g.drawString("Add New Employee Type",50,40);
    				}
    		public Dimension getPreferredSize(){return new Dimension(400,100);}	
    			     
    	}
     
     
    public Settingswindow(JFrame getParentFrame) 
    {
    	super("Employee - Settings",false,true,false,true);
    	
    	JFParentFrame = getParentFrame;
   	    	
    	panel2 = new JPanel();
    	panel2.setLayout(new FlowLayout());
    	Emp_Type = new JLabel("Employee Type :"); 
    
       	
    	TxtCategory_Name = new JTextField(10);
    	TxtCategory_Name.setText(null);
    
    	
    	 	
    	panel2.add(Emp_Type,"LEFT");
    
    	panel2.add(TxtCategory_Name,"RIGHT");
    	
    	
    	panel3 = new JPanel();
    	panel3.setLayout(new FlowLayout());
    	LblBasic_Salary = new JLabel("Basic Salary : ");
    	TxtBasic = new JTextField(10);
    	panel3.add(LblBasic_Salary,"LEFT");
    	panel3.add(TxtBasic,"RIGHT");
    	
    	
    	 panel4 = new JPanel();
    	 panel4.setLayout(new GridLayout(1,2,5,5));
    
    	 LblAllowance = new JLabel("Allowance");
    	 LblPercent1 = new JLabel(" Allowance Value");
    
    	 
    
    	 panel4.add(LblAllowance, "CENTER");
    	 panel4.add(LblPercent1, "CENTER");

    	 
    	    	 
    	 panel5 = new JPanel();
    	 panel5.setLayout(new GridLayout(3,3,5,5));
    	 
    	 LblDA = new JLabel("Food Allowance   :");
    	 LblHRA = new JLabel("Transportation Allowance :");
    	 LblWA = new JLabel("Other Allowance    :");
    	 
    	 TxtDA1 = new JTextField();
    
    	 TxtHRA1 = new JTextField();	 
    	 TxtWA1 = new JTextField();
    	 panel5.add(LblDA);
    	 panel5.add(TxtDA1);
    	 panel5.add(LblHRA);	 
    	 panel5.add(TxtHRA1);
    	 panel5.add(LblWA);
    	 panel5.add(TxtWA1);
    	 panel6 = new JPanel();
    	 panel6.setLayout(new GridLayout(1,2,5,5));
    	
    	 LblDeduction = new JLabel("Deduction :");
    	 LblPercent2 = new JLabel("Deduction Value");
    
    	
     
    	 panel6.add(LblDeduction, "CENTER");
    	 panel6.add(LblPercent2, "CENTER");
    	 
    	 
    	 
    		panel7 = new JPanel();
    		panel7.setLayout(new GridLayout(4,3,3,3));
    	
    	 LblGPF = new JLabel ("SSS  :");
    	 LblIT = new JLabel  ("Pag-Ibig :");
    	
    	 LblPF = new JLabel  ("Philhealth  :"); 
    	 TxtGPF1 = new JTextField();
    	 TxtIT1 = new JTextField();
    
    	
    	
    	 TxtPF1 = new JTextField();
    	 TxtGPF1.setEditable(false);
    	  TxtIT1.setEditable(false);
    	 
    	  TxtPF1.setEditable(false);
    	 panel7.add(LblGPF);  	
    	 panel7.add(TxtGPF1);
    	 panel7.add(LblIT);
    	 panel7.add(TxtIT1);
    	
    	
    	 panel7.add(LblPF);   	 
    	 panel7.add(TxtPF1);   	 
    	 panel7.add(TxtPF1);
    	 panel7.setOpaque(true);
    	panel8 = new JPanel();
    	panel8.setLayout(new FlowLayout(FlowLayout.CENTER));
    	AddBtn = new JButton("Add New");
    	AddBtn.addActionListener(this); 
    	DeleteBtn = new JButton("Generate Deductions");
    	DeleteBtn.addActionListener(this);
    	ExitBtn = new JButton("Exit");
    	ExitBtn.addActionListener(this);
    	
    	panel8.add(AddBtn);
    
    	panel8.add(DeleteBtn);
    	panel8.add(ExitBtn);
    	panel8.setOpaque(true);
    	   	 
    	
    
      
  
   
   			Container pane = getContentPane();
   			pane.setLayout(new BorderLayout());
   			JPanel ppp=new JPanel();
   			
    		ppp.setLayout(new BoxLayout(ppp, BoxLayout.Y_AXIS));
			ppp.add(panel2);
			ppp.add(panel3);
			ppp.add(panel4);
			ppp.add(panel5);
			ppp.add(panel6);
			ppp.add(panel7);
			ppp.add(panel8);
			
			pane.add(new j2(),BorderLayout.NORTH);
			pane.add(ppp,BorderLayout.CENTER);
    	 
    	 setFrameIcon(new ImageIcon( "images/settings.gif"));
       	 setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       	 setSize(650,600);	
       
      settings.Numvalidator(TxtBasic);
      settings.Numvalidator(TxtDA1);
      settings.Numvalidator(TxtHRA1);
      settings.Numvalidator(TxtWA1);
      settings.Numvalidator(TxtGPF1);
      settings.Numvalidator(TxtIT1);

      settings.Numvalidator(TxtPF1);
    }
    
    public void actionPerformed(ActionEvent event)
    {
    		Object source = event.getSource();
    		
    
    	if (source == AddBtn)
    	{
    		add_record();
    	}
    
    	if (source == DeleteBtn)
    	{
    		generate();
    	}
    	
    	if (source == ExitBtn)
    	{
    		setVisible (false);
				dispose();
    		
    	}
    		
    }
    
    
    
    	
    
    	
    	public void seteditable_true(JTextField chtxt )
    	{
    	 chtxt.setEditable(true);
    	 
    	}
    	
    	public void seteditable_false(JTextField chtxt)
    	{
    		chtxt.setEditable(false);
    	}
    	
    	public void checkbox_state( JCheckBox chbox, String opt)
    	{
    		
    		
    			s = Boolean.valueOf(opt);
    			chbox.setSelected(s);
    			
    	
    	}
    	
    	public void txtbox_fill(JTextField txt1, String value)
    	{
  
    			txt1.setText(value);
    	}
    	
    	
    	   public void add_Cat_combo(JComboBox cmb)
        {
        	     
                   
            try {
                conn = connect.setConnection(conn,"","");
            }
            catch(Exception e)
            {
            }
            try{
             	
				           
                Statement stmt = conn.createStatement();
                
                String query = "SELECT * FROM Settings";
                ResultSet rs = stmt.executeQuery(query);
                               
                while (rs.next())
                {
                	

                	String Txtcmb = rs.getString(2).trim();
                	record = rs.getInt("Category_Type");
                	cmb.addItem(Txtcmb); 
                	
                }	
                	conn.close();
          }
                
        
        catch(Exception ex)
          {
          	
          	}		
          	
    }
    /////////////////////////////////
    public void add_record()
    {
    	  
    	  
    	  try {
                conn = connect.setConnection(conn,"","");
                
            }
            catch(Exception e)
            {
            
            }
            try{
            	
            	
            	record = record +1;
            	sCategory_Type = ""+record; 	            
				sCategory_Name	 = TxtCategory_Name.getText().trim();
                sBasic_Pay = TxtBasic.getText().trim();
    			sDA_Allow = TxtDA1.getText().trim();
    			sHRA_Allow =TxtHRA1.getText().trim();
    			sWA_Allow = TxtWA1.getText().trim();
    			sGPF_Dedu = TxtGPF1.getText().trim();
    			sIT_Dedu = TxtIT1.getText().trim();
    	
    			sPF_Dedu = TxtPF1.getText().trim();
    		
    		
    		
    		
    			
            	 if (!sCategory_Type.equals("") &&
            	 	!sCategory_Name.equals("")&&
     				!sBasic_Pay.equals("")&&
     				!sDA_Allow.equals("") &&
     				!sHRA_Allow.equals("")&&
     				!sWA_Allow.equals("") &&
     				!sGPF_Dedu.equals("") &&
     				!sIT_Dedu.equals("")&&
     				
     				!sPF_Dedu.equals("")) 
     			
     				{
				     					
     			System.out.println("Category Name :" +sCategory_Name);		
                Statement stmt = conn.createStatement();
                
                String query = "SELECT * FROM Settings WHERE Category_Name='" +sCategory_Name+ "'";
               
                ResultSet rs = stmt.executeQuery(query);
                int foundrec=0;                               
                while (rs.next())
                {
                
                	dialogmessage = "Record Already Exists in DataBase!!!";
                    dialogtype = JOptionPane.WARNING_MESSAGE;
                    JOptionPane.showMessageDialog((Component)null, dialogmessage, dialogs, dialogtype);
                  
                    foundrec = 1;
                	                	
              
                }	
                	  if (foundrec == 0)
                {
                
               
                String temp = "INSERT INTO Settings VALUES ('" +sCategory_Name 	+"'," +
     														   sBasic_Pay      	+","  +

     														  sDA_Allow 		+","+
     														  sHRA_Allow		+","+
     														  sWA_Allow			+","+
     														  sGPF_Dedu 		+","+
     														  sIT_Dedu 			+","+
     														  
     														  sPF_Dedu 		    +")";
     														  											
     														 
     			
     			  int result = stmt.executeUpdate( temp );
                                 if ( result == 1 )
                                 {
                           		 dialogmessage = "New Position Added";
                    			dialogtype = JOptionPane.INFORMATION_MESSAGE;
                    	JOptionPane.showMessageDialog((Component)null, dialogmessage, dialogs, dialogtype);
                    
                           		
                                 }
                                 else {
                                 		dialogmessage = "Failed To Insert";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "WARNING!!",JOptionPane.WARNING_MESSAGE);
                            	
                    
                                 }
                }
                	
     				}
     				else 
     				{
     						dialogmessage = "EMPTY VALUE FOUND";
                    JOptionPane.showMessageDialog(null, dialogmessage,
                            "WARNING!!",JOptionPane.WARNING_MESSAGE);
                            	
     					
     				} 	
                	conn.close();
          }
               
        catch(Exception ex)
          {
          	System.out.println("Unknown Error" +ex);
          	
          	
          	}
    	
    }	
    	   	
 
    	  public void fill_form(String name)
        {
        	     
                   
            try {
                conn = connect.setConnection(conn,"","");
                
            }
            catch(Exception e)
            {
            
            }
            try{
             	
             
                Statement stmt = conn.createStatement();
                
                String query = "SELECT * FROM Settings WHERE Category_Name='" +name+ "'";
               
                ResultSet rs = stmt.executeQuery(query);
                                               
                while (rs.next())
                {
                 	sCategory_Type = "";
                 	sCategory_Name = "";          
					sBasic_Pay = "";
				
    				sDA_Allow = "";
    				sHRA_Allow = "";
    				sWA_Allow = "";
    				sGPF_Dedu = "";
    				sIT_Dedu = "";
    				sGIS_Dedu = "";
    				sPF_Dedu = "";
    			
				
				
                sCategory_Type += rs.getString(1).trim();
                sCategory_Name = rs.getString(2).trim();
                sBasic_Pay = rs.getString(3).trim();

    			sDA_Allow = rs.getString(12).trim();
    			sHRA_Allow = rs.getString(13).trim();
    			sWA_Allow = rs.getString(14).trim();
    			sGPF_Dedu = rs.getString(15).trim();
    			sIT_Dedu = rs.getString(16).trim();
    			sGIS_Dedu = rs.getString(17).trim();
    			sPF_Dedu = rs.getString(18).trim();
    		
    			
    			TxtBasic.setText(sBasic_Pay);
    			
    		
  
    				txtbox_fill(TxtDA1,sDA_Allow);
    				txtbox_fill(TxtHRA1,sHRA_Allow);
    				txtbox_fill(TxtWA1,sWA_Allow);
    				txtbox_fill(TxtGPF1,sGPF_Dedu);
    				txtbox_fill(TxtIT1,sIT_Dedu);
    		
    				txtbox_fill(TxtPF1, sPF_Dedu);
    			
    					
                	                	
                rs = null;
                
                	
                }	
                	conn.close();
          }
               
        catch(Exception ex)
          {
          	
          	}		
          	
    }
    
   

//////////////////////////////////

     public void generate()
    {
    	  TxtGPF1.setText(getSSS(Integer.parseInt(TxtBasic.getText()))+"");
    	  TxtIT1.setText("100");
    	   
    	  TxtPF1.setText(getPhil(Integer.parseInt(TxtBasic.getText()))+"");		
    }
       public int getSSS(int i)
    {
    	int ret=0;
    
      	     if(i<=1249)ret=33;
    	else if(i<=1749)ret=50;
    	else if(i<=2249)ret=66;
    	else if(i<=2749)ret=83;
    	else if(i<=3249)ret=100;
    	else if(i<=3749)ret=116;
    	else if(i<=4249)ret=133;
    	else if(i<=4749)ret=150;
       	else if(i<=5249)ret=166;
    	else if(i<=5749)ret=183;
    	else if(i<=6249)ret=200;
    	else if(i<=6749)ret=216;
    	else if(i<=7249)ret=233;
    	else if(i<=7749)ret=250;
    	else if(i<=8249)ret=266;
    	else if(i<=8749)ret=283;
    	else if(i<=9249)ret=300;
    	else if(i<=9749)ret=316;
    	else if(i<=10249)ret=333;
    	else if(i<=10749)ret=350;
    	else if(i<=11249)ret=366;
    	else if(i<=11749)ret=383;
    	else if(i<=12249)ret=400;
    	else if(i<=12749)ret=416;
    	else if(i<=13249)ret=433;
    	else if(i<=13749)ret=450;    	
    	else if(i<=14249)ret=466;
    	else if(i<=14749)ret=483;
    	else if(i>=14250)ret=500;
    	
    	
    	
    	
    	
    	
    	
    	
    return ret;		
    }
    public int getPhil(int i)
    { 
     int ret=0;
    	if(i<=4999)ret=50;
    	else if(i<=5999)ret=62;
    	else if(i<=6999)ret=75;
    	else if(i<=7999)ret=87;
    	else if(i<=8999)ret=100;
    	else if(i<=9999)ret=112;
    	else if(i<=10999)ret=125;
    	else if(i<=11999)ret=137;
    	else if(i<=12999)ret=150;
    	else if(i<=13999)ret=162;
    	else if(i<=14999)ret=175;
    	else if(i<=15999)ret=187;
    	else if(i<=16999)ret=200;
    	else if(i<=17999)ret=212;
    	else if(i<=18999)ret=225;
    	else if(i<=19999)ret=237;
    	else if(i<=20999)ret=250;
    	else if(i<=21999)ret=262;
    	else if(i<=22999)ret=275;
    	else if(i<=23999)ret=287;
    	else if(i<=24999)ret=300;
    	else if(i<=25999)ret=312;
    	else if(i<=26999)ret=325;
    	else if(i<=27999)ret=337;
    	else if(i<=28999)ret=350;
    	else if(i<=29999)ret=362;
    	else if(i>=30000)ret=375;
    	
    	
    	
    	
    	
    	
    return ret;	
    }
    


///////////////////////////////
}
