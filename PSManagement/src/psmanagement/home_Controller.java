/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psmanagement;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


/**
 * FXML Controller class
 *
 * @author jianh
 */
public class home_Controller implements Initializable {
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
       @FXML
    private ImageView product_Icon;

    @FXML
    private ImageView purchase_Icon;

    @FXML
    private ImageView Sale_Icon;

    @FXML
    private ImageView vendor_Icon;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private Label label;
    
     @FXML
    private BorderPane mainBorder;

    @FXML
    void productClick(MouseEvent event) throws IOException{
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Product.fxml"));
        BorderPane productPane = loader.load();
        
        mainBorder.setCenter(productPane);
       
    }

    @FXML
    void purchaseClick(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Purchase.fxml"));
        BorderPane purchasePane = loader.load();
        
        mainBorder.setCenter(purchasePane);
    }

    @FXML
    void saleClick(MouseEvent event)throws IOException {
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Sale.fxml"));
        BorderPane salePane = loader.load();
        
        mainBorder.setCenter(salePane);
    }

    @FXML
    void vendorClick(MouseEvent event) throws IOException{
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Vendor.fxml"));
        BorderPane vendorPane = loader.load();
        
        mainBorder.setCenter(vendorPane);
    }
}
