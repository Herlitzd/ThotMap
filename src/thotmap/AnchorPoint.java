/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thotmap;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Devon
 */
public class AnchorPoint extends Circle{
    private boolean isFilled;
    private boolean isParent;
    public AnchorPoint(boolean parent, double radius, int x, int y, Color c){
        super(radius, c);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(1.6);
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.isParent = parent;
        isFilled = false;
    }
    public void setIsFilled(boolean b){
        this.isFilled = b;
    }
    public boolean getIsFilled(){return this.isFilled;}
    public void setIsParent(boolean b){
        this.isParent = b;
    }
    public boolean getIsParent(){return this.isParent;}
    
}
