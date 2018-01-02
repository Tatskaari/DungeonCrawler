package com.mygdx.game.Renderers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Characters.MonsterCharacterEntity;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.ResourceLoader;

public class MonsterRenderer extends Renderer{
    private final MonsterCharacterEntity characterEntity;
    private final int tileSize;
    private final HealthbarRenderer healthBarRenderer;

    public MonsterRenderer(MonsterCharacterEntity characterEntity){
        this.characterEntity = characterEntity;
        tileSize = ResourceLoader.getTileSize();
        healthBarRenderer = new HealthbarRenderer(characterEntity);
    }

    @Override
    public void render(float delta, SpriteBatch batch) {
        if (characterEntity.isAlive()){
            GridPoint2 pos = characterEntity.getPosition();
            if (Dungeon.getActiveDungeon().getDungeonTile(pos).isVisible()){
                float visibility = Dungeon.getActiveDungeon().getDungeonTile(characterEntity.getPosition()).getVisibilityLevel();
                batch.setColor(visibility, visibility, visibility, 1);
                batch.draw(characterEntity.getTexture(), characterEntity.getPosition().x * tileSize, characterEntity.getPosition().y * tileSize);
                healthBarRenderer.render(delta, batch);
            }
            batch.setColor(Color.WHITE);
        }
    }
    @Override
    public void devRender(float delta, SpriteBatch batch){
        if (characterEntity.isAlive()){
            batch.draw(characterEntity.getTexture(), characterEntity.getPosition().x * tileSize, characterEntity.getPosition().y * tileSize);
        }
    }
}
