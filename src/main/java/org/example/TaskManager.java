package org.example;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private final TaskStorage storage = new TaskStorage();
    private final List<Task> tasks;

    public TaskManager() {
        List<Task> loaded = storage.loadTasks();
        this.tasks = (loaded != null) ? loaded : new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        storage.saveTasks(tasks);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        storage.saveTasks(tasks);
    }

    public void updateTask(Task task) {
        storage.saveTasks(tasks);
    }

    public List<Task> getTasksByCategory(String category) {
        if (category.equals("All Tasks")) {
            return tasks;
        }

        List<Task> filtered = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getCategory().equals(category)) {
                filtered.add(task);
            }
        }

        return filtered;
    }
}
