package com.mygdx.game;

/**
 * Created by Tatskaari on 13/06/2015.
 */
public interface Factory<Type> {
    public Type newInstance();
}
