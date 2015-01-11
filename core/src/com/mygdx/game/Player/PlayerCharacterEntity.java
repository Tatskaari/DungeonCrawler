package com.mygdx.game.Player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonRoom;
import com.mygdx.game.Dungeon.DungeonTile;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Characters.CharacterEntity;
import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Renderers.PlayerRenderer;
import com.mygdx.game.Renderers.Renderer;
import com.mygdx.game.ResourceLoader;
import com.mygdx.game.Screens.MainMenuScreen;
import com.mygdx.game.Tokens.DamageToken;

public class PlayerCharacterEntity implements CharacterEntity {
    private GridPoint2 position;

    public Renderer renderer;
    public PlayerStatsHandler statsHandler;
    public Inventory inventory;

    public PlayerCharacterEntity(){
        placeCharacterIn(GameHandler.dungeon);
        renderer = new PlayerRenderer(this);
        statsHandler = new PlayerStatsHandler(this);
        inventory = new Inventory(5,5);
    }

    public void placeCharacterIn(Dungeon dungeon) {
        DungeonRoom room = dungeon.getStartRoom();

        position = new GridPoint2();

        position.x = MathUtils.random(room.getX()+1, room.getX()+room.getWidth()-2);
        position.y = MathUtils.random(room.getY()+1, room.getY()+room.getHeight()-2);
    }

    public void placeAtStairsDown(){
        DungeonTile stairsDown = GameHandler.dungeon.getStairsDownDungeonTile();
        position.set(stairsDown.getPos());
    }
    public void placeAtStairsUp(){
        DungeonTile stairsUp = GameHandler.dungeon.getStairsUpDungeonTile();
        position.set(stairsUp.getPos());
    }

    @Override
    public boolean moveTo(GridPoint2 newPosition) {
        DungeonTile tile = GameHandler.dungeon.getDungeonTile(newPosition);
        if (tile.isPassable()){
            position = newPosition;
            GameHandler.stepTurn();
            GameHandler.dungeon.getDungeonTile(position).onStep();
            return true;
        } else if(tile.hasMonster()){
            attack(tile.getMonster());
            GameHandler.stepTurn();
        }

        return false;
    }

    @Override
    public void attack(CharacterEntity characterEntity) {
        int damage = (int) statsHandler.damage.getValue();
        characterEntity.beAttacked(damage);
    }

    public GridPoint2 getPosition() {
        return new GridPoint2(position);
    }

    @Override
    public boolean isDead() {
        return statsHandler.getHealth() <= 0;
    }

    @Override
    public Renderer getRenderer() {
        return renderer;
    }

    @Override
    public void act() {
        if(statsHandler.getHealth() < statsHandler.getMaxHealth() && MathUtils.randomBoolean(0.1f)){
            statsHandler.addToHealth(1);
        }
    }

    @Override
    public void beAttacked(int damage){
        DamageToken damageToken = new DamageToken(damage, position);
        GameHandler.tokens.addToken(damageToken);
        statsHandler.addToHealth(-damage);
        if (statsHandler.getHealth() <= 0){
            MyGdxGame.myGdxGame.setScreen(new MainMenuScreen());
        }
    }

    @Override
    public TextureRegion getTexture() {
        return ResourceLoader.player;
    }
}
