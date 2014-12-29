package com.mygdx.game.Player;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonRoom;
import com.mygdx.game.Dungeon.DungeonTiles.DungeonTile;
import com.mygdx.game.GameHandler;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Renderers.PlayerRenderer;
import com.mygdx.game.Renderers.Renderer;

public class PlayerCharacter {
    private GridPoint2 position;

    private int tileSize;
    private int health;
    public Renderer renderer;

    public PlayerCharacter(){
        tileSize = GameHandler.dungeon.getTileSize();
        placeCharacterIn(GameHandler.dungeon);
        renderer = new PlayerRenderer(this);
        health = 30;
    }

    public void placeCharacterIn(Dungeon dungeon) {
        int roomIndex = MathUtils.random(dungeon.getRoomCount()-1);
        DungeonRoom room = dungeon.getDungeonRoom(roomIndex);

        position = new GridPoint2();

        position.x = MathUtils.random(room.getX()+1, room.getX()+room.getWidth()-2);
        position.y = MathUtils.random(room.getY()+1, room.getY()+room.getHeight()-2);
    }

    public void moveToPos(GridPoint2 newPosition) {
        DungeonTile tile = GameHandler.dungeon.getDungeonTile(newPosition);
        if (tile.isPassable()){
            position = newPosition;
            GameHandler.stepTurn();
        } else if(tile.hasMonster()){
            tile.getMonster().beAttacked();
            GameHandler.stepTurn();
        }
    }

    public GridPoint2 getPosition() {
        return position;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void beAttacked(int damage){
        health-= damage;
    }

    public int getHealth() {
        return health;
    }
}
