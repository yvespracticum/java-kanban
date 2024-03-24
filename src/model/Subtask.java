package model;

public class Subtask extends Task {
    int myEpicID;

    public Subtask(int myEpicID, String title, String description) {
        super(title, description);
        this.myEpicID = myEpicID;
    }

    public Subtask(int myEpicID, String title, String description, TaskStatus status) {
        super(title, description, status);
        this.myEpicID = myEpicID;
    }

    public int getMyEpicID() {
        return myEpicID;
    }
}
