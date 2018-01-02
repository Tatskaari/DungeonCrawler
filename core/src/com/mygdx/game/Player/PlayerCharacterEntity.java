package com.mygdx.game.Player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonTile;
import com.mygdx.game.EventHandlers.Event;
import com.mygdx.game.EventHandlers.EventHandler;
import com.mygdx.game.EventHandlers.EventType;

import com.mygdx.game.GameHandler;
import com.mygdx.game.Characters.CharacterEntity;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Renderers.PlayerRenderer;
import com.mygdx.game.Renderers.Renderer;
import com.mygdx.game.ResourceLoader;
import com.mygdx.game.Screens.GameOverScreen;
import com.mygdx.game.Tokens.DamageToken;
import com.mygdx.game.Tokens.Tokens;
import com.mygdx.game.UserInterface.UserInterface;
import com.mygdx.game.Utils.ColouredText;

public class PlayerCharacterEntity implements CharacterEntity {
    private static final int REGEN_RATE = 5;

    private GridPoint2 position;

    public final Renderer renderer;
    public PlayerStatsHandler statsHandler;
    public final PlayerInventory inventory;

    private static final PlayerCharacterEntity player = new PlayerCharacterEntity();

    private PlayerCharacterEntity(){
        renderer = new PlayerRenderer(this);
        statsHandler = new PlayerStatsHandler();
        inventory = new PlayerInventory();
        position = new GridPoint2(0,0);
    }

    public static PlayerCharacterEntity getInstance(){
        return player;
    }

    public void placeCharacterIn(Dungeon dungeon) {
        position = dungeon.getStartRoom().getRandomFloorTile();
    }

    public void placeAtStairsDown(){
        DungeonTile stairsDown = Dungeon.getActiveDungeon().getStairsDownDungeonTile();
        position.set(stairsDown.getPos());
    }

    public void placeAtStairsUp(){
        DungeonTile stairsUp = Dungeon.getActiveDungeon().getStairsUpDungeonTile();
        position.set(stairsUp.getPos());
    }

    @Override
    public boolean moveTo(GridPoint2 newPosition) {
        DungeonTile tile = Dungeon.getActiveDungeon().getDungeonTile(newPosition);
        if (tile.isPassable()){
            position = newPosition;
            EventHandler.getInstance().triggerEvent(new Event(EventType.STEP_TURN));
            Dungeon.getActiveDungeon().getDungeonTile(position).onStep();
            return true;
        } else if(tile.hasMonster()){
            attack(tile.getMonster());
            EventHandler.getInstance().triggerEvent(new Event(EventType.STEP_TURN));
        }

        return false;
    }

    @Override
    public void attack(CharacterEntity characterEntity) {
        int maxDamage = (int) statsHandler.getDamage() + inventory.getAttackRating();
        characterEntity.beAttacked(MathUtils.round(MathUtils.randomTriangular(0, maxDamage, 3*maxDamage/4)));
    }

    public GridPoint2 getPosition() {
        return new GridPoint2(position);
    }

    @Override
    public boolean isAlive() {
        return statsHandler.getHealth() > 0;
    }

    @Override
    public Renderer getRenderer() {
        return renderer;
    }

    @Override
    public void act() {
        if(statsHandler.getHealth() < statsHandler.getMaxHealth() && GameHandler.stepCount%REGEN_RATE == 0){
            statsHandler.addToHealth(1);
        }
    }

    @Override
    public void beAttacked(int damage){
        damage = MathUtils.ceil(damage*(1- inventory.getArmourRating()));
        DamageToken damageToken = new DamageToken(damage, position);
        Tokens.getInstance().addToken(damageToken);
        statsHandler.addToHealth(-damage);

        UserInterface.growlArea.println(new ColouredText("Hit for " + damage + " damage.", Color.RED));

        if (statsHandler.getHealth() <= 0){
            MyGdxGame.myGdxGame.setScreen(new GameOverScreen());
        }
    }

    @Override
    public TextureRegion getTexture() {
        return ResourceLoader.getResTextureRegion("player");
    }

    public void respawn() {
        placeCharacterIn(Dungeon.getActiveDungeon());
        statsHandler = new PlayerStatsHandler();
        inventory.emptyInventory();
    }
}
