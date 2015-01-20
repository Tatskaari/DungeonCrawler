package com.mygdx.game.UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameHandler;

public class UserInterface {
    private final Stage stage;
    private final Table bottomTable;
    private final Table topTable;

    public static GrowlTextArea growlArea;

    // Inventory elements
    private final InventoryActor inventory;

    // Bottom table elements
    private final RangeBar healthBar;
    private final RangeBar experienceBar;
    private final Label infoLabel;

    // Top table elements
    private final TextButton inventoryOpenButton;

    public UserInterface(){
        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        skin.addRegions(new TextureAtlas(Gdx.files.internal("UI/UI.atlas")));
        skin.addRegions(new TextureAtlas(Gdx.files.internal("items/item-icons.atlas")));
        stage = new Stage();
        stage.setViewport(new ScreenViewport());

        topTable = new Table(skin);
        inventoryOpenButton = new TextButton("Inventory", skin);

        bottomTable = new Table(skin);
        growlArea = new GrowlTextArea(10);
        healthBar = new RangeBar(skin, GameHandler.player.statsHandler.getHealthRange(), "HP: ");
        experienceBar = new RangeBar(skin, GameHandler.player.statsHandler.getExperienceRange(), "EXP: ");
        infoLabel = new Label("", skin);

        inventory = new InventoryActor(skin, GameHandler.player.inventory);



        populateTopTable();
        populateBottomTable();

        stage.addActor(bottomTable);
        stage.addActor(topTable);
        stage.addActor(inventory);
    }

    private void populateTopTable(){
        topTable.setFillParent(true);
        topTable.left().top();

        inventoryOpenButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        inventory.setVisible(!inventory.isVisible());
                    }
                }
        );

        topTable.add(inventoryOpenButton);

        topTable.pack();
    }

    private void populateBottomTable(){
        bottomTable.setFillParent(true);
        bottomTable.left().bottom();

        healthBar.setHeight(20);
        healthBar.emptyColor = Color.RED;
        healthBar.filledColor = Color.GREEN;

        experienceBar.setHeight(20);
        experienceBar.emptyColor = Color.GRAY;
        experienceBar.filledColor = Color.YELLOW;

        bottomTable.add(growlArea).left();
        bottomTable.row();
        bottomTable.add(infoLabel);
        bottomTable.row();
        bottomTable.add(healthBar);
        bottomTable.row();
        bottomTable.add(experienceBar);

        bottomTable.pack();
    }

    public void draw(float delta){
        infoLabel.setText("Level: " + GameHandler.player.statsHandler.getLevel() + ", Floor: " + GameHandler.dungeon.getLevel());
        stage.act(delta);
        stage.draw();
    }

    public void resize(int width, int height){
        stage.getViewport().update(width, height, true);
        healthBar.setWidth(width);
        experienceBar.setWidth(width);
    }

    public InputProcessor getInputProcessor(){
        return stage;
    }
}
