package com.mygdx.game.EventHandlers;

public class ContextMessageEvent extends Event {
    private String contextMessage;

    public ContextMessageEvent(String contextMessage){
        super(EventType.SET_CONTEXT_MESSAGE);
        this.contextMessage = contextMessage;
    }

    public String getContextMessage(){
        return contextMessage;
    }
}
