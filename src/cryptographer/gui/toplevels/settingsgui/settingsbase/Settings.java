/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.toplevels.settingsgui.settingsbase;

import cryptographer.gui.themes.Theme;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

/**
 *
 * @author SHIMWA Bruce Emmanuel <kazehegbro1@gmail.com>
 */
public class Settings implements Observable {

    private String font;
    private Theme theme;
    private Boolean dashboard;
    private Boolean close_confirmation;
    
    private String level;
    private List<String> encryptions;
    
    public Settings() {
        font = "";
        theme = new Theme();
        dashboard = null;
        close_confirmation = null;
        level = "";
        encryptions = new ArrayList<>();
    }

    @Override
    public void addListener(InvalidationListener listener) {
    }

    @Override
    public void removeListener(InvalidationListener listener) {
    }
    
    public void setFont(String font){
        this.font = font;
    }
    
    public void setTheme(Theme theme){
        this.theme = theme;
    }
    
    public void setDashboard(Boolean dashboard){
        this.dashboard = dashboard;
    }
    
    public void setCloseConfirmation(Boolean closeConfirmation){
        this.close_confirmation = closeConfirmation;
    }
    
    public void setLevel(String level){
        this.level = level;
    }
    
    public void setEncryptions(String... encryptions){
        this.encryptions = Arrays.asList(encryptions);
    }
    
    public String getFont(){
        return this.font;
    }
    
    public Theme getTheme(){
        return this.theme;
    }
    
    public Boolean getDashboard(){
        return this.dashboard;
    }
    
    public Boolean getCloseConfirmation(){
        return this.close_confirmation;
    }
    
    public String getLevel(){
        return this.level;
    }
    
    public List<String> getEncryptions(){
        return this.encryptions;
    }
}
