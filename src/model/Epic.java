package model;

import java.util.ArrayList;

public class Epic extends Task {
    ArrayList<Subtask> mySubtasks;

    public Epic(String title, String description) {
        super(title, description);
        mySubtasks = new ArrayList<>();
    }

    public ArrayList<Subtask> getMySubtasks() {
        return mySubtasks;
    }

    public void addToMySubtasks(Subtask subtask) {
        for (int i = 0; i < mySubtasks.size(); i++) {
            if (mySubtasks.get(i).getID() == subtask.getID()) {
                mySubtasks.set(i, subtask);
                return;
            }
        }
        mySubtasks.add(subtask);
    }

    public void clearMySubtasksList() {
        mySubtasks.clear();
    }

    public boolean isAllMySubtasksDone() {
        for (Subtask subtask : mySubtasks) {
            if (subtask.getStatus() != TaskStatus.DONE) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnyMySubtaskInProgress() {
        for (Subtask subtask : mySubtasks) {
            if (subtask.getStatus() == TaskStatus.IN_PROGRESS) {
                return true;
            }
        }
        return false;
    }
}
