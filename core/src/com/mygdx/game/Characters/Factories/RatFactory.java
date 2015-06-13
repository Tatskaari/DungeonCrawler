package com.mygdx.game.Characters.Factories;

import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.Characters.Rat;
import com.mygdx.game.Factory;

/**
 * Created by Tatskaari on 13/06/2015.
 */
public class RatFactory implements Factory<NonPlayerCharacterEntity> {
    @Override
    public Rat newInstance() {
        //TODO make this use the still to be created BasicMonsterFactory
        return new Rat();
    }
}
