package com.mygdx.game.Utils;

import com.badlogic.gdx.graphics.Color;

public class ColouredText {
    public final String text;
    public final Color color;

    public ColouredText(String text){
        color = Color.WHITE;
        this.text = text;
    }

    public ColouredText(String text, Color color){
        this.text = text;
        this.color = color;
    }
}
