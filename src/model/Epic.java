package model;

import java.util.ArrayList;

public class Epic extends Task {
    ArrayList<Integer> mySubtasksIds;

    public Epic(String title, String description) {
        super(title, description);
        mySubtasksIds = new ArrayList<>();
    }

    public Epic(int id, String title, String description) {
        super(id, title, description);
        mySubtasksIds = new ArrayList<>();
    }

    public ArrayList<Integer> getMySubtasksIds() {
        return mySubtasksIds;
    }

    public void addToMySubtasks(Subtask subtask) {
        int index = mySubtasksIds.indexOf(subtask.getId());
        if (index != -1) {
            mySubtasksIds.set(index, subtask.getId());
        } else {
            mySubtasksIds.add(subtask.getId());
        }
    }

    public void deleteSubtaskFromMyList(int id) {
        mySubtasksIds.remove(id);
    }

    public void clearMySubtasksIdsList() {
        mySubtasksIds.clear();
    }
}
