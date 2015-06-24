package com.mygdx.game.UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

abstract class CenterScreenWindow extends Window {
    private final Skin skin;

    CenterScreenWindow(String title, Skin skin, CenterWindowManager centerWindowManager) {
        super(title, skin);
        this.skin = skin;

        final CenterScreenWindow thisWindow = this;

        TextButton closeButton = new TextButton("X", skin);

        closeButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        centerWindowManager.toggleActiveWindow(thisWindow);
                    }
                }
        );

        getButtonTable().add(closeButton).height(getPadTop());

        positionAndSize();

        setVisible(false);
        setMovable(false);
    }

    Skin getSkin(){
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
}
