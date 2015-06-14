package com.mygdx.game.Dungeon.DungeonTiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Dungeon.DungeonTile;
import com.mygdx.game.ResourceLoader;

public class DoorDungeonTile extends DungeonTile {
    private final TextureRegion texture;
    private final boolean isHorizontal;

    public DoorDungeonTile(GridPoint2 pos, boolean isHorizontal) {
        super(pos);
        this.isHorizontal = isHorizontal;

        if(isHorizontal){
            texture = ResourceLoader.doorHorizontal;
        }else{
            texture = ResourceLoader.doorVertical;
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
