package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public abstract class ResourceLoader {
    public static Texture floor;
    public static Texture wall;
    public static Texture doorHorizontal;
    public static Texture doorVertical;
    public static Texture player;
    public static BitmapFont defaultFont;

    public static void loadResources(){
        floor = new Texture("res/floor.png");
        wall = new Texture("res/wall.png");
        doorHorizontal = new Texture("res/door-horizontal.png");
        doorVertical = new Texture("res/door-vertical.png");
        player = new Texture("res/player.png");
        defaultFont = new BitmapFont();
    }

    public static void dispose() {
        floor.dispose();
        wall.dispose();
        doorVertical.dispose();
        doorHorizontal.dispose();
    }
}
