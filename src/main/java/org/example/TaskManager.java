package org.example;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks  = new ArrayList<>();

    public void addTask(Task task){
        tasks.add(task);
    }

    public void removeTask(Task task){
        tasks.remove(task);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public List<Task> getAllTasksByCategory(String category){
        if(category.equals("All Tasks")){
            return getAllTasks();
        }

        List<Task> filtered = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getCategory().equals(category)) {
                filtered.add(t);
            }
        }
        return filtered;
    }
}
