package model;

public class Subtask extends Task {
    int myEpicId;

    public Subtask(int myEpicId, String title, String description) {
        super(title, description);
        this.myEpicId = myEpicId;
    }

    public Subtask(int id, int myEpicId, String title, String description, TaskStatus status) {
        super(id, title, description, status);
        this.myEpicId = myEpicId;
    }

    public int getMyEpicId() {
        return myEpicId;
    }
}
