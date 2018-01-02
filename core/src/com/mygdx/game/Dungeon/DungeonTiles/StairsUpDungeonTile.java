package com.mygdx.game.Dungeon.DungeonTiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.EventHandlers.Event;
import com.mygdx.game.EventHandlers.EventHandler;
import com.mygdx.game.EventHandlers.EventType;
import com.mygdx.game.ResourceLoader;

public class StairsUpDungeonTile extends FloorDungeonTile{
    public StairsUpDungeonTile(GridPoint2 pos, Dungeon dungeon) {
        super(pos, dungeon);
    }

    @Override
    public TextureRegion getTileTexture() {
        return ResourceLoader.getResTextureRegion("stairs-up");
    }

    @Override
    public void onStep(){
        EventHandler.getInstance().triggerEvent(new Event(EventType.DUNGEON_ASCEND));
    }
}
