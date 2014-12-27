package com.mygdx.game.Renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonTiles.DungeonTile;

public class DevDungeonRenderer extends DungeonRenderer{
    private Dungeon dungeon;
    public DevDungeonRenderer(Dungeon dungeon) {
        super(dungeon);
        this.dungeon = dungeon;
    }

    @Override
    protected void renderTile(DungeonTile tile, SpriteBatch batch, int x, int y) {
        if (!tile.isEmpty()){
            batch.draw(tile.getTileTexture(), x * dungeon.getTileSize(), y * dungeon.getTileSize());
        }
    }
}
