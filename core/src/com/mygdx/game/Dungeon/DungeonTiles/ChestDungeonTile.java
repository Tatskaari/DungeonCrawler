package com.mygdx.game.Dungeon.DungeonTiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.ResourceLoader;
import com.mygdx.game.SpawnPools.ItemSpawnPools.ItemSpawnPool;
import com.mygdx.game.SpawnPools.ItemSpawnPools.T2ItemSpawnPool;

public class ChestDungeonTile extends FloorDungeonTile {
    private boolean isOpen = false;
    ItemSpawnPool spawnPool = new T2ItemSpawnPool();

    public ChestDungeonTile(GridPoint2 pos, Dungeon dungeon) {
        super(pos, dungeon);
        spawnPool.initialisePool(dungeon);
    }

    @Override
    public TextureRegion getTileTexture() {
        return ResourceLoader.getResTextureRegion(isOpen ? "chest-open" : "chest");
    }

    @Override
    public void onStep() {
        if (!isOpen){
          isOpen = true;
          addItem(spawnPool.getNewInstance());
        }
    }
}
