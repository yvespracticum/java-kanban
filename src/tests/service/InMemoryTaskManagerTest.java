package service;

import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach void setUp() {
        taskManager = Managers.getDefault();
    }

    @Test void testGetHistory() {
        Task task1 = taskManager.addTask(new Task("Task 1", "Description 1"));
        Task task2 = taskManager.addTask(new Task("Task 2", "Description 2"));
        taskManager.getTaskById(task1.getId());
        taskManager.getTaskById(task2.getId());
        List<Task> expectedHistory = new ArrayList<>();
        expectedHistory.add(task1);
        expectedHistory.add(task2);
        assertEquals(expectedHistory, taskManager.getHistory());
    }

    @Test void testAddTask() {
        Task task = taskManager.addTask(new Task("Task Title", "Task Description"));
        assertTrue(taskManager.getAllTasks().contains(task));
    }

    @Test void testGetTaskById() {
        Task task = taskManager.addTask(new Task("Task Title", "Task Description"));
        taskManager.getTaskById(task.getId());
        assertEquals(task, taskManager.getTaskById(task.getId()));
    }

    @Test void testUpdateTask() {
        Task task = taskManager.addTask(new Task("Task Title", "Task Description"));
        Task updatedTask = new Task(task.getId(), "Updated Title", "Updated Description", TaskStatus.DONE);
        taskManager.updateTask(updatedTask);
        assertEquals(updatedTask, taskManager.getTaskById(task.getId()));
    }

    @Test void testDeleteTaskById() {
        Task task = taskManager.addTask(new Task("Task Title", "Task Description"));
        taskManager.deleteTaskById(task.getId());
        assertNull(taskManager.getTaskById(task.getId()));
    }

    @Test void testAddEpic() {
        Epic epic = taskManager.addEpic(new Epic("Epic Title", "Epic Description"));
        assertTrue(taskManager.getAllEpics().contains(epic));
    }

    @Test void testGetEpicById() {
        Epic epic = taskManager.addEpic(new Epic("Epic Title", "Epic Description"));
        taskManager.getEpicById(epic.getId());
        assertEquals(epic, taskManager.getEpicById(epic.getId()));
    }

    @Test void testUpdateEpic() {
        Epic epic = taskManager.addEpic(new Epic("Epic Title", "Epic Description"));
        Epic updatedEpic = new Epic(epic.getId(), "Updated Title", "Updated Description");
        taskManager.updateEpic(updatedEpic);
        assertEquals(updatedEpic, taskManager.getEpicById(epic.getId()));
    }

    @Test void testDeleteEpicById() {
        Epic epic = taskManager.addEpic(new Epic("Epic Title", "Epic Description"));
        taskManager.deleteEpicById(epic.getId());
        assertNull(taskManager.getEpicById(epic.getId()));
    }

    @Test void testAddSubtask() {
        Epic epic = taskManager.addEpic(new Epic("Epic Title", "Epic Description"));
        Subtask subtask = taskManager.addSubtask(new Subtask(epic.getId(), "Subtask Title", "Subtask Description"));
        assertTrue(taskManager.getAllSubtasks().contains(subtask));
        assertTrue(taskManager.getEpicById(epic.getId()).getMySubtasksIds().contains(subtask.getId()));
    }

    @Test void testGetSubtaskById() {
        Epic epic = taskManager.addEpic(new Epic("Epic Title", "Epic Description"));
        Subtask subtask = taskManager.addSubtask(new Subtask(epic.getId(), "Subtask Title", "Subtask Description"));
        taskManager.getSubtaskById(subtask.getId());
        assertEquals(subtask, taskManager.getSubtaskById(subtask.getId()));
    }

    @Test void testUpdateSubtask() {
        Epic epic = taskManager.addEpic(new Epic("Epic Title", "Epic Description"));
        Subtask subtask = taskManager.addSubtask(new Subtask(epic.getId(), "Subtask Title", "Subtask Description"));
        Subtask updatedSubtask =
                new Subtask(subtask.getId(), epic.getId(), "Updated Title", "Updated Description", TaskStatus.DONE);
        taskManager.updateSubtask(updatedSubtask);
        assertEquals(updatedSubtask, taskManager.getSubtaskById(subtask.getId()));
    }

    @Test void testDeleteSubtaskById() {
        Epic epic = taskManager.addEpic(new Epic("Epic Title", "Epic Description"));
        Subtask subtask = taskManager.addSubtask(new Subtask(epic.getId(), "Subtask Title", "Subtask Description"));
        taskManager.deleteSubtaskById(subtask.getId());
        assertNull(taskManager.getSubtaskById(subtask.getId()));
        assertFalse(taskManager.getEpicById(epic.getId()).getMySubtasksIds().contains(subtask.getId()));
    }

    @Test void testGetEpicSubtasks() {
        Epic epic = taskManager.addEpic(new Epic("Epic Title", "Epic Description"));
        Subtask subtask1 = taskManager.addSubtask(new Subtask(epic.getId(), "Subtask 1", "Subtask Description 1"));
        Subtask subtask2 = taskManager.addSubtask(new Subtask(epic.getId(), "Subtask 2", "Subtask Description 2"));
        List<Subtask> expectedSubtasks = new ArrayList<>();
        expectedSubtasks.add(subtask1);
        expectedSubtasks.add(subtask2);
        assertEquals(expectedSubtasks, taskManager.getEpicSubtasks(epic.getId()));
    }

    @Test void testChangeEpicStatusDueToSubtaskStatus() {
        Epic epic = taskManager.addEpic(new Epic("Epic Title", "Epic Description"));
        Subtask subtask1 = taskManager.addSubtask(new Subtask(epic.getId(), "Subtask 1", "Subtask Description 1"));
        Subtask subtask2 = taskManager.addSubtask(new Subtask(epic.getId(), "Subtask 2", "Subtask Description 2"));
        assertEquals(TaskStatus.NEW, taskManager.getEpicById(epic.getId()).getStatus());
        subtask1.setStatus(TaskStatus.IN_PROGRESS);
        taskManager.changeEpicStatusDueToSubtaskStatus(epic.getId());
        assertEquals(TaskStatus.IN_PROGRESS, taskManager.getEpicById(epic.getId()).getStatus());
        subtask1.setStatus(TaskStatus.DONE);
        taskManager.changeEpicStatusDueToSubtaskStatus(epic.getId());
        assertEquals(TaskStatus.IN_PROGRESS, taskManager.getEpicById(epic.getId()).getStatus());
        subtask2.setStatus(TaskStatus.DONE);
        taskManager.changeEpicStatusDueToSubtaskStatus(epic.getId());
        assertEquals(TaskStatus.DONE, taskManager.getEpicById(epic.getId()).getStatus());
    }

    @Test void testDeleteAllTasks() {
        taskManager.addTask(new Task("Task 1", "Description 1"));
        taskManager.addTask(new Task("Task 2", "Description 2"));
        taskManager.deleteAllTasks();
        assertTrue(taskManager.getAllTasks().isEmpty());
    }

    @Test void testDeleteAllEpics() {
        Epic epic1 = taskManager.addEpic(new Epic("Epic 1", "Description 1"));
        Epic epic2 = taskManager.addEpic(new Epic("Epic 2", "Description 2"));
        taskManager.addSubtask(new Subtask(epic1.getId(), "Subtask 1", "Subtask Description 1"));
        taskManager.addSubtask(new Subtask(epic2.getId(), "Subtask 2", "Subtask Description 2"));
        taskManager.deleteAllEpics();
        assertTrue(taskManager.getAllEpics().isEmpty());
        assertTrue(taskManager.getAllSubtasks().isEmpty());
    }

    @Test void testDeleteAllSubtasks() {
        Epic epic = taskManager.addEpic(new Epic("Epic Title", "Epic Description"));
        Subtask subtask1 = taskManager.addSubtask(new Subtask(epic.getId(), "Subtask 1", "Subtask Description 1"));
        Subtask subtask2 = taskManager.addSubtask(new Subtask(epic.getId(), "Subtask 2", "Subtask Description 2"));
        taskManager.deleteAllSubtasks();
        assertTrue(taskManager.getAllSubtasks().isEmpty());
        assertTrue(taskManager.getEpicById(epic.getId()).getMySubtasksIds().isEmpty());
    }
}
