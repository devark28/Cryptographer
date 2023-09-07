/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.toplevels.settingsgui.settingspages;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author PC
 */
public class SettingsPage3 extends VBox {
    
    private final String name;
    private final SimpleBooleanProperty key;
    private final SimpleBooleanProperty hadkey;
    private final TextField user_fld;
    private final PasswordField pass_fld;
    private final PasswordField copass_fld;
    private final Button addKey;
    private final Button chgKey;
    private final DefManager defManager = new DefManager();

    public SettingsPage3() {
        //title : name
        name = "Security";
        
        //body
        
        //init
        key = new SimpleBooleanProperty();
        hadkey = new SimpleBooleanProperty();
        user_fld = new TextField();
        pass_fld = new PasswordField();
        copass_fld = new PasswordField();
        addKey = new Button("Set Password");
        chgKey = new Button("Change Password");
        //end init
        
        key.set(key());
        hadkey.set(key.get());
        if(key.get()){
            setbody();
        }else{
            setask();
        }
        key.addListener((observable) -> {
            if(key.get()){
                setbody();
            }else{
                setask();
            }
        });
        if(hadkey.get()){
            chgKey.setDisable(false);
        }else{
            chgKey.setDisable(true);
        }
        addKey.setOnAction((event) -> {
            key.set(true);
        });
        chgKey.setOnAction((event) -> {
            key.set(false);
        });
    }
    
    private boolean key(){
        return defManager.find("key").equals("true");
    }
    
    private void setbody(){
        this.getChildren().clear();
        Label user_lbl = new Label("User Name :");
        embed(user_lbl);
        embed(user_fld);
        Label pass_lbl = new Label("Password :");
        embed(pass_lbl);
        embed(pass_fld);
        Label copass_lbl = new Label("Confirmation Password :");
        embed(copass_lbl);
        embed(copass_fld);
        embed(new VBox(10, new Pane(), chgKey));
    }
    
    private void setask(){
        this.getChildren().clear();
        embed(addKey);
    }
    
    public String getTitle(){
        return this.name;
    }
    
    private void embed(Node node){
        this.getChildren().addAll(node);
    }
}
