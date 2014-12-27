package com.mygdx.game;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;

public class PlayerCharacter {

    GridPoint2 position;
    int tileSize;

    public PlayerCharacter(){

        tileSize = MyGdxGame.dungeon.getTileSize();

        placeCharacterIn(MyGdxGame.dungeon);
    }

    public void placeCharacterIn(Dungeon dungeon) {
        int roomIndex = MathUtils.random(dungeon.getRoomCount()-1);
        DungeonRoom room = dungeon.getDungeonRoom(roomIndex);

        position = new GridPoint2();

        position.x = MathUtils.random(room.getX()+1, room.getX()+room.getWidth()-2);
        position.y = MathUtils.random(room.getY()+1, room.getY()+room.getHeight()-2);
        updateCamera();
    }

    public void moveToPos(GridPoint2 newPosition) {
        if (MyGdxGame.dungeon.isTilePassable(newPosition)){
            position = newPosition;
        }
        updateCamera();
    }

    public void render() {
        MyGdxGame.batch.draw(ResourceLoader.player, position.x * tileSize, position.y * tileSize);
    }

    public void updateCamera(){
        MyGdxGame.camera.position.x = position.x*tileSize;
        MyGdxGame.camera.position.y = position.y*tileSize;
    }

    public GridPoint2 getPosition() {
        return position;
    }
}
