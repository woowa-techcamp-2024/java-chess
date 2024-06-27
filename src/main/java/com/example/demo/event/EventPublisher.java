package com.example.demo.event;

import java.util.LinkedList;
import java.util.Queue;

public class EventPublisher {

    public static final EventPublisher INSTANCE = new EventPublisher();
    public Queue<Event> events = new LinkedList<>();

    public void publish(Event event) {
        events.add(event);
    }

    public boolean isEmpty(){
        return events.isEmpty();
    }

    public Event consume(){
        return events.poll();
    }

    public void clear(){
        events.clear();
    }
}
