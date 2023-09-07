/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui;

import java.util.Optional;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author SHIMWA Bruce Emmanuel <kazehegbro1@gmail.com>
 */
public class CryptographerGui extends Application implements CryptographerGui_Interface {
    
    //Global Variables declarations
    
    private Scene scene;
    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
        
        this.primaryStage = primaryStage;
        
        dashboard.setOwner(primaryStage);
        settings.setOwner(primaryStage);
        fileNavigator.setOwner(primaryStage);
        
        // Styling
        BodyMainInput.prefWidthProperty().bind(primaryStage.widthProperty().subtract(BodyToolBar.widthProperty()).divide(2).subtract(10));
        BodyMainHistory_control.prefWidthProperty().bind(primaryStage.widthProperty().subtract(BodyToolBar.widthProperty()).divide(2).subtract(10));
        
        HeadBodyBottom.prefHeightProperty().bind(primaryStage.heightProperty());
        
        BodyToolBarMain.prefHeightProperty().bind(HeadBodyBottom.heightProperty().subtract(Head.heightProperty()));
        
        BodyMain.prefHeightProperty().bind(BodyToolBarMain.heightProperty());

        BodyMain.setPadding(new Insets(3));
        
        BodyToolBar.prefHeightProperty().bind(BodyToolBarMain.heightProperty());
        
        BodyMainInput.prefHeightProperty().bind(BodyMain.heightProperty().subtract(3).subtract(BottomLeft.heightProperty()));
        BodyMainHistory_control.prefHeightProperty().bind(BodyMain.heightProperty().subtract(3));
        
        BodyMainInputTop.prefHeightProperty().bind(BodyMainInput.heightProperty().divide(2));
        BodyMainInputBottom.prefHeightProperty().bind(BodyMainInput.heightProperty().divide(2));
        
        BodyMainInputTopTextArea.prefHeightProperty().bind(BodyMainInputTop.heightProperty().subtract(BodyMainInputTopLbl.heightProperty()));
        BodyMainInputBottomTextArea.prefHeightProperty().bind(BodyMainInputBottom.heightProperty().subtract(BodyMainInputBottomLbl.heightProperty()));
        
        BottomLeft.setAlignment(Pos.CENTER_LEFT);
        BottomLeftMsgCont.setAlignment(Pos.CENTER);
        
        ConsoleHistory_status.getChildren().add(BottomLeft);
        
        HBox.setHgrow(BottomLeftMsgCont, Priority.ALWAYS);
        
        HBox.setHgrow(AboveInputSider, Priority.ALWAYS);
        AboveInputSider.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(AboveHistorySider, Priority.ALWAYS);
        AboveHistorySider.setAlignment(Pos.CENTER_RIGHT);
        
        HeadAboveInput.prefWidthProperty().bind(BodyMainInput.widthProperty().add(BodyToolBar.widthProperty()).add(3));
        HeadAboveInput.setAlignment(Pos.CENTER);
        HeadAboveHistory.prefWidthProperty().bind(BodyMainHistory_control.widthProperty());
        HeadAboveHistory.setAlignment(Pos.CENTER);
        
        Head.setPadding(new Insets(0, 3, 0, 0));
        
        Head.getStyleClass().add("top-head");
        
        BodyMainInputBottomTextArea.getStyleClass().add("text-view");
        BodyMainInputTopTextArea.getStyleClass().add("text-view");
        
        BodyToolBar.setOrientation(Orientation.VERTICAL);
        //
        close_tool.setOnAction((event) -> {
            this.stop();
        });
        setupToolbarTools();
        //Mounting
        scene = new Scene(HeadBodyBottom);
        
        pool.setEventParent(scene);
        
        // Windows Platform
        pool.add(new cryptographer.gui.input.KeyCode(KeyCode.CONTROL, KeyCode.SHIFT, KeyCode.C), (Runnable) () -> {copy_tool.fire();});
        pool.add(new cryptographer.gui.input.KeyCode(KeyCode.CONTROL, KeyCode.SHIFT, KeyCode.X), (Runnable) () -> {cut_tool.fire();});
        pool.add(new cryptographer.gui.input.KeyCode(KeyCode.CONTROL, KeyCode.SHIFT, KeyCode.V), (Runnable) () -> {paste_tool.fire();});
        pool.add(new cryptographer.gui.input.KeyCode(KeyCode.CONTROL, KeyCode.S), (Runnable) () -> {save_tool.fire();});
        pool.add(new cryptographer.gui.input.KeyCode(KeyCode.CONTROL, KeyCode.O), (Runnable) () -> {open_tool.fire();});
        pool.add(new cryptographer.gui.input.KeyCode(KeyCode.ALT, KeyCode.T, KeyCode.C), (Runnable) () -> {console_tool.fire();});
        pool.add(new cryptographer.gui.input.KeyCode(KeyCode.ALT, KeyCode.X, KeyCode.C), (Runnable) () -> {console_clear_tool.fire();});
        pool.add(new cryptographer.gui.input.KeyCode(KeyCode.ALT, KeyCode.T, KeyCode.H), (Runnable) () -> {history_tool.fire();});
        pool.add(new cryptographer.gui.input.KeyCode(KeyCode.ALT, KeyCode.X, KeyCode.H), (Runnable) () -> {history_clear_tool.fire();});
        pool.add(new cryptographer.gui.input.KeyCode(KeyCode.ALT, KeyCode.E), (Runnable) () -> {encryption_tool.fire();});
        pool.add(new cryptographer.gui.input.KeyCode(KeyCode.ALT, KeyCode.D), (Runnable) () -> {dashboard_tool.fire();});
        pool.add(new cryptographer.gui.input.KeyCode(KeyCode.ALT, KeyCode.S), (Runnable) () -> {settings_tool.fire();});
        pool.add(new cryptographer.gui.input.KeyCode(KeyCode.ALT, KeyCode.C), (Runnable) () -> {close_tool.fire();});
        // Mac Platform
        pool.add(new cryptographer.gui.input.KeyCode(KeyCode.COMMAND, KeyCode.SHIFT, KeyCode.C), (Runnable) () -> {copy_tool.fire();});
        pool.add(new cryptographer.gui.input.KeyCode(KeyCode.COMMAND, KeyCode.SHIFT, KeyCode.X), (Runnable) () -> {cut_tool.fire();});
        pool.add(new cryptographer.gui.input.KeyCode(KeyCode.COMMAND, KeyCode.SHIFT, KeyCode.V), (Runnable) () -> {paste_tool.fire();});
        pool.add(new cryptographer.gui.input.KeyCode(KeyCode.COMMAND, KeyCode.S), (Runnable) () -> {save_tool.fire();});
        pool.add(new cryptographer.gui.input.KeyCode(KeyCode.COMMAND, KeyCode.O), (Runnable) () -> {open_tool.fire();});
        
        do_exit.getDialogPane().getStyleClass().add("close-confirmation");
        HeadBodyBottom.getStyleClass().add("cryptographer-gui");
        do_exit.getDialogPane().getStylesheets().add("/cryptographer/gui/rootCss.css");
        scene.getStylesheets().add("/cryptographer/gui/rootCss.css");
        
        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            event.consume();
            setupExitDialog();
            Optional<ButtonType> result = do_exit.showAndWait();
            if (result.get().equals(ButtonType.YES)){
                this.exit();
            } else {
                do_exit.close();
            }
        });
        
        primaryStage.setMaximized(true);
        primaryStage.setMinHeight(700);
        primaryStage.setMinWidth(930);
        primaryStage.setTitle("Cryptographer");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/cryptographer/gui/icons/icon.png"));
        
        BodyMainHistory_control.addHistory("hi", "nop", "bro");
        
        primaryStage.show();
    }
    
    @Override
    public void stop() {// throws Exception
        if(primaryStage.isShowing()){
            primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
        }
        if(dashboard.isShowing()){
            dashboard.close();
        }
        if(settings.isShowing()){
            settings.close();
        }
        if(fileNavigator.isShowing()){
            fileNavigator.close();
        }
    }
    
    private void exit() {// throws Exception
        if(primaryStage.isShowing()){
            primaryStage.close();
        }
        if(dashboard.isShowing()){
            dashboard.close();
        }
        if(settings.isShowing()){
            settings.close();
        }
        if(fileNavigator.isShowing()){
            fileNavigator.close();
        }
    }
}
