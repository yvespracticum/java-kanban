package service;

import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, Epic> epics;
    private final HashMap<Integer, Subtask> subtasks;
    private int globalTasksCounter = 0;

    public TaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
    }

    // Tasks management
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public Task createTask(Task task) {
        task.setId(++globalTasksCounter);
        tasks.put(task.getId(), task);
        return task;
    }

    public Task getTaskById(int taskId) {
        return tasks.get(taskId);
    }

    public Task updateTask(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    public void deleteTaskById(int taskId) {
        tasks.remove(taskId);
    }

    // Epics management
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    public Epic createEpic(Epic epic) {
        epic.setId(++globalTasksCounter);
        epics.put(epic.getId(), epic);
        return epic;
    }

    public Epic getEpicById(int epicId) {
        return epics.get(epicId);
    }

    public Epic updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        return epic;
    }

    public void deleteEpicById(int epicId) {
        for (int subtaskId : epics.get(epicId).getMySubtasksIds()) {
            subtasks.remove(subtaskId);
        }
        epics.remove(epicId);
    }

    public ArrayList<Subtask> getEpicSubtasks(int epicId) {
        ArrayList<Subtask> mySubtasks = new ArrayList<>();
        for (Integer subtaskId : epics.get(epicId).getMySubtasksIds()) {
            mySubtasks.add(subtasks.get(subtaskId));
        }
        return mySubtasks;
    }

    private void changeEpicStatusDueToSubtaskStatus(int epicId) {
        boolean areAllMySubtasksNew = true;
        boolean areAllMySubtasksDone = true;
        for (Integer mySubtaskId : epics.get(epicId).getMySubtasksIds()) {
            if (subtasks.get(mySubtaskId).getStatus() != TaskStatus.DONE) {
                areAllMySubtasksDone = false;
            }
            if (subtasks.get(mySubtaskId).getStatus() != TaskStatus.NEW) {
                areAllMySubtasksNew = false;
            }
        }
        if (areAllMySubtasksNew) {
            epics.get(epicId).setStatus(TaskStatus.NEW);
        } else if (epics.get(epicId).getMySubtasksIds().isEmpty()) {
            epics.get(epicId).setStatus(TaskStatus.NEW);
        } else if (areAllMySubtasksDone) {
            epics.get(epicId).setStatus(TaskStatus.DONE);
        } else {
            epics.get(epicId).setStatus(TaskStatus.IN_PROGRESS);
        }
    }

    // Subtasks management
    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public void deleteAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.clearMySubtasksIdsList();
            changeEpicStatusDueToSubtaskStatus(epic.getId());
        }
    }

    public Subtask createSubtask(Subtask subtask) {
        subtask.setId(++globalTasksCounter);
        subtasks.put(subtask.getId(), subtask);
        epics.get(subtask.getMyEpicId()).addToMySubtasks(subtask.getId());
        changeEpicStatusDueToSubtaskStatus(subtask.getMyEpicId());

        return subtask;
    }

    public Subtask getSubtaskById(int subtaskId) {
        return subtasks.get(subtaskId);
    }

    public Subtask updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        epics.get(subtask.getMyEpicId()).addToMySubtasks(subtask.getId());
        changeEpicStatusDueToSubtaskStatus(subtask.getMyEpicId());
        return subtask;
    }

    public void deleteSubtaskById(int subtaskId) {
        Subtask subtask = subtasks.remove(subtaskId);
        if (subtask != null) {
            int myEpicId = subtask.getMyEpicId();
            if (epics.get(myEpicId) != null) {
                epics.get(myEpicId).deleteSubtaskFromMyList(subtaskId);
                changeEpicStatusDueToSubtaskStatus(myEpicId);
            }
        }
    }
}
