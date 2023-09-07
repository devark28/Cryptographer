/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptographer.gui.toplevels.settingsgui;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 *
 * @author PC
 */
public class ListBook extends HBox {

    private int currentIndex = 0;
    private Node currentPage;
    private final ListView<String> titles_container;
    private final ObservableList<String> titles;
    private final ScrollPane page_container;
    private final ObservableList<Node> pages;
    public ListBook() {
        this.titles = FXCollections.observableArrayList();
        this.titles_container = new ListView<>(titles);
        this.page_container = new ScrollPane();
        this.pages = FXCollections.observableArrayList();
        
        titles_container.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            currentIndex = titles_container.getSelectionModel().selectedIndexProperty().get();
            currentPage = pages.get(currentIndex);
            page_container.setContent(currentPage);
        });
        
        pages.addListener((ListChangeListener<? super Node>) c -> {
            if(pages.size() > 0){
                titles_container.getSelectionModel().selectFirst();
                page_container.setContent(pages.get(0));
            }
        });
        
        this.getStyleClass().add("list-book");
        this.titles_container.getStyleClass().add("title-container");
        this.page_container.getStyleClass().add("page-container");
        
        embed();
    }
    
    private void embed(){
        HBox.setHgrow(this, Priority.ALWAYS);
        HBox.setHgrow(this.page_container, Priority.ALWAYS);
        this.getChildren().addAll(titles_container,page_container);
    }
    
    public void addPage(String title, Node content){
        addNewPage(title, content);
    }
    
    private void addNewPage(String title, Node content){
        if(title != null && content != null){
            titles.add(title);
            pages.add(content);
        }
    }
    
    public void selectFirst(){
        if(titles.size() > 0){
            titles_container.getSelectionModel().selectFirst();
            page_container.setContent(pages.get(0));
        }
    }
    
    public void switchToPage(String title){
        if(titles.size() > 0){
            if (titles.contains(title)) {
                titles_container.getSelectionModel().select(title);
            }
        }
    }
}
