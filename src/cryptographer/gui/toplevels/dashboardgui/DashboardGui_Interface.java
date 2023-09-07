/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.toplevels.dashboardgui;

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
interface DashboardGui_Interface {
    
    // Top
    Label title = new Label("Dash Board");
    Pane spacer1 = new Pane();
    Button close = new Button("", new ImageView("/cryptographer/gui/icons/icons8_close_window_26px_1.png"));
    HBox top_decoration = new HBox(title,spacer1,close);
    // Middle
    
    // Bottom
    Label messanger = new Label("currently the dashboard has no message.");
    HBox msg_cont = new HBox(messanger);
    HBox bottom_buttons = new HBox(msg_cont);
    // Main
    VBox main_window = new VBox(top_decoration,bottom_buttons);
}
