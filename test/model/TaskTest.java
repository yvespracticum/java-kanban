package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TaskTest {

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task("Task Title", "Task Description");
    }

    @Test
    void tasksAreEqualsIfTheirIdsAreEquals() {
        Task task2 = new Task(1, "Task Title 2", "Task Description 2", TaskStatus.IN_PROGRESS);
        Task task3 = new Task(1, "Task Title 3", "Task Description 3", TaskStatus.DONE);
        assertNotEquals(task2, task);
        assertEquals(task2, task3);
    }

    @Test
    void testTaskConstructor() {
        assertEquals("Task Title", task.getTitle());
        assertEquals("Task Description", task.getDescription());
        assertEquals(TaskStatus.NEW, task.getStatus());
    }

    @Test
    void testTaskConstructorWithId() {
        Task task2 = new Task(1, "Task Title 2", "Task Description 2", TaskStatus.IN_PROGRESS);
        assertEquals(1, task2.getId());
        assertEquals("Task Title 2", task2.getTitle());
        assertEquals("Task Description 2", task2.getDescription());
        assertEquals(TaskStatus.IN_PROGRESS, task2.getStatus());
    }

    @Test
    void testSetId() {
        task.setId(1);
        assertEquals(1, task.getId());
    }

    @Test
    void testSetTitle() {
        task.setTitle("New Title");
        assertEquals("New Title", task.getTitle());
    }

    @Test
    void testSetDescription() {
        task.setDescription("New Description");
        assertEquals("New Description", task.getDescription());
    }

    @Test
    void testSetStatus() {
        task.setStatus(TaskStatus.DONE);
        assertEquals(TaskStatus.DONE, task.getStatus());
    }

    @Test
    void testHashCode() {
        Task task2 = new Task(1, "Task Title 2", "Task Description 2", TaskStatus.IN_PROGRESS);
        Task task3 = new Task(1, "Task Title 3", "Task Description 3", TaskStatus.DONE);
        assertEquals(task2.hashCode(), task3.hashCode());
    }
}
