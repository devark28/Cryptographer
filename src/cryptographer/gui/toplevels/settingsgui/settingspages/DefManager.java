/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.toplevels.settingsgui.settingspages;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class DefManager {
    
    private DefLoader defaults;
    private final Prop prop = new Prop();
    private Object found = new Object();

    public DefManager() {}
    
    public String find(String key) {
        defaults = new DefLoader();
        defaults.run();
        Object[] defs;
        try {
            for (Object def : defaults.get()) {
                if(prop.getAttri(def).equals(key)){
                    found = def;
                }
            }
            return prop.getValue(found);
        } catch (InterruptedException | ExecutionException ex) {
            return null;
        }
    }
    
    private class Prop {
        public String getValue(Object prope){
            String proper = parser(prope);
            String[] all = proper.split(":");
            return all[1].trim();
        }
        
        public String getAttri(Object prope){
            String proper = parser(prope);
            String[] all = proper.split(":");
            return all[0].trim();
        }
        
        private String parser(Object prope){
            String proper = prope.toString();
            proper = proper.replace("(", "").replace(")", "").trim();
            return proper;
        }
    }
}
