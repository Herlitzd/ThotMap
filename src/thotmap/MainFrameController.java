/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thotmap;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Devon
 */
public class MainFrameController implements Initializable {
    
    @FXML
    public AnchorPane top;
    @FXML
    public AnchorPane bottom;
    @FXML
    public TreeView outline;
    @FXML
    public TextField title;
    @FXML
    public TextArea body;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
