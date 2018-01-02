package com.mygdx.game.SpawnPools;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Factory;
import com.mygdx.game.Utils.RouletteSelector;

import java.util.HashMap;
import java.util.Map;

public abstract class SpawnPool<Type> {
    RouletteSelector<Factory<Type>> wheel = new RouletteSelector<>();

    protected void addNew(Factory<Type> factory, float bias){
        wheel.add(factory, bias);
    }

    public Type getNewInstance(){
        return wheel.selectAtRandom().newInstance();
    }

    protected void reset(){
        wheel.reset();
    }
}
