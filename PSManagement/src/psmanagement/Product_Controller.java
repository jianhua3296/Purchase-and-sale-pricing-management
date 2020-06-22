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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    @FXML
    private TextField searchBox;


    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        
        
        col_pid.setCellValueFactory(new PropertyValueFactory<>("pid"));
        col_productcode.setCellValueFactory(new PropertyValueFactory<>("productcode"));
        col_productname.setCellValueFactory(new PropertyValueFactory<>("productname"));
        col_costprice.setCellValueFactory(new PropertyValueFactory<>("costprice"));
        col_sellingprice.setCellValueFactory(new PropertyValueFactory<>("sellingprice"));
        col_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        
        productTableView.getSortOrder().addAll(col_pid);
        //productTableView.setItems(oblist);
        
        
        //connect to Database and load data into TableView
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
        
        
        
        
        //search filter applied to all column
        //wrap the obserbableList in a FilterList (initially display all data)
        FilteredList<ModelTable> filteredData = new FilteredList<>(oblist,b-> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(product -> {
                        // If filter text is empty, display all persons.

                        if (newValue == null || newValue.isEmpty()) {
                                return true;
                        }

                        
                        String lowerCaseFilter = newValue.toLowerCase();
                        
                        // Filter matches pid
                        if (product.getPid().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                                return true; 
                        } 
                        // Fileter matches product code
                        else if (product.getProductcode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                return true; 
                        }
                        // Filter matches product name
                        else if (product.getProductname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                return true; 
                        }
                        // Filter matches brand name
                        else if (product.getBrand().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                return true; 
                        }
                        // Filter matches cost price
                        else if (String.valueOf(product.getCostprice()).indexOf(lowerCaseFilter)!=-1)
                        {
                             return true;
                        }
                        // Filter matches selling price
                        else if (String.valueOf(product.getSellingprice()).indexOf(lowerCaseFilter)!=-1)
                        {
                             return true;
                        }                             
                        // Does not match
                        else
                        {
                             return false; 
                        }  
                                
                });
        });
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<ModelTable> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(productTableView.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        productTableView.setItems(sortedData);
        
        
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
