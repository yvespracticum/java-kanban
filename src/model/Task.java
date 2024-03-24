package model;

import service.TaskManager;

public class Task {
    int id;
    String title;
    String description;
    TaskStatus status;

    public Task(String title, String description) {
        TaskManager.globalTasksCounter++;
        this.id = TaskManager.globalTasksCounter;
        this.title = title;
        this.description = description;
        this.status = TaskStatus.NEW;
    }

    public Task(String title, String description, TaskStatus taskStatus) {
        this.id = TaskManager.globalTasksCounter;
        this.title = title;
        this.description = description;
        this.status = taskStatus;
    }

    public int getID() {
        return id;
    }

    public void rewriteID(int id) {
        this.id = id;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override public String toString() {
        return "{id=" + id + ", title='" + title + '\'' + ", description='" + description + '\'' + ", status=" +
                status + '}';
    }

}
