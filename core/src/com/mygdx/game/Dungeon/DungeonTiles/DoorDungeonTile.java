package com.mygdx.game.Dungeon.DungeonTiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonTile;
import com.mygdx.game.ResourceLoader;

public class DoorDungeonTile extends DungeonTile {
    private final TextureRegion texture;

    public DoorDungeonTile(GridPoint2 pos, Dungeon dungeon, boolean isHorizontal) {
        super(pos, dungeon);

        if(isHorizontal){
            texture = ResourceLoader.getResTextureRegion("door-horizontal");
        }else{
            texture = ResourceLoader.getResTextureRegion("door-vertical");
        }
    }

    @Override
    public float getCorridorPlacingCost() {
        return 9000;
    }

    @Override
    public boolean isVisionObstructing() {
        return true;
    }

    @Override
    public TextureRegion getTileTexture() {
        return texture;
    }

}
