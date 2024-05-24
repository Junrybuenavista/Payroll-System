import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.*;
import java.lang.*;
import java.beans.PropertyVetoException;


public class MainMenu extends JFrame{
JDesktopPane desktop ;
String sMSGBOX_TITLE	= "Payroll System V. 1.0";


// Menu Bar Variables

JMenuBar menubar = new JMenuBar();


// Menu Item




// JPanel 

JPanel panel_Bottom = new JPanel();
JPanel panel_Top = new JPanel();



JLabel lblUsername = new JLabel("User Name:");
JLabel lblLogDetails = new JLabel("Time Login :");
JLabel lblTimeNow = new JLabel();


JTextField username = new JTextField();
JTextField logtime = new JTextField();


Addwindow FormAddwindow;
Editwindow FormEditwindow;
Deletewindow FormDeletewindow;
overtime over;
Info in;

cash cash;
Emprptwindow FormEmprptwindow;

Settingswindow FormSettingswindow;

Authorwindow FormAuthorwindow;
Helpwindow FormHelpwindow;
Deduction ded;


Connection conn;


static Date td  = new Date();



static Statement stmtLogin;


clsSettings settings 	= new clsSettings();


	static String sUser		= "";
	static String sLogin 	= DateFormat.getDateTimeInstance().format(td);
	 
	
    public MainMenu(String user, Date date) {
     		super("PayRoll ");
     		
     desktop=new JDesktopPane()
        	{	public void paintComponent(Graphics g)
					{super.paintComponent(g);
		   				 ImageIcon i=new ImageIcon("images\\back.jpg");
		    				i.paintIcon(this,g,0,0);
					}
							
			};		
     sUser = user;
     td = date; 
    
    JTextField username = new JTextField();
    username.setEditable(false);
	JTextField logtime = new JTextField();
	logtime.setEditable(false);
	username.setText(sUser);
	logtime.setText(sLogin);
    
     panel_Bottom.setLayout(new FlowLayout());
     panel_Bottom.setPreferredSize(new Dimension(10,25));
  
     panel_Bottom.add(lblUsername);
     panel_Bottom.add(username);
     panel_Bottom.add(lblLogDetails);
     panel_Bottom.add(logtime);

     
     panel_Top.setLayout(new BorderLayout());
     panel_Top.setPreferredSize(new Dimension(10,150));
     JToolBar tool=createJToolBar();tool.setBackground(new Color(36,119,183));
     panel_Top.add(tool,BorderLayout.PAGE_START);
          
     desktop.setBackground(Color.WHITE);
     desktop.setAutoscrolls(true);
     desktop.setBorder(BorderFactory.createLoweredBevelBorder());
     desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
     
     getContentPane().add(panel_Top,BorderLayout.PAGE_START);
     getContentPane().add(desktop,BorderLayout.CENTER);
     getContentPane().add(panel_Bottom,BorderLayout.PAGE_END);
     
     
     
     
    
    
     setIconImage(new ImageIcon("images/Business.png").getImage());
     setSize(1275,950);
     setLocation(2,2);
     show();
        
    }
    
    	public void paint(Graphics g)
    				{
    					super.paint(g);
    					ImageIcon i=new ImageIcon("images\\flag.gif");
    					i.paintIcon(this,g ,5,860);
    					
    				   
    				}
    
    protected JToolBar createJToolBar()
    {
        JToolBar toolbar = new JToolBar("Toolbar");
        	toolbar.addSeparator();	toolbar.addSeparator();
        toolbar.addSeparator();	
		
        toolbar.add(settings.CreateJToolbarButton("Add - Employee", "images/employee.png", "Emp_Add",
                JToolBarActionListener));
                   	toolbar.addSeparator();
        toolbar.add(settings.CreateJToolbarButton("Edit - Employee", "images/edit.png", "Emp_Edit",
                JToolBarActionListener));
        		toolbar.addSeparator();

      
        
        toolbar.add(settings.CreateJToolbarButton("Employee Position Settings", "images/setting.png","Settings",
                JToolBarActionListener));
       	toolbar.addSeparator();
       
        
        toolbar.add(settings.CreateJToolbarButton("Employee - Report", "images/rep.png","Reports_Employee",
                JToolBarActionListener));
        	toolbar.addSeparator();
        toolbar.add(settings.CreateJToolbarButton("Employee - Info", "images/inf.png","Employee_Information",
                JToolBarActionListener));
                	toolbar.addSeparator();   
       
                toolbar.addSeparator();	       
        toolbar.add(settings.CreateJToolbarButton("Cash Advance", "images/cash.png", "Cash Advance",
                JToolBarActionListener));
			toolbar.addSeparator();	
		toolbar.add(settings.CreateJToolbarButton("OverTime", "images/over.png", "OverTime",
                JToolBarActionListener));
			toolbar.addSeparator();		
       
        return toolbar;
        
    }
    
    ActionListener JToolBarActionListener = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            String source = e.getActionCommand();
            
            if (source == "Cash Advance")
            {
                loadJInternalFrame(2);
            }
            else if (source == "Emp_Add")
            {
                loadJInternalFrame(3);                
            }
            else if (source == "Emp_Edit")
            {
                loadJInternalFrame(4);                
            }
           
            else if (source == "Settings")
            {
                loadJInternalFrame(6);                
            }
            else if (source == "Tools_Calculator")
            {
                loadJInternalFrame(7);                
            }
            else if (source == "Tools_NotePad")
            {
                loadJInternalFrame(8);
            }
            else if (source == "Reports_Employee")
            {
                loadJInternalFrame(9);
            }
              else if (source == "Employee_Information")
            {
                loadJInternalFrame(10);
            }
            
            else if (source == "Help_Author")
            {
                loadJInternalFrame(11);
            }
            else if (source == "Help_Help")
            {
                loadJInternalFrame(12);
            }
            else if (source == "OverTime")
            {
                loadJInternalFrame(13);
            }
            
        }
    
    };
    
      
  
    private void loadJInternalFrame(int intWhich)
    {
        switch(intWhich)
        {
            
            case 2:
                cash=new cash(this);
                loadForm("Cash Advance",cash);
                break;
            
            case 3:
                try {
                	FormAddwindow = new Addwindow(this);
               loadForm("Add Employee", FormAddwindow);
                }
                catch(Exception e)
                {
                	System.out.println("\nError");
                }
                break;
            
            case 4:
                try {
                	FormEditwindow = new Editwindow(this);
               loadForm("Edit Employee", FormEditwindow);
                }
                catch(Exception e)
                {
                	System.out.println("\nError");
                }
                break;
            
           
            
            case 6:
                try {
                	FormSettingswindow = new Settingswindow(this);
               loadForm("Settings of Employee", FormSettingswindow);
                }
                catch(Exception e)
                {
                	System.out.println("\nError");
                }
                break;
           
            
            case 9:
            	try{
            		FormEmprptwindow = new Emprptwindow(this);
               		loadForm("Employee PaySlip", FormEmprptwindow);
            	
                }
                catch(Exception e)
                {
                	System.out.println("\nError" + e );
                }
                break;
                
             
            case 10:
                try {
                	in = new Info();
               loadForm("Settings of Employee",in);
                }
                catch(Exception e)
                {
                	System.out.println("\nError");
                }
                break;    
                
               
          
            
            case 13:
                 try {
                	over = new overtime();
               loadForm("OverTime",over);
                }
                catch(Exception e)
                {
                	System.out.println("\nError");
                }
                break;
                
                
        }
        
    }
    	protected void runComponents(String sComponents)
	{
		Runtime rt = Runtime.getRuntime();
		try{rt.exec(sComponents);}
		catch(IOException evt){JOptionPane.showMessageDialog(null,evt.getMessage(),"Error Found",JOptionPane.ERROR_MESSAGE);}
	}

protected void loadForm(String Title, JInternalFrame clsForm)
{

boolean xForm = isLoaded(Title);
if (xForm == false)
{
desktop.add(clsForm);
clsForm.setVisible(true);
clsForm.show();
}
else
{
try {
clsForm.setIcon(false);
clsForm.setSelected(true);

}
catch(PropertyVetoException e)
{}
 }
} // Complete Load Form methode


protected boolean isLoaded(String FormTitle)
{
 	JInternalFrame Form[] = desktop.getAllFrames();
	for ( int i = 0; i < Form.length; i++)
	{
	if (Form[i].getTitle().equalsIgnoreCase(FormTitle))
		{
			Form[i].show();
			try
			{
			Form[i].setIcon(false);
			Form[i].setSelected(true);
			
			}
			catch(PropertyVetoException e)
			{
				
				}
			return true;
		}	
	}
	return false;
} // Complete to Verify Form loaded or not

protected void UnloadWindow()
{
try
   {
	int reply = JOptionPane.showConfirmDialog(this,"Are you sure to exit?",sMSGBOX_TITLE,JOptionPane.YES_NO_OPTION,
			JOptionPane.WARNING_MESSAGE);
		if (reply == JOptionPane.YES_OPTION)
			{
			
			setVisible(false);
			System.exit(0);
			}
   }
	catch(Exception e)
	{}

}// Close the Windows

  
	public static void setlogin(String sUsername, Date sDate)
	{
		sUser  = sUsername;
		td	   = sDate;
		
		
	}//Set Login    
	

    
    
    
}




        

