package service;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.List;

public interface TaskManager {

    // Tasks management
    List<Task> getAllTasks();

    void deleteAllTasks();

    Task addTask(Task task);

    Task getTaskById(int taskId);

    Task updateTask(Task task);

    void deleteTaskById(int taskId);

    // Epics management
    List<Epic> getAllEpics();

    void deleteAllEpics();

    Epic addEpic(Epic epic);

    Epic getEpicById(int epicId);

    Epic updateEpic(Epic epic);

    void deleteEpicById(int epicId);

    List<Subtask> getEpicSubtasks(int epicId);

    void changeEpicStatusDueToSubtaskStatus(int epicId);

    // Subtasks management
    List<Subtask> getAllSubtasks();

    void deleteAllSubtasks();

    Subtask addSubtask(Subtask subtask);

    Subtask getSubtaskById(int subtaskId);

    Subtask updateSubtask(Subtask subtask);

    void deleteSubtaskById(int subtaskId);

    List<Task> getHistory();
}
