package com.mygdx.game.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class TexturePackerLauncher {
    public static void main(String...args) throws Exception{
        TexturePacker.Settings settings = new TexturePacker.Settings();

        settings.stripWhitespaceX = false;
        settings.stripWhitespaceY = false;

        TexturePacker.process(settings, "Assets Folder/items/images", "core/assets/items", "item-icons");
        TexturePacker.process(settings, "Assets Folder/UI/images", "core/assets/UI", "UI");
        TexturePacker.process(settings, "Assets Folder/res/images", "core/assets/res", "res");
    }
}
