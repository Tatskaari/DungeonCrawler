package com.mygdx.game.Characters;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Behaviors.Behavior;
import com.mygdx.game.Behaviors.DeadBehavior;
import com.mygdx.game.Behaviors.GenericAttackPlayerBehavior;
import com.mygdx.game.Behaviors.GenericWanderBehavior;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonUtils;
import com.mygdx.game.Player.PlayerCharacterEntity;
import com.mygdx.game.Renderers.MonsterRenderer;
import com.mygdx.game.Renderers.Renderer;
import com.mygdx.game.Tokens.DamageToken;
import com.mygdx.game.Tokens.Tokens;

public abstract class BasicMonsterCharacterEntity implements MonsterCharacterEntity {
    private final GridPoint2 position;
    private int health;
    private int maxHealth;
    private Behavior behavior;
    private int attackRating;
    private final Dungeon dungeon;

    private final Renderer renderer;

    BasicMonsterCharacterEntity(Dungeon dungeon){
        this.dungeon = dungeon;
        renderer = new MonsterRenderer(this);
        this.position = DungeonUtils.getRandomSpawnLocation(dungeon);
        behavior = new GenericWanderBehavior(this);
    }

    @Override
    public void act() {
        if (!isAlive()){
            if (!(behavior instanceof DeadBehavior)){
                behavior = new DeadBehavior(this);
                die();
            }
        }
        else if(DungeonUtils.canSeePlayerFrom(getPosition(), dungeon)){
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
    public boolean isAlive() {
        return health > 0;
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

    void setMaxHealth(int maxHealth){
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
         int damage = MathUtils.round(MathUtils.randomTriangular(0, (float) attackRating, 3*(float) attackRating /4));
        characterEntity.beAttacked(damage);
    }

    void die(){
        PlayerCharacterEntity.getInstance().statsHandler.addExperience(getExperienceValue());
    }

    void setAttackRating(int attackRating){
        this.attackRating = attackRating;
    }

    @Override
    public Dungeon getDungeon() {
        return dungeon;
    }
}
