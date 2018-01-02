package com.mygdx.game.Dungeon.Rooms;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Utils.RouletteSelector;

public class DungeonRoomFactory implements RoomFactory {

    private static RouletteSelector<RoomFactory> factoryWheel = new RouletteSelector<>();

    private static final int ROOM_MAX_SIZE = 15;
    private static final int ROOM_MIN_SIZE = 7;

    static {
        factoryWheel.add(DungeonRoomFactory::createPrisonRoom, 0.3f);
        factoryWheel.add(DungeonRoomFactory::createPlainRoom, 1.0f);
    }

    @Override
    public Room create(Dungeon dungeon) {
        return factoryWheel.selectAtRandom()
                .create(dungeon);
    }

    private static PrisonRoom createPrisonRoom(Dungeon dungeon) {
        int x = MathUtils.random(dungeon.getMapWidth() - 13);
        int y = MathUtils.random(dungeon.getMapHeight() - 11);

        return new PrisonRoom(x, y, dungeon);
    }

    private static PlainRoom createPlainRoom(Dungeon dungeon) {
        int width = MathUtils.random(ROOM_MIN_SIZE, ROOM_MAX_SIZE);
        int height = MathUtils.random(ROOM_MIN_SIZE, ROOM_MAX_SIZE);

        int x = MathUtils.random(dungeon.getMapWidth() - width);
        int y = MathUtils.random(dungeon.getMapHeight() - height);

        return new PlainRoom(x, y, width, height, dungeon);
    }
}
