package service;

import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryHistoryManagerTest {
    
    private HistoryManager historyManager;
    private Task task;
    
    @BeforeEach
    void setUp() {
        historyManager = Managers.getDefaultHistory();
        task = new Task("Task", "Description");
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
        historyManager.add(task);
        historyManager.remove(task.getId());
        List<Task> expectedHistory = new ArrayList<>(); // empty list
        assertEquals(expectedHistory, historyManager.getHistory());
    }
}
