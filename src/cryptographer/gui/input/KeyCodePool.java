/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;

/**
 *
 * @author PC
 */
public class KeyCodePool {
    
    private final ObservableList<KeyCode> Keys;
    private final List<javafx.scene.input.KeyCode> PressedKeysSack;
    private final ObservableList<javafx.scene.input.KeyCode> PressedKeys;
    private final Map<KeyCode, Runnable> Linker;
    
    public KeyCodePool(){
        this.Keys = FXCollections.observableArrayList();
        this.PressedKeysSack = new ArrayList<>();
        this.PressedKeys = FXCollections.observableList(PressedKeysSack);
        this.Linker = new HashMap<>();
        listen();
    }
    
    public void add(KeyCode keycode, Runnable runnable){
        addKey(keycode, runnable);
    }
    
    private void addKey(KeyCode keycode, Runnable runnable){
        if (keycode != null& runnable != null) {
            Keys.add(keycode);
            Linker.put(keycode, runnable);
        }
    }
    
    private void listen(){
        PressedKeys.addListener((ListChangeListener.Change<? extends javafx.scene.input.KeyCode> c) -> {
            if (!Keys.isEmpty() && !PressedKeys.isEmpty()) {
                Keys.forEach((Key) -> {
                    if(Arrays.deepEquals(PressedKeys.toArray(), Key.getKeyCodes())){
                        PressedKeysSack.clear();
                        Linker.get(Key).run();
                    }
                });
            }
        });
    }
    
    public void setEventParent(Scene scene){
        scene.setOnKeyPressed((event) -> {
            if(!PressedKeys.contains(event.getCode())){
                PressedKeys.add(event.getCode());
            }
        });
        
        scene.setOnKeyReleased((event) -> {
            if(PressedKeys.contains(event.getCode())){
                System.out.println(event.getCode() + "\t-------\t" + PressedKeys.toString());
                PressedKeys.remove(event.getCode());
            }
        });
    }
}
