package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class ResourceLoader {

    //Fonts
    public static BitmapFont titleFont;
    private static BitmapFont defaultFont;
    public static BitmapFont damageFont;
    public static BitmapFont expFont;

    private static TextureAtlas resTextureAtlas;
    public static TextureAtlas itemTextureAtlas;

    public static void loadResources(){
        loadTextures();
        loadFonts();
    }

    private static void loadTextures(){
        resTextureAtlas = new TextureAtlas(Gdx.files.internal("res/res.atlas"));
        itemTextureAtlas = new TextureAtlas(Gdx.files.internal("items/item-icons.atlas"));
    }

    private static void loadFonts(){
        defaultFont = new BitmapFont();

        damageFont = new BitmapFont();
        damageFont.setColor(1, 0, 0, 1);
        damageFont.setScale(0.4f);

        expFont = new BitmapFont();
        expFont.setColor(0, 1, 0, 1);
        expFont.setScale(0.6f);

        titleFont = new BitmapFont();
    }

    public static void dispose() {
        resTextureAtlas.dispose();

        defaultFont.dispose();
        damageFont.dispose();
    }

    public static int getTileSize(){
        return resTextureAtlas.findRegion("floor").getRegionWidth();
    }

    public static TextureRegion getResTextureRegion(String name){
        TextureRegion region = resTextureAtlas.findRegion(name);
        return region;
    }
}
