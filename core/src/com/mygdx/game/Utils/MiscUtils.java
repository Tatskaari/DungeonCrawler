package com.mygdx.game.Utils;

import com.badlogic.gdx.math.MathUtils;

public class MiscUtils {
    public static int randomIntExcluding(int start, int end, int n){
        int result = n;
        while(result == n){
            result = MathUtils.random(start, end);
        }
        return result;
    }
}
