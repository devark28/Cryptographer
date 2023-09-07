/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.textarea;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author PC
 */
public class TextAreaBase extends Pane {
    
    private final TextArea textArea;
    private final HBox toolbox;
    private final Button copyTool;
    private final Button cutTool;
    private final Button pasteTool;
    private final Clipboard clipboard;
    private final ClipboardContent clipboardContent;
    private final MenuItem undo_pop;
    private final MenuItem redo_pop;
    private final SeparatorMenuItem sep1;
    private final MenuItem copy_pop;
    private final MenuItem cut_pop;
    private final MenuItem paste_pop;
    private final MenuItem clear_pop;
    private final MenuItem delete_pop;
    private final SeparatorMenuItem sep2;
    private final MenuItem selectall_pop;
    private final ContextMenu popup;
    
    /**
     * 
     * a text area with tools to copy cut and paste in the top right corner
     */
    
    protected TextAreaBase(){
        
        //this.getStylesheets().add("/cryptographer/rootCss.css");
        
        textArea = new TextArea();
        
        copyTool = new Button("Copy");
        copyTool.setDisable(true);
        cutTool = new Button("Cut");
        cutTool.setDisable(true);
        pasteTool = new Button("Paste");
        
        undo_pop = new MenuItem("Undo");
        undo_pop.setDisable(true);
        redo_pop = new MenuItem("Redo");
        redo_pop.setDisable(true);
        sep1 = new SeparatorMenuItem();
        copy_pop = new MenuItem("Copy");
        copy_pop.setDisable(true);
        cut_pop = new MenuItem("Cut");
        cut_pop.setDisable(true);
        paste_pop = new MenuItem("Paste");
        clear_pop = new MenuItem("Clear");
        clear_pop.setDisable(true);
        delete_pop = new MenuItem("Delete");
        delete_pop.setDisable(true);
        sep2 = new SeparatorMenuItem();
        selectall_pop = new MenuItem("Select All");
        
        clipboard = Clipboard.getSystemClipboard();
        clipboardContent = new ClipboardContent();
        
        popup = new ContextMenu(undo_pop,redo_pop,sep1,copy_pop,cut_pop,paste_pop,clear_pop,delete_pop,sep2,selectall_pop);
        popup.getStyleClass().add("pop-up");
        
        textArea.undoableProperty().addListener((observable) -> {
            if(textArea.isUndoable()){
                undo_pop.setDisable(false);
            }else{
                undo_pop.setDisable(true);
            }
        });
        
        textArea.redoableProperty().addListener((observable) -> {
            if(textArea.isRedoable()){
                redo_pop.setDisable(false);
            }else{
                redo_pop.setDisable(true);
            }
        });
        
        textArea.selectionProperty().addListener((observable) -> {
            if(textArea.getSelection().getLength() > 0){
                delete_pop.setDisable(false);
            }else{
                delete_pop.setDisable(true);
            }
        });
        
        textArea.textProperty().addListener((observable) -> {
            if(textArea.getLength() >= 1){
                copyTool.setDisable(false);
                cutTool.setDisable(false);
                clear_pop.setDisable(false);
                copy_pop.setDisable(false);
                cut_pop.setDisable(false);
            }else{
                copyTool.setDisable(true);
                cutTool.setDisable(true);
                clear_pop.setDisable(true);
                copy_pop.setDisable(true);
                cut_pop.setDisable(true);
            }
        });
        
        undo_pop.setOnAction((event) -> {
            undo();
        });
        
        redo_pop.setOnAction((event) -> {
            redo();
        });
        
        copy_pop.setOnAction((event) -> {
            copy();
        });
        
        cut_pop.setOnAction((event) -> {
            cut();
        });
        
        paste_pop.setOnAction((event) -> {
            paste();
        });
        
        clear_pop.setOnAction((event) -> {
            clear();
        });
        
        delete_pop.setOnAction((event) -> {
            delete();
        });
        
        selectall_pop.setOnAction((event) -> {
            selectAll();
        });
        
        textArea.setContextMenu(popup);
        
        copyTool.setFont(Font.font("System", FontWeight.BOLD, USE_PREF_SIZE));
        cutTool.setFont(Font.font("System", FontWeight.BOLD, USE_PREF_SIZE));
        pasteTool.setFont(Font.font("System", FontWeight.BOLD, USE_PREF_SIZE));
        
        toolbox = new HBox(copyTool,cutTool,pasteTool);
        
        toolbox.setStyle("-fx-padding: 1 1 0 0;");
        copyTool.setStyle("-fx-background-radius: 0;");
        cutTool.setStyle("-fx-background-radius: 0;");
        pasteTool.setStyle("-fx-background-radius: 0;");
        
        toolbox.layoutXProperty().bind(textArea.layoutXProperty().add(textArea.widthProperty()).subtract(toolbox.widthProperty()));
        
        copyTool.setOnAction((ActionEvent event) -> {
            copy();
        });
        
        cutTool.setOnAction((ActionEvent event) -> {
            cut();
        });
        
        pasteTool.setOnAction((ActionEvent event) -> {
            paste();
        });
        
        toolbox.setOpacity(0.3);
        toolbox.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(toolbox.isHover()){
                toolbox.setOpacity(1);
            }else{
                toolbox.setOpacity(0.3);
            }
        });
        
        embed();
    }
    
    private void embed(){
        this.getChildren().addAll(textArea,toolbox);
        textArea.prefWidthProperty().bind(this.widthProperty());
        textArea.prefHeightProperty().bind(this.heightProperty());
    }
    
    protected void copy(){
        if(textArea.getSelectedText().length() > 0){
            textArea.copy();
        }else{
            clipboardContent.put(DataFormat.PLAIN_TEXT, textArea.getText());
            clipboard.setContent(clipboardContent);
        }
    }
    
    protected void cut(){
        if(textArea.getSelectedText().length() > 0){
            textArea.cut();
        }else{
            clipboardContent.put(DataFormat.PLAIN_TEXT, textArea.getText());
            clipboard.setContent(clipboardContent);
            clear();
        }
    }
    
    protected void paste(){
        textArea.paste();
    }
    
    private void selectAll(){
        textArea.selectAll();
    }
    
    private void delete(){
        textArea.deleteText(textArea.getSelection());
    }
    
    private void redo(){
        textArea.redo();
    }
    
    private void undo(){
        textArea.undo();
    }
    
    private void clear(){
        textArea.selectAll();
        textArea.deleteText(textArea.getSelection());
    }
    
    /**
     * 
     * gets text from the current TextArea
     * @return String
     */
    
    protected String getText(){
        return textArea.getText();
    }
    
    /**
     * 
     * sets text of the current TextArea
     * @param text to the TextArea
     */
    
    protected void setText(String text){
        textArea.setText(text);
    }
}
