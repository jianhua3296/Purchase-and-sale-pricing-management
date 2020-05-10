/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psmanagement;

import DBconnection.DBConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;



/**
 * FXML Controller class
 *
 * @author jianh
 */
public class Product_Controller implements Initializable {
    
    Connection con = null;
    PreparedStatement preStatement = null;
    ResultSet resultSet = null;
    
    @FXML
    private TableView<ModelTable> productTableView;
    @FXML
    private TableColumn<ModelTable, String> col_pid;
    @FXML
    private TableColumn<ModelTable, String> col_productcode;
    @FXML
    private TableColumn<ModelTable, String> col_productname;
    @FXML
    private TableColumn<ModelTable, String> col_costprice;
    @FXML
    private TableColumn<ModelTable, String> col_sellingprice;
    @FXML
    private TableColumn<ModelTable, String> col_brand;

    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        
        try {
            //1. get a connection to database 
            con = DBConnection.getConnection();
            
            // 2. set prepare statement, what query to execute
//            preStatement = con.prepareStatement("SELECT  * FROM products WHERE costprice > ?");
             preStatement = con.prepareStatement("SELECT  * FROM products");
            
            // 3. set the parameters to replace ?
//            preStatement.setDouble(1, 30);
            
            // 4 . execute SQL query
            resultSet = preStatement.executeQuery();
            
            // 5. Display the result set
            while(resultSet.next())
            {
                oblist.add(
                        new ModelTable(
                                resultSet.getString("pid"), 
                                resultSet.getString("productcode"), 
                                resultSet.getString("productname"), 
                                resultSet.getString("costprice"), 
                                resultSet.getString("sellingprice"), 
                                resultSet.getString("brand")
                        
                        ) 
                
                
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Product_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        col_pid.setCellValueFactory(new PropertyValueFactory<>("pid"));
        col_productcode.setCellValueFactory(new PropertyValueFactory<>("productcode"));
        col_productname.setCellValueFactory(new PropertyValueFactory<>("productname"));
        col_costprice.setCellValueFactory(new PropertyValueFactory<>("costprice"));
        col_sellingprice.setCellValueFactory(new PropertyValueFactory<>("sellingprice"));
        col_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
         productTableView.getSortOrder().addAll(col_pid);
        productTableView.setItems(oblist);
        
        
    }    
    
    @FXML
    private BorderPane productPane;

    
    
    void loadData()
    {
//        productTableView.
    }
    
    @FXML
    void edit(ActionEvent event)
    {
        System.out.println("edit??");
    }
    
    
}
