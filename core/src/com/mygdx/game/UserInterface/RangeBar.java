package com.mygdx.game.UserInterface;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Utils.RangeValue;

public class RangeBar extends Actor {
    private NinePatch progressBar;
    private RangeValue rangeValue;
    private BitmapFont font;
    private String prefixText;

    public Color filledColor;
    public Color emptyColor;

    public RangeBar(Skin skin, RangeValue rangeValue, String prefixText) {
        progressBar = skin.get("rounded.9", NinePatch.class);
        this.rangeValue = rangeValue;
        font = new BitmapFont();
        this.prefixText = prefixText;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        filledColor.a = parentAlpha;
        emptyColor.a = parentAlpha;
        float progressRatio = getProgressRatio();
        batch.setColor(emptyColor);
        progressBar.draw(batch, getX(), getY(), getWidth() * getScaleX(), getHeight() * getScaleY());
        if(progressRatio > 0){
            batch.setColor(filledColor);
            progressBar.draw(batch, getX(), getY(), progressRatio * getWidth() * getScaleX(), getHeight() * getScaleY());
        }

        String text = prefixText + " " + (int)(rangeValue.getValue()-rangeValue.getMin()) + "/" + (int)(rangeValue.getMax() -rangeValue.getMin());
        BitmapFont.TextBounds bounds = font.getBounds(text);
        font.draw(batch, text, getX() + getWidth() / 2 - bounds.width / 2, getY() + getHeight() / 2 + bounds.height / 2);

        batch.setColor(Color.WHITE);
    }

    private float getProgressRatio(){
        float max = rangeValue.getMax() - rangeValue.getMin();
        float value = rangeValue.getValue() - rangeValue.getMin();
        return value/max;
    }

}
