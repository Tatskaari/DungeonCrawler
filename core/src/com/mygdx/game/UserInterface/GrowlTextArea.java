package com.mygdx.game.UserInterface;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.mygdx.game.Utils.ColouredText;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.Objects;

public class GrowlTextArea extends Actor {
    private int bufferSize;
    private ArrayQueue<ColouredText> queue;
    private BitmapFont font;
    private BitmapFont.TextBounds em;

    public GrowlTextArea(int bufferSize) {
        this.bufferSize = bufferSize;
        queue = new ArrayQueue<ColouredText>(bufferSize);
        font = new BitmapFont();
        em = font.getBounds("M");

        println(new ColouredText("Your head hurts. You are trapped. The only way is down.", Color.YELLOW));

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Object[] buffer = queue.toArray();
        for (int i = 0; i < buffer.length; i++) {
            ColouredText text = (ColouredText) buffer[i];
            font.setColor(text.color);
            font.draw(batch, text.text, getX()+20, getY() + (queue.size() - i)*(em.height+2) + 10);
        }
    }

    public void println(ColouredText text){
        if (queue.size() == bufferSize){
            queue.remove(0);
        }
        queue.add(text);
    }
}
