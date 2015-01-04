package com.mygdx.game.Dungeon.DungeonTiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.GameHandler;
import com.mygdx.game.ResourceLoader;

public class StairsUpDungeonTile extends FloorDungeonTile{
    public StairsUpDungeonTile(GridPoint2 pos) {
        super(pos);
    }

    @Override
    public Texture getTileTexture() {
        return ResourceLoader.stairUp;
    }

    @Override
    public void onStep(){
        Dungeon floorAbove = GameHandler.dungeon.getFloorAbove();
        if (floorAbove != null){
            GameHandler.dungeon = floorAbove;
            GameHandler.player.placeAtStairsDown();
        }
    }
}
