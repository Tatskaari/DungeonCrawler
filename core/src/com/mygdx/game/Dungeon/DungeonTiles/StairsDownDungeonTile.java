package com.mygdx.game.Dungeon.DungeonTiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Player.PlayerCharacterEntity;
import com.mygdx.game.ResourceLoader;

public class StairsDownDungeonTile extends FloorDungeonTile {

    public StairsDownDungeonTile(GridPoint2 pos) {
        super(pos);
    }

    @Override
    public TextureRegion getTileTexture() {
        return ResourceLoader.stairsDown;
    }

    @Override
    public void onStep(){
        GameHandler.dungeon = GameHandler.dungeon.getFloorBelow();
        PlayerCharacterEntity.getInstance().placeAtStairsUp();
    }
}
