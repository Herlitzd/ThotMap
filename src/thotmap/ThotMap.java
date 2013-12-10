/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thotmap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Devon
 */
public class ThotMap extends Application {

    BubbleArea bubbleArea;
    TreeItem TreeRoot;
    ContextMenu contextMenu;
    TreeView Outline;

    @Override
    public void start(Stage stage) throws Exception {
        LoadData MainFrame = new LoadData("MainFrame.fxml", MainFrameController.class);
        AnchorPane root = (AnchorPane) MainFrame.node;
        final Scene scene = new Scene(root);
        bubbleArea = new BubbleArea(this, scene);


        ((MainFrameController) MainFrame.Controller).top.getChildren().add(bubbleArea);




        TreeRoot = new TreeItem("Root");
        Outline = ((MainFrameController) MainFrame.Controller).outline;
        
        Outline.setRoot(TreeRoot);
        Outline.setShowRoot(false);
        
        GenerateTree(bubbleArea.Bubbles);

        stage.setScene(scene);
        stage.show();
        this.addListeners(scene, ((MainFrameController) MainFrame.Controller));
    }

    private void addListeners(final Scene scene, final MainFrameController ctrl) {
        contextMenu = new ContextMenu();
        MenuItem m = new MenuItem("Add Bubble");

        contextMenu.getItems().add(m);
        ctrl.top.setPickOnBounds(true);
        ctrl.top.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.getButton() == MouseButton.SECONDARY) {
                    contextMenu.show(scene.getWindow(), t.getScreenX(), t.getScreenY());
                }
            }
        });
        ctrl.top.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent t) {
                AnchorPane top =(AnchorPane) t.getSource();
                double uVal = 0.25;
                double val = Math.copySign(uVal, t.getDeltaY());
                top.setScaleX(top.getScaleX()+ val);
                top.setScaleY(top.getScaleY()+ val);
                if(top.getScaleX() == 0 && top.getScaleY() == 0){
                    top.setScaleX(top.getScaleX()+ uVal);
                    top.setScaleY(top.getScaleY()+ uVal);
                }
            }
        });
        m.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                try {
                    bubbleArea.AddBubble();
                } catch (IOException ex) {
                    Logger.getLogger(ThotMap.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        
        bubbleArea.bubbleObs.addListener(new ListChangeListener<Bubble>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Bubble> change) {
                GenerateTree(bubbleArea.Bubbles);
            }
        });
        
        
        
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                try {
                    if (t.getCode() == KeyCode.SPACE) {
                        ctrl.top.setScaleX(1);
                        ctrl.top.setScaleY(1);
                    }
                    else if(t.getCode() == KeyCode.BACK_SPACE){
                        for (int i = 0; i < bubbleArea.Bubbles.size(); i++) {
                            Bubble bubble = bubbleArea.Bubbles.get(i);
                            if(bubble.isSelected){
                                bubbleArea.RemoveBubble(bubble);
                            }
                            
                        }
                        
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ThotMap.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
    public void GenerateTree(final ArrayList<Bubble> Bubbles) {
        try {
            OutlineGenerator.Build(Bubbles,Outline);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThotMap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }




}
//if isChild is already set, trigger doesnt get hit