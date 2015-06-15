package com.mygdx.game.Dungeon.DungeonTiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Player.PlayerCharacterEntity;
import com.mygdx.game.ResourceLoader;

public class StairsDownDungeonTile extends FloorDungeonTile {

    public StairsDownDungeonTile(GridPoint2 pos, Dungeon dungeon) {
        super(pos, dungeon);
    }

    @Override
    public TextureRegion getTileTexture() {
        return ResourceLoader.stairsDown;
    }

    @Override
    public void onStep(){
        Dungeon.setActiveDungeon(Dungeon.getActiveDungeon().getFloorBelow());
        PlayerCharacterEntity.getInstance().placeAtStairsUp();
    }
}
