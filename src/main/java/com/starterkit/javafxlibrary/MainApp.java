package com.starterkit.javafxlibrary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/book-list.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/darktheme.css");
        
        stage.setTitle("Library Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
    	Application.launch(args);
    }

}
