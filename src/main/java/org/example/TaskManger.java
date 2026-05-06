package org.example;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class TaskManger {
    private final List<Task> tasks  = new ArrayList<>();

    public void addTask(Task task){
        task.add(task);
    }

    public void removeTask(Task task){
        task.remove(task);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public List<Task> getAllTask(){
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
