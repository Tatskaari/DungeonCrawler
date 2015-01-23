package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
    public static TextureRegion goblin;

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

        floor = resTextureAtlas.findRegion("floor");
        wall = resTextureAtlas.findRegion("wall");
        doorHorizontal = resTextureAtlas.findRegion("door-horizontal");
        doorVertical = resTextureAtlas.findRegion("door-vertical");
        stairsUp = resTextureAtlas.findRegion("stairs-up");
        stairsDown = resTextureAtlas.findRegion("stairs-down");
        player = resTextureAtlas.findRegion("player");
        skeleton = resTextureAtlas.findRegion("skeleton");
        goblin = resTextureAtlas.findRegion("goblin");
        rat = resTextureAtlas.findRegion("rat");
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
        return floor.getRegionWidth();
    }
}
