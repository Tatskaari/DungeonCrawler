package com.mygdx.game.Tokens;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameHandler;

public abstract class BasicToken implements Token{
    protected Vector2 velocity = new Vector2();
    protected Vector2 position = new Vector2();
    protected float duration;
    protected float age;

    public BasicToken(GridPoint2 position){
        int tileSize = GameHandler.dungeon.getTileSize();

        this.velocity = new Vector2(MathUtils.random(50.0f) - 25, 50);;
        this.position.x = (position.x+1) * tileSize - tileSize/2;
        this.position.y = (position.y+1) * tileSize - tileSize/2;
        this.duration = 0.3f;
        this.age = 0;
    }
    @Override
    public boolean isAlive(){
        return age <= duration;
    }

    protected void updatePosition(float delta){
        position.x += velocity.x * delta;
        position.y += velocity.y * delta;
    }
}
