/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBconnection;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author jianh
 */
public class DBConnection {


    static Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    boolean flag=false;
    PreparedStatement preStm = null;
    
    //Constructor starts
    public DBConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //con=DriverManager.getConnection("jdbc:mysql://localhost/IMS?user=root&password");
            con=DriverManager.getConnection("jdbc:mysql://inventorydb.cyrqeqfl5kxe.us-east-1.rds.amazonaws.com:3306/inventoryDB","admin","inventory");
            stmt=con.createStatement();
            System.out.println("Connection extablish");
        }catch(Exception e){
            e.printStackTrace();
        }
    }//end of constructor ConnectionFactory
    
    
    
    //method Connection starts
    public static Connection getConnection() throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //con=DriverManager.getConnection("jdbc:mysql://localhost/IMS?user=root&password");
            con=DriverManager.getConnection("jdbc:mysql://inventorydb.cyrqeqfl5kxe.us-east-1.rds.amazonaws.com:3306/inventoryDB","admin","inventory");
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }//end of method Connection
    
    
    
    //method checkLogin starts
    public boolean checkLogin(String username,String password){
 
        String query = "SELECT * FROM users WHERE username=? ";
        System.out.println("checkLogin passed in \nUser name:"+username+ "\nPW: "+password);

          try{
              preStm=con.prepareStatement(query);
              preStm.setString(1,username);


//              System.out.println("after replacing\nUser name:"+username+ "\nPW: "+password);

              rs=preStm.executeQuery();
              String encPass=null;
              while(rs.next()){

                 
                  String returnPW = rs.getString("password");

                  //encrypt result password with MD5
                   try{
                      MessageDigest digest=MessageDigest.getInstance("MD5");
                      digest.update(returnPW.getBytes(),0,returnPW.length());
                     encPass =new BigInteger(1,digest.digest()).toString(16);
                  }
                  catch(Exception e){
                      e.printStackTrace();
                  }

                   //compare the encrypted password that is entered by user and the one from the query result
                   //set the flag to true if they match.
                   if(encPass.equals(password))
                   {
                       //System.out.println("You've entered the correct username and password");
                       flag=true;
                   }

                  //System.out.println(returnPW);

              }
          }catch(Exception e){
              e.printStackTrace();
          }
        
        return flag;
        
    }//end checkLogin()

}
