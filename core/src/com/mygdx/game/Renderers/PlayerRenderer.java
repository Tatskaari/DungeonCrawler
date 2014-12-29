package com.mygdx.game.Renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Player.PlayerCharacter;
import com.mygdx.game.ResourceLoader;

public class PlayerRenderer extends Renderer {
    private PlayerCharacter player;
    private int tileSize;

    private HealthBarRenderer healthBarRenderer;

    public PlayerRenderer(PlayerCharacter player){
        this.player = player;
        tileSize = player.getTileSize();
        healthBarRenderer = new HealthBarRenderer(player);
    }
    @Override
    public void render(float delta, SpriteBatch batch) {
        GridPoint2 position = player.getPosition();
        batch.draw(ResourceLoader.player, position.x * tileSize, position.y * tileSize);
        healthBarRenderer.render(delta, batch);
    }
}
