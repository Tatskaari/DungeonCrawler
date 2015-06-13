package com.mygdx.game.Dungeon;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Characters.NonPlayerCharacterEntity;

/**
 * Created by Tatskaari on 13/06/2015.
 */
public class DungeonUtils {
    public static DungeonRoom getRandomNotStartDungeonRoom(Dungeon dungeon){
        int roomIndex = MathUtils.random(dungeon.getRoomCount() - 1);
        while (roomIndex == dungeon.startRoom.getRoomNumber()){
            roomIndex = MathUtils.random(dungeon.getRoomCount()-1);
        }
        return dungeon.getDungeonRoom(roomIndex);
    }

    public static GridPoint2 getRandomTileInRoom(DungeonRoom room){
        GridPoint2 tilePosition = new GridPoint2();

        tilePosition.y = MathUtils.random(room.getY()+1, room.getY() + room.getHeight()-2);
        tilePosition.x = MathUtils.random(room.getX()+1, room.getX()+room.getWidth()-2);

        return tilePosition;
    }

    public static GridPoint2 getRandomSpawnLocation(Dungeon dungeon){
        DungeonRoom room = getRandomNotStartDungeonRoom(dungeon);
        return getRandomTileInRoom(room);
    }
}
