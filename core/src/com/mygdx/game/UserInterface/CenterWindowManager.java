package com.mygdx.game.UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

class CenterWindowManager {
    private CenterScreenWindow activeWindow;
    private InputProcessor gameInputProcessor;
    private final InputProcessor UIInputProcessor;

    protected CenterWindowManager(InputProcessor UIInputProcessor){
        this.UIInputProcessor = UIInputProcessor;
    }

    public void toggleActiveWindow(CenterScreenWindow window){
        if (activeWindow != null){
            if (activeWindow == window){
                activeWindow.setVisible(false);
                Gdx.input.setInputProcessor(gameInputProcessor);
                activeWindow = null;
            }
            else {
                activeWindow.setVisible(false);
                window.setVisible(true);

                activeWindow = window;
            }
        }
        else {
            window.setVisible(true);

            gameInputProcessor = Gdx.input.getInputProcessor();
            Gdx.input.setInputProcessor(UIInputProcessor);

            activeWindow = window;
        }
    }
}
