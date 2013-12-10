/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thotmap;

import java.util.ArrayList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 *
 * @author Devon
 */
class OutlineGenerator{

    public static void Build(ArrayList<Bubble> Bubbles, TreeView Outline) throws InterruptedException {
        ArrayList<Bubble> parents = getParents(Bubbles);
        System.out.println("hit");
        Outline.setRoot(generateTree(parents, Bubbles));
    }

    private static TreeItem OutlineItemFromBubble(Bubble b) {
        OutlineItem item = new OutlineItem(b);
        return item;
    }

    private static ArrayList<Bubble> getParents(final ArrayList<Bubble> allB) throws InterruptedException {
        final ArrayList<Bubble> parents = new ArrayList<Bubble>();
        for (Bubble b : allB) {
                    if (!b.IsChild.get()) {
                        parents.add(b);
                    }
                }
        //should be threaded
        return parents;
    }

    private static TreeItem generateTree(ArrayList<Bubble> parents, ArrayList<Bubble> Bubbles) {
        TreeItem Root = new TreeItem();
        System.out.println(parents.size());
        for(Bubble b: parents){
            System.out.println("hit2");
            Root.getChildren().add(OutlineItemFromBubble(b));
        }
        return Root;
    }
}
