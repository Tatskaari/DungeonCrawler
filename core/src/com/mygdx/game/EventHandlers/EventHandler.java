package com.mygdx.game.EventHandlers;

import com.badlogic.gdx.utils.Array;

public class EventHandler{
    private static final EventHandler instance = new EventHandler();

    private Array<EventListener> listeners;

    private EventHandler(){
        listeners = new Array<>();
    }

    public static EventHandler getInstance(){
        return instance;
    }

    public void registerEventListener(EventListener eventListener) {
        listeners.add(eventListener);
    }

    public void unregisterEventListener(EventListener eventListener) {
        listeners.removeValue(eventListener, true);
    }

    public void triggerEvent(Event event) {
        for (EventListener listener : listeners){
            listener.handleEvent(event);
        }
    }
}
