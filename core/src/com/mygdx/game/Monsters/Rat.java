package com.mygdx.game.Monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Behaviors.*;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Renderers.MonsterRenderer;
import com.mygdx.game.Renderers.Renderer;
import com.mygdx.game.ResourceLoader;
import com.mygdx.game.Tokens.DamageToken;

public class Rat implements Monster {
    private GridPoint2 position;
    private int health;
    private int maxHealth = 5;
    private Behavior behavior;
    private int minDamage = 0;
    private int maxDamage = 3;
    private Renderer renderer;

    public Rat(GridPoint2 position){
        renderer = new MonsterRenderer(this);
        this.position = position;
        behavior = new SkeletonFindPlayerBehavior(this);
        health = maxHealth;
    }

    @Override
    public void act() {
        if (isDead()){
            if (!(behavior instanceof DeadBehavior)){
                behavior = new DeadBehavior(this, 20);
            }
        }
        else if(Behavior.canSeePlayerFrom(getPosition())){
            behavior = new SkeletonAttackPlayerBehavior(this);
        }
        behavior = behavior.act();
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
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public Texture getTexture() {
        return ResourceLoader.rat;
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
}
