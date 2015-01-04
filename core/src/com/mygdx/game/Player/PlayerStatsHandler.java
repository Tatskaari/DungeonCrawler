package com.mygdx.game.Player;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Tokens.LevelUpToken;
import com.mygdx.game.Utils.RandomRangeValue;
import com.mygdx.game.Utils.RangeValue;

public class PlayerStatsHandler {
    protected final RangeValue healthRange;
    protected final RandomRangeValue damage;
    private RangeValue expRange;
    private int level = 1;

    private PlayerCharacterEntity player;

    public PlayerStatsHandler(PlayerCharacterEntity player){
        healthRange = new RangeValue(0, 50, 50);
        expRange = new RangeValue(0, getNextLevelExp(), 0);
        damage = new RandomRangeValue(0,5);

        this.player = player;
    }

    public void addExperience(int exp){
        expRange.setValue(expRange.getValue() + exp);
        if (expRange.getValue() >= expRange.getMax()){
            levelUp();
        }
    }

    private int getNextLevelExp(){
        return MathUtils.ceil(level * 8 + (0.5f * level * level));
    }

    private void levelUp(){
        expRange.setMin(getNextLevelExp());
        level++;
        expRange.setMax(getNextLevelExp());

        damage.setMin(damage.getMin()+1);
        damage.setMax(damage.getMax()+1);
        healthRange.setMin(healthRange.getMin()+3);
        healthRange.setMax(healthRange.getMax() + 3);
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
