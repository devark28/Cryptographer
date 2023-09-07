/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.toplevels.settingsgui.settingspages;

import java.util.List;
import javafx.concurrent.Task;
import javafx.scene.text.Font;

/**
 *
 * @author PC
 */
public class FontLoader extends Task<Object[]> {

    public FontLoader() {
        
    }
    
    @Override
    protected Object[] call() throws Exception {
        List fonts = Font.getFamilies();
        
        return fonts.toArray();
    }
    
}
