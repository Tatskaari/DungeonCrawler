package com.mygdx.game.UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;
import com.mygdx.game.Inventory.ItemContextActions.ItemContextAction;

class ContextMenuActor extends Window {
    public ContextMenuActor(Skin skin, InventoryItem item){
        super(item.getItemName(), skin);

        Array<ItemContextAction> actions = item.getItemContextActions();

        for (int i = 0; i < actions.size; i++){
            add(new ContextMenuLabel(actions.get(i), skin));
            row();
        }
        defaults().fill().expand();
        setVisible(false);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                remove();
            }
        });

        pack();
    }

    public void showOnCursor(Stage stage){
        stage.addActor(this);
        setPosition(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY() - getHeight());
        setVisible(true);
    }
}
