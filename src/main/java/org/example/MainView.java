package org.example;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class MainView {

    public BorderPane createUI() {
        BorderPane root = new BorderPane();

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
        return root;
    }
}
