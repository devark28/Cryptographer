/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.toplevels.settingsgui.settingsbase;

/**
 *
 * @author SHIMWA Bruce Emmanuel <kazehegbro1@gmail.com>
 */
public class SettingsBase {
    
    private final Settings formerSettings;
    private final Settings currentSettings;
    
    public SettingsBase(){
        formerSettings = new Settings();
        currentSettings = new Settings();
    }
    
    public final void save(){
        if(!this.currentSettings.equals(this.formerSettings))
            internalSave();
    }
    
    private void internalSave(){
        
    }
    
    public Settings getSettings(){
        return currentSettings;
    }
}
