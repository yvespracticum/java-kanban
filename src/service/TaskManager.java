package service;

import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    public static int globalTasksCounter = 0;
    HashMap<Integer, Task> tasks;
    HashMap<Integer, Epic> epics;
    HashMap<Integer, Subtask> subtasks;

    public TaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
    }

    // Tasks management
    public HashMap<Integer, Task> getAllTasks() {
        return tasks;
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public Task createTask(Task task) {
        tasks.put(task.getID(), task);
        return task;
    }

    public Task getTaskByID(int id) {
        return tasks.get(id);
    }

    public Task updateTask(int oldTaskID, Task newTask) {
        globalTasksCounter--;
        for (int taskID : tasks.keySet()) {
            if (oldTaskID == taskID) {
                tasks.put(oldTaskID, newTask);
            }
        }
        newTask.rewriteID(oldTaskID);
        return newTask;
    }

    public void deleteTaskByID(int id) {
        tasks.remove(id);
    }

    // Epics management
    public HashMap<Integer, Epic> getAllEpics() {
        return epics;
    }

    public void deleteAllEpics() {
        epics.clear();
    }

    public Epic createEpic(Epic epic) {
        epics.put(epic.getID(), epic);
        return epic;
    }

    public Epic getEpicByID(int id) {
        return epics.get(id);
    }

    public Epic updateEpic(int oldEpicID, Epic newEpic) {
        globalTasksCounter--;
        for (int epicID : epics.keySet()) {
            if (oldEpicID == epicID) {
                epics.put(oldEpicID, newEpic);
            }
        }
        newEpic.rewriteID(oldEpicID);
        return newEpic;
    }

    public void deleteEpicByID(int id) {
        epics.get(id).clearMySubtasksList();
        epics.remove(id);
        for (Subtask subtask : subtasks.values()) {
            if (id == subtask.getMyEpicID()) {
                subtasks.remove(subtask.getID());
            }
        }
    }

    public ArrayList<Subtask> getEpicSubtasks(Epic epic) {
        return epic.getMySubtasks();
    }

    // Subtasks management
    public HashMap<Integer, Subtask> getAllSubtasks() {
        return subtasks;
    }

    public void deleteAllSubtasks() {
        subtasks.clear();
    }

    public Subtask createSubtask(Subtask subtask) {
        subtasks.put(subtask.getID(), subtask);
        epics.get(subtask.getMyEpicID()).addToMySubtasks(subtask);
        return subtask;
    }

    public Subtask getSubtaskByID(int id) {
        return subtasks.get(id);
    }

    public Subtask updateSubtask(int oldSubtaskID, Subtask newSubtask) {
        globalTasksCounter--;
        for (int SubtaskID : subtasks.keySet()) {
            if (oldSubtaskID == SubtaskID) {
                subtasks.put(oldSubtaskID, newSubtask);
            }
        }
        newSubtask.rewriteID(oldSubtaskID);
        epics.get(newSubtask.getMyEpicID()).addToMySubtasks(newSubtask);
        if (epics.get(newSubtask.getMyEpicID()).isAllMySubtasksDone()) {
            epics.get(newSubtask.getMyEpicID()).setStatus(TaskStatus.DONE);
        } else if (epics.get(newSubtask.getMyEpicID()).isAnyMySubtaskInProgress()) {
            epics.get(newSubtask.getMyEpicID()).setStatus(TaskStatus.IN_PROGRESS);
        }
        return newSubtask;
    }

    public void deleteSubtaskByID(int id) {
        subtasks.remove(id);
    }
}
