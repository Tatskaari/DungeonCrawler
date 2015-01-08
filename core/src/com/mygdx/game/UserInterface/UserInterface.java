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
import com.mygdx.game.ResourceLoader;

public class UserInterface {
    private final Skin skin;
    private final Stage stage;
    private final Table bottomTable;
    private final Table topTable;

    // Inventory elements
    private final Window inventory;
    private final TextButton inventoryCloseButton;

    // Bottom table elements
    private final RangeBar healthBar;
    private final RangeBar experienceBar;
    private final Label infoLabel;

    // Top table elements
    private TextButton inventoryOpenButton;

    public UserInterface(){
        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        skin.addRegions(new TextureAtlas(Gdx.files.internal("UI/UI.atlas")));
        skin.addRegions(ResourceLoader.getResTextureAtlas());
        stage = new Stage();
        stage.setViewport(new ScreenViewport());

        topTable = new Table(skin);
        inventoryOpenButton = new TextButton("Inventory", skin);

        bottomTable = new Table(skin);
        healthBar = new RangeBar(skin, GameHandler.player.statsHandler.getHealthRange(), "HP: ");
        experienceBar = new RangeBar(skin, GameHandler.player.statsHandler.getExperienceRange(), "EXP: ");
        infoLabel = new Label("", skin);

        inventory = new Window("Inventory", skin);
        inventoryCloseButton = new TextButton("X", skin);


        populateTopTable();
        populateBottomTable();
        populateInventory();

        stage.addActor(bottomTable);
        stage.addActor(topTable);
        stage.addActor(inventory);

    }

    private void populateInventory(){
        inventory.setVisible(false);
        inventoryCloseButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        inventory.setVisible(false);
                    }
                }
        );
        inventory.getButtonTable().add(inventoryCloseButton).height(inventory.getPadTop());
        inventory.setPosition((Gdx.graphics.getWidth() - 500)/2, (Gdx.graphics.getHeight()-500)/2);
        inventory.defaults().fill().expand();
        inventory.row();

        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                ItemSlot slot = new ItemSlot(skin.getDrawable("rat"), skin.getDrawable("rounded.9"));
                slot.setBackgroundColor(Color.DARK_GRAY);
                slot.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.println("casdf");
                    }
                });
                inventory.add(slot);
            }
            inventory.row();
        }

        inventory.setWidth(500);
        inventory.setHeight(500);
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
