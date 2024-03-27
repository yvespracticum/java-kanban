package model;

public class Subtask extends Task {
    private final int MY_EPIC_ID;

    public Subtask(int MY_EPIC_ID, String title, String description) {
        super(title, description);
        this.MY_EPIC_ID = MY_EPIC_ID;
    }

    public Subtask(int id, int MY_EPIC_ID, String title, String description, TaskStatus status) {
        super(id, title, description, status);
        this.MY_EPIC_ID = MY_EPIC_ID;
    }

    public int getMY_EPIC_ID() {
        return MY_EPIC_ID;
    }
}
