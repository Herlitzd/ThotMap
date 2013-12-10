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
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Devon
 */
public class BubbleViewController implements Initializable {
    @FXML
    Rectangle cover;
    @FXML
    TextArea textArea;
    @FXML
    TextField textField;
    @FXML
    Label Title;
    @FXML
    Label Body;
    @FXML
    Circle kids;
    @FXML
    Circle parent;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void CoverClicked(MouseEvent event) {
        //if(event.getClickCount() == 2){
//            this.cover.setVisible(false);
//            this.Body.setVisible(false);
//            this.Title.setVisible(false);
//            this.textArea.setVisible(true);
//            this.textField.setVisible(true);
//            this.textArea.textProperty().bindBidirectional(this.Body.textProperty());
//            this.textField.textProperty().bindBidirectional(this.Title.textProperty());
//            
//            textArea.setFocusTraversable(true);
//            textField.setFocusTraversable(true);
//            textArea.setEditable(true);
//            textField.setEditable(true);
        //}
    }
    public void toggleEdit(){
        this.cover.setVisible(false);
            this.Body.setVisible(false);
            this.Title.setVisible(false);
            this.textArea.setVisible(true);
            this.textField.setVisible(true);
            this.textArea.textProperty().bindBidirectional(this.Body.textProperty());
            this.textField.textProperty().bindBidirectional(this.Title.textProperty());
            
            textArea.setFocusTraversable(true);
            textField.setFocusTraversable(true);
            textArea.setEditable(true);
            textField.setEditable(true);
    }
        @FXML
    private void Typed(KeyEvent event) {
        if(event.getCode() == KeyCode.ESCAPE){
            this.Body.setVisible(true);
            this.Title.setVisible(true);
            this.textArea.setVisible(false);
            this.textField.setVisible(false);
            
            textArea.setFocusTraversable(false);
            textField.setFocusTraversable(false);
            textArea.setEditable(false);
            textField.setEditable(false);
            this.cover.setVisible(true);
        }
    }

    void toggleSelected(boolean isSelected) {
        if(!isSelected){
            this.cover.setEffect(new DropShadow(10,0,0, Color.GREEN));
            this.cover.setStroke(Color.GREEN);
        }else{
            this.cover.setEffect(null);
            this.cover.setStroke(Color.BLACK);
        }
        
    }
}
