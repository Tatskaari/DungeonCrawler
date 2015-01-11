package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.*;

public abstract class ResourceLoader {
    //Textures
    public static TextureRegion floor;
    public static TextureRegion wall;
    public static TextureRegion doorHorizontal;
    public static TextureRegion doorVertical;
    public static TextureRegion stairsUp;
    public static TextureRegion stairsDown;
    public static TextureRegion player;
    public static TextureRegion skeleton;
    public static TextureRegion rat;

    //Fonts
    public static BitmapFont titleFont;
    public static BitmapFont defaultFont;
    public static BitmapFont damageFont;
    public static BitmapFont expFont;

    public static TextureAtlas resTextureAtlas;
    public static TextureAtlas itemTextureAtlas;

    public static void loadResources(){
        loadTextures();
        loadFonts();
    }

    public static void loadTextures(){
        resTextureAtlas = new TextureAtlas(Gdx.files.internal("res/res.atlas"));
        itemTextureAtlas = new TextureAtlas(Gdx.files.internal("items/item-icons.atlas"));

        floor = resTextureAtlas.findRegion("floor");
        wall = resTextureAtlas.findRegion("wall");
        doorHorizontal = resTextureAtlas.findRegion("door-horizontal");
        doorVertical = resTextureAtlas.findRegion("door-vertical");
        stairsUp = resTextureAtlas.findRegion("stairs-up");
        stairsDown = resTextureAtlas.findRegion("stairs-down");
        player = resTextureAtlas.findRegion("player");
        skeleton = resTextureAtlas.findRegion("skeleton");
        rat = resTextureAtlas.findRegion("rat");
    }

    public static void loadFonts(){
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
        return floor.getRegionWidth();
    }
}
