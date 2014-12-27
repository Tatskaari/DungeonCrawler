package com.mygdx.game.Renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonTile;
import com.mygdx.game.MyGdxGame;
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
        for (int i = 0; i < dungeon.getMapWidth(); i++){
            for (int j = 0; j < dungeon.getMapHeight(); j++){
                DungeonTile tile = dungeon.getDungeonTile(i, j);
                float colourVal = tile.checkVisibility();
                batch.setColor(colourVal, colourVal, colourVal, colourVal);
                if(tile.tileType == DungeonTile.FLOOR || tile.tileType == DungeonTile.CORRIDOR_FLOOR){
                    batch.draw(ResourceLoader.floor, i * tileSize, j * tileSize);
                }
                if (tile.tileType == DungeonTile.WALL || tile.tileType == DungeonTile.CORRIDOR_WALL){
                    batch.draw(ResourceLoader.wall, i * tileSize, j * tileSize);
                }
                if(tile.tileType == DungeonTile.DOOR_HORIZONTAL){
                    batch.draw(ResourceLoader.doorHorizontal, i * tileSize, j * tileSize);
                }
                if(tile.tileType == DungeonTile.DOOR_VERTICAL){
                    batch.draw(ResourceLoader.doorVertical, i * tileSize, j * tileSize);
                }
                batch.setColor(1,1,1,1);
            }
        }
    }

}
