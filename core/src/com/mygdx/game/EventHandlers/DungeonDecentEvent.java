package com.mygdx.game.EventHandlers;

import com.badlogic.gdx.utils.Array;

public class DungeonDecentEvent implements EventHandler{
    private static final DungeonDecentEvent instance = new DungeonDecentEvent();

    private Array<EventListener> listeners;

    private DungeonDecentEvent(){
        listeners = new Array<>();
    }

    public static DungeonDecentEvent getInstance(){
        return instance;
    }

    @Override
    public void registerEventListener(EventListener eventListener) {
        listeners.add(eventListener);
    }

    @Override
    public void unregisterEventListener(EventListener eventListener) {
        listeners.removeValue(eventListener, true);
    }

    @Override
    public void triggerEvent() {
        for (EventListener listener : listeners){
            listener.handleEvent(this);
        }
    }
}
