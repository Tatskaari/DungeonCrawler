package com.mygdx.game.SpawnPools;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Factory;

import java.util.HashMap;
import java.util.Map;

public abstract class SpawnPool<Type> {
    private float totalBias = 0;
    private HashMap<Factory<Type>, Float> spawnTable = new HashMap<Factory<Type>, Float>();

    public void addNew(Factory<Type> factory, float bias){
        totalBias+=bias;
        spawnTable.put(factory, bias);
    }

    public Type getNewInstance(){
        float roll = MathUtils.random(totalBias);
        float runningBiasTotal = 0;

        for (Map.Entry<Factory<Type>, Float> FactoryBiasMapEntry : spawnTable.entrySet()){
            Factory<Type> currentFactory = FactoryBiasMapEntry.getKey();
            float currentBias = FactoryBiasMapEntry.getValue();

            if (roll >= runningBiasTotal && (roll < runningBiasTotal + currentBias)){
                return currentFactory.newInstance();
            }
            runningBiasTotal += currentBias;
        }

        throw new RuntimeException("Error calculating what to spawn.");
    }

    public void reset(){
        spawnTable = new HashMap<Factory<Type>, Float>();
        totalBias = 0;
    }
}
