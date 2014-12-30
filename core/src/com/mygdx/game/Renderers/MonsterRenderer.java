package com.mygdx.game.Renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Monsters.Monster;

public class MonsterRenderer extends Renderer{
    private Monster monster;
    private int tileSize;
    private HealthBarRenderer healthBarRenderer;

    public MonsterRenderer(Monster monster){
        this.monster = monster;
        tileSize = GameHandler.dungeon.getTileSize();
        healthBarRenderer = new HealthBarRenderer(monster);
    }

    @Override
    public void render(float delta, SpriteBatch batch) {
        if (!monster.isDead()){
            GridPoint2 pos = monster.getPosition();
            if (GameHandler.dungeon.getDungeonTile(pos).isVisible()){
                batch.draw(monster.getTexture(), monster.getPosition().x * tileSize, monster.getPosition().y * tileSize);
                healthBarRenderer.render(delta, batch);
            }
        }
    }
    @Override
    public void devRender(float delta, SpriteBatch batch){
        if (!monster.isDead()){
            GridPoint2 pos = monster.getPosition();
            batch.draw(monster.getTexture(), monster.getPosition().x * tileSize, monster.getPosition().y * tileSize);
        }
    }
}
