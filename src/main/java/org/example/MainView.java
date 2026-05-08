package org.example;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class MainView {

    private Stage primaryStage;

    private TaskManager taskManager = new TaskManager();
    private SidebarView sidebarView = new SidebarView();
    private TaskListView taskListView = new TaskListView();
    private AddTaskPopup addTaskPopup = new AddTaskPopup();
    private EditTaskPopup editTaskPopup = new EditTaskPopup();

    private VBox centrebox;

    public BorderPane createUI(Stage primaryStage) {
        this.primaryStage = primaryStage;

        BorderPane root = new BorderPane();

        // Sidebar
        VBox sidebar = sidebarView.createSidebar(category -> {
            renderTasks(category);
        });
        root.setLeft(sidebar);

        // Header
        HBox header = new HBox(10);
        header.setPadding(new Insets(20));

        Label title = new Label("Task Manager");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button addTaskButton = new Button("+ Add Task");
        addTaskButton.setOnAction(e -> {
            addTaskPopup.show(primaryStage, task -> {
                taskManager.addTask(task);
                renderTasks("All Tasks");
            });
        });

        Button markAllButton = new Button("Mark All Complete");
        markAllButton.setOnAction(e -> {
            taskManager.markAllComplete();
            renderTasks("All Tasks");
        });

        header.getChildren().addAll(title, spacer, addTaskButton, markAllButton);
        root.setTop(header);

        // Centre area
        centrebox = new VBox(10);
        centrebox.setPadding(new Insets(20));
        root.setCenter(centrebox);

        // Initial render
        renderTasks("All Tasks");

        return root;
    }

    private void renderTasks(String category) {
        centrebox.getChildren().clear();

        var tasks = taskManager.getTasksByCategory(category);

        VBox taskList = taskListView.createTaskList(
                tasks,
                task -> {
                    task.setCompleted(!task.isCompleted());
                    taskManager.updateTask(task);
                    renderTasks(category);
                },
                task -> {
                    taskManager.removeTask(task);
                    renderTasks(category);
                },
                task -> {
                    editTaskPopup.show(primaryStage, task, () -> {
                        taskManager.updateTask(task);
                        renderTasks(category);
                    });
                }
        );

        centrebox.getChildren().add(taskList);
    }
}
