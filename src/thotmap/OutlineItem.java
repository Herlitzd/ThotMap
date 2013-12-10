/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thotmap;

import javafx.concurrent.Task;
import javafx.scene.control.TreeItem;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Devon
 */
public class OutlineItem extends TreeItem{
    public Bubble bubble;
    OutlineItem(Bubble b) {
        this.bubble = b;
        TreeItem body = new TreeItem();
        body.valueProperty().bind(b.Body);
        this.valueProperty().bind(b.Title);
        this.getChildren().add(body);
        body.setGraphic(new Circle(4,Color.GRAY));
        if(b.children.size()>0){
            for(final Bubble c : b.children){
                Task t = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        OutlineItem.this.getChildren().add(new OutlineItem(c));
                        return null;
                    }
                };
                new Thread(t).start();
            }
        } 
    }
    
}
