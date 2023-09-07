/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.toplevels.dashboardgui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author PC
 */
public class DashboardGui extends Stage implements DashboardGui_Interface {

    private final Scene scene;
    
    public DashboardGui() {
        this.setMinHeight(500);
        this.setMinWidth(700);
        this.initStyle(StageStyle.TRANSPARENT);
        this.initModality(Modality.WINDOW_MODAL);
        this.setResizable(false);
        
        close.setOnAction((event) -> {
            this.close();
        });
        
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        HBox.setHgrow(msg_cont, Priority.ALWAYS);
        msg_cont.setAlignment(Pos.CENTER);
        
        // Styling
        close.getStyleClass().add("close");
        main_window.getStyleClass().add("main-window");
        top_decoration.getStyleClass().add("top-decoration");
        bottom_buttons.getStyleClass().add("bottom-buttons");
        
        main_window.getStyleClass().add("dashboard-gui");
        
        this.scene = new Scene(main_window);
        scene.getStylesheets().add("/cryptographer/gui/toplevels/dashboardgui/dashboardCss.css");
        
        this.setScene(scene);
    }
    
    public void setOwner(Stage owner){
        this.initOwner(owner);
    }
    
}
