package com.mygdx.game.Dungeon.DungeonTiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Player.PlayerCharacterEntity;
import com.mygdx.game.ResourceLoader;

public class StairsUpDungeonTile extends FloorDungeonTile{
    public StairsUpDungeonTile(GridPoint2 pos, Dungeon dungeon) {
        super(pos, dungeon);
    }

    @Override
    public TextureRegion getTileTexture() {
        return ResourceLoader.stairsUp;
    }

    @Override
    public void onStep(){
        Dungeon floorAbove = Dungeon.getActiveDungeon().getFloorAbove();
        if (floorAbove != null){
            Dungeon.setActiveDungeon(floorAbove);
            PlayerCharacterEntity.getInstance().placeAtStairsDown();
        }
    }
}
