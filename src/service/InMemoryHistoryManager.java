package service;

import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {

    private final Map<Integer, Node> historyNodesMap;
    private Node head;
    private Node tail;

    public InMemoryHistoryManager() {
        this.historyNodesMap = new HashMap<>();
    }

    @Override
    public List<Task> getHistory() {
        return prepareHistory();
    }

    @Override
    public void add(Task task) {
        Node existingNode = historyNodesMap.get(task.getId());
        if (existingNode != null) {
            removeNode(existingNode);
        }
        linkLast(task);
    }

    @Override
    public void remove(int taskId) {
        removeNode(historyNodesMap.get(taskId));
    }

    private void linkLast(Task task) {
        final Node l = tail;
        final Node newNode = new Node(task);
        tail = newNode;
        if (l == null) {
            head = newNode;
        } else {
            l.next = newNode;
            newNode.prev = l;
        }
        historyNodesMap.put(task.getId(), newNode);
    }

    private void removeNode(Node node) {
        if (node != null) {
            if (node.prev == null) {
                head = node.next;
            } else {
                node.prev.next = node.next;
            }
            if (node.next == null) {
                tail = node.prev;
            } else {
                node.next.prev = node.prev;
            }
            historyNodesMap.remove(node.task.getId());
        }
    }

    private List<Task> prepareHistory() {
        List<Task> history = new ArrayList<>();
        Node current = head;
        while (current != null) {
            history.add(current.task);
            current = current.next;
        }
        return history;
    }

    private static class Node {

        Task task;
        Node prev;
        Node next;

        public Node(Task task) {
            this.task = task;
        }
    }
}
