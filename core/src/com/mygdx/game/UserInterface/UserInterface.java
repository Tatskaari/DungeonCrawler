package com.mygdx.game.UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Player.PlayerCharacterEntity;

public class UserInterface {
    private final Stage stage;
    private final Table bottomTable;
    private final Table topTable;

    public static GrowlTextArea growlArea;

    // CenterScreenWindows
    private final CenterWindowManager centerWindowManager;
    private final InventoryActor inventory;
    private final DeveloperInfo devInfoScreen;

    // Bottom table elements
    private final RangeBar healthBar;
    private final RangeBar experienceBar;
    private final Label infoLabel;

    // Top table elements
    private final TextButton inventoryOpenButton;
    private final TextButton devInfoOpenButton;

    public UserInterface(){
        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        PlayerCharacterEntity player = PlayerCharacterEntity.getInstance();

        skin.addRegions(new TextureAtlas(Gdx.files.internal("UI/UI.atlas")));
        skin.addRegions(new TextureAtlas(Gdx.files.internal("items/item-icons.atlas")));
        stage = new Stage();
        stage.setViewport(new ScreenViewport());

        topTable = new Table(skin);
        inventoryOpenButton = new TextButton("Inventory", skin);
        devInfoOpenButton = new TextButton("Dev Info", skin);

        bottomTable = new Table(skin);
        growlArea = new GrowlTextArea();
        healthBar = new RangeBar(skin, player.statsHandler.getHealthRange(), "HP: ");
        experienceBar = new RangeBar(skin, player.statsHandler.getExperienceRange(), "EXP: ");
        infoLabel = new Label("", skin);

        inventory = new InventoryActor(skin, player.inventory);
        devInfoScreen = new DeveloperInfo(skin);



        populateTopTable();
        populateBottomTable();

        stage.addActor(bottomTable);
        stage.addActor(topTable);
        stage.addActor(inventory);
        stage.addActor(devInfoScreen);
        centerWindowManager = new CenterWindowManager(stage);
    }

    private void populateTopTable(){
        topTable.setFillParent(true);
        topTable.left().top();

        inventoryOpenButton.addListener(
            new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    centerWindowManager.toggleActiveWindow(inventory);
                }
            }
        );

        devInfoOpenButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        centerWindowManager.toggleActiveWindow(devInfoScreen);
                    }
                }
        );

        stage.addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.TAB){
                    centerWindowManager.toggleActiveWindow(inventory);
                }
                return false;
            }
        });

        topTable.add(inventoryOpenButton);
        //TODO make sure this isn't added when released
        topTable.add(devInfoOpenButton);

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
        infoLabel.setText("Level: " + PlayerCharacterEntity.getInstance().statsHandler.getLevel() + ", Floor: " + Dungeon.getActiveDungeon().getLevel());
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
