import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;
import service.TaskManager;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task t1 = new Task("t1", "t1d"); // id 1
        taskManager.createTask(t1);
        Task t2 = new Task("t2", "t2d"); // id 2
        taskManager.createTask(t2);
        Epic e1 = new Epic("e1", "e1d"); // id 3
        taskManager.createEpic(e1);
        Subtask e1s1 = new Subtask(3, "e1s1", "e1s1d"); // id 4
        taskManager.createSubtask(e1s1);
        Subtask e1s2 = new Subtask(3, "e1s2", "e1s2d"); // id 5
        taskManager.createSubtask(e1s2);
        Epic e2 = new Epic("e2", "e2d"); // id 6
        taskManager.createEpic(e2);
        Subtask e2s1 = new Subtask(6, "e2s1", "e2s1d"); // id 7
        taskManager.createSubtask(e2s1);
        System.out.println("\nTasks:\n" + taskManager.getAllTasks());
        System.out.println("\nEpics:\n" + taskManager.getAllEpics());
        System.out.println("\nSubtasks:\n" + taskManager.getAllSubtasks());
        System.out.println("\nChanging... Voila!");
        t1 = new Task("t1", "t1d", TaskStatus.DONE);
        taskManager.updateTask(1, t1);
        t2 = new Task("t2", "t2d", TaskStatus.IN_PROGRESS);
        taskManager.updateTask(2, t2);
        e1s1 = new Subtask(3, "e1s1", "e1s1d", TaskStatus.DONE);
        taskManager.updateSubtask(4, e1s1);
        e1s2 = new Subtask(3, "e1s2", "e1s2d", TaskStatus.IN_PROGRESS);
        taskManager.updateSubtask(5, e1s2);
        e2s1 = new Subtask(6, "e2s1", "e2s1d", TaskStatus.DONE);
        taskManager.updateSubtask(7, e2s1);
        System.out.println("\nTasks:\n" + taskManager.getAllTasks());
        System.out.println("\nEpics:\n" + taskManager.getAllEpics());
        System.out.println("\nSubtasks:\n" + taskManager.getAllSubtasks());
        System.out.println("\nRemoving... Voila!");
        taskManager.deleteTaskByID(1);
        taskManager.deleteEpicByID(6);
        System.out.println("\nTasks:\n" + taskManager.getAllTasks());
        System.out.println("\nEpics:\n" + taskManager.getAllEpics());
        System.out.println("\nSubtasks:\n" + taskManager.getAllSubtasks());

    }
}
