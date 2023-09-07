/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.toplevels.settingsgui.settingspages;

import cryptographer.gui.slideSwitch.SlideSwitchView;
import cryptographer.gui.themes.Theme;
import cryptographer.gui.themes.ThemeAttribute;
import cryptographer.gui.themes.Themes;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author PC
 */
public class SettingsPage1 extends VBox {

    private final ComboBox theme_lst;
    private final ObservableList<String> theme_s;
    private final ComboBox font_lst;
    private final ObservableList<String> font_s;
    private final SlideSwitchView dashboard_s;
    private final SlideSwitchView close_confirmation_s;
    private final String name;
    private final DefManager defManager = new DefManager();
    private final Themes themes = new Themes();
    
    public SettingsPage1() {
        //title : name
        name = "General";
        
        //font : combobox
        Label font_lbl = new Label("Font :");
        embed(font_lbl);
        font_s = FXCollections.observableArrayList();
        font_lst = new ComboBox(font_s);
        try {
            addFont();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(SettingsPage1.class.getName()).log(Level.SEVERE, "Can not load Fonts.", ex);
        }
        embed(font_lst);
        
        //theme : combobox
        Label theme_lbl = new Label("Theme :");
        embed(theme_lbl);
        theme_s = FXCollections.observableArrayList();
        theme_lst = new ComboBox(theme_s);
        addThemes();
        embed(theme_lst);
        
        //dashboard : switch
        Label dashboard_lbl = new Label("Auto Show Dashboard :");
        embed(dashboard_lbl);
        dashboard_s = new SlideSwitchView(show_dashboard());
        embed(dashboard_s);
        
        //close confirmation : switch
        Label close_confirmation_lbl = new Label("Show Close Confimation Dialog :");
        embed(close_confirmation_lbl);
        close_confirmation_s = new SlideSwitchView(show_close_confirmation());
        embed(close_confirmation_s);
    }
    
    public String getTitle(){
        return this.name;
    }
    
    private void embed(Node node){
        this.getChildren().addAll(node);
    }
    
    private void addThemes(){
        // Select the default theme
        themes.getAll().forEach((Theme theme_) -> {
            theme_s.add(theme_.get(ThemeAttribute.DISPLAY));
        });
        // Select the default theme
        theme_lst.setValue(themes.getDefault().get(ThemeAttribute.DISPLAY));
    }
    
    private void addFont() throws InterruptedException, ExecutionException{
        DefFontLoader tsk = new DefFontLoader();
        tsk.run();
        for (Object font_ : tsk.get()) {
            font_lst.setValue(fontParser(font_).get("family").toString());
        }
        FontLoader tsk2 = new FontLoader();
        tsk2.run();
        for (Object font_ : tsk2.get()) {
            font_s.add(font_.toString());
        }
    }
    
    private Map fontParser(Object _font) {
        Map<String,String> results = new HashMap<>();
        String[] splitted;
        splitted = _font.toString().split("-");
        results.put("name", splitted[1].replace("(", "").replace(")", "").trim());
        results.put("family", splitted[2].replace("(", "").replace(")", "").trim());
        results.put("style", splitted[3].replace("(", "").replace(")", "").trim());
        results.put("size", splitted[4].replace("(", "").replace(")", "").trim());
        
        return results;
    }
    
    private boolean show_dashboard(){
        return defManager.find("dashboard").equals("true");
    }
    
    private boolean show_close_confirmation(){
        return defManager.find("close-dialog").equals("true");
    }
}
