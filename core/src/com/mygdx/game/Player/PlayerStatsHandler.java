package com.mygdx.game.Player;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Tokens.LevelUpToken;

public class PlayerStatsHandler {
    private int maxHealth;
    private int health;
    private int maxDamage;
    private int minDamage;
    private int exp;
    private int level;

    private PlayerCharacterEntity player;

    public PlayerStatsHandler(PlayerCharacterEntity player){
        maxHealth = 30;
        health = maxHealth;
        minDamage = 1;
        maxDamage = 5;
        level = 1;

        this.player = player;
    }

    public void addExperience(int exp){
        this.exp += exp;
        if (this.exp >= getNextLevelExp()){
            levelUp();
        }
    }

    private int getNextLevelExp(){
        return MathUtils.ceil((16f * ((float) level / 2f)));
    }

    private void levelUp(){
        level++;
        minDamage++;
        maxDamage++;
        health+=3;
        maxHealth+=3;
        GameHandler.tokens.addToken(new LevelUpToken(player.getPosition(), level));
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void addToHealth(int healthDelta) {
        health += healthDelta;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
