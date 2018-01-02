package com.mygdx.game.InputHandlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.mygdx.game.EventHandlers.Event;
import com.mygdx.game.EventHandlers.EventHandler;
import com.mygdx.game.EventHandlers.EventType;
import com.mygdx.game.GameHandler;

public class GameInputHandler extends InputAdapter {
    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.T){
            EventHandler.getInstance().triggerEvent(new Event(EventType.STEP_TURN));
            return true;
        }

        return false;
    }
}
