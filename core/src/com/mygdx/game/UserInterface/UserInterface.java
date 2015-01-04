package com.mygdx.game.UserInterface;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameHandler;

public class UserInterface {
    private Skin skin;
    private Stage stage;
    private Table table;

    private RangeBar healthBar;
    private RangeBar experienceBar;
    private Label infoLabel;

    public UserInterface(){
        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        stage = new Stage();
        stage.setViewport(new ScreenViewport());

        table = new Table(skin);
        table.setFillParent(true);
        table.left().bottom();

        stage.addActor(table);

        healthBar = new RangeBar(skin, GameHandler.player.statsHandler.getHealthRange(), "HP: ");
        healthBar.setHeight(20);
        healthBar.emptyColor = Color.RED;
        healthBar.filledColor = Color.GREEN;

        experienceBar = new RangeBar(skin, GameHandler.player.statsHandler.getExperienceRange(), "EXP: ");
        experienceBar.setHeight(20);
        experienceBar.emptyColor = Color.GRAY;
        experienceBar.filledColor = Color.YELLOW;

        infoLabel = new Label("", skin);

        table.add(infoLabel);
        table.row();
        table.add(healthBar);
        table.row();
        table.add(experienceBar);

    }

    public void draw(float delta){
        infoLabel.setText("Level: " + GameHandler.player.statsHandler.getLevel());
        stage.act(delta);
        stage.draw();
    }

    public void resize(int width, int height){
        stage.getViewport().update(width, height, true);
        healthBar.setWidth(width);
        experienceBar.setWidth(width);
    }
}
