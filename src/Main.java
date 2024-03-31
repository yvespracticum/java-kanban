import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;
import service.InMemoryTaskManager;

public class Main {

    public static void main(String[] args) {
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

        Task t1 = new Task("t1", "t1d");
        inMemoryTaskManager.createTask(t1);
        Task t2 = new Task("t2", "t2d");
        inMemoryTaskManager.createTask(t2);
        Epic e1 = new Epic("e1", "e1d");
        inMemoryTaskManager.createEpic(e1);
        Subtask e1s1 = new Subtask(3, "e1s1", "e1s1d");
        inMemoryTaskManager.createSubtask(e1s1);
        Subtask e1s2 = new Subtask(3, "e1s2", "e1s2d");
        inMemoryTaskManager.createSubtask(e1s2);
        Epic e2 = new Epic("e2", "e2d");
        inMemoryTaskManager.createEpic(e2);
        Subtask e2s1 = new Subtask(6, "e2s1", "e2s1d");
        inMemoryTaskManager.createSubtask(e2s1);
        System.out.println("\nTasks:\n" + inMemoryTaskManager.getAllTasks());
        System.out.println("\nEpics:\n" + inMemoryTaskManager.getAllEpics());
        System.out.println("\nSubtasks:\n" + inMemoryTaskManager.getAllSubtasks());
        System.out.println("\nChange t1 to DONE, t2 to IN_PROGRESS, e1s1 to DONE, e1s2 to IN_PROGRESS, e2s1 to DONE");
        t1 = new Task(1, "t1", "t1d", TaskStatus.DONE);
        inMemoryTaskManager.updateTask(t1);
        t2 = new Task(2, "t2", "t2d", TaskStatus.IN_PROGRESS);
        inMemoryTaskManager.updateTask(t2);
        e1s1 = new Subtask(4, 3, "e1s1", "e1s1d", TaskStatus.DONE);
        inMemoryTaskManager.updateSubtask(e1s1);
        e1s2 = new Subtask(5, 3, "e1s2", "e1s2d", TaskStatus.IN_PROGRESS);
        inMemoryTaskManager.updateSubtask(e1s2);
        e2s1 = new Subtask(7, 6, "e2s1", "e2s1d", TaskStatus.DONE);
        inMemoryTaskManager.updateSubtask(e2s1);
        System.out.println("\nTasks:\n" + inMemoryTaskManager.getAllTasks());
        System.out.println("\nEpics:\n" + inMemoryTaskManager.getAllEpics());
        System.out.println("\nSubtasks:\n" + inMemoryTaskManager.getAllSubtasks());
        System.out.println("\nUpdate e2");
        e2 = new Epic(6, "e2new", "e2newd");
        inMemoryTaskManager.updateEpic(e2);
        System.out.println(inMemoryTaskManager.getAllEpics());
        System.out.println("\nDelete t1 and e1");
        inMemoryTaskManager.deleteTaskById(1);
        inMemoryTaskManager.deleteEpicById(6);
        System.out.println("\nTasks:\n" + inMemoryTaskManager.getAllTasks());
        System.out.println("\nEpics:\n" + inMemoryTaskManager.getAllEpics());
        System.out.println("\nSubtasks:\n" + inMemoryTaskManager.getAllSubtasks());

    }
}
