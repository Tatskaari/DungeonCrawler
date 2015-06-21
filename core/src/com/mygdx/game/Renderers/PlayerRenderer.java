package com.mygdx.game.Renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Player.PlayerCharacterEntity;
import com.mygdx.game.ResourceLoader;

public class PlayerRenderer extends Renderer {
    private final PlayerCharacterEntity player;
    private final int tileSize;

    public PlayerRenderer(PlayerCharacterEntity player){
        this.player = player;
        tileSize = ResourceLoader.getTileSize();
    }
    @Override
    public void render(float delta, SpriteBatch batch) {
        GridPoint2 position = player.getPosition();
        batch.draw(ResourceLoader.getResTextureRegion("player"), position.x * tileSize, position.y * tileSize);
    }
}
