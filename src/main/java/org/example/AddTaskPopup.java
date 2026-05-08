package org.example;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddTaskPopup {

    public void show(Stage owner, java.util.function.Consumer<Task> onAdd) {
        Stage popup = new Stage();
        popup.setTitle("Add New Task");

        popup.initOwner(owner);
        popup.initModality(Modality.WINDOW_MODAL);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        TextField nameField = new TextField();
        nameField.setPromptText("Task name");

        ComboBox<String> categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll("All Tasks", "Work", "School", "Personal");
        categoryBox.setValue("Work");

        Button add = new Button("Save");
        Button cancel = new Button("Cancel");

        add.setOnAction(e -> {
            String name = nameField.getText().trim();
            String category = categoryBox.getValue();

            if (name.isEmpty() || category == null) return;

            Task newTask = new Task(name, category);
            onAdd.accept(newTask);
            popup.close();
        });

        cancel.setOnAction(e -> popup.close());

        layout.getChildren().addAll(
                new Label("Task Name:"), nameField,
                new Label("Category:"), categoryBox,
                new HBox(10, add, cancel)
        );

        popup.setScene(new Scene(layout,300,200));
        popup.show();
    }
}