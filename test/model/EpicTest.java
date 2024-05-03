package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EpicTest {
    
    private Epic epic;
    
    @BeforeEach
    void setUp() {
        epic = new Epic("Epic Title", "Epic Description");
    }
    
    @Test
    void epicsAreEqualsIfTheirIdsAreEquals() {
        Epic epic1 = new Epic(1, "Epic 1 Title", "Epic 2 Description");
        Epic epic2 = new Epic(1, "Epic 2 Title", "Epic 2 Description");
        assertEquals(epic1, epic2);
    }
    
    @Test
    void testEpicConstructor() {
        assertEquals("Epic Title", epic.getTitle());
        assertEquals("Epic Description", epic.getDescription());
        assertEquals(TaskStatus.NEW, epic.getStatus());
        assertTrue(epic.getMySubtasksIds().isEmpty());
    }
    
    @Test
    void testEpicConstructorWithId() {
        Epic epic2 = new Epic(1, "Epic Title 2", "Epic Description 2");
        assertEquals(1, epic2.getId());
        assertEquals("Epic Title 2", epic2.getTitle());
        assertEquals("Epic Description 2", epic2.getDescription());
        assertEquals(TaskStatus.NEW, epic2.getStatus());
        assertTrue(epic2.getMySubtasksIds().isEmpty());
    }
    
    @Test
    void testAddToMySubtasks() {
        epic.addToMySubtasks(1);
        epic.addToMySubtasks(2);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        assertEquals(expected, epic.getMySubtasksIds());
    }
    
    @Test
    void testDeleteSubtaskFromMyList() {
        epic.addToMySubtasks(1);
        epic.addToMySubtasks(2);
        epic.deleteSubtaskFromMyList(1);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(2);
        assertEquals(expected, epic.getMySubtasksIds());
    }
    
    @Test
    void testClearMySubtasksIdsList() {
        epic.addToMySubtasks(1);
        epic.addToMySubtasks(2);
        epic.clearMySubtasksIdsList();
        assertTrue(epic.getMySubtasksIds().isEmpty());
    }
}
