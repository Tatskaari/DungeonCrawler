package com.mygdx.game.Utils;

/**
 * Created by jony1710 on 03/01/2015.
 */
public class RangeValue {
    private float value;
    private float min;
    private float max;

    public RangeValue(float min, float max, float value){
        this.min = min;
        this.max = max;
        this.value = value;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
