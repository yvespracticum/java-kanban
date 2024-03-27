package service;

import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, Task> TASKS;
    private final HashMap<Integer, Epic> EPICS;
    private final HashMap<Integer, Subtask> SUBTASKS;
    private int globalTasksCounter = 0;

    public TaskManager() {
        TASKS = new HashMap<>();
        EPICS = new HashMap<>();
        SUBTASKS = new HashMap<>();
    }

    // Tasks management
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(TASKS.values());
    }

    public void deleteAllTasks() {
        TASKS.clear();
    }

    public Task createTask(Task task) {
        task.setId(++globalTasksCounter);
        TASKS.put(task.getId(), task);
        return task;
    }

    public Task getTaskById(int taskId) {
        return TASKS.get(taskId);
    }

    public Task updateTask(Task task) {
        TASKS.put(task.getId(), task);
        return task;
    }

    public void deleteTaskById(int taskId) {
        TASKS.remove(taskId);
    }

    // Epics management
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(EPICS.values());
    }

    public void deleteAllEpics() {
        EPICS.clear();
        SUBTASKS.clear();
    }

    public Epic createEpic(Epic epic) {
        epic.setId(++globalTasksCounter);
        EPICS.put(epic.getId(), epic);
        return epic;
    }

    public Epic getEpicById(int epicId) {
        return EPICS.get(epicId);
    }

    public Epic updateEpic(Epic epic) {
        EPICS.put(epic.getId(), epic);
        return epic;
    }

    public void deleteEpicById(int epicId) {
        EPICS.remove(epicId);
    }

    public ArrayList<Subtask> getEpicSubtasks(int epicId) {
        ArrayList<Subtask> mySubtasks = new ArrayList<>();
        for (Integer subtaskId : EPICS.get(epicId).getMySubtasksIds()) {
            mySubtasks.add(SUBTASKS.get(subtaskId));
        }
        return mySubtasks;
    }

    private void changeEpicStatusDueToSubtaskStatus(int epicId) {
        boolean areAllMySubtasksNew = true;
        boolean areAllMySubtasksDone = true;
        for (Integer mySubtaskId : EPICS.get(epicId).getMySubtasksIds()) {
            if (SUBTASKS.get(mySubtaskId).getStatus() != TaskStatus.DONE) {
                areAllMySubtasksDone = false;
            }
            if (SUBTASKS.get(mySubtaskId).getStatus() != TaskStatus.NEW) {
                areAllMySubtasksNew = false;
            }
        }
        if (areAllMySubtasksNew) {
            EPICS.get(epicId).setStatus(TaskStatus.NEW);
        } else if (EPICS.get(epicId).getMySubtasksIds().isEmpty()) {
            EPICS.get(epicId).setStatus(TaskStatus.NEW);
        } else if (areAllMySubtasksDone) {
            EPICS.get(epicId).setStatus(TaskStatus.DONE);
        } else {
            EPICS.get(epicId).setStatus(TaskStatus.IN_PROGRESS);
        }
    }

    // Subtasks management
    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(SUBTASKS.values());
    }

    public void deleteAllSubtasks() {
        SUBTASKS.clear();
        for (Epic epic : EPICS.values()) {
            epic.clearMySubtasksIdsList();
            changeEpicStatusDueToSubtaskStatus(epic.getId());
        }
    }

    public Subtask createSubtask(Subtask subtask) {
        subtask.setId(++globalTasksCounter);
        SUBTASKS.put(subtask.getId(), subtask);
        EPICS.get(subtask.getMY_EPIC_ID()).addToMySubtasks(subtask.getId());
        changeEpicStatusDueToSubtaskStatus(subtask.getMY_EPIC_ID());

        return subtask;
    }

    public Subtask getSubtaskById(int subtaskId) {
        return SUBTASKS.get(subtaskId);
    }

    public Subtask updateSubtask(Subtask subtask) {
        SUBTASKS.put(subtask.getId(), subtask);
        EPICS.get(subtask.getMY_EPIC_ID()).addToMySubtasks(subtask.getId());
        changeEpicStatusDueToSubtaskStatus(subtask.getMY_EPIC_ID());
        return subtask;
    }

    public void deleteSubtaskById(int subtaskId) {
        Subtask subtask = SUBTASKS.remove(subtaskId);
        if (subtask != null) {
            int myEpicId = subtask.getMY_EPIC_ID();
            if (EPICS.get(myEpicId) != null) {
                EPICS.get(myEpicId).deleteSubtaskFromMyList(subtaskId);
                changeEpicStatusDueToSubtaskStatus(myEpicId);
            }
        }
    }
}
