package com.mygdx.game.Player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Tokens.ExpToken;
import com.mygdx.game.Tokens.LevelUpToken;
import com.mygdx.game.Tokens.Tokens;
import com.mygdx.game.UserInterface.UserInterface;
import com.mygdx.game.Utils.ColouredText;
import com.mygdx.game.Utils.RangeValue;

public class PlayerStatsHandler {
    private static final int BASE_HEALTH = 30;
    private static final int BASE_DAMAGE = 6;
    private static final int XP_LEVEL_RATIO = 10;


    private final RangeValue healthRange;
    private float damage;
    private final RangeValue expRange;
    private int level;

    public PlayerStatsHandler(){
        level = 1;
        healthRange = new RangeValue(0, BASE_HEALTH, BASE_HEALTH);
        expRange = new RangeValue(0, getNextLevelExp(level), 0);
        damage = BASE_DAMAGE;
    }

    public void addExperience(int exp){
        expRange.setValue(expRange.getValue() + exp);
        Tokens.getInstance().addToken(new ExpToken(PlayerCharacterEntity.getInstance().getPosition(), exp));
        if (expRange.getValue() >= expRange.getMax()){
            levelUp();
        }
    }

    private int getNextLevelExp(float level){
        return MathUtils.round(level*(XP_LEVEL_RATIO+level));
    }

    private void levelUp(){
        level++;
        expRange.setValue(0);
        expRange.setMin(0);
        expRange.setMax(getNextLevelExp(level));

        damage += MathUtils.ceil((float)level/3f);
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

    public float getDamage() {
        return damage;
    }
}
