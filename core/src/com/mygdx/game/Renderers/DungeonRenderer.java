package com.mygdx.game.Renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonTile;
import com.mygdx.game.Characters.CharacterEntity;
import com.mygdx.game.ResourceLoader;

public class DungeonRenderer extends Renderer{
    private Dungeon dungeon;
    private int tileSize;

    public DungeonRenderer(Dungeon dungeon){
        this.dungeon = dungeon;
        tileSize = ResourceLoader.getTileSize();
    }

    public void render(float delta, SpriteBatch batch){
        for (int i = -1; i < dungeon.getMapWidth()+1; i++){
            for (int j = -1; j < dungeon.getMapHeight()+1; j++){
                DungeonTile tile = dungeon.getDungeonTile(i, j);
                renderTile(tile, batch, i, j);
            }
        }

        for (CharacterEntity characterEntity : dungeon.monsters){
            characterEntity.getRenderer().render(delta,batch);
        }
    }
    @Override
    public void devRender(float delta, SpriteBatch batch){
        for (int i = -1; i < dungeon.getMapWidth()+1; i++){
            for (int j = -1; j < dungeon.getMapHeight()+1; j++){
                DungeonTile tile = dungeon.getDungeonTile(i, j);
                devRenderTile(tile, batch, i, j);
            }
        }

        for (CharacterEntity characterEntity : dungeon.monsters){
            characterEntity.getRenderer().devRender(delta, batch);
        }
    }

    protected void renderTile(DungeonTile tile, SpriteBatch batch, int x, int y) {
        if (!tile.isEmpty()){
            float colourVal = tile.getVisibilityLevel();
            batch.setColor(colourVal, colourVal, colourVal, colourVal);
            batch.draw(tile.getTileTexture(), x * tileSize, y * tileSize);
            batch.setColor(1,1,1,1);
        }
    }

    protected void devRenderTile(DungeonTile tile, SpriteBatch batch, int x, int y) {
        if (!tile.isEmpty()){
            batch.draw(tile.getTileTexture(), x * ResourceLoader.getTileSize(), y * ResourceLoader.getTileSize());
        }
    }

}
