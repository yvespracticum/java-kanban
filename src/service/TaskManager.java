package service;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;

public interface TaskManager {

    // Tasks management
    ArrayList<Task> getAllTasks();

    void deleteAllTasks();

    Task createTask(Task task);

    Task getTaskById(int taskId);

    Task updateTask(Task task);

    void deleteTaskById(int taskId);

    // Epics management
    ArrayList<Epic> getAllEpics();

    void deleteAllEpics();

    Epic createEpic(Epic epic);

    Epic getEpicById(int epicId);

    Epic updateEpic(Epic epic);

    void deleteEpicById(int epicId);

    ArrayList<Subtask> getEpicSubtasks(int epicId);

    void changeEpicStatusDueToSubtaskStatus(int epicId);

    // Subtasks management
    ArrayList<Subtask> getAllSubtasks();

    void deleteAllSubtasks();

    Subtask createSubtask(Subtask subtask);

    Subtask getSubtaskById(int subtaskId);

    Subtask updateSubtask(Subtask subtask);

    void deleteSubtaskById(int subtaskId);
}
