package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubtaskTest {

    private Subtask subtask;

    @BeforeEach
    void setUp() {
        subtask = new Subtask(1, "Subtask Title", "Subtask Description");
    }

    @Test
    void subtasksAreEqualsIfTheirIdsAreEquals() {
        Subtask subtask2 = new Subtask(1, "Subtask Title 2", "Subtask Description 2");
        assertEquals(subtask2, subtask);
    }

    @Test
    void testSubtaskConstructor() {
        assertEquals(1, subtask.getMyEpicId());
        assertEquals("Subtask Title", subtask.getTitle());
        assertEquals("Subtask Description", subtask.getDescription());
        assertEquals(TaskStatus.NEW, subtask.getStatus());
    }

    @Test
    void testSubtaskConstructorWithId() {
        Subtask subtask2 = new Subtask(2, 3, "Subtask Title 2", "Subtask Description 2", TaskStatus.IN_PROGRESS);
        assertEquals(2, subtask2.getId());
        assertEquals(3, subtask2.getMyEpicId());
        assertEquals("Subtask Title 2", subtask2.getTitle());
        assertEquals("Subtask Description 2", subtask2.getDescription());
        assertEquals(TaskStatus.IN_PROGRESS, subtask2.getStatus());
    }
}
