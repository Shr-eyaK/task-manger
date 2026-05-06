package org.example;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.function.Consumer;

public class AddTaskPopup {

    public void show(Consumer<Task> onTaskCreated) {
        Stage popup = new Stage();
        popup.setTitle("Add New Task");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        TextField nameField = new TextField();
        ComboBox<String> categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll("All Tasks", "Work", "School", "Personal");

        Button save = new Button("Save");
        Button cancel = new Button("Cancel");

        save.setOnAction(e -> {
            String name = nameField.getText().trim();
            String category = categoryBox.getValue();

            boolean valid = true;

            if (name.isEmpty()) {
                nameField.setStyle("-fx-border-color: red;");
                valid = false;
            } else {
                nameField.setStyle(null);
            }

            if (category == null) {
                categoryBox.setStyle("-fx-border-color: red;");
                valid = false;
            } else {
                categoryBox.setStyle(null);
            }

            if (!valid) return;

            onTaskCreated.accept(new Task(name, category));
            popup.close();
        });

        layout.getChildren().addAll(
                new Label("Task Name"), nameField,
                new Label("Category: "), categoryBox,
                new HBox(10, save, cancel)
        );

        popup.setScene(new Scene(layout,300,200));
        popup.show();
    }
}