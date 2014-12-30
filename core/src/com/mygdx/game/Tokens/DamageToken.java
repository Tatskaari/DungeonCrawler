package com.mygdx.game.Tokens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameHandler;
import com.mygdx.game.ResourceLoader;

public class DamageToken implements Token{
    private Vector2 velocity = new Vector2();
    private Vector2 position = new Vector2();
    private float duration;
    private float age;
    private final int damage;

    public DamageToken(int damage, GridPoint2 position){
        int tileSize = GameHandler.dungeon.getTileSize();

        this.velocity = new Vector2(MathUtils.random(50.0f) - 25, 50);;
        this.position.x = (position.x+1) * tileSize - tileSize/2;
        this.position.y = (position.y+1) * tileSize - tileSize/2;
        this.duration = 0.3f;
        this.age = 0;
        this.damage = damage;
    }

    @Override
    public void render(float delta, SpriteBatch batch){
        age+=delta;
        if (age <= duration){
            position.x += velocity.x * delta;
            position.y += velocity.y * delta;
            ResourceLoader.damageFont.draw(batch, "" + damage, position.x, position.y);
        }
    }

    @Override
    public boolean isAlive(){
        return age <= duration;
    }
}
