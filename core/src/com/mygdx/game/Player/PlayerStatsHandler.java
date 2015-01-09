package com.mygdx.game.Player;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Tokens.ExpToken;
import com.mygdx.game.Tokens.LevelUpToken;
import com.mygdx.game.Utils.RandomRangeValue;
import com.mygdx.game.Utils.RangeValue;

public class PlayerStatsHandler {
    protected final RangeValue healthRange;
    protected final RandomRangeValue damage;
    private RangeValue expRange;
    private int level;

    private PlayerCharacterEntity player;

    public PlayerStatsHandler(PlayerCharacterEntity player){
        level = 1;
        healthRange = new RangeValue(0, 50, 50);
        expRange = new RangeValue(0, getNextLevelExp(level), 0);
        damage = new RandomRangeValue(0,5);

        this.player = player;
    }

    public void addExperience(int exp){
        expRange.setValue(expRange.getValue() + exp);
        GameHandler.tokens.addToken(new ExpToken(player.getPosition(), exp));
        if (expRange.getValue() >= expRange.getMax()){
            levelUp();
        }
    }

    private int getNextLevelExp(float level){
        return MathUtils.round(level * 8f + level * level + 0.25f * level * level * level);
    }

    private void levelUp(){
        level++;
        expRange.setMin(getNextLevelExp(level-1));
        expRange.setMax(getNextLevelExp(level));

        damage.setMin(damage.getMin()+1);
        damage.setMax(damage.getMax()+1);
        healthRange.setMax(healthRange.getMax()+3);
        healthRange.setValue(healthRange.getValue()+3);
        GameHandler.tokens.addToken(new LevelUpToken(player.getPosition(), level));
    }

    public RangeValue getHealthRange() {
        return healthRange;
    }

    public float getHealth() {
        return healthRange.getValue();
    }

    public float getMaxHealth() {
        return healthRange.getMax();
    }

    public void addToHealth(int healthDelta) {
        healthRange.setValue(healthRange.getValue() + healthDelta);
    }

    public RangeValue getExperienceRange() {
        return expRange;
    }

    public int getLevel() {
        return level;
    }
}
