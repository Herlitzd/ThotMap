/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thotmap;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import thotmap.FXML.FxmlLoader;

/**
 *
 * @author Devon
 */
class LoadData<T>{
    public T Controller;
    public Node node;
    public LoadData (String loc, Class<T> type) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        this.node = (Node) loader.load(FxmlLoader.class.getResourceAsStream(loc));
        this.Controller = type.cast(loader.getController());
    }
}
