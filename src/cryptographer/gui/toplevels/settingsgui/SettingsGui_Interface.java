/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.toplevels.settingsgui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author PC
 */
interface SettingsGui_Interface {
    
    // Top
    Label title = new Label("Settings");
    Pane spacer1 = new Pane();
    Button close = new Button("", new ImageView("/cryptographer/gui/icons/icons8_close_window_26px_1.png"));
    HBox top_decoration = new HBox(title,spacer1,close);
    // Middle
    ListBook main_part = new ListBook();
    // Bottom
    Label messanger = new Label("You have not yet set a Password.");
    HBox msg_cont = new HBox(messanger);
    Button save = new Button("Save");
    Button apply = new Button("Apply");
    Button cancel = new Button("Cancel");
    HBox bottom_buttons = new HBox(msg_cont,save,apply,cancel);
    // Main
    VBox main_window = new VBox(top_decoration,main_part,bottom_buttons);
}
