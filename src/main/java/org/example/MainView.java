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
    private TaskManager taskManager = new TaskManager();
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

        root.setCenter(centrebox);
        renderTasksForCategory("All Tasks");
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
                nameField.setStyle("-fx-border-color: red;");
                return;
            }
            nameField.setStyle(null);

            if (category == null) {
                categoryBox.setStyle("-fx-border-color: red;");
                return;
            }
            categoryBox.setStyle(null);

            //Create a Task Object
            Task task = new Task(taskName, category);

            //Add to logic layer
            taskManager.addTask(task);

            //Re-render UI
            renderTasksForCategory("All Tasks");

            popup.close();
        });

        cancelButton.setOnAction(e -> popup.close());
        popup.show();
    }

    private void renderTasksForCategory(String category){
        centrebox.getChildren().clear();

        var tasksToShow = taskManager.getAllTasksByCategory(category);

        if(tasksToShow.isEmpty()){
            centrebox.getChildren().add(new Label("No Tasks Yet"));
            return;
        }

        for(Task task : tasksToShow){
            centrebox.getChildren().add(buildTaskRow(task));
        }
    }

    private HBox buildTaskRow(Task task) {
        HBox taskRow = new HBox();
        taskRow.setSpacing(10);
        taskRow.setPadding(new Insets(5));
        taskRow.setStyle("-fx-background-color: #f0f0f0; -fx-background-radius: 5;");

        CheckBox checkBox = new CheckBox();
        checkBox.setSelected(task.isCompleted());

        Label taskLabel = new Label(task.getName() + " (" + task.getCategory() + ")");

        if (task.isCompleted()) {
            taskLabel.setStyle("-fx-text-fill: gray; -fx-strikethrough: true;");
        }

        checkBox.setOnAction(e -> {
            task.setCompleted((checkBox.isSelected()));
            renderTasksForCategory("All Tasks");
        });

        Button deleteButton = new Button("X");
        deleteButton.setOnAction(e->{
            taskManager.removeTask(task);
            renderTasksForCategory(("All Tasks"));
        });

        taskRow.getChildren().addAll(checkBox, taskLabel, deleteButton);
        return taskRow;

    }

}
