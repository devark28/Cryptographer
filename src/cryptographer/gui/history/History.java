/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.history;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author PC
 */
public class History {
    
    private final SimpleStringProperty date;
    private final SimpleStringProperty input;
    private final SimpleStringProperty action;
    private final SimpleStringProperty output;
    
    protected History(String date,String input,String action,String output){
        this.input = new SimpleStringProperty(input);
        this.action = new SimpleStringProperty(action);
        this.output = new SimpleStringProperty(output);
        this.date = new SimpleStringProperty(date);
    }
    
    public String getInput() {
        return input.get();
    }
 
    protected void setInput(String input) {
        this.input.set(input);
    }
 
    public String getAction() {
        return action.get();
    }
 
    protected void setAction(String action) {
        this.action.set(action);
    }
 
    public String getOutput() {
        return output.get();
    }
 
    protected void setOutput(String output) {
        this.output.set(output);
    }
    
    public String getDate() {
        return date.get();
    }
 
    protected void setDate(String date) {
        this.date.set(date);
    }
}
