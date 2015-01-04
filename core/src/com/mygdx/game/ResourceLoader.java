package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.awt.*;

public abstract class ResourceLoader {
    //Textures
    public static Texture floor;
    public static Texture wall;
    public static Texture doorHorizontal;
    public static Texture doorVertical;
    public static Texture stairUp;
    public static Texture stairDown;
    public static Texture player;
    public static Texture skeleton;
    public static Texture rat;

    //Fonts
    public static BitmapFont defaultFont;
    public static BitmapFont damageFont;
    public static BitmapFont expFont;



    public static void loadResources(){
        loadTextures();
        loadFonts();
    }

    public static void loadTextures(){
        floor = new Texture(Gdx.files.internal("res/floor.png"));
        wall = new Texture(Gdx.files.internal("res/wall.png"));
        doorHorizontal = new Texture(Gdx.files.internal("res/door-horizontal.png"));
        doorVertical = new Texture(Gdx.files.internal("res/door-vertical.png"));
        stairUp = new Texture(Gdx.files.internal("res/stairs-up.png"));
        stairDown = new Texture(Gdx.files.internal("res/stairs-down.png"));
        player = new Texture(Gdx.files.internal("res/player.png"));
        skeleton = new Texture(Gdx.files.internal("res/skeleton.png"));
        rat = new Texture(Gdx.files.internal("res/rat.png"));
    }

    public static void loadFonts(){
        defaultFont = new BitmapFont();

        damageFont = new BitmapFont();
        damageFont.setColor(1, 0, 0, 1);
        damageFont.setScale(0.4f);

        expFont = new BitmapFont();
        expFont.setColor(0, 1, 0, 1);
        expFont.setScale(0.6f);
    }

    public static void dispose() {
        floor.dispose();
        wall.dispose();
        doorVertical.dispose();
        doorHorizontal.dispose();
        player.dispose();
        skeleton.dispose();

        defaultFont.dispose();
        damageFont.dispose();
    }
}
