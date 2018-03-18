package com.buzz.test.list;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class ReverseList {
    static class Node<T> {
        private T value;
        private Node<T> next;

        public Node() {
        }

        public Node(T date, Node<T> next) {
            this.value = date;
            this.next = next;
        }
    }

    private Node initList() {
        Node<Integer> head = new Node<Integer>(0, null);
        Node cur = head;

        for (int i = 1; i < 10; i++) {
            cur.next = new Node<Integer>(i, null);
            cur = cur.next;
        }
        return head;
    }

    private void printLinkedList(Node head) {
        Node node = head;
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }

    private Node reverseLinkedList(Node head) {
        Node node = head;
        if (head == null || head.next == null) {
            return null;
        }

        Node prevNode = null;
        Node curNode = head;
        Node nextNode = null;

        while (curNode != null) {
            nextNode = curNode.next; //第一个元素
            curNode.next = prevNode;
            prevNode = curNode;
            curNode = nextNode;
        }

        head.next = prevNode;

        return head;
    }

    private Node reverse(Node head) {

        if (head == null || head.next == null) {
            return null;
        }

        Node curNode = head;
        Node nextNode = null;
        Node prevNode = null;
        while (curNode != null) {
            nextNode = curNode.next;
            curNode.next = prevNode;
            prevNode = curNode;
            curNode = nextNode;

        }

        head = prevNode;

        System.out.println("---after reverse--");
        printLinkedList(head);


        return head;
    }

    public static void main(String[] args) {
        ReverseList reverseList = new ReverseList();
        Node head = reverseList.initList();
        reverseList.printLinkedList(head);
        head = reverseList.reverse(head);

        new LinkedList();
        Collections.synchronizedMap(new HashMap<>());
        new Hashtable<>();
        new ConcurrentHashMap<>();
        new String("1");
        Object object = new Object();


    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public static <T> T getWithDCL(Supplier<T> supplier, Supplier<T> factory, Object mutex) {
        T object = supplier.get();
        if (object == null) {
            synchronized (mutex) {
                object = supplier.get();
                if (object == null) {
                    object = factory.get();
                }
            }
        }
        return object;
    }
}
