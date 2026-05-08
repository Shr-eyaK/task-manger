package org.example;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.List;
import java.util.function.Consumer;

public class TaskListView {

    public VBox createTaskList(
            List<Task> tasks,
            Consumer<Task> onToggle,
            Consumer<Task> onDelete,
            Consumer<Task> onEdit
    ) {
        VBox box = new VBox(10);
        box.setPadding(new Insets(20));

        if (tasks.isEmpty()) {
            box.getChildren().add(new Label("No Tasks Yet"));
            return box;
        }

        for (Task task : tasks) {
            HBox row = new HBox(10);
            row.setPadding(new Insets(5));
            row.setStyle("-fx-background-color: #f0f0f0; -fx-background-radius: 5;");

            row.setOnMouseClicked(e ->{
                if(e.getClickCount()==2){
                    onEdit.accept(task);
                }
            });

            CheckBox check = new CheckBox();
            check.setSelected(task.isCompleted());

            Label label = new Label(task.getName() + " (" + task.getCategory() + ")");

            check.setOnAction(e -> onToggle.accept(task));

            Button delete = new Button("X");
            delete.setOnAction(e -> onDelete.accept(task));

            row.getChildren().addAll(check, label, delete);
            box.getChildren().add(row);
        }

        return box;
    }
}
