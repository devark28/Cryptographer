/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.input;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author PC
 */
public class KeyCode {
    
    private final ObservableList<javafx.scene.input.KeyCode> KeyCodes;
    
    public KeyCode(javafx.scene.input.KeyCode... keyCodes){
        this.KeyCodes = FXCollections.observableArrayList();
        addKeyCode(keyCodes);
    }
    
    private void addKeyCode(javafx.scene.input.KeyCode... keycode){
        if (keycode != null) {
            KeyCodes.addAll(keycode);
        }
    }
    
    public Object[] getKeyCodes(){
        return KeyCodes.toArray();
    }
}
