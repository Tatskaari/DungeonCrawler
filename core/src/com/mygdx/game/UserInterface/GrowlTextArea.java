package com.mygdx.game.UserInterface;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Utils.ColouredText;
import com.sun.jmx.remote.internal.ArrayQueue;

public class GrowlTextArea extends Actor {
    private static final int BUFFER_SIZE = 10;
    private final ArrayQueue<ColouredText> queue;
    private final BitmapFont font;
    private final BitmapFont.TextBounds em;

    public GrowlTextArea() {
        queue = new ArrayQueue<ColouredText>(BUFFER_SIZE);
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
        if (queue.size() == BUFFER_SIZE){
            queue.remove(0);
        }
        queue.add(text);
    }
}
