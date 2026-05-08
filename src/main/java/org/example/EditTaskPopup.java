package org.example;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditTaskPopup {

    public void show(Task task, Runnable onSave) {
        Stage popup = new Stage();
        popup.setTitle("Edit Task");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        TextField nameField = new TextField(task.getName());
        ComboBox<String> categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll("All Tasks", "Work","School","Personal" );
        categoryBox.setValue(task.getCategory());

        Button save = new Button("Save");
        Button cancel = new Button("Cancel");

        save.setOnAction(e ->{
            String name = nameField.getText().trim();
            String category = categoryBox.getValue();

            if (name.isEmpty() || category == null) return;

            task.setName(name);
            task.setCategory(category);

            onSave.run();
            popup.close();
        });

        layout.getChildren().addAll(
                new Label("Task Name:"), nameField,
                new Label("Category:"), categoryBox,
                new HBox(10, save, cancel)
        );

        popup.setScene(new Scene(layout, 300, 200));
        popup.show();

    }
}
