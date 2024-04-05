package service;

public class Managers {
//    private static TaskManager InMemoryTaskManager;
//    private static HistoryManager InMemoryHistoryManager;
//    public static TaskManager getDefault() {
//        if (InMemoryTaskManager == null) {
//            return new InMemoryTaskManager();
//        }
//        return InMemoryTaskManager;
//    }
//    public static HistoryManager getDefaultHistory() {
//        if (InMemoryHistoryManager == null) {
//            return new InMemoryHistoryManager();
//        }
//        return InMemoryHistoryManager;
//    }

    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
