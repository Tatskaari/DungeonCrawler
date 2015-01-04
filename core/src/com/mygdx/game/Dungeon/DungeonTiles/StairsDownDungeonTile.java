package com.mygdx.game.Dungeon.DungeonTiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.GameHandler;
import com.mygdx.game.ResourceLoader;

public class StairsDownDungeonTile extends FloorDungeonTile {

    public StairsDownDungeonTile(GridPoint2 pos) {
        super(pos);
    }

    @Override
    public Texture getTileTexture() {
        return ResourceLoader.stairDown;
    }

    @Override
    public void onStep(){
        GameHandler.dungeon = GameHandler.dungeon.getFloorBelow();
        GameHandler.player.placeAtStairsUp();
    }
}
