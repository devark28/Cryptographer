/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.history;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author PC
 */
public class HistoryViewBase extends VBox {
    
    private String Notification;
    
    public Boolean hasNotification = false;
    
    private final TableView<History> tableView;
    
    private final PropertyValueFactory<History, String> dateColFactory;
    
    private final TableColumn dateCol;
    
    private final PropertyValueFactory<History, String> inputColFactory;
    
    private final TableColumn inputCol;
    
    private final PropertyValueFactory<History, String> actionColFactory;
    
    private final TableColumn actionCol;
    
    private final PropertyValueFactory<History, String> outputColFactory;
    
    private final TableColumn outputCol;
    
    private final ObservableList<History> data = FXCollections.observableArrayList();
    
    private final SimpleIntegerProperty rows = new SimpleIntegerProperty();
    
    
    private final Label BottomRightCountLbl;
    private final Label BottomRightCountNbr;
    private final Label BottomRightCountNbr_cmplt;
    private final Label BottomRightMaxCont_max;
    private final HBox BottomRightMaxCont;
    private final HBox BottomRight;
    
    /**
     * 
     * constructor of historyViewBase 
     * for displaying history logs
     */
    
    protected HistoryViewBase(){
        
        tableView = new TableView<>();
        
        dateCol = new TableColumn("Date");
        dateColFactory = new PropertyValueFactory<>("date");
        dateCol.setCellValueFactory(dateColFactory);
        
        inputCol = new TableColumn("Input");
        inputColFactory = new PropertyValueFactory<>("input");
        inputCol.setCellValueFactory(inputColFactory);
        
        actionCol = new TableColumn("Action");
        actionColFactory = new PropertyValueFactory<>("action");
        actionCol.setCellValueFactory(actionColFactory);
        
        outputCol = new TableColumn("Output");
        outputColFactory = new PropertyValueFactory<>("output");
        outputCol.setCellValueFactory(outputColFactory);
        
        tableView.getColumns().addAll(dateCol, inputCol, actionCol, outputCol);
        tableView.setItems(data);
        tableView.setPlaceholder(new Label("No History Logs Available"));
        
        BottomRightCountLbl = new Label(" Count : ");
        BottomRightCountNbr = new Label();
        getRowCount();
        BottomRightCountNbr_cmplt = new Label(" logs");
        BottomRightMaxCont_max = new Label("max: 100 ");
        BottomRightMaxCont = new HBox(BottomRightMaxCont_max);
        BottomRightMaxCont.setAlignment(Pos.CENTER_RIGHT);
        BottomRight = new HBox(BottomRightCountLbl,BottomRightCountNbr,BottomRightCountNbr_cmplt,BottomRightMaxCont);
        HBox.setHgrow(BottomRightMaxCont, Priority.ALWAYS);
        
        embed();
    }
    
    private void embed(){
        tableView.getStyleClass().add("history-view");
        this.getChildren().addAll(tableView,BottomRight);
        tableView.prefWidthProperty().bind(this.widthProperty());
        tableView.prefHeightProperty().bind(this.heightProperty());
        tableView.setEditable(false);
        
        dateCol.prefWidthProperty().bind(tableView.widthProperty().divide(4));
        //dateCol.maxWidthProperty().bind(tableView.widthProperty().divide(4));
        dateCol.setResizable(false);
        
        outputCol.prefWidthProperty().bind(tableView.widthProperty().divide(4));
        //outputCol.maxWidthProperty().bind(tableView.widthProperty().divide(4));
        outputCol.setResizable(false);
        
        
        actionCol.prefWidthProperty().bind(tableView.widthProperty().divide(4));
        //actionCol.maxWidthProperty().bind(tableView.widthProperty().divide(4));
        actionCol.setResizable(false);
        
        inputCol.prefWidthProperty().bind(tableView.widthProperty().divide(4));
        //inputCol.maxWidthProperty().bind(tableView.widthProperty().divide(4));
        inputCol.setResizable(false);
    }
    
    /**
     * 
     * adds a new row to the history logs
     * @param input the data the user entered for processing
     * @param action the action done to the entered data
     * @param output the output data given back to the user
     */
    
    public void addHistory(String input,String action,String output){
        addTuple(input, action, output);
    }
    
    /**
     * 
     * returns a message to be put to the status bar notification area
     * @return String
     */
    
    public String getNotification(){
        return Notification;
    }
    
    /**
     * 
     * returns the number of rows or history logs
     * @return Count
     */
    
    private void getRowCount(){
        BottomRightCountNbr.setText(String.valueOf(data.size()));
        data.addListener((ListChangeListener.Change<? extends History> c) -> {
            BottomRightCountNbr.setText(String.valueOf(data.size()));
        });
    }
    
    private void alert(String message){
        this.Notification = message;
        hasNotification = true;
        hasNotification = false;
    }
    
    private void addTuple(String input,String action,String output){
        data.add(new History(getDateTime(), input, action, output));
    }
    
    private String getDateTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM dd yyyy");
        return dateFormat.format(calendar.getTime());
    }
    
    /**
     * 
     * clears content on the history logs list
     */
    
    public void clear(){
        data.clear();
        tableView.refresh();
    }
}
