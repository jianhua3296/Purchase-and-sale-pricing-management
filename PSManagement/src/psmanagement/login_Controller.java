/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psmanagement;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import DBconnection.DBConnection;
import java.io.File;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author jianh
 */
public class login_Controller implements Initializable {
    
   @FXML
    private Button loginBtn;

    @FXML
    private Label label;

    @FXML
    private Text errorTxt;

    @FXML
    private TextField userNameTxt;

    @FXML
    private TextField passwordTxt;
    
    @FXML
     void checkEnterKeyPress(ActionEvent event) throws IOException{
         handleButtonAction(event);
    }
    
    
    //encryption function using MD5
     private String encryptPassword(String input){
        String encPass=null;
        if(input==null) return null;
        
        try{
            MessageDigest digest=MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(),0,input.length());
            encPass=new BigInteger(1,digest.digest()).toString(16);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return encPass;
    }

    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        //System.out.println("click");
        //System.out.println("username: "+ userNameTxt.getText() + "\npassword: "+ passwordTxt.getText());
        String username = userNameTxt.getText();
        String password =  encryptPassword(passwordTxt.getText());
        
        //username & password correct
        if(new DBConnection().checkLogin(username,password)==true){
          
            Parent home_parent = FXMLLoader.load(getClass().getResource("home.fxml"));
            Scene home_scene = new Scene(home_parent);
            
            File f = new File("src/CSS/style.css");
            System.out.println(f.getAbsolutePath());
            home_scene.getStylesheets().add("file:///"+f.getAbsolutePath().replace("\\","/"));

            Stage app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(home_scene);
            app_stage.show();
        }
        else{//username & password incorrect
            errorTxt.setText("Wrong username or password, try again.");
        }
     
       
    }
    
    
  
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
