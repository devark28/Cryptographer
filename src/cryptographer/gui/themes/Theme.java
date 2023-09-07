/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.themes;

import java.util.Map;

/**
 *
 * @author PC
 * Theme class give access to a theme properties like names and display names
 */
public final class Theme {

    private Map<String, String> theme_;
    
    public Theme() {
    }
    
    public Theme(Map _theme) {
        if (_theme != null) {
            this.theme_ = _theme;
        }
    }
    
    public String get(ThemeAttribute attribute){
        switch(attribute){
            case NAME:
                return theme_.get("name");
            case DISPLAY:
                return theme_.get("display");
            case FILE:
                return theme_.get("file");
            default:
                return null;
        }
    }
}
