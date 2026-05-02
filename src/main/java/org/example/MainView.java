package org.example;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.Priority;


public class MainView {

    public BorderPane createUI() {
        BorderPane root = new BorderPane();

        //Sidebar
        VBox box = new VBox();
        box.setSpacing(10);
        box.setPadding(new Insets(20));
        box.setStyle("-fx-background-color: #f0f0f0;");

        Label title = new Label("Categories");
        Label all = new Label("All Tasks");
        Label work = new Label("Work");
        Label school = new Label("School");
        Label personal = new Label("Personal");

        box.getChildren().addAll(title,all,work,school,personal);
        root.setLeft(box);

        //Header
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(20));

        Label title2 = new Label("Task Manager");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button addTaskButton = new Button("+ Add Task");

        hbox.getChildren().addAll(title2,addTaskButton,spacer);
        root.setTop(hbox);

        //Centre
        VBox centrebox = new VBox();
        centrebox.setSpacing(10);
        centrebox.setPadding(new Insets(20));
        Label defaultTitle = new Label("No Tasks Yet");

        centrebox.getChildren().addAll(defaultTitle);
        root.setCenter(centrebox);


        return root;

    }
}
