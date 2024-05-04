package service;

import model.Task;
import model.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryHistoryManagerTest {

    private HistoryManager historyManager;
    private Task task;

    @BeforeEach
    void setUp() {
        historyManager = Managers.getDefaultHistory();
        task = new Task(1, "Task", "Description", TaskStatus.NEW);
    }

    @Test
    void testGetHistory() {
        historyManager.add(task);
        List<Task> expectedHistory = new ArrayList<>();
        expectedHistory.add(task);
        assertEquals(expectedHistory, historyManager.getHistory());
    }

    @Test
    void testAdd() {
        historyManager.add(task);
        assertTrue(historyManager.getHistory().contains(task));
    }

    @Test
    void testAddExistingTask() {
        historyManager.add(task);
        historyManager.add(task);
        List<Task> expectedHistory = new ArrayList<>();
        expectedHistory.add(task);
        assertEquals(expectedHistory, historyManager.getHistory());
    }

    @Test
    void testRemove() {
        Task task2 = new Task(2, "Task", "Description", TaskStatus.NEW);
        Task task3 = new Task(3, "Task", "Description", TaskStatus.NEW);
        Task task4 = new Task(4, "Task", "Description", TaskStatus.NEW);

        historyManager.add(task);
        historyManager.add(task2);
        historyManager.add(task3);
        historyManager.add(task4);

        // Remove from the middle of the history linked list
        historyManager.remove(task2.getId());
        List<Task> expectedHistory = new ArrayList<>(Arrays.asList(task, task3, task4));
        assertEquals(expectedHistory, historyManager.getHistory());

        // Remove from the beginning of the history linked list
        historyManager.remove(task.getId());
        expectedHistory.remove(task);
        assertEquals(expectedHistory, historyManager.getHistory());

        // Remove from the end of the history linked list
        historyManager.remove(task4.getId());
        expectedHistory.remove(task4);
        assertEquals(expectedHistory, historyManager.getHistory());
    }
}
