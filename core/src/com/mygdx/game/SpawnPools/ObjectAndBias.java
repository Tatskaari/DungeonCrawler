package com.mygdx.game.SpawnPools;

import com.mygdx.game.Characters.NonPlayerCharacterEntity;

public class ObjectAndBias {
    public Class objectClass;
    public float bias;

    public ObjectAndBias(Class objectClass, float bias) {
        this.objectClass = objectClass;
        this.bias = bias;
    }
}
