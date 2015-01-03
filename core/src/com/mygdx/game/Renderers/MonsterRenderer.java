package com.mygdx.game.Renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Characters.CharacterEntity;

public class MonsterRenderer extends Renderer{
    private NonPlayerCharacterEntity characterEntity;
    private int tileSize;
    private HealthBarRenderer healthBarRenderer;

    public MonsterRenderer(NonPlayerCharacterEntity characterEntity){
        this.characterEntity = characterEntity;
        tileSize = GameHandler.dungeon.getTileSize();
        healthBarRenderer = new HealthBarRenderer(characterEntity);
    }

    @Override
    public void render(float delta, SpriteBatch batch) {
        if (!characterEntity.isDead()){
            GridPoint2 pos = characterEntity.getPosition();
            if (GameHandler.dungeon.getDungeonTile(pos).isVisible()){
                batch.draw(characterEntity.getTexture(), characterEntity.getPosition().x * tileSize, characterEntity.getPosition().y * tileSize);
                healthBarRenderer.render(delta, batch);
            }
        }
    }
    @Override
    public void devRender(float delta, SpriteBatch batch){
        if (!characterEntity.isDead()){
            GridPoint2 pos = characterEntity.getPosition();
            batch.draw(characterEntity.getTexture(), characterEntity.getPosition().x * tileSize, characterEntity.getPosition().y * tileSize);
        }
    }
}
