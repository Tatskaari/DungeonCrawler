package com.mygdx.game.Renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Characters.CharacterEntity;

public class HealthBarRenderer extends Renderer {
    private int tileSize;
    private ShapeRenderer shapeRenderer;

    private Rectangle redRect;
    private Rectangle greenRect;

    private NonPlayerCharacterEntity characterEntity;


    public HealthBarRenderer(NonPlayerCharacterEntity characterEntity){
        tileSize = GameHandler.dungeon.getTileSize();
        shapeRenderer = new ShapeRenderer();

        redRect = new Rectangle();
        redRect.setHeight(2);
        redRect.setWidth(tileSize);

        greenRect = new Rectangle();
        greenRect.setHeight(2);

        this.characterEntity = characterEntity;
    }

    @Override
    public void render(float delta, SpriteBatch batch) {
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(batch.getTransformMatrix());

        Vector2 monsterPos = new Vector2(characterEntity.getPosition().x * tileSize, characterEntity.getPosition().y * tileSize);

        redRect.setPosition(monsterPos);
        greenRect.setPosition(monsterPos);

        greenRect.setWidth(((float) characterEntity.getHealth() / (float) characterEntity.getMaxHealth()) * tileSize);

        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.rect(redRect.x, redRect.y, redRect.width, redRect.height);

        shapeRenderer.setColor(0, 1, 0, 1);
        shapeRenderer.rect(greenRect.x, greenRect.y, greenRect.width, greenRect.height);
        shapeRenderer.end();

        batch.begin();
    }
}
