package com.mygdx.game.Characters.Factories;

import com.mygdx.game.Characters.BasicNonPlayerCharacterEntity;
import com.mygdx.game.Characters.Goblin;
import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.Factory;

/**
 * Created by Tatskaari on 13/06/2015.
 */
public class GoblinFactory implements Factory<NonPlayerCharacterEntity> {
    @Override
    public Goblin newInstance() {
        return new Goblin();
    }
}
