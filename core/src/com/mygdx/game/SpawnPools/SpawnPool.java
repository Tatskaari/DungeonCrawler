package com.mygdx.game.SpawnPools;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public abstract class SpawnPool <Type> {
    private final Array<ObjectAndBias> spawnTable;
    private float totalBias;

    SpawnPool(){
        spawnTable = new Array<ObjectAndBias>();
    }

    public Type getSpawn() {
        float roll = MathUtils.random(totalBias);
        float runningBiasTotal = 0;

        for(int i = 0; i < spawnTable.size; i++){
            ObjectAndBias currentSpawnable = spawnTable.get(i);
            if (roll >= runningBiasTotal && (roll < runningBiasTotal + currentSpawnable.bias)){
                try {
                    return (Type)currentSpawnable.objectClass.newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("Error spawning in pool: ", e);
                }
            }
            runningBiasTotal += currentSpawnable.bias;
        }

        throw new RuntimeException("Error calculating what to spawn.");
    }

    void addNew(Class monsterClass, float bias) {
        spawnTable.add(new ObjectAndBias(monsterClass, bias));
        totalBias += bias;
    }

}
