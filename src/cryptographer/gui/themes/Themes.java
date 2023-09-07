/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.themes;

import cryptographer.gui.toplevels.settingsgui.settingspages.SettingsPage1;
import cryptographer.gui.toplevels.settingsgui.settingspages.ThemeLoader;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SHIMWA Bruce Emmanuel <kazehegbro1@gmail.com>
 */
public final class Themes {

    private final Map<String, Theme> themes = new HashMap<>();
    private Theme _default;
    
    public Themes() {
        try {
            loadThemes();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(SettingsPage1.class.getName()).log(Level.SEVERE, "Can not load Themes.", ex);
        }
    }
    
    private void loadThemes() throws InterruptedException, ExecutionException{
        ThemeLoader tsk = new ThemeLoader();
        tsk.run();
        Object theme;
        for (Object theme_ : tsk.get()) {
            if (theme_.toString().contains("[def]")) {
                theme = theme_.toString().replace("[def]", "");
                themes.put(themeParser(theme).get("name").toString(), new Theme(themeParser(theme)));
                _default = new Theme(themeParser(theme));
            }else{
                themes.put(themeParser(theme_).get("name").toString(), new Theme(themeParser(theme_)));
                
            }
        }
    }
    
    private Map themeParser(Object _theme) {
        Map<String,String> results = new HashMap<>();
        String[] splitted;
        String[] attr_value;
        splitted = _theme.toString().split("-");
        for (String string : splitted) {
            if(string.contains(":")){
                attr_value = string.replace("(", "").replace(")", "").split(":");
                results.put(attr_value[0].trim(), attr_value[1].trim());
            }
        }
        
        return results;
    }
    
    /**
     *
     * @param name
     * @return a Theme object
     */
    
    public Theme getTheme(String name){
        return themes.keySet().contains(name) ? themes.get(name) : null;
    }
    
    /**
     *
     * @return all Themes object
     */

    public Collection<Theme> getAll(){
        return themes.values();
    }
    
    /**
     *
     * @return a default Theme object
     */

    public Theme getDefault(){
        return _default;
    }
    
    private URL getResources(String name){
        return Themes.class.getResource(name);
    }
}
