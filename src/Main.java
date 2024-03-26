import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;
import service.TaskManager;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task t1 = new Task("t1", "t1d");
        taskManager.createTask(t1);
        Task t2 = new Task("t2", "t2d");
        taskManager.createTask(t2);
        Epic e1 = new Epic("e1", "e1d");
        taskManager.createEpic(e1);
        Subtask e1s1 = new Subtask(3, "e1s1", "e1s1d");
        taskManager.createSubtask(e1s1);
        Subtask e1s2 = new Subtask(3, "e1s2", "e1s2d");
        taskManager.createSubtask(e1s2);
        Epic e2 = new Epic("e2", "e2d");
        taskManager.createEpic(e2);
        Subtask e2s1 = new Subtask(6, "e2s1", "e2s1d");
        taskManager.createSubtask(e2s1);
        System.out.println("\nTasks:\n" + taskManager.getAllTasks());
        System.out.println("\nEpics:\n" + taskManager.getAllEpics());
        System.out.println("\nSubtasks:\n" + taskManager.getAllSubtasks());
        System.out.println("\nChange t1 to DONE, t2 to IN_PROGRESS, e1s1 to DONE, e1s2 to IN_PROGRESS, e2s1 to DONE");
        t1 = new Task(1, "t1", "t1d", TaskStatus.DONE);
        taskManager.updateTask(t1);
        t2 = new Task(2, "t2", "t2d", TaskStatus.IN_PROGRESS);
        taskManager.updateTask(t2);
        e1s1 = new Subtask(4, 3, "e1s1", "e1s1d", TaskStatus.DONE);
        taskManager.updateSubtask(e1s1);
        e1s2 = new Subtask(5, 3, "e1s2", "e1s2d", TaskStatus.IN_PROGRESS);
        taskManager.updateSubtask(e1s2);
        e2s1 = new Subtask(7, 6, "e2s1", "e2s1d", TaskStatus.DONE);
        taskManager.updateSubtask(e2s1);
        System.out.println("\nTasks:\n" + taskManager.getAllTasks());
        System.out.println("\nEpics:\n" + taskManager.getAllEpics());
        System.out.println("\nSubtasks:\n" + taskManager.getAllSubtasks());
        System.out.println("\nUpdate e2");
        e2 = new Epic(6, "e2new", "e2newd");
        taskManager.updateEpic(e2);
        System.out.println(taskManager.getAllEpics());
        System.out.println("\nDelete t1 and e1");
        taskManager.deleteTaskById(1);
        taskManager.deleteEpicById(6);
        System.out.println("\nTasks:\n" + taskManager.getAllTasks());
        System.out.println("\nEpics:\n" + taskManager.getAllEpics());
        System.out.println("\nSubtasks:\n" + taskManager.getAllSubtasks());

    }
}
