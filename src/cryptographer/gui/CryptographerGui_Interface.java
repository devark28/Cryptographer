/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui;

import cryptographer.gui.history.HistoryView;
import cryptographer.gui.input.KeyCodePool;
import cryptographer.gui.textarea.TextArea;
import cryptographer.gui.toplevels.dashboardgui.DashboardGui;
import cryptographer.gui.toplevels.filenavigator.FileNavigatorGui;
import cryptographer.gui.toplevels.settingsgui.SettingsGui;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author PC
 */
interface CryptographerGui_Interface {
    /**************************************************************************
     * Instances
     **************************************************************************/
    // Head
    Button AboveInputSiderButtons_enc = new Button("Encrypt");
    Button AboveInputSiderButtons_dec = new Button("Decrypt");
    
    HBox AboveInputSiderButtons = new HBox(3,AboveInputSiderButtons_enc,AboveInputSiderButtons_dec);
    
    HBox AboveInputSider = new HBox(AboveInputSiderButtons);
    
    Label AboveInputDescription = new Label("Text Input");
    
    Button AboveHistorySider_clr = new Button("Clear");
    
    HBox AboveHistorySider = new HBox(AboveHistorySider_clr);
    
    Label AboveHistoryName = new Label("History :");
    
    HBox HeadAboveInput = new HBox(AboveInputDescription,AboveInputSider);
    HBox HeadAboveHistory = new HBox(AboveHistoryName,AboveHistorySider);
    // Body
    
    // BodyMain
    Label BodyMainInputTopLbl = new Label("Original Content");
    TextArea BodyMainInputTopTextArea = new TextArea();
    
    VBox BodyMainInputTop = new VBox(BodyMainInputTopLbl,BodyMainInputTopTextArea);
    
    Label BodyMainInputBottomLbl = new Label("Encrypted/Decrypted Content");
    TextArea BodyMainInputBottomTextArea = new TextArea();
    
    VBox BodyMainInputBottom = new VBox(BodyMainInputBottomLbl,BodyMainInputBottomTextArea);
    
    // BodyMain
    VBox BodyMainInput = new VBox(BodyMainInputTop,BodyMainInputBottom);
    
    // BodyMainHistory
    HistoryView BodyMainHistory_control = new HistoryView();
    
    HBox ConsoleHistory = new HBox(3,BodyMainInput);
    
    VBox ConsoleHistory_status = new VBox(ConsoleHistory);
    
    String icons = "/cryptographer/gui/icons/";
    Button copy_tool = new Button("", new ImageView(icons+"icons8_copy_26px_2.png"));
    Tooltip copy_tool_tip = new Tooltip("Copy");
    Button cut_tool = new Button("", new ImageView(icons+"icons8_cut_26px.png"));
    Tooltip cut_tool_tip = new Tooltip("Cut");
    Button paste_tool = new Button("", new ImageView(icons+"icons8_paste_26px.png"));
    Tooltip paste_tool_tip = new Tooltip("Paste");
    Separator sep1 = new Separator(Orientation.HORIZONTAL);
    Button save_tool = new Button("", new ImageView(icons+"icons8_save_26px.png"));
    Tooltip save_tool_tip = new Tooltip("Save");
    Button open_tool = new Button("", new ImageView(icons+"icons8_opened_folder_26px.png"));
    Tooltip open_tool_tip = new Tooltip("Open");
    Separator sep2 = new Separator(Orientation.HORIZONTAL);
    Button console_tool = new Button("", new ImageView(icons+"icons8_console_26px.png"));
    Tooltip console_tool_tip = new Tooltip("Toogle Console");
    Button console_clear_tool = new Button("", new ImageView(icons+"icons8_console_clear_26px.png"));
    Tooltip console_clear_tool_tip = new Tooltip("Clear Console");
    Separator sep3 = new Separator(Orientation.HORIZONTAL);
    Button history_tool = new Button("", new ImageView(icons+"icons8_history_26px.png"));
    Tooltip history_tool_tip = new Tooltip("Toogle History");
    Button history_clear_tool = new Button("", new ImageView(icons+"icons8_history_clear_26px.png"));
    Tooltip history_clear_tool_tip = new Tooltip("Clear History");
    Separator sep4 = new Separator(Orientation.HORIZONTAL);
    Button encryption_tool = new Button("", new ImageView(icons+"icons8_password_26px.png"));
    Tooltip encryption_tool_tip = new Tooltip("Encryption");
    Button dashboard_tool = new Button("", new ImageView(icons+"icons8_dashboard_26px.png"));
    Tooltip dashboard_tool_tip = new Tooltip("Dash Board");
    Pane sep5 = new Pane();
    Button close_tool = new Button("", new ImageView(icons+"icons8_shutdown_26px.png"));
    Tooltip close_tool_tip = new Tooltip("Close");
    Button settings_tool = new Button("", new ImageView(icons+"icons8_settings_26px.png"));
    Tooltip settings_tool_tip = new Tooltip("Settings");
    
    ToolBar BodyToolBar = new ToolBar(copy_tool,cut_tool,paste_tool,sep1,save_tool,open_tool,sep2,console_tool,console_clear_tool,sep3,history_tool,history_clear_tool,sep4,encryption_tool,dashboard_tool,sep5,close_tool,settings_tool);
    HBox BodyMain = new HBox(3,ConsoleHistory_status,BodyMainHistory_control);
    // Bottom
    Label BottomLeftMsgCont_msg = new Label("centered message");
    HBox BottomLeftMsgCont = new HBox(BottomLeftMsgCont_msg);
    
    Label BottomLeftStatus = new Label("Ready");
    
    HBox BottomLeft = new HBox(BottomLeftStatus,BottomLeftMsgCont);
    // SubScenes
    HBox Head = new HBox(3,HeadAboveInput,HeadAboveHistory);
    HBox BodyToolBarMain = new HBox(BodyToolBar,BodyMain);
    // Scene
    VBox HeadBodyBottom = new VBox(Head,BodyToolBarMain);
    // Top Levels
    SettingsGui settings = new SettingsGui();
    DashboardGui dashboard = new DashboardGui();
    FileNavigatorGui fileNavigator = new FileNavigatorGui();
    // KeyEvents Handler
    KeyCodePool pool = new KeyCodePool();
    // Exit confirmation dialog
    Alert do_exit = new Alert(Alert.AlertType.CONFIRMATION);
    
    /**************************************************************************
     * Methods
     **************************************************************************/
    
    default void setupToolbarTools(){
        copy_tool.getStyleClass().add("tools");
        copy_tool.setTooltip(copy_tool_tip);
        cut_tool.getStyleClass().add("tools");
        cut_tool.setTooltip(cut_tool_tip);
        paste_tool.getStyleClass().add("tools");
        paste_tool.setTooltip(paste_tool_tip);
        save_tool.getStyleClass().add("tools");
        save_tool.setTooltip(save_tool_tip);
        open_tool.getStyleClass().add("tools");
        open_tool.setTooltip(open_tool_tip);
        console_tool.getStyleClass().add("tools");
        console_tool.setTooltip(console_tool_tip);
        console_clear_tool.getStyleClass().add("tools");
        console_clear_tool.setTooltip(console_clear_tool_tip);
        history_tool.getStyleClass().add("tools");
        history_tool.setTooltip(history_tool_tip);
        history_clear_tool.getStyleClass().add("tools");
        history_clear_tool.setTooltip(history_clear_tool_tip);
        encryption_tool.getStyleClass().add("tools");
        encryption_tool.setTooltip(encryption_tool_tip);
        dashboard_tool.getStyleClass().add("tools");
        dashboard_tool.setTooltip(dashboard_tool_tip);
        settings_tool.getStyleClass().add("tools");
        settings_tool.setTooltip(settings_tool_tip);
        settings_tool.setMnemonicParsing(true);
        close_tool.getStyleClass().add("tools");
        close_tool.setTooltip(close_tool_tip);
        
        VBox.setVgrow(sep5, Priority.ALWAYS);
        AboveHistorySider_clr.setOnAction((event) -> {
            BodyMainHistory_control.clear();
        });
        /*******************/
        BodyMainInputTopTextArea.setClipBoardAccess(true);
        copy_tool.setOnAction((event) -> {
            BodyMainInputTopTextArea.clipBoardCopy();
        });
        cut_tool.setOnAction((event) -> {
            BodyMainInputTopTextArea.clipBoardCut();
        });
        paste_tool.setOnAction((event) -> {
            BodyMainInputTopTextArea.clipBoardPaste();
        });
        /*******************/
        save_tool.setOnAction((event) -> {
            String text = BodyMainInputBottomTextArea.getText();
            fileNavigator.save(text, "Data");
        });
        open_tool.setOnAction((event) -> {
            String text = fileNavigator.open();
            BodyMainInputTopTextArea.setText(text);
        });
        /*******************/
        console_tool.setOnAction((event) -> {
            // action
        });
        /*******************/
        history_tool.setOnAction((event) -> {
            // action
        });
        history_clear_tool.setOnAction((event) -> {
            BodyMainHistory_control.clear();
        });
        /*******************/
        encryption_tool.setOnAction((event) -> {
            settings.show();
            settings.switchTo(settings.encryptionPage.getTitle());
        });
        dashboard_tool.setOnAction((event) -> {
            dashboard.show();
        });
        // close_tool
        settings_tool.setOnAction((event) -> {
            settings.show();
        });
    }
    
    default void setupExitDialog(){
        do_exit.setTitle("Close Confirmation Dialog");
        do_exit.setHeaderText("Do you really what to QUIT ?");
        do_exit.setContentText("We will save your Data and you will be able to use them Later.\n\nTHANKS FOR USING CRYPTOGRAPHER !");
        do_exit.getButtonTypes().clear();
        do_exit.getButtonTypes().addAll(ButtonType.NO, ButtonType.YES);
        EventHandler<KeyEvent> fireOnEnter = event -> {
            if (KeyCode.ENTER.equals(event.getCode()) 
                    && event.getTarget() instanceof Button) {
                ((Button) event.getTarget()).fire();
            }
        };
        do_exit.getButtonTypes().stream().map(do_exit.getDialogPane()::lookupButton).forEach(button -> button.addEventHandler(KeyEvent.KEY_PRESSED, fireOnEnter));
    }
}
