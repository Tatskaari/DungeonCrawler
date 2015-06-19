package com.mygdx.game.Renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonTile;
import com.mygdx.game.Characters.CharacterEntity;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;
import com.mygdx.game.ResourceLoader;

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

    private void renderTiles(float delta, SpriteBatch batch, TileRenderer tileRenderer){
        for (int i = -1; i < dungeon.getMapWidth()+1; i++){
            for (int j = -1; j < dungeon.getMapHeight()+1; j++){
                DungeonTile tile = dungeon.getDungeonTile(i, j);
                tileRenderer.renderTile(tile, batch, i, j);
            }
        }
    }

    @Override
    public void render(float delta, SpriteBatch batch){
        renderTiles(delta, batch, this::renderTile);
        for (CharacterEntity characterEntity : dungeon.monsters){
            characterEntity.getRenderer().render(delta,batch);
        }
    }

    @Override
    public void devRender(float delta, SpriteBatch batch){
        renderTiles(delta, batch, this::devRenderTile);
        for (CharacterEntity characterEntity : Dungeon.getActiveDungeon().monsters){
            characterEntity.getRenderer().devRender(delta, batch);
        }
    }

    public void mapRender(float delta, SpriteBatch batch) {
        renderTiles(delta, batch, this::mapRenderTile);
    }

    private void renderTile(DungeonTile tile, SpriteBatch batch, int x, int y) {
        if (!tile.isEmpty()){
            float colourVal = tile.getVisibilityLevel();
            batch.setColor(colourVal, colourVal, colourVal, colourVal);
            drawDungeonTile(tile, batch, x, y);
            if (tile.isVisible()){
                drawTileItems(tile, batch, x, y);
            }
            batch.setColor(1,1,1,1);
        }
    }

    private void devRenderTile(DungeonTile tile, SpriteBatch batch, int x, int y) {
        if (!tile.isEmpty()){
            drawDungeonTile(tile, batch, x, y);
            drawTileItems(tile, batch, x, y);
        }
    }

    private void mapRenderTile(DungeonTile tile, SpriteBatch batch, int x, int y) {
        if (!tile.isEmpty() && tile.isDiscovered()){
            drawDungeonTile(tile, batch, x, y);
        }
    }

    private void drawDungeonTile(DungeonTile tile, SpriteBatch batch,  int x, int y){
        batch.draw(tile.getTileTexture(), x * ResourceLoader.getTileSize(), y * ResourceLoader.getTileSize());
    }

    private void drawTileItems(DungeonTile tile, SpriteBatch batch, int x, int y){
        for (InventoryItem item : tile.getItems()){
            TextureRegion textureRegion = ResourceLoader.itemTextureAtlas.findRegion(item.getTextureName());
            batch.draw(textureRegion, x * tileSize, y * tileSize);
        }
    }

}
