package service;

import model.Node;
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
    public void add(Task task) {
        if (task != null) {
            Node existingNode = historyNodesMap.get(task.getId());
            if (existingNode != null) {
                removeNode(existingNode);
            }
            linkLast(task);
        }
    }
    
    @Override
    public void remove(int taskId) {
        Node nodeToRemove = historyNodesMap.get(taskId);
        if (nodeToRemove != null) {
            removeNode(nodeToRemove);
        }
    }
    
    @Override
    public List<Task> getHistory() {
        List<Task> history = new ArrayList<>();
        Node current = head;
        while (current != null) {
            history.add(current.getTask());
            current = current.getNext();
        }
        return history;
    }
    
    public void linkLast(Task task) {
        final Node t = tail;
        final Node newNode = new Node(task);
        tail = newNode;
        if (t == null) {
            head = newNode;
        } else {
            t.setNext(newNode);
            newNode.setPrev(t);
        }
        historyNodesMap.put(task.getId(), newNode);
    }
    
    public void removeNode(Node node) {
        if (node.getPrev() == null) {
            head = node.getNext();
        } else {
            node.getPrev().setNext(node.getNext());
        }
        if (node.getNext() == null) {
            tail = node.getPrev();
        } else {
            node.getNext().setPrev(node.getPrev());
        }
        historyNodesMap.remove(node.getTask().getId());
    }
}
