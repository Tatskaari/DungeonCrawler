package com.mygdx.game.Dungeon.Rooms;

import com.mygdx.game.Dungeon.Dungeon;

public interface RoomFactory {
    Room create(Dungeon dungeon);
}
