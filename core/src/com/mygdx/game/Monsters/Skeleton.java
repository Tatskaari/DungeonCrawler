package com.mygdx.game.Monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Behaviors.Behavior;
import com.mygdx.game.Behaviors.SkeletonWanderBehavior;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Renderers.MonsterRenderer;
import com.mygdx.game.Renderers.Renderer;
import com.mygdx.game.ResourceLoader;
import com.mygdx.game.Tokens.DamageToken;

public class Skeleton implements Monster{
    private GridPoint2 position;
    private int health;
    private int maxHealth = 5;
    private Behavior behavior;
    private int minDamage = 0;
    private int maxDamage = 3;

    public Renderer renderer;

    public Skeleton(GridPoint2 position){
        renderer = new MonsterRenderer(this);
        this.position = position;
        behavior = new SkeletonWanderBehavior(this);
        health = maxHealth;
    }

    @Override
    public void beAttacked(int damage) {
        DamageToken damageToken = new DamageToken(damage, position);
        GameHandler.tokens.addToken(damageToken);
        health-= damage;
    }

    @Override
    public boolean isDead() {
        return health <= 0;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public Texture getTexture() {
        return ResourceLoader.skeleton;
    }

    @Override
    public GridPoint2 getPosition() {
        return new GridPoint2(position);
    }

    @Override
    public Renderer getRenderer() {
        return renderer;
    }

    @Override
    public boolean moveTo(GridPoint2 position) {
        if(GameHandler.dungeon.isTilePassable(position)){
            this.position.set(position);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void attack(Monster monster) {
        int damage = MathUtils.random(minDamage, maxDamage);
        monster.beAttacked(damage);
    }

    @Override
    public void act() {
        behavior = behavior.act();
    }
}
