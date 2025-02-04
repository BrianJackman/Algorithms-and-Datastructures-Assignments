// Undo/Redo method for double linked list project
// By: Brian Jackman
// 2025/02/03

package DoubleLinkedList;

/**
 * Implement an application that supports undo/redo functionality. Use a linked list to maintain a sequence of states.
 * Each state change is stored as a node in the list, allowing for easy navigation
 * 1<>2<>3<>4<>5
 */

public class UndoRedoManager<T> {
    private class Node {
        private T state;
        private Node prev;
        private Node next;

        private Node(T state) {
            this.state = state;
        }
    }

    private Node currentState;

    // Undo operation
    public T undo() {
        if (currentState == null || currentState.prev == null) {
            System.out.println("No previous state to undo.");
            return null;
        }
        currentState = currentState.prev;
        return currentState.state;
    }

    // Perform an operation
    public void addState(T newState) {
        Node newNode = new Node(newState);
        if (currentState != null) {
            newNode.prev = currentState;
            currentState.next = newNode;
        }
        currentState = newNode;
    }

    // Redo operation
    public T redo() {
        if (currentState == null || currentState.next == null) {
            System.out.println("No next state to redo.");
            return null;
        }
        currentState = currentState.next;
        return currentState.state;
    }
