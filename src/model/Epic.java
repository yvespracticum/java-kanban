package model;

import java.util.ArrayList;

public class Epic extends Task {
    
    private final ArrayList<Integer> mySubtaskIds;
    
    public Epic(String title, String description) {
        super(title, description);
        mySubtaskIds = new ArrayList<>();
    }
    
    public Epic(int id, String title, String description) {
        super(id, title, description, TaskStatus.NEW);
        mySubtaskIds = new ArrayList<>();
    }
    
    public ArrayList<Integer> getMySubtasksIds() {
        return mySubtaskIds;
    }
    
    public void addToMySubtasks(int subtaskId) {
        if (!mySubtaskIds.contains(subtaskId)) {
            mySubtaskIds.add(subtaskId);
        }
    }
    
    public void deleteSubtaskFromMyList(Integer subtaskId) {
        mySubtaskIds.remove(subtaskId);
    }
    
    public void clearMySubtasksIdsList() {
        mySubtaskIds.clear();
    }
}
