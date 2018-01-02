package com.mygdx.game.Utils;

import com.badlogic.gdx.math.MathUtils;

import java.util.HashMap;
import java.util.Map;

public class RouletteSelector<T> {
    private Map<T, Float> wheel = new HashMap<>();
    private float totalChance = 0;


    public void add(T item, Float chance){
        wheel.put(item, chance);
        totalChance += chance;
    }

    public T selectAtRandom(){
        float roll = MathUtils.random(totalChance);
        float runningTotalChance = 0;


        for (Map.Entry<T, Float> entry : wheel.entrySet()){
            T item = entry.getKey();
            float currentBias = entry.getValue();

            if (roll >= runningTotalChance && (roll < runningTotalChance + currentBias)){
                return item;
            } else {
                runningTotalChance += currentBias;
            }
        }

        throw new RuntimeException("Failed to select an item from the roulette wheel. This can only happen if the roll was too high.");
    }

    public void reset() {
        wheel = new HashMap<>();
        totalChance = 0;
    }
}
