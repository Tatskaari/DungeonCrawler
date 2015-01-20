package com.mygdx.game.UserInterface;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Inventory.ItemContextAction;

class ContextMenuLabel extends Label {
    public ContextMenuLabel(final ItemContextAction action, Skin skin) {
        super(action.getActionPrompt(), skin);
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.onClick();
            }
        });
    }
}
