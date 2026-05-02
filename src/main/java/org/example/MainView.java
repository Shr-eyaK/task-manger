package org.example;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;


public class MainView {

    private VBox centrebox;
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

        //Add New Task
        Button addTaskButton = new Button("+ Add Task");
        addTaskButton.setOnAction(e -> openAddTaskPopup());

        hbox.getChildren().addAll(title2,addTaskButton,spacer);
        root.setTop(hbox);

        //Centre
        centrebox = new VBox();
        centrebox.setSpacing(10);
        centrebox.setPadding(new Insets(20));
        Label defaultTitle = new Label("No Tasks Yet");

        centrebox.getChildren().addAll(defaultTitle);
        root.setCenter(centrebox);


        return root;

    }

    private void openAddTaskPopup() {
        Stage popup = new Stage();
        popup.setTitle("Add New Task");

        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(20));

        Label nameLabel = new Label("Task Name:");
        TextField nameField = new TextField();

        Label categoryLabel = new Label("Category:");
        ComboBox<String> categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll("All Tasks", "Work", "School", "Personal");

        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");

        HBox buttonRow = new HBox(10, saveButton, cancelButton);

        layout.getChildren().addAll(
                nameLabel, nameField,
                categoryLabel, categoryBox,
                buttonRow
        );

        Scene scene = new Scene(layout, 300, 200);
        popup.setScene(scene);

        // Button Logic
        saveButton.setOnAction(e -> {
            String taskName = nameField.getText().trim();
            String category = categoryBox.getValue();

            // Validation
            if (taskName.isEmpty()) {
                //visual feedback
                nameField.setStyle("-fx-border-color: red;");
                return;
            } else {
                nameField.setStyle(null);
            }

            if (category == null) {
                categoryBox.setStyle("-fx-border-color: red;");
                return;
            } else {
                categoryBox.setStyle(null);
            }

            // Remove placeholder if it's still there
            if (centrebox.getChildren().size() == 1 &&
                    centrebox.getChildren().get(0) instanceof Label &&
                    ((Label) centrebox.getChildren().get(0)).getText().equals("No Tasks Left")) {
                centrebox.getChildren().clear();
            }

            // Add the new task to the main area
            HBox taskRow = new HBox();
            taskRow.setSpacing(10);
            taskRow.setPadding(new Insets(5));
            taskRow.setStyle("-fx-background-color: #f0f0f0; -fx-background-radius: 5;");

            CheckBox check = new CheckBox();
            Label taskLabel = new Label(taskName + " (" + category + ")");
            check.setOnAction(ev -> {
                if (check.isSelected()) {
                    taskLabel.setStyle("-fx-text-fill: gray; -fx-strikethrough: true;");
                } else {
                    taskLabel.setStyle("-fx-text-fill: black; -fx-strikethrough: false;");
                }
            });

            Button deleteButton = new Button("X");
            deleteButton.setOnAction(ev -> centrebox.getChildren().remove(taskRow));

            taskRow.getChildren().addAll(check, taskLabel, deleteButton);
            centrebox.getChildren().add(taskRow);

            popup.close();
        });

        cancelButton.setOnAction(e -> popup.close());
        popup.show();
    }
}
