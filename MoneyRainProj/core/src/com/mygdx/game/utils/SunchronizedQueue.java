package com.mygdx.game.utils;

import java.util.LinkedList;
import java.util.Queue;

public class SunchronizedQueue<T> {
    private Queue<T> queue;
    public SunchronizedQueue() {
        this.queue = new LinkedList<>();
    }
    public synchronized void add(T item) {
        queue.add(item);
    }
    public synchronized T poll() {
        return queue.poll();
    }
    public synchronized T peek() {
        return queue.peek();
    }
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
    public synchronized int size() {
        return queue.size();
    }

}
