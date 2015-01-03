package com.mygdx.game.Characters;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Behaviors.Behavior;
import com.mygdx.game.Behaviors.DeadBehavior;
import com.mygdx.game.Behaviors.SkeletonAttackPlayerBehavior;
import com.mygdx.game.Behaviors.SkeletonWanderBehavior;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Renderers.MonsterRenderer;
import com.mygdx.game.Renderers.Renderer;
import com.mygdx.game.Tokens.DamageToken;
import com.mygdx.game.Tokens.ExpToken;

public abstract class BasicNonPlayerCharacterEntity implements NonPlayerCharacterEntity {
    private GridPoint2 position;
    private int health;
    private int maxHealth;
    private Behavior behavior;
    private int minDamage;
    private int maxDamage;

    public Renderer renderer;

    public BasicNonPlayerCharacterEntity(GridPoint2 position){
        renderer = new MonsterRenderer(this);
        this.position = position;
        behavior = new SkeletonWanderBehavior(this);
    }

    @Override
    public void act() {
        if (isDead()){
            if (!(behavior instanceof DeadBehavior)){
                behavior = new DeadBehavior(this, 20);
                die();
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
    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
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
    public void attack(CharacterEntity characterEntity) {
        int damage = MathUtils.random(minDamage, maxDamage);
        characterEntity.beAttacked(damage);
    }

    private void die(){
        GameHandler.tokens.addToken(new ExpToken(position, getExperienceValue()));
        GameHandler.player.statsHandler.addExperience(getExperienceValue());
    }

    protected void setDamageRange(int minDamage, int maxDamage){
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }
}
