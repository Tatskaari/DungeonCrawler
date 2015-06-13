package com.mygdx.game.Dungeon;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.GameHandler;
import com.mygdx.game.LineOfSight;

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

    public static GridPoint2 getRandomNonVisibleTileInAnyRoom(Dungeon dungeon){
        int roomIndex = MathUtils.random(dungeon.getRoomCount()-1);
        GridPoint2 tilePos = getRandomTileInRoom(dungeon.getDungeonRoom(roomIndex));
        while(dungeon.getDungeonTile(tilePos).isVisible()){
            roomIndex = MathUtils.random(dungeon.getRoomCount()-1);
            tilePos = getRandomTileInRoom(dungeon.getDungeonRoom(roomIndex));
        }
        return tilePos;
    }

    public static boolean canSeePlayerFrom(GridPoint2 pos){
        return LineOfSight.checkLineOfSight(pos, GameHandler.player.getPosition());
    }


    public static GridPoint2 getRandomTileInAnyRoom(Dungeon dungeon) {
        int roomIndex = MathUtils.random(dungeon.getRoomCount()-1);
        return getRandomTileInRoom(dungeon.getDungeonRoom(roomIndex));
    }
}
