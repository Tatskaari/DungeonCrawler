package com.mygdx.game.EventHandlers;

public interface EventHandler {
    public void registerEventListener(EventListener eventListener);
    public void unregisterEventListener(EventListener eventListener);
    public void triggerEvent();

}
