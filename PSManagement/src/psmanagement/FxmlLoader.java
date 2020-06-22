/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psmanagement;

import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author jianh
 */
public class FxmlLoader {
    private Pane view;
    
    
    //this method return a Pane with name of fileName parameter
    //it acts as a loader to switch between different scenes
    public Pane getPage(String fileName)
    {
        try {
            URL fileUrl = PSManagement.class.getResource("/psmanagement/" + fileName + ".fxml");
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException("FXML file can't be found, no such scene to load");
            }
           view = new FXMLLoader().load(fileUrl);
        } catch (Exception e) {
            System.out.println("No page " + fileName +", please check FXMLLoader.");
        }
        return view;
    }
}
