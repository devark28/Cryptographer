/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.textarea;

/**
 *
 * @author PC
 */
public class TextArea extends TextAreaBase {

    private Boolean clipBoardAccess = false;
    
    public TextArea() {
        super();
    }
    
    @Override
    public String getText(){
        return super.getText();
    }
    
    @Override
    public void setText(String text){
        super.setText(text);
    }
    
    public void clipBoardCopy(){
        if (clipBoardAccess) {
            super.copy();
        }
    }
    
    public void clipBoardCut(){
        if (clipBoardAccess) {
            super.cut();
        }
    }
    
    public void clipBoardPaste(){
        if (clipBoardAccess) {
            super.paste();
        }
    }
    
    public void setClipBoardAccess(Boolean access){
        clipBoardAccess = access;
    }
}
