package com.mygdx.game.Renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Monsters.Monster;

public class MonsterRenderer implements Renderer{
    private Monster monster;
    private int tileSize;

    public MonsterRenderer(Monster monster){
        this.monster = monster;
        tileSize = GameHandler.dungeon.getTileSize();
    }

    @Override
    public void render(float delta, SpriteBatch batch) {
        GridPoint2 pos = monster.getPosition();
        if (GameHandler.dungeon.getDungeonTile(pos).isVisible()){
            batch.draw(monster.getTexture(), monster.getPosition().x * tileSize, monster.getPosition().y * tileSize);
        }
    }
}
