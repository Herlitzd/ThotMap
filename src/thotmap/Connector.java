/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thotmap;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 *
 * @author Devon
 */
class Connector extends Group{
    Bubble Parent;
    Bubble Child;
    Line line;
    
    Connector(Bubble p, Bubble c){
        this.Child = c;
        this.Parent = p;
        
        line = new Line();
        line.startXProperty().bind(c.layoutXProperty());
        line.startYProperty().bind(c.layoutYProperty().add(c.Cover.heightProperty().divide(2)));
        line.endXProperty().bind(p.layoutXProperty().add(p.Cover.widthProperty()));
        line.endYProperty().bind(p.layoutYProperty().add(p.Cover.heightProperty().divide(2)));
        
//        Text s = new Text();
//        Text e = new Text();
//        s.layoutXProperty().bind(line.startXProperty());
//        s.layoutYProperty().bind(line.startYProperty());
//        e.layoutXProperty().bind(line.endXProperty());
//        e.layoutYProperty().bind(line.endYProperty());
//        e.setText(line.endXProperty().get() +" || "+line.endYProperty().get());
//        s.setText(line.startXProperty().get() +" || "+line.startYProperty().get());
//        this.getChildren().add(e);
//        this.getChildren().add(s);

        p.children.add(c);
        c.IsChild.set(true);
        p.childCount.add(1);
        c.kids.setIsFilled(true);
        p.parent.setIsFilled(true);
        c.DirectParents.add(p);
        
        this.getChildren().add(line);
    }
}
