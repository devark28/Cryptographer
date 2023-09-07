/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.slideSwitch;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author PC
 */
public class SlideSwitchViewBase extends StackPane{

    private final HBox track;
    private final Label slider;
    private final Button button;
    private final SimpleBooleanProperty value;
    
    public SlideSwitchViewBase(Boolean val) {
        value = new SimpleBooleanProperty();
        button = new Button();
        button.setOpacity(0);
        slider = new Label();
        slider.setTextAlignment(TextAlignment.CENTER);
        track = new HBox(slider);
        slider.minWidthProperty().bind(track.widthProperty().divide(2));
        slider.minHeightProperty().bind(track.heightProperty());
        button.minWidthProperty().bind(this.widthProperty());
        track.minWidthProperty().bind(this.widthProperty());
        
        if(val){
            track.setAlignment(Pos.CENTER_RIGHT);
            slider.setText("   "+val.toString().toUpperCase());
        }else{
            track.setAlignment(Pos.CENTER_LEFT);
            slider.setText("  "+val.toString().toUpperCase());
        }
        value.setValue(val);
        button.setOnAction((event) -> {
            if(value.get()){
                track.setAlignment(Pos.CENTER_LEFT);
                value.set(false);
                slider.setText("  "+value.getValue().toString().toUpperCase());
            }else{
                track.setAlignment(Pos.CENTER_RIGHT);
                value.set(true);
                slider.setText("   "+value.getValue().toString().toUpperCase());
            }
        });
        value.addListener((observable) -> {
            if(value.get()){
                track.setAlignment(Pos.CENTER_RIGHT);
                slider.setText("   "+value.getValue().toString().toUpperCase());
            }else{
                track.setAlignment(Pos.CENTER_LEFT);
                slider.setText("  "+value.getValue().toString().toUpperCase());
            }
        });
        
        embed();
    }
    
    private void embed(){
        this.track.getStyleClass().add("track");
        this.slider.getStyleClass().add("slider");
        this.getStyleClass().add("slide-switch-view");
        this.getStylesheets().add("cryptographer/gui/slideSwitch/slideSwitchViewCss.css");
        this.getChildren().addAll(track, button);
    }
    
    public boolean getValue(){
        return value.get();
    }
    
    public void setValue(boolean val){
        value.set(val);
    }
}
