package com.mygdx.game.Player;

import com.apple.eawt.AppEvent;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Tokens.ExpToken;
import com.mygdx.game.Tokens.LevelUpToken;
import com.mygdx.game.Tokens.Tokens;
import com.mygdx.game.UserInterface.UserInterface;
import com.mygdx.game.Utils.ColouredText;
import com.mygdx.game.Utils.RandomRangeValue;
import com.mygdx.game.Utils.RangeValue;

public class PlayerStatsHandler {
    private final RangeValue healthRange;
    final RandomRangeValue damage;
    private final RangeValue expRange;
    private int level;

    private float health;

    public PlayerStatsHandler(){
        level = 1;
        healthRange = new RangeValue(0, 50, 50);
        expRange = new RangeValue(0, getNextLevelExp(level), 0);
        damage = new RandomRangeValue(0,5);
    }

    public void addExperience(int exp){
        expRange.setValue(expRange.getValue() + exp);
        Tokens.getInstance().addToken(new ExpToken(PlayerCharacterEntity.getInstance().getPosition(), exp));
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

        Tokens.getInstance().addToken(new LevelUpToken(PlayerCharacterEntity.getInstance().getPosition(), level));
        UserInterface.growlArea.println(new ColouredText("Level up: " + level, Color.GREEN));
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

    public void resetHealth(float health) {
        this.health = getMaxHealth();
    }
}
