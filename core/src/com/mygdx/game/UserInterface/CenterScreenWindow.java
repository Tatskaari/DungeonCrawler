package com.mygdx.game.UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public abstract class CenterScreenWindow extends Window {
    private static CenterScreenWindow activeWindow;

    private final TextButton closeButton;
    private final Skin skin;

    public CenterScreenWindow(String title, Skin skin) {
        super(title, skin);
        this.skin = skin;

        closeButton = new TextButton("X", skin);

        closeButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        setVisible(false);
                    }
                }
        );

        getButtonTable().add(closeButton).height(getPadTop());

        positionAndSize();

        setVisible(false);
        setMovable(false);
    }

    public Skin getSkin(){
        return skin;
    }

    @Override
    public void setVisible(boolean visible) {
        //Stage can be null if the window has not been added to the stage yet
        if (!visible && getStage() != null){
            for (Actor actor : getStage().getActors()){
                if (actor instanceof ContextMenuActor){
                    actor.remove();
                }
            }
        }
        super.setVisible(visible);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        positionAndSize();
    }

    private void positionAndSize(){
        int screenHeight = Gdx.graphics.getHeight();

        float size = 0.8f*screenHeight;
        float y = 0.1f*screenHeight;
        float x = (Gdx.graphics.getWidth() - size)/2;

        setPosition(x,y);
        setSize(size, size);
    }

    public static void toggleActiveWindow(CenterScreenWindow window){
        if (activeWindow == null){
            window.setVisible(true);
            activeWindow = window;
        }
        else if (activeWindow == window){
            window.setVisible(false);
            activeWindow = null;
        }
        else {
            window.setVisible(true);
            activeWindow.setVisible(false);
            activeWindow = window;
        }


    }
}
