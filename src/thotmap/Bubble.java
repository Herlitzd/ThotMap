/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thotmap;

import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Devon
 */
class Bubble extends Pane{
    ArrayList<Bubble> children;
    ArrayList<Bubble> DirectParents;
    StringProperty Title;
    StringProperty Body;
    BooleanProperty IsChild;
    AnchorPoint parent;
    AnchorPoint kids;
    Rectangle Cover;
    IntegerProperty childCount;
    private final BubbleArea BubbleArea;
    public boolean isSelected;
    Bubble(BubbleArea ba) throws IOException {
        LoadData ld = new LoadData("BubbleView.fxml",BubbleViewController.class);
        this.children = new ArrayList<Bubble>();
        this.DirectParents = new ArrayList<Bubble>();
        this.BubbleArea = ba;
        this.getChildren().add(ld.node);
        BubbleViewController ctrl = (BubbleViewController) ld.Controller;
        this.kids = new AnchorPoint(false, 5,0,75, Color.ORANGE);
        this.Cover = ctrl.cover;
        this.parent = new AnchorPoint(true,5, (int) Cover.getWidth(),75,Color.PURPLE);
        
        this.getChildren().add(kids);
        this.getChildren().add(parent);
        this.Title = new SimpleStringProperty();
        this.Body = new SimpleStringProperty();
        this.IsChild = new SimpleBooleanProperty(false);
        this.childCount = new SimpleIntegerProperty();
        System.out.println(this.pickOnBoundsProperty().getValue());
        addListeners(ctrl);
    }

    private void addListeners(final BubbleViewController ctrl) {
        this.Title.bind(ctrl.Title.textProperty());
        this.Body.bind(ctrl.Body.textProperty());
//        ctrl.Body.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
//                Body = t1;
//            }
//        });
//        ctrl.Title.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
//                Title = t1;
//            }
//        });
        ctrl.cover.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if(t.getButton() == MouseButton.SECONDARY){
                    ctrl.toggleEdit();
                    t.consume();
                }else if(t.getButton() == MouseButton.PRIMARY){
                    t.consume();
                    if(Bubble.this.isSelected == true){
                        Bubble.this.isSelected =false;
                    }else{
                        Bubble.this.isSelected =true;
                    }
                    ctrl.toggleSelected(Bubble.this.isSelected);
                }
                
            }
        });
        ctrl.cover.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                Bubble.this.setCursor(Cursor.MOVE);
                Bubble.this.setLayoutX(t.getSceneX());
                Bubble.this.setLayoutY(t.getSceneY());
            }
        });
        ctrl.cover.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                Bubble.this.setCursor(Cursor.DEFAULT);
            }
        });
    }
    
}
