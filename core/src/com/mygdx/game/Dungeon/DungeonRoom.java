package com.mygdx.game.Dungeon;

public class DungeonRoom {
    private final int roomNumber;
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public DungeonRoom(int x, int y, int width, int height, int roomNumber){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.roomNumber = roomNumber;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRoomNumber(){
        return roomNumber;
    }
}
