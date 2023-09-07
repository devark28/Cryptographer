/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.toplevels.settingsgui.settingspages;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.concurrent.Task;

/**
 *
 * @author PC
 */
public class DefFontLoader extends Task<Object[]>{

    public DefFontLoader() {}

    @Override
    protected Object[] call() throws Exception {
        File file = new File("C:\\Users\\PC\\Documents\\NetBeansProjects\\Cryptographer\\src\\cryptographer\\gui\\FONTS.resource");
        List fonts;
        try (FileInputStream fis = new FileInputStream(file)) {
            Scanner scanner = new Scanner(fis);
            fonts = new ArrayList();
            while (scanner.hasNextLine()) {
                fonts.add(scanner.nextLine());
            }
            fis.close();
        }
        return fonts.toArray();
    }
    
}
