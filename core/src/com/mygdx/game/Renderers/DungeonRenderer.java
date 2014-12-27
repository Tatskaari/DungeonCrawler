package com.mygdx.game.Renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonTiles.DungeonTile;
import com.mygdx.game.ResourceLoader;

/**
 * Created by jony1710 on 27/12/2014.
 */
public class DungeonRenderer implements Renderer{
    private Dungeon dungeon;
    private int tileSize;

    public DungeonRenderer(Dungeon dungeon){
        this.dungeon = dungeon;
        tileSize = dungeon.getTileSize();
    }

    public void render(float delta, SpriteBatch batch){
        for (int i = -1; i < dungeon.getMapWidth()+1; i++){
            for (int j = -1; j < dungeon.getMapHeight()+1; j++){
                DungeonTile tile = dungeon.getDungeonTile(i, j);
                renderTile(tile, batch, i, j);
            }
        }
    }

    protected void renderTile(DungeonTile tile, SpriteBatch batch, int x, int y) {
        if (!tile.isEmpty()){
            float colourVal = tile.checkVisibility();
            batch.setColor(colourVal, colourVal, colourVal, colourVal);
            batch.draw(tile.getTileTexture(), x * tileSize, y * tileSize);
            batch.setColor(1,1,1,1);
        }
    }

}
