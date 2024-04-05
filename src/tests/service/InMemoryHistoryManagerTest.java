package service;

import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class InMemoryHistoryManagerTest {

    private HistoryManager historyManager;

    @BeforeEach void setUp() {
        historyManager = Managers.getDefaultHistory();
    }

    @Test void testHistoryManagerDoNotChangeTasks() {
        Task task1 = new Task("Task 1", "Description 1");
        Task task2 = new Task("Task 2", "Description 2");
        Task task3 = new Task("Task 3", "Description 3");
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task3);
        List<Task> history = historyManager.getHistory();
        assertEquals(3, history.size());
        assertSame(task1, history.get(0));
        assertSame(task2, history.get(1));
        assertSame(task3, history.get(2));
    }

    @Test void testAdd() {
        Task task1 = new Task("Task 1", "Description 1");
        Task task2 = new Task("Task 2", "Description 2");
        Task task3 = new Task("Task 3", "Description 3");
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task3);
        List<Task> expectedHistory = new ArrayList<>();
        expectedHistory.add(task1);
        expectedHistory.add(task2);
        expectedHistory.add(task3);
        assertEquals(expectedHistory, historyManager.getHistory());
    }

    @Test void testAddWithMaxHistorySize() {
        List<Task> tasks = new ArrayList<>();
        for (int i = 1; i <= 11; i++) {
            tasks.add(new Task("Task " + i, "Description " + i));
        }
        for (Task task : tasks) {
            historyManager.add(task);
        }
        List<Task> expectedHistory = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            expectedHistory.add(tasks.get(i));
        }
        assertEquals(expectedHistory, historyManager.getHistory());
    }
}
