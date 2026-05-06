package org.example;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.util.function.Consumer;

public class SidebarView {

    public VBox createSidebar(Consumer<String> onCategorySelected){
        VBox box = new VBox(10);
        box.setPadding(new Insets(20));
        box.setStyle("-fx-background-color: #f0f0f0;");

        Label title = new Label("Categories");
        Label all = new Label("All Tasks");
        Label work = new Label("Work");
        Label school = new Label("School");
        Label personal = new Label("Personal");

        all.setOnMouseClicked(e -> onCategorySelected.accept("All Tasks"));
        work.setOnMouseClicked(e -> onCategorySelected.accept("Work"));
        school.setOnMouseClicked(e -> onCategorySelected.accept("School"));
        personal.setOnMouseClicked(e -> onCategorySelected.accept("Personal"));

        box.getChildren().addAll(title,all,work,school,personal);
        return box;
    }

}
