package com.mygdx.game.Renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonTile;
import com.mygdx.game.Characters.CharacterEntity;
import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;
import com.mygdx.game.ResourceLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DungeonRenderer extends Renderer{
    private final Dungeon dungeon;
    private final int tileSize;

    @FunctionalInterface
    private interface TileRenderer{
        public void renderTile(DungeonTile tile, SpriteBatch batch, int x, int y);
    }

    public DungeonRenderer(Dungeon dungeon){
        this.dungeon = dungeon;
        tileSize = ResourceLoader.getTileSize();
    }

    private void render(float delta, SpriteBatch batch, TileRenderer tileRenderer){
        for (int i = -1; i < dungeon.getMapWidth()+1; i++){
            for (int j = -1; j < dungeon.getMapHeight()+1; j++){
                DungeonTile tile = dungeon.getDungeonTile(i, j);
                tileRenderer.renderTile(tile, batch, i, j);
            }
        }

        for (CharacterEntity characterEntity : dungeon.monsters){
            characterEntity.getRenderer().render(delta,batch);
        }
    }

    public void render(float delta, SpriteBatch batch){
        render(delta, batch, this::renderTile);
    }

    @Override
    public void devRender(float delta, SpriteBatch batch){
        render(delta, batch, this::devRenderTile);
    }

    public void mapRender(float delta, SpriteBatch batch) {
        render(delta, batch, this::mapRenderTile);
    }

    void renderTile(DungeonTile tile, SpriteBatch batch, int x, int y) {
        if (!tile.isEmpty()){
            float colourVal = tile.getVisibilityLevel();
            batch.setColor(colourVal, colourVal, colourVal, colourVal);
            batch.draw(tile.getTileTexture(), x * tileSize, y * tileSize);
            if (tile.isVisible()){
                for (InventoryItem item : tile.getItems()){
                    TextureRegion textureRegion = ResourceLoader.itemTextureAtlas.findRegion(item.getTextureName());
                    batch.draw(textureRegion, x * tileSize, y * tileSize);
                }
            }
            batch.setColor(1,1,1,1);
        }
    }

    void devRenderTile(DungeonTile tile, SpriteBatch batch, int x, int y) {
        if (!tile.isEmpty()){
            batch.draw(tile.getTileTexture(), x * ResourceLoader.getTileSize(), y * ResourceLoader.getTileSize());
            for (InventoryItem item : tile.getItems()){
                TextureRegion textureRegion = ResourceLoader.itemTextureAtlas.findRegion(item.getTextureName());
                batch.draw(textureRegion, x * tileSize, y * tileSize);
            }
        }
    }

    void mapRenderTile(DungeonTile tile, SpriteBatch batch, int x, int y) {
        if (!tile.isEmpty() && tile.isDiscovered()){
            batch.draw(tile.getTileTexture(), x * ResourceLoader.getTileSize(), y * ResourceLoader.getTileSize());
        }
    }

}
