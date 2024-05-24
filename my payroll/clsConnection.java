import java.io.IOException;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JDialog;
import java.io.*;
import java.util.*;
import java.net.*;


public class clsConnection {
		String url = "";
        String username = "";
        String password = "";
	
   
   
    public Connection setConnection(Connection conn, String username, String password )
    
    {
    	try
	{
    	
    
       
        
        
          conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=PayRoll.mdb;DriverID=22;READONLY=true) ","","");
         
    }catch(SQLException e)
		{
			System.err.println("SQl Exception");
			e.printStackTrace();
			
		}
    
          
            catch (Exception e)
            {
                System.out.println("\nAnother Error");
            }
    		return conn;
 
    }
    	
    
    
       
}