/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thotmap;

import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author Devon
 */
class BubbleArea extends Pane {

    public ArrayList<Bubble> Bubbles;
    public ArrayList<Connector> Connectors;
    ThotMap aThis;
    Scene scene;
    ObservableList<Bubble> bubbleObs;
    ArrayList<Bubble> selection;

    BubbleArea(ThotMap aThis, Scene scene) {
        this.scene = scene;
        this.aThis = aThis;
        selection = new ArrayList<Bubble>();
        Connectors = new ArrayList<Connector>();
        Bubbles = new ArrayList<Bubble>();
        this.bubbleObs = FXCollections.observableArrayList(Bubbles);
    }

    private void addListeners(final Bubble bubble) {
        final Line l = new Line();
        bubble.getChildren().add(l);
        l.setVisible(false);
        bubble.isPickOnBounds();
        
        
        bubble.childCount.addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                aThis.GenerateTree(Bubbles);
            }
        });

        bubble.IsChild.addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                aThis.GenerateTree(Bubbles);
            }
        });


        bubble.parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {

                l.endXProperty().unbind();
                l.endYProperty().unbind();
                l.setStartX(bubble.parent.getLayoutX());
                l.setStartY(bubble.parent.getLayoutY());
                l.setEndX(-1 * (-bubble.parent.getLayoutX() - t.getX()));
                l.setEndY(-1 * (-bubble.parent.getLayoutY() - t.getY())); //+bubble.Cover.getHeight()
                l.setVisible(true);
            }
        });

        bubble.parent.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                l.setVisible(false);
                for (Bubble bubbleC : Bubbles) {
                    if (bubbleC.getBoundsInParent().contains(t.getSceneX(), t.getSceneY()) && bubbleC != bubble) {
                        if (!bubbleC.children.contains(bubble) && !bubble.children.contains(bubbleC)) {
                            Connectors.add(new Connector(bubble, bubbleC));
                            updateCanvas();
                        }
                    }
                }
            }
        });

    }

    public void AddBubble() throws IOException {
        Bubble bubble = new Bubble(this);
        addListeners(bubble);
        this.Bubbles.add(bubble);
        this.bubbleObs.add(bubble);
        this.getChildren().add(bubble);
    }
    
    public void RemoveBubble(Bubble b) throws IOException {
        this.Bubbles.remove(b);
        this.bubbleObs.remove(b);
        this.getChildren().remove(b);
    }
    

    private void updateCanvas() {
        aThis.GenerateTree(Bubbles);
        this.getChildren().add(Connectors.get(Connectors.size() - 1));
    }
}
