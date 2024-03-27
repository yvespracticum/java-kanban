package model;

import java.util.ArrayList;

public class Epic extends Task {
    private final ArrayList<Integer> MY_SUBTASKS_IDS;

    public Epic(String title, String description) {
        super(title, description);
        MY_SUBTASKS_IDS = new ArrayList<>();
    }

    public Epic(int id, String title, String description) {
        super(id, title, description, TaskStatus.NEW);
        MY_SUBTASKS_IDS = new ArrayList<>();
    }

    public ArrayList<Integer> getMySubtasksIds() {
        return MY_SUBTASKS_IDS;
    }

    public void addToMySubtasks(int subtaskId) {
        if (!MY_SUBTASKS_IDS.contains(subtaskId)) {
            MY_SUBTASKS_IDS.add(subtaskId);
        }
    }

    public void deleteSubtaskFromMyList(Integer subtaskId) {
        MY_SUBTASKS_IDS.remove(subtaskId);
    }

    public void clearMySubtasksIdsList() {
        MY_SUBTASKS_IDS.clear();
    }
}
