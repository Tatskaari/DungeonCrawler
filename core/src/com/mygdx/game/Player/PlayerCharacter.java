package com.mygdx.game.Player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonRoom;
import com.mygdx.game.Dungeon.DungeonTile;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Monsters.Monster;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Renderers.PlayerRenderer;
import com.mygdx.game.Renderers.Renderer;
import com.mygdx.game.ResourceLoader;
import com.mygdx.game.Screens.MainMenuScreen;
import com.mygdx.game.Tokens.DamageToken;

public class PlayerCharacter implements Monster {
    private GridPoint2 position;

    private int tileSize;
    private int maxHealth;
    private int health;
    private int maxDamage;
    private int minDamage;
    public Renderer renderer;

    public PlayerCharacter(){
        tileSize = GameHandler.dungeon.getTileSize();
        placeCharacterIn(GameHandler.dungeon);
        renderer = new PlayerRenderer(this);
        maxHealth = 30;
        health = maxHealth;
        minDamage = 1;
        maxDamage = 5;
    }

    public void placeCharacterIn(Dungeon dungeon) {
        DungeonRoom room = dungeon.getStartRoom();

        position = new GridPoint2();

        position.x = MathUtils.random(room.getX()+1, room.getX()+room.getWidth()-2);
        position.y = MathUtils.random(room.getY()+1, room.getY()+room.getHeight()-2);
    }
    @Override
    public boolean moveTo(GridPoint2 newPosition) {
        DungeonTile tile = GameHandler.dungeon.getDungeonTile(newPosition);
        if (tile.isPassable()){
            position = newPosition;
            GameHandler.stepTurn();
            return true;
        } else if(tile.hasMonster()){
            attack(tile.getMonster());
            GameHandler.stepTurn();
        }

        return false;
    }

    @Override
    public void attack(Monster monster) {
        int damage = MathUtils.random(minDamage, maxDamage);
        monster.beAttacked(damage);
    }

    public GridPoint2 getPosition() {
        return new GridPoint2(position);
    }

    @Override
    public Renderer getRenderer() {
        return renderer;
    }

    public int getTileSize() {
        return tileSize;
    }

    @Override
    public void act() {
        if(health < maxHealth && MathUtils.randomBoolean(0.05f)){
            health++;
        }
    }

    @Override
    public void beAttacked(int damage){
        DamageToken damageToken = new DamageToken(damage, position);
        GameHandler.tokens.addToken(damageToken);
        health-= damage;
        if (health <= 0){
            MyGdxGame.myGdxGame.setScreen(new MainMenuScreen());
        }
    }

    @Override
    public boolean isDead() {
        return health <= 0;
    }

    @Override
    public Texture getTexture() {
        return ResourceLoader.player;
    }
    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getMaxHealth(){
        return maxHealth;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }
}
