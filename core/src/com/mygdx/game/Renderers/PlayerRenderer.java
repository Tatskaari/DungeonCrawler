package com.mygdx.game.Renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Player.PlayerCharacter;
import com.mygdx.game.ResourceLoader;

public class PlayerRenderer implements Renderer {
    private PlayerCharacter player;
    private int tileSize;
    public PlayerRenderer(PlayerCharacter player){
        this.player = player;
        tileSize = player.getTileSize();
    }
    @Override
    public void render(float delta, SpriteBatch batch) {
        GridPoint2 position = player.getPosition();
        batch.draw(ResourceLoader.player, position.x * tileSize, position.y * tileSize);
    }
}
