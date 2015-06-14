package com.mygdx.game.Characters;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Behaviors.Behavior;
import com.mygdx.game.Behaviors.DeadBehavior;
import com.mygdx.game.Behaviors.GenericAttackPlayerBehavior;
import com.mygdx.game.Behaviors.GenericWanderBehavior;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonUtils;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Player.PlayerCharacterEntity;
import com.mygdx.game.Renderers.MonsterRenderer;
import com.mygdx.game.Renderers.Renderer;
import com.mygdx.game.Tokens.DamageToken;
import com.mygdx.game.Tokens.Tokens;

public abstract class BasicNonPlayerCharacterEntity implements NonPlayerCharacterEntity {
    private final GridPoint2 position;
    private int health;
    private int maxHealth;
    private Behavior behavior;
    private int minDamage;
    private int maxDamage;
    private final Dungeon dungeon;

    private final Renderer renderer;

    BasicNonPlayerCharacterEntity(GridPoint2 position, Dungeon dungeon){
        this.dungeon = dungeon;
        renderer = new MonsterRenderer(this);
        this.position = position;
        behavior = new GenericWanderBehavior(this);
    }

    @Override
    public void act() {
        if (isDead()){
            if (!(behavior instanceof DeadBehavior)){
                behavior = new DeadBehavior(this, 20);
                die();
            }
        }
        else if(DungeonUtils.canSeePlayerFrom(getPosition())){
            behavior = new GenericAttackPlayerBehavior(this);
        }
        behavior = behavior.act();
    }

    @Override
    public void beAttacked(int damage) {
        DamageToken damageToken = new DamageToken(damage, position);
        Tokens.getInstance().addToken(damageToken);
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
        if(Dungeon.getActiveDungeon().isTilePassable(position)){
            this.position.set(position);
            return true;
        } else {
            return false;
        }
    }

    public void setPos(GridPoint2 pos){
        this.position.set(pos);
    }

    @Override
    public void attack(CharacterEntity characterEntity) {
        int damage = MathUtils.random(minDamage, maxDamage);
        characterEntity.beAttacked(damage);
    }

    void die(){
        PlayerCharacterEntity.getInstance().statsHandler.addExperience(getExperienceValue());
    }

    void setDamageRange(int minDamage, int maxDamage){
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    @Override
    public Dungeon getDungeon() {
        return dungeon;
    }
}
