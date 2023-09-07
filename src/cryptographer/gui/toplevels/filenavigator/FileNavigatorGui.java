/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.toplevels.filenavigator;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author PC
 */
public class FileNavigatorGui extends Stage implements FileNavigatorGui_Interface{

    private File file;
    
    public FileNavigatorGui() {
        this.setMinHeight(500);
        this.setMinWidth(700);
        this.initStyle(StageStyle.TRANSPARENT);
        this.initModality(Modality.WINDOW_MODAL);
        this.setResizable(false);
        
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text File", "*.txt"),
                new FileChooser.ExtensionFilter("Cryptographer Text Data", "*.crptd")
        );
    }
    
    public void save(String text, String initName){
        fileChooser.setTitle("Save Output Data Dialog");
        fileChooser.setInitialFileName(initName);
        file = fileChooser.showSaveDialog(this.getOwner());
        if(file != null){
            try (FileWriter fis = new FileWriter(file)) {
                file.createNewFile();
                fis.write(text);
                fis.close();
                fileChooser.setInitialDirectory(file.getParentFile());
            } catch (IOException ex) {
                Logger.getLogger(FileNavigatorGui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.close();
    }
    
    public String open(){
        fileChooser.setTitle("Open Output Data Dialog");
        FileChooser.ExtensionFilter allFiles = new FileChooser.ExtensionFilter("All Files", "*.*");
        fileChooser.getExtensionFilters().add(allFiles);
        file = fileChooser.showOpenDialog(this.getOwner());
        StringBuilder data = new StringBuilder();
        if(file != null && file.canRead()){
            try (FileReader fis = new FileReader(file)) {
                Scanner scanner = new Scanner(fis);
                while (scanner.hasNextLine()) {
                    data.append("\n");
                    data.append(scanner.nextLine());
                }
                fis.close();
                fileChooser.setInitialDirectory(file.getParentFile());
            } catch (IOException ex) {
                Logger.getLogger(FileNavigatorGui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.close();
        fileChooser.getExtensionFilters().remove(allFiles);
        return data.toString().trim();
    }
    
    public void setOwner(Stage owner){
        this.initOwner(owner);
    }
}
