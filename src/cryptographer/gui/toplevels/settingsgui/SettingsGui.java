/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.toplevels.settingsgui;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import cryptographer.gui.toplevels.settingsgui.settingspages.SettingsPage1;
import cryptographer.gui.toplevels.settingsgui.settingspages.SettingsPage2;
import cryptographer.gui.toplevels.settingsgui.settingspages.SettingsPage3;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author PC
 */
public class SettingsGui extends Stage implements SettingsGui_Interface {

    private final Scene scene;
    public final SettingsPage1 generalPage = new SettingsPage1();
    public final SettingsPage2 encryptionPage = new SettingsPage2();
    public final SettingsPage3 securityPage = new SettingsPage3();
    
    public SettingsGui() {
        this.setMinHeight(500);
        this.setMinWidth(700);
        this.initStyle(StageStyle.TRANSPARENT);
        this.initModality(Modality.WINDOW_MODAL);
        this.setResizable(false);
        this.setOnShowing((event) -> {
            main_part.selectFirst();
        });
        
        close.setOnAction((event) -> {
            this.close();
        });
        
        cancel.setOnAction((event) -> {
            this.close();
        });
        
        apply.setOnAction((event) -> {
            this.apply();
        });
        
        save.setOnAction((event) -> {
            this.save();
        });
        
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        HBox.setHgrow(msg_cont, Priority.ALWAYS);
        msg_cont.setAlignment(Pos.CENTER);
        VBox.setVgrow(main_part, Priority.ALWAYS);
        
        // Pages
        main_part.addPage(generalPage.getTitle(), generalPage);
        main_part.addPage(encryptionPage.getTitle(), encryptionPage);
        main_part.addPage(securityPage.getTitle(), securityPage);
        
        // Styling
        close.getStyleClass().add("close");
        main_window.getStyleClass().add("main-window");
        top_decoration.getStyleClass().add("top-decoration");
        bottom_buttons.getStyleClass().add("bottom-buttons");
        
        main_window.getStyleClass().add("settings-gui");
        
        this.scene = new Scene(main_window);
        scene.getStylesheets().add("/cryptographer/gui/toplevels/settingsgui/settingsCss.css");
        
        this.setScene(scene);
    }
    
    public void setOwner(Stage owner){
        this.initOwner(owner);
    }
    
    @Override
    public void close(){
        super.close();
    }
    
    private void apply(){
        internalSave();
    }
    
    private void save(){
        internalSave();
        close();
    }
    
    private void internalSave(){
        
    }
    
    public void switchTo(String page_name){
        main_part.switchToPage(page_name);
    }
}
