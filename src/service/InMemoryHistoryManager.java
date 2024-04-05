package service;

import model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private final List<Task> history;
    private static final int MAX_HISTORY_SIZE = 10;

    public InMemoryHistoryManager() {
        this.history = new ArrayList<>();
    }

    @Override public void add(Task task) {
        if (history.size() == MAX_HISTORY_SIZE) {
            history.remove(0);
        }
        history.add(task);
    }

    @Override public List<Task> getHistory() {
        return history;
    }
}
