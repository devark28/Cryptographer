/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.toplevels.settingsgui.settingspages;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

/**
 *
 * @author PC
 */
public class SettingsPage2 extends VBox {
    
    private final double ROW_HEIGHT = 24.5;
    private final String name;
    private final ObservableList<String> lvl_s;
    ComboBox lvl_lst;
    private final ObservableList<String> enc_s;
    ListView enc_lst;
    
    public SettingsPage2(){
        //title : name
        name = "Encryption";
        
        //levels
        Label lvl_lbl = new Label("Encryption Level :");
        embed(lvl_lbl);
        lvl_s = FXCollections.observableArrayList("Single","Double","Triple","Random Levels (excluding single, can be above triple)");
        lvl_lst = new ComboBox(lvl_s);
        lvl_lst.setValue("Single");
        embed(lvl_lst);
        
        //encryptions
        Label enc_lbl = new Label("Encryption :");
        embed(enc_lbl);
        enc_s = FXCollections.observableArrayList();
        enc_lst = new ListView(enc_s);
        enc_lst.setPrefHeight(enc_s.size() * ROW_HEIGHT);
        enc_s.addListener((ListChangeListener.Change<? extends String> c) -> {
            enc_lst.setPrefHeight(enc_s.size() * ROW_HEIGHT);
        });
        try {
            addEnc();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(SettingsPage2.class.getName()).log(Level.SEVERE, null, ex);
        }
        embed(enc_lst);
        
    }
    
    public String getTitle(){
        return this.name;
    }
    
    private void embed(Node node){
        this.getChildren().addAll(node);
    }
    
    private void addEnc() throws InterruptedException, ExecutionException{
        EncLoader tsk = new EncLoader();
        tsk.run();
        encParser parser;
        for (Object enc_ : tsk.get()) {
            if (enc_.toString().contains("[def]")) {
                Object def_less = enc_.toString().replace("[def]", "").trim();
                encParser encParser = new encParser(def_less);
                enc_s.add(encParser.getDisplay());
                enc_lst.getSelectionModel().select(encParser.getDisplay());
            }else{
                encParser encParser = new encParser(enc_);
                enc_s.add(encParser.getDisplay());
            }
        }
    }
    
    class encParser {
        Object encryption_s;
        String[] encryption_attr;
        protected encParser(Object enc) throws InterruptedException, ExecutionException{
            encryption_s = enc;
            encryption_attr = encryption_s.toString().split("-");
        }
        
        public String getName(){
            String name = encryption_attr[1].replace("(", "").replace(")", "").trim();
            return name.split(":")[1].trim();
        }
        
        public String getDisplay(){
            String name = encryption_attr[2].replace("(", "").replace(")", "").trim();
            return name.split(":")[1].trim();
        }
        
        public String getVersion(){
            String name = encryption_attr[3].replace("(", "").replace(")", "").trim();
            return name.split(":")[1].trim();
        }
        
        public boolean getPreview(){
            String name = encryption_attr[4].replace("(", "").replace(")", "").trim();
            return name.split(":")[1].trim().equals("true");
        }
    }
}
