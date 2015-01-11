package com.mygdx.game.Tokens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.ResourceLoader;

public class DamageToken extends BasicToken{
    private final int damage;

    public DamageToken(int damage, GridPoint2 position){
        super(position);

        this.damage = damage;
    }

    @Override
    public void render(float delta, SpriteBatch batch){
        age+=delta;
        if (age <= duration){
            updatePosition(delta);
            ResourceLoader.damageFont.draw(batch, "" + damage, position.x, position.y);
        }
    }
}
