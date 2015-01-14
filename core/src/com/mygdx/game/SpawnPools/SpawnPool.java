package com.mygdx.game.SpawnPools;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public abstract class SpawnPool <Type> {
    private Array<ObjectAndBias> spawnTable;
    float totalBias;

    public SpawnPool(){
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

        return null;
    }

    public void addNew(Class monsterClass, float bias) {
        spawnTable.add(new ObjectAndBias(monsterClass, bias));
        totalBias += bias;
    }

}
