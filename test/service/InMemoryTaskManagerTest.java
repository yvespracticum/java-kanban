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
    private Task task;
    private Epic epic;
    private Subtask subtask;

    @BeforeEach
    void setUp() {
        taskManager = Managers.getDefault();
        task = taskManager.addTask(new Task("Task Title", "Task Desc."));
        epic = taskManager.addEpic(new Epic("Epic Title", "Epic Desc."));
        subtask = taskManager.addSubtask(new Subtask(epic.getId(), "Subtask Title", "Subtask Desc."));
    }

    @Test
    void testGetHistory() {
        taskManager.getTaskById(task.getId());
        taskManager.getEpicById(epic.getId());
        List<Task> expectedHistory = new ArrayList<>();
        expectedHistory.add(task);
        expectedHistory.add(epic);
        assertEquals(expectedHistory, taskManager.getHistory());
    }

    @Test
    void testAddTask() {
        assertTrue(taskManager.getAllTasks().contains(task));
    }

    @Test
    void testGetTaskById() {
        assertEquals(task, taskManager.getTaskById(task.getId()));
    }

    @Test
    void testUpdateTask() {
        Task updatedTask = new Task(task.getId(), "Upd. Title", "Upd. Desc.", TaskStatus.DONE);
        taskManager.updateTask(updatedTask);
        assertEquals(updatedTask, taskManager.getTaskById(task.getId()));
    }

    @Test
    void testDeleteTaskById() {
        taskManager.deleteTaskById(task.getId());
        assertNull(taskManager.getTaskById(task.getId()));
    }

    @Test
    void testAddEpic() {
        assertTrue(taskManager.getAllEpics().contains(epic));
    }

    @Test
    void testGetEpicById() {
        assertEquals(epic, taskManager.getEpicById(epic.getId()));
    }

    @Test
    void testUpdateEpic() {
        Epic updatedEpic = new Epic(epic.getId(), "Upd. Title", "Upd. Desc.");
        taskManager.updateEpic(updatedEpic);
        assertEquals(updatedEpic, taskManager.getEpicById(epic.getId()));
    }

    @Test
    void testDeleteEpicById() {
        taskManager.deleteEpicById(epic.getId());
        assertNull(taskManager.getEpicById(epic.getId()));
    }

    @Test
    void testAddSubtask() {
        assertTrue(taskManager.getAllSubtasks().contains(subtask));
        assertTrue(taskManager.getEpicById(epic.getId()).getMySubtasksIds().contains(subtask.getId()));
    }

    @Test
    void testGetSubtaskById() {
        assertEquals(subtask, taskManager.getSubtaskById(subtask.getId()));
    }

    @Test
    void testUpdateSubtask() {
        Subtask updatedSubtask =
                new Subtask(subtask.getId(), subtask.getMyEpicId(), "Upd. Title", "Upd. Desc.", TaskStatus.DONE);
        taskManager.updateSubtask(updatedSubtask);
        assertEquals(updatedSubtask, taskManager.getSubtaskById(subtask.getId()));
    }

    @Test
    void testDeleteSubtaskById() {
        taskManager.deleteSubtaskById(subtask.getId());
        assertNull(taskManager.getSubtaskById(subtask.getId()));
        assertFalse(taskManager.getEpicById(epic.getId()).getMySubtasksIds().contains(subtask.getId()));
    }

    @Test
    void testGetEpicSubtasks() {
        Subtask subtask2 = taskManager.addSubtask(new Subtask(epic.getId(), "Subtask 2", "Subtask Desc. 2"));
        List<Subtask> expectedSubtasks = new ArrayList<>();
        expectedSubtasks.add(subtask);
        expectedSubtasks.add(subtask2);
        assertEquals(expectedSubtasks, taskManager.getEpicSubtasks(epic.getId()));
    }

    @Test
    void testChangeEpicStatusDueToSubtaskStatus() {
        assertEquals(TaskStatus.NEW, taskManager.getEpicById(epic.getId()).getStatus());
        taskManager.updateSubtask(
                new Subtask(subtask.getId(), subtask.getMyEpicId(), "Subtask 1", "Now Subtask 1 is IN_PROGRESS",
                        TaskStatus.IN_PROGRESS));
        assertEquals(TaskStatus.IN_PROGRESS, taskManager.getEpicById(epic.getId()).getStatus());
        taskManager.updateSubtask(
                new Subtask(subtask.getId(), subtask.getMyEpicId(), "Subtask 1", "Now Subtask 1 is DONE",
                        TaskStatus.DONE));
        assertEquals(TaskStatus.DONE, taskManager.getEpicById(epic.getId()).getStatus());
    }

    @Test
    void testDeleteAllTasks() {
        taskManager.addTask(new Task("Task 2", "Desc. 2"));
        taskManager.deleteAllTasks();
        assertTrue(taskManager.getAllTasks().isEmpty());
    }

    @Test
    void testDeleteAllEpics() {
        Epic epic2 = taskManager.addEpic(new Epic("Epic 2", "Desc. 2"));
        taskManager.addSubtask(new Subtask(epic.getId(), "Subtask 1", "Subtask Desc. 1"));
        taskManager.addSubtask(new Subtask(epic2.getId(), "Subtask 2", "Subtask Desc. 2"));
        taskManager.deleteAllEpics();
        assertTrue(taskManager.getAllEpics().isEmpty());
        assertTrue(taskManager.getAllSubtasks().isEmpty());
    }

    @Test
    void testDeleteAllSubtasks() {
        taskManager.deleteAllSubtasks();
        assertTrue(taskManager.getAllSubtasks().isEmpty());
        assertTrue(taskManager.getEpicById(epic.getId()).getMySubtasksIds().isEmpty());
    }
}
