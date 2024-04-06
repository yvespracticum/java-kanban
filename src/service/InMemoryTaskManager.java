package service;

import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {

    private final HistoryManager historyManager;
    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, Epic> epics;
    private final HashMap<Integer, Subtask> subtasks;
    private int globalTasksCounter = 0;

    public InMemoryTaskManager() {
        this.historyManager = Managers.getDefaultHistory();
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subtasks = new HashMap<>();
    }

    //History
    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    // Tasks management
    @Override
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public Task addTask(Task task) {
        task.setId(++globalTasksCounter);
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public Task getTaskById(int taskId) {
        historyManager.add(tasks.get(taskId));
        return tasks.get(taskId);
    }

    @Override
    public Task updateTask(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public void deleteTaskById(int taskId) {
        tasks.remove(taskId);
    }

    // Epics management
    @Override
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    @Override
    public Epic addEpic(Epic epic) {
        epic.setId(++globalTasksCounter);
        epics.put(epic.getId(), epic);
        return epic;
    }

    @Override
    public Epic getEpicById(int epicId) {
        historyManager.add(epics.get(epicId));
        return epics.get(epicId);
    }

    @Override
    public Epic updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        return epic;
    }

    @Override
    public void deleteEpicById(int epicId) {
        for (int subtaskId : epics.get(epicId).getMySubtasksIds()) {
            subtasks.remove(subtaskId);
        }
        epics.remove(epicId);
    }

    @Override
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
    @Override
    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public void deleteAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.clearMySubtasksIdsList();
            changeEpicStatusDueToSubtaskStatus(epic.getId());
        }
    }

    @Override
    public Subtask addSubtask(Subtask subtask) {
        subtask.setId(++globalTasksCounter);
        subtasks.put(subtask.getId(), subtask);
        epics.get(subtask.getMyEpicId()).addToMySubtasks(subtask.getId());
        changeEpicStatusDueToSubtaskStatus(subtask.getMyEpicId());
        return subtask;
    }

    @Override
    public Subtask getSubtaskById(int subtaskId) {
        historyManager.add(subtasks.get(subtaskId));
        return subtasks.get(subtaskId);
    }

    @Override
    public Subtask updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        epics.get(subtask.getMyEpicId()).addToMySubtasks(subtask.getId());
        changeEpicStatusDueToSubtaskStatus(subtask.getMyEpicId());
        return subtask;
    }

    @Override
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
