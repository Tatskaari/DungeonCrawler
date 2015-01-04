package com.mygdx.game.Utils;

import com.badlogic.gdx.math.MathUtils;

public class RandomRangeValue extends RangeValue{
    public RandomRangeValue(float min, float max) {
        super(min, max, 0);
    }

    @Override
    public float getValue(){
        return MathUtils.random(getMin(), getMax());
    }
}
